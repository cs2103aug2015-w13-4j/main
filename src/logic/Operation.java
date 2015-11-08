package logic;

import parser.CommandParser;

import resources.view.TaskDisplayController;

import database.StorageImp;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import utilities.CommandElements;
import utilities.Command_Field;
import utilities.Command_Type;
import utilities.TaskDate;
import utilities.TaskEvent;
import utilities.Exceptions.OperationNotPerformed;


//@@author A0130909H Shirlene
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
	int size;

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
		return resultView;
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
		processed = findId(processed);
		String name = findTaskName(processed);
		try {
			if (performCommand(processed.getType(), processed)) {
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
		TaskDisplayController controller = TaskDisplayController.getInstance();
		if (processed.getID() != -1) {
			int index = processed.getID() - 1;
			if (controller.isResultViewEnabled()) {
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
		if (content.getType().toString().equals(("ADD_TASK"))) {
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
		Display message = Launch.getDisplay();
		ArrayList<TaskEvent> tasks = message.taskView();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskID() == id) {
				return tasks.get(i).getTaskName();
			}
		}
		return DEFAULT_RETURN;
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
			resultView = action.searchTaskByString(getSearchString(content));
			size = resultView.size();
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
			break;
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