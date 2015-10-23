package database;

import utilities.*;

import java.util.ArrayList;

/**
 * Created by zhongwei-z on 23/10/15.
 */
public interface StorageAPI {

	boolean addTask(String taskName, TaskDate from, TaskDate to, Command_Priority priority) throws Exception;

	boolean editTask(int taskID, Command_Field field, String content);

	boolean deleteTaskByID(int taskID);

	boolean deleteTaskByName(String taskName);

	boolean markTaskAsDone(int taskID);

	boolean markTaskAsUndone(int taskID);

	TaskEvent searchTaskByID(int taskID);

	ArrayList<TaskEvent> loadAllTasks();

	FileState checkFileState();

	boolean changeDirectory(String dir);
}
