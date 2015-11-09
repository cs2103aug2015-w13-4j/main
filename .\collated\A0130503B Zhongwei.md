# A0130503B Zhongwei
###### ./src/database/FileUtils.java
``` java

/**
 * A helper class to do File I/O
 * 
 * @author Zhongwei
 *
 */
public class FileUtils {
	// ================================================================
	// METHODS
	// ================================================================
	/**
	 * Checks if a file name is valid by creating a File and using
	 * {@link File#getCanonicalPath()}
	 * 
	 * @param filename
	 *            the file name to be checked
	 * @return boolean if there is no IOException
	 */
	public static boolean isFilenameValid(String filename) {
		File file = new File(filename);
		try {
			file.getCanonicalPath();
		} catch (IOException ioe) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the file exists and is a directory
	 * 
	 * @param file
	 *            the file to be checked
	 * @return true if file exists and is a directory
	 */
	public static boolean isValidDirectory(File file) {
		return file.exists() && file.isDirectory();
	}

	/**
	 * Get the first line of a file
	 * 
	 * @param filename
	 *            name of the file
	 * @return the first line as a String
	 * @throws IOException
	 */
	public static String getFirstLine(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		String string = br.readLine();
		br.close();
		return string;
	}

	/**
	 * Move the a file from src location to dst location, and delete the src
	 * file after moving
	 * 
	 * @param src
	 *            source path
	 * @param dst
	 *            destination path
	 * @throws IOException
	 */
	public static void moveFile(String src, String dst) throws IOException {
		File srcFile = new File(src);
		BufferedReader br = new BufferedReader(new FileReader(srcFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dst)));
		while (br.ready()) {
			String line = br.readLine();
			bw.write(line);
			bw.newLine();
		}
		bw.flush();
		srcFile.delete();
		br.close();
		bw.close();
	}

	/**
	 * Count the lines in a file
	 * 
	 * @param dir
	 *            the file to be counted
	 * @return the line count number
	 * @throws FileNotFoundException
	 *             if file is not found
	 * @throws IOException
	 */
	public static int getLinesCount(String dir) throws FileNotFoundException, IOException {
		int counter = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(dir)))) {
			while (br.ready()) {
				br.readLine();
				++counter;
			}
			return counter + 1;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Append a file name after dir if it is only a directory name.
	 * 
	 * @param dir
	 *            path
	 * @return a String of the updated path name
	 */
	public static String addDefFileNameToDir(String dir) {
		if (dir.endsWith(StorageImp.DIV)) {
			return dir + StorageImp.DEFAULT_SAVE_DIR;
		} else {
			return dir + StorageImp.DIV + StorageImp.DEFAULT_SAVE_DIR;
		}
	}

	/**
	 * Clears the content of a file using RandomAccessFile to set the length to
	 * 0
	 * 
	 * @param directory
	 *            the file to be cleared
	 */
	public static void clearFileContent(String directory) {
		try (RandomAccessFile raf = new RandomAccessFile(directory, "rw")) {
			raf.setLength(0);
		} catch (IOException e) {
			return;
		}
	}
}
```
###### ./src/database/FileUtilsTest.java
``` java
/**
 * Junit testing of FileUtils
 * 
 * @author Zhongwei
 *
 */
public class FileUtilsTest {

	/**
	 * Test if a file name is correctly reported valid or invalid
	 */
	@Test
	public void testFileNameValidFileNames() throws IOException {
		assertTrue(FileUtils.isFilenameValid("valid.txt"));
		assertTrue(FileUtils.isFilenameValid("valid valid.txt"));
		assertTrue(FileUtils.isFilenameValid("va|id.t*t"));
		assertFalse(FileUtils.isFilenameValid("invalid . invalid"));
	}

	/**
	 * Test if a path name is correctly reported valid or invalid
	 */
	@Test
	public void testFileNameValidPathNames() {
		assertTrue(FileUtils.isFilenameValid("/Users/zhongwei-z/Desktop"));
		assertTrue(FileUtils.isFilenameValid("/"));
		assertTrue(FileUtils.isFilenameValid("/usr/"));
		assertFalse(FileUtils.isFilenameValid("////////"));
	}

}
```
###### ./src/database/SelectScope.java
``` java

/**
 * Enum to indicate which tasks to be selected
 * 
 * @author Zhongwei
 */
public enum SelectScope {
	DELETED, COMPLETED, FRESH
}
```
###### ./src/database/Storage.java
``` java

/**
 * A Storage interface facilitating as an API showing all the methods available
 * public
 * 
 * @author Zhongwei
 */
public interface Storage {
	// ================================================================
	// FEATURE METHODS
	// ================================================================
	/**
	 * Add a task using the necessary info. The developer-defined toString() in
	 * TaskEvent Class is used and the result string is appended to the user
	 * tasks text file.
	 * 
	 * @return true if task gets successfully appended; false otherwise
	 */
	boolean addTask(String taskName, TaskDate startDate, TaskTime startTime, TaskDate endDate, TaskTime endTime,
			Command_Priority priority);

	/**
	 * Overloading method Edit a task by Command_Field with a string provided.
	 * This usually is used to edit task name
	 * 
	 * @param taskId
	 * @param field
	 *            normally Command_Field.NAME
	 * @param content
	 * @return true if the task is edited successfully; false if exception is
	 *         caught
	 */
	boolean editTask(int taskId, Command_Field field, String content);

	/**
	 * Overloading method to defend for no casting for the respective param Edit
	 * a task by Command_Field.Priority.
	 * 
	 * @param taskId
	 * @param field
	 *            normally Command_Field.PRIORITY
	 * @param priority
	 *            normally Command_Priority
	 * @return true if the task is edited successfully; false if exception is
	 *         caught
	 */
	boolean editTask(int taskId, Command_Field field, Command_Priority priority);

	/**
	 * Overloading method to defend for no casting for the respective param Edit
	 * a task by Command_Field.START_DATE or Command_Field.END_DATE
	 * 
	 * @return true if the task is edited successfully; false if exception is
	 *         caught
	 */
	boolean editTask(int taskId, Command_Field field, TaskDate date);

	/**
	 * Lazy delete a task in the user task text file. The availability signature
	 * of the task associated with taskId will be changed to false
	 * 
	 * @return true if the task is lazy deleted; false if exception is caught
	 */
	boolean deleteTaskByID(int taskId);

	/**
	 * Change the availability signature of the task associated with taskId to
	 * true
	 * 
	 * @return true if the task is lazy added; false if exception is caught
	 */
	boolean undoDeleteTaskByID(int taskId);

	/**
	 * Change the completion signature of the task associated with taskId to
	 * true
	 * 
	 * @return true if the task is marked completed; false if exception is
	 *         caught
	 */
	boolean markTaskAsDone(int taskId);

	/**
	 * Change the completion signature of the task associated with taskId to
	 * false
	 * 
	 * @return true if the task is marked uncompleted; false if exception is
	 *         caught
	 */
	boolean markTaskAsUndone(int taskId);

	/**
	 * Change the priority of a task associated with taskId to FLAG
	 * 
	 * @param taskId
	 * @return true if the task is marked FLAG; false if exception is caught
	 */
	boolean flagTask(int taskId);

	/**
	 * Change the priority of a task associated with taskId to UNFLAG
	 * 
	 * @param taskId
	 * @return true if the task is marked UNFLAG; false if exception is caught
	 */
	boolean unflagTask(int taskId);

	/**
	 * Search through the list of tasks and find the tasks matching content.
	 * This method uses whitespace to tokenize content and uses Pattern to do a
	 * regex match, so substring can be matched and casing will also be ignored.
	 * However, phrases like "it's" can only be matched with "it's", not "its".
	 * 
	 * @param content
	 * @return an ArrayList of TaskEvent that match the content
	 */
	ArrayList<TaskEvent> searchTaskByString(String content);

	/**
	 * Get the total number of tasks. Unless a cleanup is done, this count will
	 * include all tasks including deleted, and/or completed tasks
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
	 * 
	 * @return true if the hard delete is successful; false if exception is
	 *         caught
	 */
	boolean cleanup();

	/**
	 * Change the default saving location to a user specified location. It will
	 * check the availability of the dir provided. User tasks file would be
	 * transferred to dir if it is available.
	 * 
	 * @param dir
	 * @return true if the dir is valid and file is successfully copied to dir;
	 */
	boolean changeDirectory(String dir);

	/**
	 * Get the current saving directory.
	 */
	String getDirectory();
}
```
###### ./src/database/StorageImp.java
``` java

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
```
###### ./src/database/StorageImpTest.java
``` java
/**
 * Junit testing of StorageImp
 * 
 * @author Zhongwei
 *
 */
public class StorageImpTest {

	static TaskDate d1 = new TaskDate(2015, 1, 1);
	static TaskDate d2 = new TaskDate(2015, 2, 2);
	static TaskDate d3 = new TaskDate(2014, 2, 28);
	static TaskDate d4 = new TaskDate(2017, 4, 1);
	static TaskDate d6 = new TaskDate(9999, 9, 9);
	static TaskTime t1 = new TaskTime(10, 10);
	static TaskTime t2 = new TaskTime(11, 11);
	static TaskTime t3 = new TaskTime(12, 12);
	static TaskTime t4 = new TaskTime(8, 00);
	static String n1 = "visit grandma";
	static String n2 = "visit zoo";
	static String n3 = "pay bill $10";
	static String n4 = "pay clyde $10";
	static final Command_Priority F = Command_Priority.FLAG;
	static final Command_Priority U = Command_Priority.UNFLAG;
	StorageImp s = StorageImp.getInstance();

	/**
	 * Test if the task count is correctly reported.
	 * First clear the content of the task file and then add
	 * some tasks.
	 */
	@Test
	public void testGetTaskCount() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		assertEquals(4, s.getTaskCount());
	}

	/**
	 * Test if add task can work
	 */
	@Test
	public void testAddTask() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		s.addTask("three", d1, t1, d2, t2, Command_Priority.FLAG);
		assertEquals(1, s.getTaskCount());
	}

	/**
	 * Test if edit can work
	 */
	@Test
	public void testEditTask() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		s.editTask(1, Command_Field.NAME, "new");
		s.editTask(1, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.PRIORITY, Command_Priority.FLAG.toString());
		s.editTask(4, Command_Field.START_TIME, t3.toString());
		ArrayList<TaskEvent> list = s.loadAllTasks();
		assertEquals("new", list.get(0).getTaskName());
		assertEquals(d3, list.get(0).getEndDate());
		assertEquals(d3, list.get(3).getEndDate());
		assertEquals(F, list.get(3).getPriority());
		assertEquals(t3, list.get(3).getStartTime());
	}

	/**
	 * Test if delete can work
	 */
	@Test
	public void testDeleteTaskByID() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		s.deleteTaskByID(1);
		ArrayList<TaskEvent> list = s.loadAllTasks();
		assertNotSame(1, list.get(0).getTaskID());
	}

	/**
	 * Test if task can be marked done
	 */
	@Test
	public void testMarkTaskAsDone() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		s.markTaskAsDone(1);
		ArrayList<TaskEvent> list = s.loadAllTasks();
		assertEquals(true, list.get(0).isCompleted());
	}

	/**
	 * Test if task can be marked undone
	 */
	@Test
	public void testMarkTaskAsUndone() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		s.markTaskAsUndone(1);
		ArrayList<TaskEvent> list = s.loadAllTasks();
		assertEquals(false, list.get(0).isCompleted());
	}

	/**
	 * Test if an directory can be changed to an empty string
	 * expects no
	 */
	@Test
	public void testPathTypeOfAnEmptyString() {
		assertFalse(s.changeDirectory(""));
	}

	/**
	 * Test if change directory can be done with different types of paths
	 */
	@Test
	public void testChangeDirectory() throws Exception {
		assertTrue(s.changeDirectory("/Users/zhongwei-z/Desktop/new folder"));
		assertTrue(s.changeDirectory("/Users/zhongwei-z/Desktop/task2.txt"));
		assertTrue(s.changeDirectory("."));
		assertFalse(s.changeDirectory("dgegeae.aegaa.aega.aeg/aeg/ar//rg"));
	}

	/**
	 * Helper method to add four tasks
	 */
	private void generateTasks() {
		s.addTask(n1, d1, t1, d2, t2, F);
		s.addTask(n2, d2, t2, d3, t3, U);
		s.addTask(n3, d3, t3, d4, t4, U);
		s.addTask(n4, d4, t4, d1, t1, U);
	}
}
```
###### ./src/utilities/Command_Priority.java
``` java
/**
 * enum for the priority class
 * 
 * @author Zhongwei
 *
 */
public enum Command_Priority {
	FLAG, UNFLAG, UNDEFINED
};
```
###### ./src/utilities/TaskEvent.java
``` java
/**
 * Store each tasks
 * 
 * @author Zhongwei
 *
 */
public class TaskEvent {
	// ================================================================
	// CONSTANT
	// ================================================================
	public static final int ELEMENTS_COUNT = 9;
	private static final String TOK = "&&";
	private static final String DIV = "``";

	private static String TASK_STR = "%1$s" + TOK + "name" + DIV + "%2$s" + TOK + "start_date" + DIV + "%3$s" + TOK
			+ "start_time" + DIV + "%4$s" + TOK + "end_date" + DIV + "%5$s" + TOK + "end_time" + DIV + "%6$s" + TOK
			+ "priority" + DIV + "%7$s" + TOK + "completed" + DIV + "%8$s" + TOK + "available" + DIV + "%9$s" + TOK;

	// ================================================================
	// VARIABLES
	// ================================================================
	protected int taskID;
	protected String taskName;
	protected TaskDate startDate;
	protected TaskDate endDate;
	protected TaskTime startTime;
	protected TaskTime endTime;
	protected Command_Priority flag;
	protected boolean completed; // has the task been completed, default to
									// false
	protected boolean available; // default to true; false to indicate deleted

	// ================================================================
	// CONSTRUCTOR
	// ================================================================
	public TaskEvent(int taskID, String name, TaskDate startDate, TaskTime startTime, TaskDate endDate,
			TaskTime endTime, Command_Priority priority) {
		setTaskID(taskID);
		setTaskName(name);
		setStartDate(startDate);
		setStartTime(startTime);
		setEndDate(endDate);
		setEndTime(endTime);
		setPriority(priority);
		setCompleted(false);
		setAvailable(true);
	}

	// ================================================================
	// GETTERS
	// ================================================================
	public int getTaskID() {
		return taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public TaskDate getStartDate() {
		return startDate;
	}

	public TaskDate getEndDate() {
		return endDate;
	}

	public TaskTime getEndTime() {
		return endTime;
	}

	public TaskTime getStartTime() {
		return startTime;
	}

	public Command_Priority getPriority() {
		return flag;
	}

	public boolean isCompleted() {
		return completed;
	}

	public boolean isAvailable() {
		return available;
	}

	// ================================================================
	// SETTERS
	// ================================================================
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setStartDate(TaskDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(TaskDate endDate) {
		this.endDate = endDate;
	}

	public void setEndTime(TaskTime endTime) {
		this.endTime = endTime;
	}

	public void setStartTime(TaskTime startTime) {
		this.startTime = startTime;
	}

	public void setPriority(Command_Priority flag) {
		this.flag = flag;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	// ================================================================
	// TO STRING
	// ================================================================
	@Override
	public String toString() {
		return String.format(TASK_STR, taskID, taskName, startDate.toString(), startTime.toString(), endDate.toString(),
				endTime.toString(), flag.toString(), String.valueOf(completed), String.valueOf(available));
	}
}
```
