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
	protected static final String PREF_DIR = "pref.json";
	protected static final String SAVE_DIR = "user_tasks.json";
	private static Storage ourInstance;

	static {
		ourInstance = new StorageImp();
	}

	public static Storage getInstance() { return ourInstance; }

	private FileState saveState, prefState;
	private PrintWriter writer;
	private int taskCounter;
	private ArrayList<TaskEvent> taskBuffer;

	private StorageImp() {
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(SAVE_DIR), true)));
			taskCounter = getTaskCounter();
			taskBuffer = new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getTaskCount() {
		return getTaskCounter();
	}


	private int getTaskCounter() {
		int counter = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(SAVE_DIR)));
			while (br.ready()) {
				br.readLine();
				++counter;
			}
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
	public boolean editTask(int taskID, Command_Field field, String content) {
		return true;
	}

	@Override
	public boolean deleteTaskByID(int taskID) {
		return false;
	}

	@Override
	public boolean deleteTaskByName(String taskName) {
		return false;
	}

	@Override
	public boolean markTaskAsDone(int taskID) {
		return false;
	}

	@Override
	public boolean markTaskAsUndone(int taskID) {
		return false;
	}

	@Override
	public TaskEvent searchTaskByID(int taskID) {
		return null;
	}

	@Override
	public ArrayList<TaskEvent> loadAllTasks() {
		return null;
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
