package database;

import utilities.TaskEvent;
import utilities.TaskDate;

import java.util.ArrayList;

public class Storage {
	protected String savingDirectory;
	private ArrayList<String> taskListBuffer;
	private FileManager fileManager;
	private int taskIDCounter;

	public Storage(String savingDirectory) {
		this.savingDirectory = savingDirectory;
		try {
			fileManager = new FileManager(savingDirectory);
		} catch (Exception e) {

		}
		taskIDCounter = fileManager.getTaskIDCounter();
		loadToBuffer();
	}

	public ArrayList<String> load() {
		try {
			return taskListBuffer;
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
		taskListBuffer.add(taskInfoWithID);
		return true;
	}

	public boolean editTask(Integer taskID, String prevTask, String currTask) {
		try {
			fileManager.replace(taskID, prevTask, currTask);
		} catch (Exception e) {
			return false;
		}
		loadToBuffer();
		return true;
	}

	public boolean delete(Integer taskID) {
		try {
			fileManager.delete(taskID);
		} catch (Exception e) {
			return false;
		}
		loadToBuffer();
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

	private void loadToBuffer() {
		try {
			taskListBuffer = fileManager.load();
		} catch (Exception e) {

		}
	}

}
