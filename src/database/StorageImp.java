package database;

import utilities.*;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

// @@author A0130503B


public class StorageImp implements Storage {
	private static final Logger LOGGER = Logger.
			getLogger(StorageImp.class.getName());

	protected static final String PREF_DIR = "pref.txt";
	protected static final String DEFAULT_SAVE_DIR = "tasks.txt";

	protected static final String TOK = "&&";
	protected static final String COL = "``";
	protected static final String DIV = "/";
	private static final String WHITESPACE = " ";

	private static final String AVAILABLE = "available";
	private static final String COMPLETED = "completed";
	private static final String AVAILABILITY_NO = "false";
	private static final String AVAILABILITY_YES = "true";
	private static final String AVAILABILITY_YES_SIG = AVAILABLE + COL + AVAILABILITY_YES;
	private static final String AVAILABILITY_NO_SIG = AVAILABLE + COL + AVAILABILITY_NO;
	private static final String COMPLETION_YES = "true";
	private static final String COMPLETION_YES_SIG = COMPLETED + COL + COMPLETION_YES;
	private static final String COMPLETION_NO = "false";
	private static final String DATE_COMMON_HEADER = "date";
	private static final String FLAG_STR = "FLAG";
	private static final String UNFLAG_STR = "UNFLAG";
	private static final short SEARCH_BY_ID = 0;
	private static final short SEARCH_BY_STRING = 1;
	private static final short SEARCH_BY_FLAG = 2;
	private static final int STR_START = 0;
	private static StorageImp ourInstance;
	private static String saveDir;

	private PrintWriter writer;
	private int taskCounter;

	private StorageImp() {
		try {
			saveDir = getSaveDir();
			writer = new PrintWriter(new BufferedWriter(
					new FileWriter(new File(saveDir), true)));
			taskCounter = getTaskCounter();
		} catch (IOException e) {
			LOGGER.log(Level.WARNING,
					"Failed to create StorageImp singleton.", e);
		}
	}

