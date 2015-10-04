package src.database;

import src.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
	protected String savingDirectory;
	private ArrayList<TaskEvent> bufferTaskList;
	private FileManager fileManager;
	private int taskIDCounter;

	public Storage(String savingDirectory) {
		savingDirectory = savingDirectory;
		fileManager = new FileManager(savingDirectory);
		taskIDCounter = fileManager.getTaskIDCounter();
		try {
			bufferTaskList = fileManager.load();
		} catch (IOException ioe) {
			;
		}
	}

	public ArrayList<TaskEvent> load() {
		return bufferTaskList;
	}

	public boolean addTask(String description, TaskDate date, String priority, boolean recurring) {
		Task task = new Task(taskIDCounter++, description, date, priority, recurring);
		String taskInfo = task.toString();
		try {
			fileManager.append(taskInfo);
		} catch (Exception e) {
			return false;
		}
		bufferTaskList.add(task);
		return true;
	}

	public boolean editTask(Integer taskID, String editField, String editContent) {
		// TODO
		return true;
	}

	public boolean delete(Integer taskID) {
		// TODO
		return true;
	}

	public boolean undo() {
		// TODO
		return true;
	}

	public boolean undo(int times) {
		//TODO
		return true;
	}

	public boolean redo() {
		// TODO
		return true;
	}

	private boolean saveTask() {
		// TODO
		return true;
	}

}

