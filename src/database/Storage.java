package database;

import utilities.TaskEvent;
import utilities.TaskDate;

import java.io.IOException;
import java.util.ArrayList;

public class Storage {
	protected static final String CONFIG_PATH = "config.txt";
	protected static final String TOKEN = "&&";

	private FileManager configFile;
	private FileManager savingFile;
	private String savingPath; // get from config file
	private int taskIDCounter;

	private ArrayList<TaskEvent> taskEventListBuf;

	public Storage() {
		configFile = new FileManager(CONFIG_PATH);
		try {
			savingPath = configFile.getSavingPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print("savingPath: " + savingPath);
		savingFile = new FileManager(savingPath);
		try {
			taskIDCounter = savingFile.getTaskIDCounter();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		taskEventListBuf = new ArrayList<TaskEvent>();
	}

	public ArrayList<TaskEvent> load() {
		loadToBuffer();
		return taskEventListBuf;
	}

	public boolean addTask(String name, TaskDate date, int prio, String des) {
		String dateStr = date.toString();
		String prioStr = String.valueOf(prio);
		String taskInfo = "name:" + name + TOKEN
						+ "date:" + dateStr + TOKEN
						+ "priority:" + prioStr + TOKEN
						+ "description:" + des;
		int taskID = taskIDCounter++;
		try {
			savingFile.addTask(taskID, taskInfo);
		} catch (Exception e) {
			return false;
		}
		taskEventListBuf.add(new TaskEvent(taskID, name, date, prio, des));
		return true;
	}

	public boolean editTask(int taskID, String field, String newContent) {
		try {
			savingFile.editTask(taskID, field, newContent);
		} catch (Exception e) {
			return false;
		}
		return loadToBuffer();
	}

	public boolean delete(Integer taskID) {
		try {
			savingFile.deleteTask(taskID);
		} catch (Exception e) {
			return false;
		}
		return loadToBuffer();
	}

	public void setSavingDirectory(String dir) {
		configFile.setSavingDirectory(dir);
	}

	private boolean loadToBuffer() {
		try {
			ArrayList<String> strings = savingFile.loadTasks();
			for (String str: strings) {
				TaskEvent task = new TaskEvent(str.split(TOKEN));
				taskEventListBuf.add(task);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
