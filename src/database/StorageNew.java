package database;

import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskEvent;

import java.util.ArrayList;

/**
 * Created by zhongwei-z on 23/10/15.
 */
public class StorageNew implements StorageAPI {
	private static StorageAPI ourInstance = new StorageNew();

	public static StorageAPI getInstance() { return ourInstance; }

	private FileState saveState, prefState;

	private StorageNew() {
		saveState = checkFileState();
		switch (saveState) {
			case AVAILABLE:
				break;
			case MISSING:
				break;
			case CORRUPTED:
				break;
			default:
				break;
		}
	}

	@Override
	public boolean addTask(String taskName, TaskDate from, TaskDate to, Command_Priority priority) {
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
	public FileState checkFileState() {
		return FileState.MISSING;
	}

	@Override
	public boolean changeDirectory(String dir) {
		return false;
	}
}
