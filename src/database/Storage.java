package database;

import utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
	protected String savingDirectory;
	private ArrayList<TaskEvent> bufferTaskList;

	public Storage() {
		// savingDirectory = (get dir from utitlites)
		// TODO
	}

	public Storage(String directory) {
		// replace savingDirectory
		// updateDirectory(directory);
		// TODO
	}

	public ArrayList<TaskEvent> load() {
		// TODO
		return null;
	}

	public boolean addTask(String name) {
		// TODO
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

