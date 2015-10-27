package database;

import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskEvent;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by zhongwei-z on 23/10/15.
 */
public class StorageImp implements Storage {
	protected static final String PREF_DIR = "pref.txt";
	protected static final String SAVE_DIR = "tasks.txt";
	protected static final String TOK = "&&";
	protected static final String COL = ":";
	private   static final String AVAILABLE = "available";
	private   static final String COMPLETED = "completed";
	private   static final String AVAILABILITY_NO = "false";
	private   static final String AVAILABILITY_YES = "true";
	private   static final String AVAILABILITY_YES_SIG = AVAILABLE + COL + AVAILABILITY_YES;
	private   static final String COMPLETION_YES = "true";
	private   static final String COMPLETION_NO = "false";
	private   static final short SEARCH_BY_ID = 0;
	private   static final short SEARCH_BY_STRING = 1;
	private   static final int STR_START = 0;
	private   static Storage ourInstance;

	static {
		ourInstance = new StorageImp();
	}

	private FileState saveState, prefState;
	private PrintWriter writer;
	private int taskCounter;
	private StorageImp() {
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(SAVE_DIR), true)));
			taskCounter = getTaskCounter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Storage getInstance() {
		return ourInstance;
	}

	@Override
	public int getTaskCount() {
		return getTaskCounter();
	}


	private int getTaskCounter() {
		int counter = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(SAVE_DIR)))) {
			while (br.ready()) {
				++counter;
			}
			return counter + 1;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return counter;
	}

	@Override
	public boolean addTask(String taskName, TaskDate from, TaskDate to, Command_Priority priority) {
		TaskEvent task = new TaskEvent(taskCounter++, taskName, from, to, priority);
		writer.write(task.toString());
		writer.println();
		writer.flush();
		return true;
	}

	@Override
	public boolean editTask(int taskID, Command_Field field, Command_Priority priority) {
		String fieldStr = field.toString();
		String prioStr = priority.toString();
		return editTask(taskID, fieldStr, prioStr);
	}

	@Override
	public boolean editTask(int taskID, Command_Field field, TaskDate date) {
		String fieldStr = field.toString();
		String dateStr = date.toString();
		return editTask(taskID, fieldStr, dateStr);
	}

	@Override
	public boolean editTask(int taskID, Command_Field field, String content) {
		String fieldStr = field.toString().toLowerCase();
		return editTask(taskID, fieldStr, content);
	}

	private boolean editTask(int taskID, String field, String content) {
		ArrayList<String> temp = new ArrayList<String>(taskCounter);
		try (BufferedReader br = new BufferedReader(new FileReader(new File(SAVE_DIR)))) {
			while (br.ready()) {
				String string = br.readLine();
				String idStr = string.substring(STR_START, string.indexOf(TOK));
				if (Integer.parseInt(idStr) == taskID) {
					int pos = string.indexOf(TOK + field);
					String newStr = string.substring(STR_START, pos);
					newStr += TOK + field + ":" + content;
					newStr += string.substring(string.indexOf(TOK, pos + 1));
					temp.add(newStr);
				} else {
					temp.add(string);
				}
			}
			rewriteSaveFile(temp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void rewriteSaveFile(ArrayList<String> temp) throws IOException {
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(new File(SAVE_DIR), false)));
		for (String s : temp) {
			w.write(s);
			w.println();
		}
		w.flush();
		w.close();
	}

	@Override
	public boolean deleteTaskByID(int taskID) {
		return editTask(taskID, AVAILABLE, AVAILABILITY_NO);
	}

	@Override
	public boolean undoDeleteTaskByID(int taskID) {
		return editTask(taskID, AVAILABLE, AVAILABILITY_YES);
	}

	@Override
	public boolean markTaskAsDone(int taskID) {
		return editTask(taskID, COMPLETED, COMPLETION_YES);
	}

	@Override
	public boolean markTaskAsUndone(int taskID) {
		return editTask(taskID, COMPLETED, COMPLETION_NO);
	}

	@Override
	public ArrayList<TaskEvent> searchTaskByID(int taskID) {
		return searchTask(String.valueOf(taskID), SEARCH_BY_ID);
	}

	@Override
	public ArrayList<TaskEvent> searchTaskByString(String content) {
		return searchTask(content, SEARCH_BY_STRING);
	}

	private ArrayList<TaskEvent> searchTask(String content, short scope) {
		ArrayList<TaskEvent> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(SAVE_DIR)))) {
			while (br.ready()) {
				String string = br.readLine();
				String idStr = string.substring(STR_START, string.indexOf(TOK));
				switch (scope) {
					case SEARCH_BY_ID:
						if (Integer.parseInt(idStr) == Integer.parseInt(content)) {
							list.add(stringToTask(string));
						}
						break;
					case SEARCH_BY_STRING:
						if (string.contains(content)) {
							list.add(stringToTask(string));
						}
						break;
					default:
						break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<TaskEvent> loadAllTasks() {
		ArrayList<TaskEvent> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(SAVE_DIR)))) {
			while (br.ready()) {
				String string = br.readLine();
				if (string.contains(AVAILABILITY_YES_SIG)) {
					list.add(stringToTask(string));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean cleanup() {
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(SAVE_DIR)))) {
			while (br.ready()) {
				String string = br.readLine();
				if (string.contains(AVAILABILITY_YES_SIG)) {
					list.add(string);
				}
			}
			rewriteSaveFile(list);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private TaskEvent stringToTask(String string) {
		String[] sigSplit = string.split(TOK);
		assert(sigSplit.length == TaskEvent.ELEMENTS_COUNT);
		int taskID = Integer.parseInt(sigSplit[0]);
		String  name = sigSplit[1].split(COL)[1],
				from = sigSplit[2].split(COL)[1],
				to   = sigSplit[3].split(COL)[1],
				prio = sigSplit[4].split(COL)[1],
				comp = sigSplit[5].split(COL)[1],
				avlb = sigSplit[6].split(COL)[1];
		TaskEvent task = new TaskEvent(taskID, name, new TaskDate(from), new TaskDate(to), priorityStrToEnum(prio));
		task.setCompleted(Boolean.parseBoolean(comp));
		task.setAvailable(Boolean.parseBoolean(avlb));
		return task;
	}

	private Command_Priority priorityStrToEnum(String prio) {
		switch (prio.toUpperCase()) {
			case "HIGH":
				return Command_Priority.HIGH;
			case "MEDIUM":
				return Command_Priority.MEDIUM;
			case "LOW":
				return Command_Priority.LOW;
			default:
				return Command_Priority.MEDIUM;
		}
	}

	@Override
	public FileState checkSaveFileState() {
		return saveState;
	}

	@Override
	public FileState checkPrefFileState() {
		return prefState;
	}

	@Override
	public boolean changeDirectory(String dir) {
		return false;
	}

}
