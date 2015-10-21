package database;

import utilities.TaskEvent;
import utilities.TaskDate;
import utilities.Validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

import static java.util.logging.Level.WARNING;

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
			logger.log(WARNING, "error at getting config info", e);
		}
		logger.log(Level.INFO, "end of getting config info, starting getting backup file");
		//System.out.print("savingPath: " + savingPath);
		try {
			savingFile = new FileManager(savingPath);
			taskIDCounter = savingFile.getTaskIDCounter();
		} catch (NumberFormatException nfe) {
			logger.log(WARNING, "task ID format incorrect, check file", nfe);
		} catch (IOException ioe) {
			logger.log(WARNING, "error at reading backup file", ioe);
		}
		taskEventListBuf = new ArrayList<TaskEvent>();
	}

	public ArrayList<TaskEvent> load() {
		reloadBuffer();
		return taskEventListBuf;
	}

	public boolean addTask(
			String name, TaskDate startDate, TaskDate endDate, String prio) {

		String dateStr = "startdate:" + startDate.toString() + TOKEN
				+ "enddate:" + endDate.toString();
		//name:n&&startDate:d1&&endDate:d2&&priority:1
		String taskInfo = "name:" + name + TOKEN
				+ dateStr + TOKEN
				+ "priority:" + TaskEvent.priorityToIntString(prio);
		try {
			savingFile.setTaskIDCounter(++taskIDCounter);
			//1$$name:n&&start:d1&&end:d2&&priority:1
			savingFile.addTask(taskIDCounter, taskInfo);
		} catch (Exception e) {
			return false;
		}
		taskEventListBuf.add(new TaskEvent(taskIDCounter, name, startDate, endDate, prio));
		/*for (TaskEvent task: taskEventListBuf) {
			System.out.println("task: " + task.toString());
		}*/
		return true;
	}

	public boolean editTask(int taskID, String field, String newContent) {
		try {
			savingFile.editTask(taskID, field, newContent);
		} catch (Exception e) {
			e.printStackTrace();
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

	public void clearSavingFile() {
		savingFile.clearFile();
	}

	private boolean reloadBuffer() {
		//1$$name:n&&start:d1&&end:d2&&priority:1
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
				System.out.println("task:"+ task.getPriority());
				taskEventListBuf.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("e"+e.getMessage());
			return false;
		}
		return true;
	}

}
