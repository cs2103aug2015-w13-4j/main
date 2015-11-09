package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskEvent;
import utilities.TaskTime;

// @@author A0130503B Zhongwei

/**
 * This class implements {@link Storage Storage} using local File I/O.
 * @author Zhongwei
 */
public class StorageImp implements Storage {
	private static final Logger LOGGER = Logger.getLogger(StorageImp.class.getName());

	// ================================================================
	// CONSTANT STRING
	// ================================================================
	protected static final String PREF_DIR = "pref.txt";
	protected static final String DEFAULT_SAVE_DIR = "tasks.txt";

	protected static final String TOK = "&&";
	protected static final String COL = "``";
	protected static final String DIV = "/";
	private static final String WHITESPACE = " ";

	private static final String AVAILABLE = "available";
	private static final String COMPLETED = "completed";
	private static final String NO = "false";
	private static final String YES = "true";
	private static final String AVAILABILITY_YES_SIG = AVAILABLE + COL + YES;
	private static final String AVAILABILITY_NO_SIG = AVAILABLE + COL + NO;
	private static final String COMPLETION_YES_SIG = COMPLETED + COL + YES;
	private static final String FRESHNESS_SIG = COMPLETED + COL + NO + TOK + AVAILABILITY_YES_SIG;
	private static final String FLAG_STR = "FLAG";
	private static final String UNFLAG_STR = "UNFLAG";

	// ================================================================
	// VARIABLES
	// ================================================================
	private static final int STR_START = 0;
	private static StorageImp ourInstance;
	private static String saveDir;
	private PrintWriter writer;
	private int taskCounter;

