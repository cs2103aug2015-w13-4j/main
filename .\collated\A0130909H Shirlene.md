# A0130909H Shirlene
###### ./src/logic/Display.java
``` java
/**
 * This class is to provide UI with correct output
 * 
 * @author Shirlene
 *
 */
public class Display {

	// ================================================================
	// FEEDBACK MESSAGE
	// ================================================================
	private static final String MESSAGE_ADD = "%s has been added successfully";
	private static final String MESSAGE_EDIT = "%s has been edited successfully";
	private static final String MESSAGE_ERROR = "%s could not be performed";
	private static final String MESSAGE_DELETE = "%s has been deleted successfully";
	private static final String MESSAGE_DIRECTORY = "file has been relocated to %s";
	private static final String MESSAGE_FINISHED = "%s has been completed!";
	private static final String MESSAGE_UNFINISH = "%s has been uncompleted";
	private static final String MESSAGE_SEARCH = "%d tasks has been found with your search";
	private static final String MESSAGE_NO_SEARCH_RESULT = "no task has been found with your search";
	private static final String MESSAGE_UNDO = "previous task has been undone";
	private static final String MESSAGE_REDO = "previous task has been redone";
	private static final String MESSAGE_FLAG = "%s has been flagged";
	private static final String MESSAGE_UNFLAG = "%s has been unflagged";
	private static final String MESSAGE_VIEW_COMPLETED = "you have %d completed tasks";
	private static final String MESSAGE_NO_COMPLETED = "you do not have any completed tasks";
	private static final String MESSAGE_HELP = "help activated";

	// ================================================================
	// ATTRIBUTES
	// ================================================================
	private ArrayList<TaskEvent> view;
	private ArrayList<TaskEvent> search;

	// ================================================================
	// METHOD
	// ================================================================
	public Display() {
		view = new ArrayList<TaskEvent>();
		search = new ArrayList<TaskEvent>();
	}

	/**
	 * update the view on the screen of tasks
	 * 
	 * @return ArrayList of task to show user
	 */
	public ArrayList<TaskEvent> taskView() {
		Operation op = Launch.getOperation();
		view = op.fullView();
		return view;
	}

	/**
	 * Provides the pop up view content
	 * 
	 * @return search or view completed result
	 */
	public ArrayList<TaskEvent> resultView() {
		Operation op = Launch.getOperation();
		search = op.getResultView();
		return search;
	}

	/**
	 * Provide user with a feedback on the current operation
	 * 
	 * @param op
	 *            command user specify
	 * @param content
	 *            user input
	 * @return feedback to user
	 */
	public String operation(Command_Type op, String content) {
		Operation operation = Launch.getOperation();
		TaskDisplayController taskDisplay = TaskDisplayController.getInstance();
		switch (op) {
		case ADD_TASK:
			return String.format(MESSAGE_ADD, content);
		case EDIT_TASK:
			return String.format(MESSAGE_EDIT, content);
		case DELETE_TASK:
			return String.format(MESSAGE_DELETE, content);
		case DIRECTORY:
			return String.format(MESSAGE_DIRECTORY, content);
		case FINISH_TASK:
			return String.format(MESSAGE_FINISHED, content);
		case UNFINISH_TASK:
			return String.format(MESSAGE_UNFINISH, content);
		case SEARCH_TASK:
			view = operation.getResultView();
			if (view.size() == 0) {
				return MESSAGE_NO_SEARCH_RESULT;
			} else {
				return String.format(MESSAGE_SEARCH, view.size());
			}
		case UNDO:
			return MESSAGE_UNDO;
		case REDO:
			return MESSAGE_REDO;
		case FLAG_TASK:
			return String.format(MESSAGE_FLAG, content);
		case UNFLAG_TASK:
			return String.format(MESSAGE_UNFLAG, content);
		case VIEW_COMPLETED:
			view = operation.getResultView();
			if (view.size() == 0) {
				return MESSAGE_NO_COMPLETED;
			}
			return String.format(MESSAGE_VIEW_COMPLETED, view.size());
		case HELP:
			taskDisplay.triggerHelpView();
			return MESSAGE_HELP;
		default:
			return String.format(MESSAGE_ERROR, content);
		}
	}

	/**
	 * Provide warning when input is not valid
	 * 
	 * @param user
	 *            input content
	 * @return error message
	 */
	public String error(String input) {
		return String.format(MESSAGE_ERROR, input);
	}
}
```
###### ./src/logic/Launch.java
``` java
public class Launch {
	private static StorageImp storage;
	private static Display display;
	private static Operation op;
	private static ArrayList<TaskEvent> tasks;
	private static Launch launch;
	private static Undo undo;
	private static Redo redo;

	// ================================================================
	// INITIALIZATION
	// ================================================================
	private Launch() {
	}

	private static void createObjects() {
		display = new Display();
		op = new Operation();
		launch = new Launch();
		storage = StorageImp.getInstance();
		tasks = storage.loadAllTasks();
		undo = new Undo();
		redo = new Redo();
	}

	// ================================================================
	// GETTERS
	// ================================================================
	public static StorageImp getStorage() {
		return storage;
	}

	public static Operation getOperation() {
		return op;
	}

	public static Display getDisplay() {
		return display;
	}

	public static Undo getUndo() {
		return undo;
	}

	public static Launch getInstance() {

		if (launch == null) {
			createObjects();
		}
		return launch;
	}

	public static Redo getRedo() {
		return redo;
	}

	// ================================================================
	// METHODS
	// ================================================================

	/**
	 * Provide UI with all the active tasks
	 * 
	 * @return ArrayList<TaskEvent> of Tasks
	 */
	public ArrayList<TaskEvent> updateView() {
		tasks = display.taskView();
		return tasks;
	}

	/**
	 * Provide UI with a list of tasks matching user's request
	 * 
	 * @return ArrayList<TaskEvent> containing tasks that match request
	 */
	public ArrayList<TaskEvent> resultView() {
		return display.resultView();
	}

	/**
	 * Main method which UI calls to perform user input
	 * 
	 * @param input
	 *            user's command
	 * @return String which acts as a form of feedback
	 */
	public String command(String input) {
		return op.processOperation(input);
	}
}
```
###### ./src/logic/Operation.java
``` java
/**
 * This method is to coordinate the user input with parser component and
 * database
 * 
 * @author Shirlene
 *
 */
public class Operation {

	// ================================================================
	// ATTRIBUTES
	// ================================================================
	private static Logger logger;
	private Stack<CommandElements> undoList;
	private Stack<CommandElements> redoList;
	ArrayList<TaskEvent> resultView;
	ArrayList<TaskEvent> allView;
	ArrayList<Integer> idList;
	boolean flagView;
	int size;
	boolean searchFlag;
	String search;

	// ================================================================
	// CONSTANT STRING
	// ================================================================
	private static final String DEFAULT_RETURN = "not found";

	// ================================================================
	// DEFAULT CONSTRUTOR
	// ================================================================
	public Operation() {
		undoList = new Stack<CommandElements>();
		logger = Logger.getLogger("Operation");
		resultView = new ArrayList<TaskEvent>();
		redoList = new Stack<CommandElements>();
		idList = new ArrayList<Integer>();
		allView = new ArrayList<TaskEvent>();
		flagView = false;
		search = new String();
		searchFlag = false;
	}

	// ================================================================
	// UI DISPLAY
	// ================================================================
	/**
	 * result view matching user request
	 * 
	 * @return ArrayList<TaskEvent> with tasks matching user request
	 */
	public ArrayList<TaskEvent> getResultView() {
		ArrayList<TaskEvent> list = resultView;
		StorageImp action = Launch.getStorage();
		if (searchFlag) {
			list = action.searchTaskByString(search);
		} else {
			list = action.loadCompletedTasks();
		}
		return list;
	}

	/**
	 * getting the full view of all tasks
	 * 
	 * @return ArrayList<TaskEvent> with all tasks
	 */
	public ArrayList<TaskEvent> fullView() {
		StorageImp storage = Launch.getStorage();
		allView = sortArray(storage.loadAllTasks());
		return allView;
	}

	/**
	 * sort the array based on the types, flag, others, float
	 * 
	 * @param list
	 *            generated from the file
	 * @return sorted ArrayList<TaskEvent>
	 */
	private ArrayList<TaskEvent> sortArray(ArrayList<TaskEvent> list) {
		ArrayList<TaskEvent> sorted = new ArrayList<TaskEvent>();
		ArrayList<TaskEvent> flag = new ArrayList<TaskEvent>();
		ArrayList<TaskEvent> others = new ArrayList<TaskEvent>();
		ArrayList<TaskEvent> floating = new ArrayList<TaskEvent>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPriority().toString().equals("FLAG")) {
				flag.add(list.get(i));
			} else if (list.get(i).getEndDate().getDay() == 0 && list.get(i).getStartDate().getDay() == 0) {
				floating.add(list.get(i));
			} else {
				others.add(list.get(i));
			}
		}
		sorted.addAll(flag);
		sorted.addAll(others);
		sorted.addAll(floating);
		return sorted;
	}

	// ================================================================
	// METHODS
	// ================================================================
	/**
	 * Main method to control the flow of input
	 * 
	 * @param input
	 *            user specified input
	 * @return String as a form of feedback
	 */
	public String processOperation(String input) {
		logger.log(Level.INFO, "input recieved");
		Display message = Launch.getDisplay();
		CommandElements processed = new CommandElements();
		try {
			processed = CommandParser.ProcessInput(input);
		} catch (Exception e) {
			logger.log(Level.INFO, "error message");
			return message.error(input);
		}
		if (!isValidInput(processed)) {
			return message.error(input);
		}
		logger.log(Level.INFO, "input processed successfully");
		return performOperation(input, message, processed);
	}

	/**
	 * perform user input from CommandElements
	 * 
	 * @param input
	 *            user input
	 * @param message
	 *            message class
	 * @param processed
	 *            input converted to CommandElements
	 * @return
	 */
	private String performOperation(String input, Display message, CommandElements processed) {
		TaskDisplayController controller = TaskDisplayController.getInstance();
		processed = findId(processed);
		String name = findTaskName(processed);
		try {
			if (performCommand(processed.getType(), processed)) {
				flagView = controller.isResultViewEnabled();
				logger.log(Level.INFO, "performing command " + processed.getType());
				return message.operation(processed.getType(), name);
			} else {
				return message.error(input);
			}
		} catch (OperationNotPerformed e) {
			logger.log(Level.INFO, "invalid input " + e.getMessage());
			return message.error(input);
		}
	}

	/**
	 * find the task id based on numbering of UI
	 * 
	 * @param processed
	 *            input user specified
	 * @return CommandElement with the correct id of the task
	 */
	private CommandElements findId(CommandElements processed) {
		if (processed.getID() != -1) {
			int index = processed.getID() - 1;
			if (flagView) {
				processed.setID(resultView.get(index).getTaskID());
			} else {
				processed.setID(allView.get(index).getTaskID());
			}
		}
		return processed;
	}

	/**
	 * check if the input user specified is valid
	 * 
	 * @param content
	 * @return
	 */
	private boolean isValidInput(CommandElements content) {
		if (content.getEndDate() == null || content.getStartDate() == null) {
			return true;
		} else if (content.getEndDate().getDay() == -1 || content.getStartDate().getDay() == -1) {
			return false;
		}
		return true;
	}

	/**
	 * finding the task name for feedback message
	 * 
	 * @param content
	 *            user input stored as CommandElements
	 * @return name of the task
	 */
	private String findTaskName(CommandElements content) {
		String name;
		if (content.getType().toString().equals(("ADD_TASK")) || (content.getType().toString().equals(("DIRECTORY")))) {
			name = content.getName();
		} else {
			name = findTaskNameById(content.getID());
		}
		return name;
	}

	/**
	 * find the task name based of its id
	 * 
	 * @param id
	 * @return the task name
	 */
	private String findTaskNameById(int id) {
		ArrayList<TaskEvent> tasks = findViewArray();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskID() == id) {
				return tasks.get(i).getTaskName();
			}
		}
		return DEFAULT_RETURN;
	}

	/**
	 * get the correct tasks view user is viewing
	 * 
	 * @return corresponding ArrayList<TaskEvent> that user sees
	 */
	private ArrayList<TaskEvent> findViewArray() {
		if (flagView) {
			return resultView;
		} else {
			return allView;
		}
	}

	/**
	 * perform the command
	 * 
	 * @param command
	 * @param content
	 * @return
	 * @throws OperationNotPerformed
	 */
	private boolean performCommand(Command_Type command, CommandElements content) throws OperationNotPerformed {
		TaskDisplayController controller = TaskDisplayController.getInstance();
		StorageImp action = Launch.getStorage();
		Undo undo = Launch.getUndo();
		Redo redo = Launch.getRedo();
		OperationNotPerformed exception = new OperationNotPerformed("operation not performed");
		boolean isSuccessful = false;
		switch (command) {
		case ADD_TASK:
			logger.log(Level.INFO, "command is add");
			try {
				isSuccessful = action.addTask(content.getName(), content.getStartDate(), content.getStartTime(),
						content.getEndDate(), content.getEndTime(), content.getPriority());
			} catch (Exception e) {
				logger.log(Level.INFO, "exception caught :" + e.getMessage());
				return false;
			}
			if (isSuccessful) {
				int id = findID(content);
				undoList.push(undoAdd(id));
			}
			break;
		case EDIT_TASK:
			logger.log(Level.INFO, "command is edit");
			String name = findInitialContent(content.getID(), content.getField());
			isSuccessful = action.editTask(content.getID(), content.getField(), getEditContent(content));
			logger.log(Level.INFO, "success is " + isSuccessful);
			if (isSuccessful) {
				undoList.push(undoEdit(content.getID(), content.getField(), name));
			}
			break;
		case DELETE_TASK:
			logger.log(Level.INFO, "command is delete");
			undoList.push(undoDelete(content.getID()));
			isSuccessful = action.deleteTaskByID(content.getID());
			if (!isSuccessful) {
				undoList.pop();
			}
			break;
		case FINISH_TASK:
			logger.log(Level.INFO, "command is completed");
			isSuccessful = action.markTaskAsDone(content.getID());
			if (isSuccessful) {
				undoList.push(undoFinish(content.getID()));
			}
			break;
		case UNFINISH_TASK:
			logger.log(Level.INFO, "command is unfinish");
			isSuccessful = action.markTaskAsUndone(content.getID());
			if (isSuccessful) {
				undoList.push(undoUnfinish(content.getID()));
			}
			break;
		case SEARCH_TASK:
			logger.log(Level.INFO, "command is search");
			search = getSearchString(content);
			resultView = action.searchTaskByString(search);
			size = resultView.size();
			controller.triggerResultView();
			isSuccessful = true;
			break;
		case UNDO:
			logger.log(Level.INFO, "command is undo");
			content = undoList.pop();
			redoList.push(findRedoContent(content));
			logger.log(Level.INFO, "undoing " + content.getType().toString() + " to " + content.getName());
			return undo.undoTask(content);
		case REDO:
			logger.log(Level.INFO, "command is redo");
			content = redoList.pop();
			logger.log(Level.INFO, "content type " + content.getType());
			return redo.redoTask(content);
		case DIRECTORY:
			logger.log(Level.INFO, "command is change directory");
			String directory = action.getDirectory();
			isSuccessful = action.changeDirectory(content.getName());
			if (isSuccessful) {
				undoList.push(undoChange(directory));
			}
			break;
		case FLAG_TASK:
			logger.log(Level.INFO, "command is flag");
			isSuccessful = action.flagTask(content.getID());
			undoList.push(undoFlag(content.getID()));
			break;
		case UNFLAG_TASK:
			logger.log(Level.INFO, "command is unflag");
			isSuccessful = action.unflagTask(content.getID());
			undoList.push(undoUnflag(content.getID()));
			break;
		case VIEW_COMPLETED:
			logger.log(Level.INFO, "command is view completed");
			resultView = action.loadCompletedTasks();
			controller.triggerResultView();
			size = resultView.size();
			return true;
		case HELP:
			return true;
		default:
			throw exception;
		}
		return isSuccessful;
	}

	// ================================================================
	// HELPER METHOD FOR DIFFERENT COMMAND
	// ================================================================
	/**
	 * For Add command. getting the id of the task from its content
	 * 
	 * @param content
	 *            processed user input
	 * @return corresponding task id
	 */
	private int findID(CommandElements content) {
		StorageImp storage = Launch.getStorage();
		ArrayList<TaskEvent> tasks = storage.loadAllTasks();
		int id = 0;
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskName().equals(content.getName())) {
				id = tasks.get(i).getTaskID();
				break;
			}
		}
		return id;
	}

	/**
	 * For the edit command. Get the field which user want to edit
	 * 
	 * @param content
	 *            processed content based on user input
	 * @return corresponding edit content
	 */
	private String getEditContent(CommandElements content) {
		logger.log(Level.INFO, "finding edit command");
		switch (content.getField()) {
		case NAME:
			logger.log(Level.INFO, "edit name");
			return content.getName();
		case START_DATE:
			logger.log(Level.INFO, "edit start date");
			TaskDate startDate = content.getStartDate();
			return startDate.toString();
		case END_DATE:
			TaskDate endDate = content.getEndDate();
			logger.log(Level.INFO, "edit end date to " + endDate.toString());
			return endDate.toString();
		case PRIORITY:
			logger.log(Level.INFO, "edit priority");
			return content.getPriority().toString();
		case END_TIME:
			logger.log(Level.INFO, "edit endtime");
			return content.getEndTime().toString();
		case START_TIME:
			logger.log(Level.INFO, "edit starttime to " + content.getEndTime());
			return content.getStartTime().toString();
		default:
			assert false : content.getField();
		}
		return DEFAULT_RETURN;
	}

	/**
	 * For edit command, looking for initial content from id to facilitate undo
	 * operation
	 * 
	 * @param id
	 *            task id
	 * @param field
	 *            content that is going to be edited
	 * @return content before edit
	 */
	private String findInitialContent(int id, Command_Field field) {
		StorageImp store = Launch.getStorage();
		ArrayList<TaskEvent> tasks = store.loadAllTasks();
		TaskEvent task = tasks.get(0);
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskID() == id) {
				task = tasks.get(i);
			}
		}
		return getContent(task, field);
	}

	/**
	 * For edit command, retrieve the content of the initial content
	 * 
	 * @param task
	 *            the task which is going to be edited
	 * @param field
	 *            the field which user wants to edit
	 * @return the content to be edited
	 */
	private String getContent(TaskEvent task, Command_Field field) {
		logger.log(Level.INFO, "finding old content");
		switch (field) {
		case NAME:
			logger.log(Level.INFO, "name");
			return task.getTaskName();
		case START_DATE:
			logger.log(Level.INFO, "start date");
			TaskDate startDate = task.getStartDate();
			return startDate.toString();
		case END_DATE:
			TaskDate endDate = task.getEndDate();
			logger.log(Level.INFO, "end date");
			return endDate.toString();
		case PRIORITY:
			logger.log(Level.INFO, "priority");
			return task.getPriority().toString();
		case END_TIME:
			logger.log(Level.INFO, "end time");
			return task.getEndTime().toString();
		case START_TIME:
			logger.log(Level.INFO, "start time");
			return task.getStartTime().toString();
		default:
			break;
		}
		return DEFAULT_RETURN;
	}

	/**
	 * For search command, get the string to search
	 * 
	 * @param content
	 *            processed user input
	 * @return search String
	 */
	private String getSearchString(CommandElements content) {
		if (content.getName() != null) {
			return content.getName();
		} else if (content.getEndDate() != null) {
			return content.getEndDate().toString();
		} else if (content.getEndTime() != null) {
			return content.getEndTime().toString();
		}
		return DEFAULT_RETURN;
	}

	// ================================================================
	// UNDO : to add corresponding action to be undone
	// ================================================================
	private CommandElements undoAdd(int id) {
		logger.log(Level.INFO, "adding add undo" + id);
		CommandElements next = new CommandElements(Command_Type.ADD_TASK, id);
		return next;
	}

	private CommandElements undoDelete(int id) {
		logger.log(Level.INFO, "adding delete undo");
		CommandElements next = new CommandElements(Command_Type.DELETE_TASK, id);
		return next;
	}

	private CommandElements undoEdit(int id, Command_Field field, String content) {
		logger.log(Level.INFO, "adding edit undo");
		CommandElements next = new CommandElements(Command_Type.EDIT_TASK, id, field, content);
		return next;
	}

	private CommandElements undoFinish(int id) {
		logger.log(Level.INFO, "adding complete undo");
		CommandElements next = new CommandElements(Command_Type.FINISH_TASK, id);
		return next;
	}

	private CommandElements undoUnfinish(int id) {
		logger.log(Level.INFO, "adding unfinish undo");
		CommandElements next = new CommandElements(Command_Type.UNFINISH_TASK, id);
		return next;
	}

	private CommandElements undoFlag(int id) {
		logger.log(Level.INFO, "adding flag undo");
		CommandElements next = new CommandElements(Command_Type.FLAG_TASK, id);
		return next;
	}

	private CommandElements undoUnflag(int id) {
		logger.log(Level.INFO, "adding unflag undo");
		CommandElements next = new CommandElements(Command_Type.UNFLAG_TASK, id);
		return next;
	}

	private CommandElements undoChange(String old) {
		logger.log(Level.INFO, "adding undo change directory");
		CommandElements next = new CommandElements(Command_Type.DIRECTORY, old);
		return next;
	}

	// ================================================================
	// REDO
	// ================================================================
	/**
	 * to locate the content for easy redo action
	 * 
	 * @param content
	 *            to be redone
	 * @return corresponding content to redo
	 */
	private CommandElements findRedoContent(CommandElements content) {
		Command_Type type = content.getType();
		StorageImp action = Launch.getStorage();
		switch (type) {
		case ADD_TASK:
			return new CommandElements(Command_Type.ADD_TASK, content.getID());
		case DELETE_TASK:
			return new CommandElements(Command_Type.DELETE_TASK, content.getID());
		case EDIT_TASK:
			String name = findInitialContent(content.getID(), content.getField());
			return new CommandElements(Command_Type.EDIT_TASK, content.getID(), content.getField(), name);
		case FINISH_TASK:
			return new CommandElements(Command_Type.FINISH_TASK, content.getID());
		case UNFINISH_TASK:
			return new CommandElements(Command_Type.UNFINISH_TASK, content.getID());
		case DIRECTORY:
			return new CommandElements(Command_Type.DIRECTORY, action.getDirectory());
		case FLAG_TASK:
			return new CommandElements(Command_Type.FLAG_TASK, content.getID());
		case UNFLAG_TASK:
			return new CommandElements(Command_Type.UNFLAG_TASK, content.getID());
		default:
			break;
		}
		return new CommandElements();
	}
}
```
###### ./src/logic/Redo.java
``` java
/**
 * This class is to perform redo command
 * @author Shirlene
 *
 */
public class Redo {
	private static Logger logger;
	StorageImp storage;

	// ================================================================
	// DEFAULT CONSTRUTOR
	// ================================================================
	public Redo() {
		logger = Logger.getLogger("redo");
		storage = Launch.getStorage();
	}

	// ================================================================
	// MAIN REDO METHOD
	// ================================================================
	/**
	 * main method to perform redo
	 * 
	 * @param content
	 *            action to be redone
	 * @return if redo action is successful
	 */
	public boolean redoTask(CommandElements content) {
		logger.log(Level.INFO, "starting to redo");
		Command_Type type = content.getType();
		switch (type) {
		case ADD_TASK:
			return redoAdd(content);
		case DELETE_TASK:
			return redoDelete(content);
		case EDIT_TASK:
			return redoEdit(content);
		case FINISH_TASK:
			return redoComplete(content);
		case UNFINISH_TASK:
			return redoUnfinish(content);
		case DIRECTORY:
			return redoDirectory(content);
		case FLAG_TASK:
			return redoFlag(content);
		case UNFLAG_TASK:
			return redoUnflag(content);
		default:
			return false;
		}
	}

	// ================================================================
	// HELPER METHOD FOR REDO
	// ================================================================
	private boolean redoAdd(CommandElements content) {
		logger.log(Level.INFO, "redo add");
		return storage.undoDeleteTaskByID(content.getID());
	}

	private boolean redoDelete(CommandElements content) {
		logger.log(Level.INFO, "redo delete");
		return storage.deleteTaskByID(content.getID());
	}

	private boolean redoEdit(CommandElements content) {
		logger.log(Level.INFO, "redo edit");
		return storage.editTask(content.getID(), content.getField(), content.getName());
	}

	private boolean redoComplete(CommandElements content) {
		logger.log(Level.INFO, "redo Complete");
		return storage.markTaskAsDone(content.getID());
	}

	private boolean redoUnfinish(CommandElements content) {
		logger.log(Level.INFO, "redo unfinish");
		return storage.markTaskAsUndone(content.getID());
	}

	private boolean redoDirectory(CommandElements content) {
		logger.log(Level.INFO, "redo change directory");
		return storage.changeDirectory(content.getName());
	}

	private boolean redoFlag(CommandElements content) {
		logger.log(Level.INFO, "redo flag");
		return storage.flagTask(content.getID());
	}

	private boolean redoUnflag(CommandElements content) {
		logger.log(Level.INFO, "undo unflag");
		return storage.unflagTask(content.getID());
	}
}
```
###### ./src/logic/Undo.java
``` java
/**
 * This class is to perform the undo command
 * 
 * @author Shirlene
 *
 */
public class Undo {
	private static Logger logger;
	StorageImp storage;

	// ================================================================
	// DEFAULT CONSTRUTOR
	// ================================================================
	public Undo() {
		logger = Logger.getLogger("Undo");
		storage = Launch.getStorage();
	}

	// ================================================================
	// MAIN UNDO METHOD
	// ================================================================
	public boolean undoTask(CommandElements content) {
		logger.log(Level.INFO, "starting to undo");
		Command_Type type = content.getType();
		switch (type) {
		case ADD_TASK:
			return undoAdd(content);
		case DELETE_TASK:
			return undoDelete(content);
		case EDIT_TASK:
			return undoEdit(content);
		case FINISH_TASK:
			return undoComplete(content);
		case UNFINISH_TASK:
			return undoUnfinish(content);
		case DIRECTORY:
			return undoDirectory(content);
		case FLAG_TASK:
			return undoFlag(content);
		case UNFLAG_TASK:
			return undoUnflag(content);
		default:
			return false;
		}
	}

	// ================================================================
	// HELPER METHOD FOR UNDO
	// ================================================================
	private boolean undoAdd(CommandElements content) {
		logger.log(Level.INFO, "undo add" + content.getID());
		return storage.deleteTaskByID(content.getID());
	}

	private boolean undoDelete(CommandElements content) {
		logger.log(Level.INFO, "undo delete");
		return storage.undoDeleteTaskByID(content.getID());
	}

	private boolean undoEdit(CommandElements content) {
		logger.log(Level.INFO, "undo edit");
		return storage.editTask(content.getID(), content.getField(), content.getName());
	}

	private boolean undoComplete(CommandElements content) {
		logger.log(Level.INFO, "undo Complete");
		return storage.markTaskAsUndone(content.getID());
	}

	private boolean undoUnfinish(CommandElements content) {
		logger.log(Level.INFO, "undo unfinish");
		return storage.markTaskAsDone(content.getID());
	}

	private boolean undoDirectory(CommandElements content) {
		logger.log(Level.INFO, "undo Change");
		return storage.changeDirectory(content.getName());
	}

	private boolean undoFlag(CommandElements content) {
		logger.log(Level.INFO, "undo flag");
		return storage.unflagTask(content.getID());
	}

	private boolean undoUnflag(CommandElements content) {
		logger.log(Level.INFO, "undo unflag");
		return storage.flagTask(content.getID());
	}
}
```
###### ./src/utilities/Command_Field.java
``` java
/**
 * This is a Command Field enum 
 * @author Shirlene 
 *
 */
public enum Command_Field {
	NAME,
	START_DATE,
	END_DATE,
	START_TIME,
	END_TIME,
	PRIORITY
};
```
###### ./src/utilities/CommandElements.java
``` java
	/**
	 * Commands that does not exist
	 */
	public CommandElements() {
		type = Command_Type.NOT_FOUND;
	}

	/**
	 * Add of tasks
	 * 
	 * @param cmd
	 *            users enters
	 * @param nm
	 *            name of tasks
	 * @param dt
	 *            date user input
	 * @param prio
	 *            priority which user sets
	 * @param tm
	 *            time user input
	 */
	public CommandElements(Command_Type cmd, String nm, TaskDate dt[], Command_Priority prio, TaskTime tm[]) {
		this.id = -1;
		this.type = cmd;
		this.name = nm;
		this.date = dt;
		this.priority = prio;
		this.time = tm;
	}

	/**
	 * Editing of date field
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 *            id of task
	 * @param fld
	 *            field to be edited
	 * @param dt
	 *            date to change to
	 */
	public CommandElements(Command_Type cmd, int id, Command_Field fld, TaskDate dt) {
		this.type = cmd;
		this.id = id;
		this.field = fld;
		if (fld == Command_Field.END_DATE) {
			this.date[1] = dt;
			this.date[0] = new TaskDate(0, 0, 0);
		} else {
			this.date[0] = dt;
			this.date[1] = new TaskDate(0, 0, 0);
		}
	}

	/**
	 * Editing of time
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 *            id of task
	 * @param fld
	 *            field to be edited
	 * @param tm
	 *            time to change to
	 */
	public CommandElements(Command_Type cmd, int id, Command_Field fld, TaskTime tm) {
		this.type = cmd;
		this.id = id;
		this.field = fld;
		if (fld == Command_Field.END_TIME) {
			this.time[1] = tm;
			this.time[0] = new TaskTime(0, 0);
		} else {
			this.time[0] = tm;
			this.time[1] = new TaskTime(0, 0);
		}
	}

```
###### ./src/utilities/Exceptions.java
``` java
/**
 * Handles all exception
 * 
 * @author Shirlene
 *
 */
public class Exceptions {
	/**
	 * Exception for when taskid does not exist in file
	 *
	 */
	public static class TaskIDNotExistException extends Exception {
		private static final long serialVersionUID = 404514596737087454L;

		public TaskIDNotExistException(String message) {
			super(message);
		}
	}

	/**
	 * Exception for when operation is not performed
	 *
	 */
	public static class OperationNotPerformed extends Exception {
		private static final long serialVersionUID = -5066490802491867540L;

		public OperationNotPerformed(String message) {
			super(message);
		}
	}

	/**
	 * Exception when edit field cannot be found
	 *
	 */
	public static class EditFieldNotFound extends Exception {
		private static final long serialVersionUID = -452260938938082504L;

		public EditFieldNotFound(String message) {
			super(message);
		}
	}

	/**
	 * Exception when command user input does not exist
	 *
	 */
	public static class CommandNotFound extends Exception {
		private static final long serialVersionUID = -2444651689762170226L;

		public CommandNotFound(String message) {
			super(message);
		}
	}
}
```
###### ./src/utilities/TaskDate.java
``` java
	public TaskDate(int year, int month, int day) {
		myYear = year;
		myMonth = month;
		myDay = day;
	}

	public TaskDate(String date) {
		if (date.equals("null")) {
			myYear = 0;
			myMonth = 0;
			myDay = 0;
			return;
		}
		String[] split = date.split("/");
		myDay = Integer.parseInt(split[0]);
		myMonth = Integer.parseInt(split[1]);
		myYear = Integer.parseInt(split[2]);
	}

	public TaskDate() {
		myYear = -1;
		myMonth = -1;
		myDay = -1;
	}

	// ================================================================
	// SETTERS
	// ================================================================
```
