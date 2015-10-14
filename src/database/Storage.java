package database;

import utilities.TaskEvent;
import utilities.TaskDate;
import utilities.Validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class Storage {
	protected static final String CONFIG_PATH = "config.txt";
	protected static final String TOKEN = "&&";

	private FileManager configFile;
	private FileManager savingFile;
	private String savingPath; // get from config file
	private int taskIDCounter;
	private ArrayList<TaskEvent> taskEventListBuf;

	private static Logger logger;

	public Storage() {
		logger = Logger.getLogger("Storage");
		logger.log(Level.INFO, "starting to create a Storage class");
		logger.log(Level.INFO, "getting config info");
		try {
			configFile = new FileManager(CONFIG_PATH);
			savingPath = configFile.getSavingPath();
		} catch (IOException e) {
			logger.log(Level.WARNING, "error at getting config info", e);
		}
		logger.log(Level.INFO, "end of getting config info, starting getting backup file");
		//System.out.print("savingPath: " + savingPath);
		try {
			savingFile = new FileManager(savingPath);
			taskIDCounter = savingFile.getTaskIDCounter();
		} catch (NumberFormatException nfe) {
			logger.log(Level.WARNING, "task ID format incorrect, check file", nfe);
		} catch (IOException e) {
			logger.log(Level.WARNING, "error at reading backup file", e);
		}
		taskEventListBuf = new ArrayList<TaskEvent>();
	}

	public ArrayList<TaskEvent> load() {
		reloadBuffer();
		return taskEventListBuf;
	}

	public boolean addTask(String name, TaskDate date, int prio, String des) {
		assert Validation.isValidString(name);
		assert Validation.isValidInteger(prio);
		assert Validation.isValidInteger(des);

		String dateStr = date.toString();
		String prioStr = String.valueOf(prio);
		String taskInfo = "name:" + name + TOKEN
						+ "date:" + dateStr + TOKEN
						+ "priority:" + prioStr + TOKEN
						+ "description:" + des;
		try {
			savingFile.setTaskIDCounter(++taskIDCounter);
			savingFile.addTask(taskIDCounter, taskInfo);
		} catch (Exception e) {
			return false;
		}
		taskEventListBuf.add(new TaskEvent(taskIDCounter, name, date, prio, des));
		for (TaskEvent task: taskEventListBuf) {
			System.out.println("task: " + task.toString());
		}
		return true;
	}

	public boolean editTask(int taskID, String field, String newContent) {
		assert Validation.isValidString(taskID);
		assert Validation.isValidTaskField(field);
		assert Validation.isValidString(newContent);
		try {
			savingFile.editTask(taskID, field, newContent);
		} catch (Exception e) {
			return false;
		}
		return reloadBuffer();
	}

	public boolean delete(Integer taskID) {
		assert Validation.isValidInteger(taskID);
		try {
			savingFile.deleteTask(taskID);
		} catch (Exception e) {
			return false;
		}
		return reloadBuffer();
	}

	public void setSavingDirectory(String dir) {
		configFile.setSavingDirectory(dir);
	}

	private boolean reloadBuffer() {
		try {
			taskEventListBuf = new ArrayList<TaskEvent>();
			ArrayList<String> strings = savingFile.loadTasks();
			for (String taskStr: strings) {
				String[] fieldAndContent = taskStr.split(Storage.TOKEN);
				String[] justContent = new String[5];
				justContent[0] = fieldAndContent[0];
				for (int i = 1; i < 5; i++) {
					String content = (fieldAndContent[i].split(":"))[1];
					justContent[i] = content;
				}
				TaskEvent task = new TaskEvent(justContent);
				taskEventListBuf.add(task);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
