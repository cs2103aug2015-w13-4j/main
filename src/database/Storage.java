package database;

import utilities.TaskEvent;
import utilities.TaskDate;

import java.util.ArrayList;

public class Storage {
	protected String savingDirectory;
	private ArrayList<String> bufferTaskList;
	private FileManager fileManager;
	private int taskIDCounter;

	public Storage(String savingDirectory) {
		this.savingDirectory = savingDirectory;
		try {
			fileManager = new FileManager(savingDirectory);
		} catch (Exception e) {

		}
		taskIDCounter = fileManager.getTaskIDCounter();
		try {
			bufferTaskList = fileManager.load();
		} catch (Exception e) {

		}
	}

	public ArrayList<String> load() {
		try {
			return bufferTaskList;
		} catch (Exception e) {

		}
		return null;
	}

	public boolean addTask(String taskInfo) {
		String taskInfoWithID = "";
		try {
			int id = taskIDCounter++;
			taskInfoWithID = "" + id + taskInfo;
			fileManager.append(taskInfoWithID);
		} catch (Exception e) {
			return false;
		}
		bufferTaskList.add(taskInfoWithID);
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

}
