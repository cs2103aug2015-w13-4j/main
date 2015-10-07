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
		try {
			configFile = new FileManager(CONFIG_PATH);
			savingPath = configFile.getSavingPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print("savingPath: " + savingPath);
		try {
			savingFile = new FileManager(savingPath);
			taskIDCounter = savingFile.getTaskIDCounter();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		taskEventListBuf = new ArrayList<TaskEvent>();
	}

	public ArrayList<TaskEvent> load() {
		System.out.println("load is called");
		reloadBuffer();
		System.out.println("size " + taskEventListBuf);
		return taskEventListBuf;
	}

	public boolean addTask(String name, TaskDate date, int prio, String des) {
		String dateStr = date.toString();
		String prioStr = String.valueOf(prio);
		String taskInfo = "name:" + name + TOKEN
						+ "date:" + dateStr + TOKEN
						+ "priority:" + prioStr + TOKEN
						+ "description:" + des;
		try {
			savingFile.setTaskIDCounter(++taskIDCounter);
			System.out.println("taskIDCounter: " + taskIDCounter);
			savingFile.addTask(taskIDCounter, taskInfo);
		} catch (Exception e) {
			return false;
		}
		taskEventListBuf.add(new TaskEvent(taskIDCounter, name, date, prio, des));
		return true;
	}

	public boolean editTask(int taskID, String field, String newContent) {
		try {
			savingFile.editTask(taskID, field, newContent);
		} catch (Exception e) {
			return false;
		}
		return reloadBuffer();
	}

	public boolean delete(Integer taskID) {
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
			System.out.println("loaded from savingFile: " + strings);
			for (String taskStr: strings) {
				String[] fieldAndContent = taskStr.split(Storage.TOKEN);
				String[] justContent = new String[5];
				justContent[0] = fieldAndContent[0];
				for (int i = 1; i < 5; i++) {
					String content = (fieldAndContent[i].split(":"))[1];
					justContent[i] = content;
				}
				for (int i = 0; i < 5; i++) {
					System.out.println("   " + i + "   " + justContent[i]);
				}
				TaskEvent task = new TaskEvent(justContent);
				taskEventListBuf.add(task);
				//System.out.println("   str: " + str);
			}
			System.out.println("after creating taskevents: " + taskEventListBuf);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
