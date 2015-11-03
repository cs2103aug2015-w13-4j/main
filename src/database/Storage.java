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
	 * @return true if task gets successfully appended; false otherwise
	 */
	boolean addTask(String taskName, TaskDate startDate, TaskTime startTime,
	                TaskDate endDate, TaskTime endTime, Command_Priority priority);

	/**
	 * Overloading method
	 * Edit a task by Command_Field with a string provided. This usually is used to edit
	 * task name
	 * @param taskId
	 * @param field   normally Command_Field.NAME
	 * @param content
	 * @return true if the task is edited successfully; false if exception is caught
	 */
	boolean editTask(int taskId, Command_Field field, String content);

	/**
	 * Overloading method to defend for no casting for the respective param
	 * Edit a task by Command_Field.Priority.
	 * @param taskId
	 * @param field   normally Command_Field.PRIORITY
	 * @param priority normally Command_Priority
	 * @return true if the task is edited successfully; false if exception is caught
	 */
	boolean editTask(int taskId, Command_Field field, Command_Priority priority);

	/**
	 * Overloading method to defend for no casting for the respective param
	 * Edit a task by Command_Field.START_DATE or Command_Field.END_DATE
	 * @return true if the task is edited successfully; false if exception is caught
	 */
	boolean editTask(int taskId, Command_Field field, TaskDate date);

	/**
	 * Lazy delete a task in the user task text file. The availability signature
	 * of the task associated with taskId will be changed to false
	 * @return true if the task is lazy deleted; false if exception is caught
	 */
	boolean deleteTaskByID(int taskId);

	/**
	 * Change the availability signature of the task associated with taskId
	 * to true
	 * @return true if the task is lazy added; false if exception is caught
	 */
	boolean undoDeleteTaskByID(int taskId);

	/**
	 * Change the completion signature of the task associated with taskId
	 * to true
	 * @return true if the task is marked completed; false if exception is caught
	 */
	boolean markTaskAsDone(int taskId);

	/**
	 * Change the completion signature of the task associated with taskId
	 * to false
	 * @return true if the task is marked uncompleted; false if exception is caught
	 */
	boolean markTaskAsUndone(int taskId);

	/**
	 * Change the priority of a task associated with taskId to FLAG
	 * @param taskId
	 * @return true if the task is marked FLAG; false if exception is caught
	 */
	boolean flagTask(int taskId);

	/**
	 * Change the priority of a task associated with taskId to UNFLAG
	 * @param taskId
	 * @return true if the task is marked UNFLAG; false if exception is caught
	 */
	boolean unflagTask(int taskId);

	/**
	 * Tasks with ID 12 and with ID 2 will both be returned if taskId is 2
	 * @return an array of TaskEvent that contains the specified taskId
	 */
	ArrayList<TaskEvent> searchTaskByID(int taskId);

	/**
	 * Search through the list of tasks and find the tasks matching content.
	 * This method uses whitespace to tokenize content and uses Pattern to
	 * do a regex match, so substring can be matched and casing will also be ignored.
	 * However, phrases like "it's" can only be matched with "it's", not "its".
	 * @param content
	 * @return an ArrayList of TaskEvent that match the content
	 */
	ArrayList<TaskEvent> searchTaskByString(String content);

	/**
	 * Search for tasks with the specified date
	 * @param date
	 * @return an ArrayList of TaskEvent having the date as their attributes
	 */
	ArrayList<TaskEvent> searchTaskByDate(TaskDate date);

	/**
	 * Get the total number of tasks. Unless a cleanup is done, this count
	 * will include all tasks including deleted, and/or completed tasks
	 */
	int getTaskCount();

	/**
	 * @return all the available tasks, excluding completed or deleted tasks
	 */
	ArrayList<TaskEvent> loadAllTasks();

	/**
	 * @return only completed tasks
	 */
	ArrayList<TaskEvent> loadCompletedTasks();

	/**
	 * @return only deleted tasks
	 */
	ArrayList<TaskEvent> loadDeletedTasks();

	/**
	 * Hard delete all the tasks that are marked deleted
	 * @return true if the hard delete is successful; false if exception is caught
	 */
	boolean cleanup();

	/**
	 * Change the default saving location to a user specified location. It will check
	 * the availability of the dir provided. User tasks file would be transferred to
	 * dir if it is available.
	 * @param dir
	 * @return true if the dir is valid and file is successfully copied to dir;
	 */
	boolean changeDirectory(String dir);

	/**
	 * Get the current saving directory.
	 */
	String getDirectory();

}