	// ================================================================
	// DEFAULT CONSTRUCTOR
	// ================================================================
	/**
	 * Singleton initialization
	 */
	private StorageImp() {
		try {
			saveDir = getSaveDir();
			writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(saveDir), true)));
			taskCounter = getTaskCounter();
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Failed to create StorageImp singleton.", e);
		}
	}

	// ================================================================
	// METHODS
	// ================================================================
	/**
	 * First attempt to load from preference dir. If failed (either means the
	 * user has not {@link #changeDirectory(String) changeDirectory} or there is
	 * an IOException.
	 * 
	 * @return a string indicating the saving directory
	 */
	private String getSaveDir() {
		try {
			return FileUtils.getFirstLine(PREF_DIR);
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "Saving directory not specified, use default.");
			return DEFAULT_SAVE_DIR;
		}
	}

	/**
	 * Singleton get instance method. Returns an instance if it has been
	 * initiated, or uses {@link #StorageImp() StorageImp()} to initialise it if
	 * it has not been.
	 * 
	 * @return a singleton instance of StorageImp
	 */
	public static StorageImp getInstance() {
		if (ourInstance == null) {
			ourInstance = new StorageImp();
		}
		return ourInstance;
	}

	@Override
	public int getTaskCount() {
		return getTaskCounter() - 1;
	}

	/**
	 * Get the task count based on the line numbers in the saving file
	 * 
	 * @return number of tasks
	 */
	private int getTaskCounter() {
		int counter = 0;
		try {
			counter = FileUtils.getLinesCount(saveDir);
		} catch (FileNotFoundException ffe) {
			LOGGER.log(Level.WARNING, "Cannot find the tasks file.", ffe);
		} catch (IOException ioe) {
			LOGGER.log(Level.WARNING, "Unable to count tasks.", ioe);
		}
		return counter;
	}

	// ================================================================
	// FEATURES EXECUTION METHOD
	// ================================================================
	@Override
	public boolean addTask(String taskName, TaskDate startDate, TaskTime startTime, TaskDate endDate, TaskTime endTime,
			Command_Priority priority) {
		assert taskName != null;
		assert startDate != null;
		assert startTime != null;
		assert endDate != null;
		assert endTime != null;
		assert priority != null;

		try {
			TaskEvent task = new TaskEvent(taskCounter++, taskName, startDate, startTime, endDate, endTime, priority);
			writer.write(task.toString());
			writer.println();
			writer.flush();
		} catch (NullPointerException npe) {
			return false;
		}
		return true;
	}

	@Override
	public boolean editTask(int taskID, Command_Field field, Command_Priority priority) {
		return editTask(taskID, fieldEnumToLowerCaseString(field), priority.toString());
	}

	@Override
	public boolean editTask(int taskID, Command_Field field, TaskDate date) {
		return editTask(taskID, fieldEnumToLowerCaseString(field), date.toString());
	}

	@Override
	public boolean editTask(int taskID, Command_Field field, String content) {
		return editTask(taskID, fieldEnumToLowerCaseString(field), content);
	}

	/**
	 * Convert a {@link utilities.Command_Field} to a lower case String
	 * 
	 * @param field
	 *            a {@link utilities.Command_Field}
	 * @return a lower case String
	 */
	private String fieldEnumToLowerCaseString(Command_Field field) {
		assert field != null;
		return field.toString().toLowerCase();
	}

	/**
	 * Get each line from the task file and edit it if necessary, and rewrite to
	 * the same task file
	 * 
	 * @param taskID
	 *            a number associated with a {@link utilities.TaskEvent
	 *            TaskEvent}
	 * @param field
	 *            header for content to be edited using
	 *            {@link #fieldEnumToLowerCaseString(Command_Field)}
	 * @param content
	 *            String to replace old content under a certain field
	 * @return true if File is successfully rewritten with the new content
	 */
	private boolean editTask(int taskID, String field, String content) {
		ArrayList<String> temp = new ArrayList<String>(taskCounter);
		boolean success = false;
		if (!isTaskIdValid(taskID)) {
			return false;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
			while (br.ready()) {
				String string = br.readLine();
				String idStr = string.substring(STR_START, string.indexOf(TOK));
				if (Integer.parseInt(idStr) == taskID) {
					int pos = string.indexOf(TOK + field);
					String newStr = string.substring(STR_START, pos);
					newStr += TOK + field + COL + content;
					newStr += string.substring(string.indexOf(TOK, pos + 1));
					temp.add(newStr);
					success = true;
				} else {
					temp.add(string);
				}
			}
			rewriteSaveFile(temp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return success;
	}

	/**
	 * Rewrite task file with the content of an ArrayList of
	 * {@link utilities.TaskEvent TaskEvent}
	 * 
	 * @param temp
	 *            an ArrayList of TaskEvent
	 * @throws IOException
	 */
	private void rewriteSaveFile(ArrayList<String> temp) throws IOException {
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(new File(saveDir), false)));
		for (String s : temp) {
			w.write(s);
			w.println();
		}
		w.flush();
		w.close();
	}

	@Override
	public boolean deleteTaskByID(int taskID) {
		return editTask(taskID, AVAILABLE, NO);
	}

	@Override
	public boolean undoDeleteTaskByID(int taskID) {
		return editTask(taskID, AVAILABLE, YES);
	}

	@Override
	public boolean markTaskAsDone(int taskID) {
		return editTask(taskID, COMPLETED, YES);
	}

	@Override
	public boolean markTaskAsUndone(int taskID) {
		return editTask(taskID, COMPLETED, NO);
	}

	@Override
	public ArrayList<TaskEvent> searchTaskByString(String content) {
		ArrayList<TaskEvent> list = new ArrayList<>();
		String[] contentSplit = content.split(WHITESPACE);
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
			while (br.ready()) {
				String string = br.readLine();
				if (patternMatched(string, contentSplit)) {
					list.add(stringToTask(string));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * Check if every item in an array of String appears in string.
	 * 
	 * @param string
	 *            the string to be matched
	 * @param target
	 *            the content to be found
	 * @return true if every item of target appears in string
	 */
	private boolean patternMatched(String string, String[] target) {
		int splitSize = target.length;
		final Pattern[] PATTERNS = new Pattern[splitSize];
		for (int i = 0; i < splitSize; i++) {
			PATTERNS[i] = Pattern.compile(Pattern.quote(target[i]), Pattern.CASE_INSENSITIVE);
		}
		boolean found = true;
		for (int i = 0; i < splitSize; i++) {
			if (!PATTERNS[i].matcher(string).find()) {
				found = false;
				break;
			}
		}
		return found;
	}

	@Override
	public ArrayList<TaskEvent> loadAllTasks() {
		ArrayList<TaskEvent> list = new ArrayList<>();
		try {
			list = loadTasks(SelectScope.FRESH);
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Unable to load tasks", e);
		}
		return list;
	}

	@Override
	public ArrayList<TaskEvent> loadCompletedTasks() {
		ArrayList<TaskEvent> list = new ArrayList<>();
		try {
			list = loadTasks(SelectScope.COMPLETED);
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Unable to load completed tasks", e);
		}
		return list;
	}

	@Override
	public ArrayList<TaskEvent> loadDeletedTasks() {
		ArrayList<TaskEvent> list = new ArrayList<>();
		try {
			list = loadTasks(SelectScope.DELETED);
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Unable to load deleted tasks", e);
		}
		return list;
	}

	private ArrayList<TaskEvent> loadTasks(SelectScope scope) throws IOException {
		ArrayList<TaskEvent> list = new ArrayList<>();
		String signature = determineSignature(scope);
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
			while (br.ready()) {
				String string = br.readLine();
				if (string.contains(signature)) {
					list.add(stringToTask(string));
				}
			}
		}
		return list;
	}

	private String determineSignature(SelectScope scope) {
		switch (scope) {
		case DELETED:
			return AVAILABILITY_NO_SIG;
		case COMPLETED:
			return COMPLETION_YES_SIG;
		case FRESH:
			return FRESHNESS_SIG;
		default:
			return new String();
		}
	}

	@Override
	public boolean cleanup() {
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(saveDir)))) {
			while (br.ready()) {
				String string = br.readLine();
				if (string.contains(AVAILABILITY_YES_SIG)) {
					list.add(string);
				}
			}
			rewriteSaveFile(list);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Convert the serialised TaskEvent to a TaskEvent object.
	 * 
	 * @param string
	 *            serialised TaskEvent
	 * @return a TaskEvent object
	 */
	private TaskEvent stringToTask(String string) {
		String[] sigSplit = string.split(TOK);
		assert (sigSplit.length == TaskEvent.ELEMENTS_COUNT);
		int taskID = Integer.parseInt(sigSplit[0]);
		String name = sigSplit[1].split(COL)[1], startDate = sigSplit[2].split(COL)[1],
				startTime = sigSplit[3].split(COL)[1], endDate = sigSplit[4].split(COL)[1],
				endTime = sigSplit[5].split(COL)[1], flag = sigSplit[6].split(COL)[1],
				completed = sigSplit[7].split(COL)[1], available = sigSplit[8].split(COL)[1];
		TaskEvent task = new TaskEvent(taskID, name, new TaskDate(startDate), new TaskTime(startTime),
				new TaskDate(endDate), new TaskTime(endTime), priorityStrToEnum(flag));
		task.setCompleted(Boolean.parseBoolean(completed));
		task.setAvailable(Boolean.parseBoolean(available));
		return task;
	}

	/**
	 * Convert a string to a {@link utilities.Command_Priority Command_Priority}
	 * 
	 * @param prio
	 *            a String
	 * @return a Command_Priority
	 */
	private Command_Priority priorityStrToEnum(String prio) {
		switch (prio.toUpperCase()) {
		case FLAG_STR:
			return Command_Priority.FLAG;
		case UNFLAG_STR:
			return Command_Priority.UNFLAG;
		default:
			return Command_Priority.UNFLAG;
		}
	}

	@Override
	public boolean changeDirectory(String dir) {
		if (dir == null) {
			return false;
		}

		File file = new File(dir);
		if (FileUtils.isValidDirectory(file)) {
			dir = FileUtils.addDefFileNameToDir(dir);
		}

		try {
			file = new File(dir);
			if (file.createNewFile()) {
				updateSaveDirInPref(dir);
				moveSaveFile(dir);
				saveDir = dir;
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public String getDirectory() {
		return saveDir;
	}

	/**
	 * Remember in the pref file the user designated directory to save tasks.
	 * 
	 * @param path
	 *            the designated directory
	 * @throws IOException
	 *             see {@link java.io.IOException} in
	 *             {@link #moveSaveFile(String)}
	 */
	private void updateSaveDirInPref(String path) throws IOException {
		try (PrintWriter pw = new PrintWriter(new FileWriter(new File(PREF_DIR), false))) {
			pw.write(path);
			pw.flush();
		}
	}

	/**
	 * Moves task file to a designated location
	 * 
	 * @param dir
	 *            the designated location
	 * @throws IOException
	 *             if the directory is invalid or the user has no permission to
	 *             write to the location. Note that there is no perfect way to
	 *             check this for the user as it is dependent on the OS and the
	 *             administrative level of the user
	 */
	private void moveSaveFile(String dir) throws IOException {
		FileUtils.moveFile(saveDir, dir);
	}

	@Override
	public boolean flagTask(int taskId) {
		return editTask(taskId, Command_Field.PRIORITY, Command_Priority.FLAG);
	}

	@Override
	public boolean unflagTask(int taskId) {
		return editTask(taskId, Command_Field.PRIORITY, Command_Priority.UNFLAG);
	}

	/**
	 * Checks if task ID is valid, that is, an int larger than 0 and not larger
	 * than the total task count
	 * 
	 * @param taskId
	 *            a task ID
	 * @return true if it is valid and false otherwise
	 */
	private boolean isTaskIdValid(int taskId) {
		return taskId <= taskCounter && taskId > 0;
	}

}
