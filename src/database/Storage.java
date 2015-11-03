package database;

import utilities.*;

import java.util.ArrayList;

/**
 * Created by zhongwei-z on 23/10/15.
 */
public interface Storage {

	/**
	 * Add a task using the necessary info. The developer-defined toString() in TaskEvent Class
	 * is used and the result string is appended to the user tasks text file.
	 * @param taskName name of the task
	 * @param from     start date
	 * @param to       end date
	 * @param priority an enum
	 * @return true if task gets successfully appended; false otherwise
	 */
	boolean addTask(String taskName, TaskDate from, TaskDate to, Command_Priority priority);

	/**
	 * Overloading method
	 * Edit a task by Command_Field with a string provided. This usually is used to edit
	 * task name
	 * @param taskID
	 * @param field   normally Command_Field.NAME
	 * @param content
	 * @return true if the task is edited successfully; false if exception is caught
	 */
	boolean editTask(int taskID, Command_Field field, String content);

	/**
	 * Overloading method to defend for no casting for the respective param
	 * Edit a task by Command_Field.Priority.
	 * @param taskID
	 * @param field   normally Command_Field.PRIORITY
	 * @param priority normally Command_Priority
	 * @return true if the task is edited successfully; false if exception is caught
	 */
	boolean editTask(int taskID, Command_Field field, Command_Priority priority);

	/**
	 * Overloading method to defend for no casting for the respective param
	 * Edit a task by Command_Field.START_DATE or Command_Field.END_DATE
	 * @return true if the task is edited successfully; false if exception is caught
	 */
	boolean editTask(int taskID, Command_Field field, TaskDate date);

	/**
	 * Lazy delete a task in the user task text file. The availability signature
	 * of the task associated with taskID will be changed to false
	 * @return true if the task is lazy deleted; false if exception is caught
	 */
	boolean deleteTaskByID(int taskID);

	/**
	 * Change the availability signature of the task associated with taskID
	 * to true
	 * @return true if the task is lazy added; false if exception is caught
	 */
	boolean undoDeleteTaskByID(int taskID);

	/**
	 * Change the completion signature of the task associated with taskID
	 * to true
	 * @return true if the task is marked completed; false if exception is caught
	 */
	boolean markTaskAsDone(int taskID);

	/**
	 * Change the completion signature of the task associated with taskID
	 * to false
	 * @return true if the task is marked uncompleted; false if exception is caught
	 */
	boolean markTaskAsUndone(int taskID);
	
	boolean flagTask(int taskId);
	
	boolean unflagTask(int taskId);

	/**
	 * Tasks with ID 12 and with ID 2 will both be returned if taskID is 2
	 * @return an array of TaskEvent that contains the specified taskID
	 */
	ArrayList<TaskEvent> searchTaskByID(int taskID);
	
	ArrayList<TaskEvent> searchTaskByString(String content);

	/**
	 * Get the total number of tasks. Unless a cleanup is done, this count
	 * will include all tasks including deleted, and/or completed tasks
	 */
	int getTaskCount();

	/**
	 * @return all the available tasks
	 */
	ArrayList<TaskEvent> loadAllTasks();

	ArrayList<TaskEvent> loadCompletedTasks();

	/**
	 * Hard delete all the tasks that are marked deleted
	 * @return true if the hard delete is successful; false if exception is caught
	 */
	boolean cleanup();

	boolean changeDirectory(String dir);

}