	private String getSaveDir() {
		try {
			return FileUtils.getFirstLine(PREF_DIR);
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "Saving directory not specified, use default.");
			return DEFAULT_SAVE_DIR;
		}
	}

	public static StorageImp getInstance() {
		if (ourInstance == null) {
			ourInstance = new StorageImp();
		}
		return ourInstance;
	}

	@Override
	public int getTaskCount() {
		return getTaskCounter();
	}

	private int getTaskCounter() {
		int counter = 0;
		try {
			counter = FileUtils.getLinesCount(saveDir);
		} catch (FileNotFoundException ffe) {
			LOGGER.log(Level.WARNING, "Cannot find the tasks file.", ffe);
		} catch (IOException ioe) {
			LOGGER.log(Level.WARNING, "Unable to count tasks.", ioe);
		}
		return counter;
	}

	@Override
	public boolean addTask(String taskName, TaskDate startDate, TaskTime startTime,
	                TaskDate endDate, TaskTime endTime, Command_Priority priority) {
		//TODO defense for taskName having special char like COL or TOK

		try {
			TaskEvent task = new TaskEvent(
					taskCounter++, taskName, startDate, startTime, endDate, endTime, priority);
			writer.write(task.toString());
			writer.println();
			writer.flush();
		} catch (NullPointerException npe) {
			return false;
		}
		return true;
	}

	@Override
	public boolean editTask(int taskID, Command_Field field, Command_Priority priority) {
		return editTask(taskID, fieldEnumToLowerCaseString(field), priority.toString());
	}

	@Override
	public boolean editTask(int taskID, Command_Field field, TaskDate date) {
		return editTask(taskID, fieldEnumToLowerCaseString(field), date.toString());
	}

	@Override
	public boolean editTask(int taskID, Command_Field field, String content) {
		return editTask(taskID, fieldEnumToLowerCaseString(field), content);
	}
	
	private String fieldEnumToLowerCaseString(Command_Field field) {
		return field.toString().toLowerCase();
	}

	private boolean editTask(int taskID, String field, String content) {
		ArrayList<String> temp = new ArrayList<String>(taskCounter);
		boolean success = false;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
			while (br.ready()) {
				String string = br.readLine();
				String idStr = string.substring(STR_START, string.indexOf(TOK));
				if (Integer.parseInt(idStr) == taskID) {
					int pos = string.indexOf(TOK + field);
					String newStr = string.substring(STR_START, pos);
					newStr += TOK + field + COL + content;
					newStr += string.substring(string.indexOf(TOK, pos + 1));
					temp.add(newStr);
					success = true;
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
		return success;
	}

	private void rewriteSaveFile(ArrayList<String> temp) throws IOException {
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(new File(saveDir), false)));
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
		switch (content.toUpperCase()) {
			case FLAG_STR:
				return searchTask(content, SEARCH_BY_FLAG);
			case UNFLAG_STR:
				return searchTask(content, SEARCH_BY_FLAG);
			default:
				return searchTask(content, SEARCH_BY_STRING);
		}
	}

	@Override
	public ArrayList<TaskEvent> searchTaskByDate(TaskDate date) {
		String dateSignature = DATE_COMMON_HEADER + COL + date;
		return searchTaskByString(dateSignature);
	}

	private ArrayList<TaskEvent> searchTask(String content, short scope) {
		ArrayList<TaskEvent> list = new ArrayList<>();
		String[] contentSplit = content.split(WHITESPACE);
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
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
						if (patternMatched(string, contentSplit)) {
							list.add(stringToTask(string));
						}
						break;
					case SEARCH_BY_FLAG:
						String[] split = (COL + content + TOK).split(WHITESPACE);
						if (patternMatched(string, split)) {
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

	private boolean patternMatched(String string, String[] target) {
		int splitSize = target.length;
		final Pattern[] PATTERNS = new Pattern[splitSize];
		for (int i = 0; i < splitSize; i++) {
			PATTERNS[i] = Pattern.compile(Pattern.quote(target[i]), Pattern.CASE_INSENSITIVE);
		}
		boolean found = true;
		for (int i = 0; i < splitSize; i++) {
			if (!PATTERNS[i].matcher(string).find()) {
				found = false;
				break;
			}
		}
		return found;
	}

	@Override
	public ArrayList<TaskEvent> loadAllTasks() {
		ArrayList<TaskEvent> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
			while (br.ready()) {
				String string = br.readLine();
				if (string.contains(AVAILABILITY_YES_SIG) &&string.contains(COMPLETION_NO) ) {
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
	public ArrayList<TaskEvent> loadCompletedTasks() {
		ArrayList<TaskEvent> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
			while (br.ready()) {
				String string = br.readLine();
				if (string.contains(COMPLETION_YES_SIG)) {
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
	public ArrayList<TaskEvent> loadDeletedTasks() {
		ArrayList<TaskEvent> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
			while (br.ready()) {
				String string = br.readLine();
				if (string.contains(AVAILABILITY_NO_SIG)) {
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
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
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
		String  name      = sigSplit[1].split(COL)[1],
				startDate = sigSplit[2].split(COL)[1],
				startTime = sigSplit[3].split(COL)[1],
				endDate   = sigSplit[4].split(COL)[1],
				endTime   = sigSplit[5].split(COL)[1],
				flag      = sigSplit[6].split(COL)[1],
				completed = sigSplit[7].split(COL)[1],
				available = sigSplit[8].split(COL)[1];
		TaskEvent task = new TaskEvent(taskID, name, new TaskDate(startDate), new TaskTime(startTime),
				new TaskDate(endDate), new TaskTime(endTime), priorityStrToEnum(flag));
		task.setCompleted(Boolean.parseBoolean(completed));
		task.setAvailable(Boolean.parseBoolean(available));
		return task;
	}

	private Command_Priority priorityStrToEnum(String prio) {
		switch (prio.toUpperCase()) {
		case FLAG_STR:
			return Command_Priority.FLAG;
		case UNFLAG_STR:
			return Command_Priority.UNFLAG;
		default:
			return Command_Priority.UNFLAG;
		}
	}

	@Override
	public boolean changeDirectory(String dir) {
		if (dir == null) { return false; }

		File file = new File(dir);
		if (FileUtils.isValidDirectory(file)) {
			dir = addDefFileNameToDir(dir);
		}

		try {
			file = new File(dir);
			if (file.createNewFile()) {
				updateSaveDirInPref(dir);
				moveSaveFile(dir);
				saveDir = dir;
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public String getDirectory() {
		return saveDir;
	}

	private String addDefFileNameToDir(String dir) {
		if (dir.endsWith(DIV)) {
			return dir + DEFAULT_SAVE_DIR;
		} else {
			return dir + DIV + DEFAULT_SAVE_DIR;
		}
	}

	private void updateSaveDirInPref(String path) throws IOException {
		try (PrintWriter pw = new PrintWriter(new FileWriter(new File(PREF_DIR), false))) {
			pw.write(path);
			pw.flush();
		}
	}

	private void moveSaveFile(String dir) throws IOException {
		FileUtils.moveFile(saveDir, dir);
	}

	@Override
	public boolean flagTask(int taskId) {
		return editTask(taskId, Command_Field.PRIORITY, Command_Priority.FLAG);
	}

	@Override
	public boolean unflagTask(int taskId) {
		return editTask(taskId, Command_Field.PRIORITY, Command_Priority.UNFLAG);
	}

}
