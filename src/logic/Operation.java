//@@author Shirlene A0130909H
package logic;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.StorageImp;
import utilities.CommandElements;
import utilities.Command_Field;
import utilities.Command_Type;
import utilities.TaskDate;
import utilities.TaskEvent;
import parser.CommandParser;
import utilities.Exceptions.OperationNotPerformed;

public class Operation {

	private static Logger logger;
	private Stack<CommandElements> undoList;
	private Stack<CommandElements> redoList;
	private static final String DEFAULT_RETURN = "not found";
	ArrayList<TaskEvent> searchView;
	int size;

	public Operation() {
		undoList = new Stack<CommandElements>();
		logger = Logger.getLogger("Operation");
		searchView = new ArrayList<TaskEvent>();
		redoList = new Stack<CommandElements>();
	}

	public ArrayList<TaskEvent> getArray() {
		return searchView;
	}

	/**
	 * Main method to process the user input
	 */
	public String processOperation(String input) {
		logger.log(Level.INFO, "input recieved");
		// get the commandElements from parser
		Display message = Launch.getDisplay();
		CommandElements processed = new CommandElements();
		try {
			processed = CommandParser.ProcessInput(input);
		} catch (Exception e) {
			logger.log(Level.INFO, "error message");
			return message.error(input);
		}
		String name = getName(processed);
		logger.log(Level.INFO, "input processed" + processed.getType());
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

	private String getName(CommandElements content) {
		String name;
		if (content.getType().toString().equals(("ADD_TASK"))) {
			name = content.getName();
		} else {
			name = getTaskName(content.getID());
		}
		return name;
	}

	private String getTaskName(int id) {
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
				isSuccessful = action.addTask(content.getName(), content.getStartDate(), content.getEndDate(),
						content.getPriority());
			} catch (Exception e) {
				logger.log(Level.INFO, "exception caught :" + e.getMessage());
				return isSuccessful;
			}
			if (isSuccessful) {
				int id = Launch.getStorage().loadAllTasks().size();
				undoList.push(undoAdd(id));
			}
			return isSuccessful;
		case EDIT_TASK:
			logger.log(Level.INFO, "command is edit");
			String name = getInitialContent(content.getID(), content.getField());
			isSuccessful = action.editTask(content.getID(), content.getField(), getEditContent(content));
			logger.log(Level.INFO, "success is " + isSuccessful);
			if (isSuccessful) {
				undoList.push(undoEdit(content.getID(), content.getField(), name));
			}
			return isSuccessful;
		case DELETE_TASK:
			logger.log(Level.INFO, "command is delete");
			undoList.push(undoDelete(content.getID()));
			isSuccessful = action.deleteTaskByID(content.getID());
			if (!isSuccessful) {
				undoList.pop();
			}
			return isSuccessful;
		case FINISH_TASK:
			logger.log(Level.INFO, "command is completed");
			isSuccessful = action.markTaskAsDone(content.getID());
			if (isSuccessful) {
				undoList.push(undoComplete(content.getID()));
			}
			return isSuccessful;
		case SEARCH_TASK:
			// working for basic names
			logger.log(Level.INFO, "command is search");
			searchView = action.searchTaskByString(content.getName());
			size = searchView.size();
			return isSuccessful = true;
		case UNDO: // not working for directory
			// undo add, delete, edit, complete, directory
			logger.log(Level.INFO, "command is undo");
			content = undoList.pop();
			redoList.push(findRedoContent(content));
			logger.log(Level.INFO, "undoing " + content.getType().toString());
			return undo.UndoTask(content);
		case REDO:
			logger.log(Level.INFO, "command is redo");
			content = redoList.pop();
			logger.log(Level.INFO, "content type "+ content.getType());
			return redo.redoTask(content);
		case DIRECTORY:
			// not implemented!!
			logger.log(Level.INFO, "command is change directory");
		case FLAG_TASK:
			logger.log(Level.INFO, "command is flag");
			isSuccessful = action.flagTask(content.getID());
			undoList.push(undoFlag(content.getID()));
			return isSuccessful;
		case UNFLAG_TASK:
			logger.log(Level.INFO, "command is unflag");
			isSuccessful = action.unflagTask(content.getID());
			undoList.push(undoUnflag(content.getID()));
			return isSuccessful;
		case VIEW_COMPLETED:
			logger.log(Level.INFO, "command is view completed");
			searchView = action.loadCompletedTasks();
			size = searchView.size();
			return true;
		case HELP:
			return true;
		default:
			throw exception;
		}
	}

	/**
	 * For the edit command. Get the field which user want to edit
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
		}
		return DEFAULT_RETURN;
	}

	private String getInitialContent(int id, Command_Field field) {
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
			logger.log(Level.INFO, "end date" + endDate.toString());
			return endDate.toString();
		case PRIORITY:
			logger.log(Level.INFO, "priority");
			return task.getPriority().toString();
		}
		return DEFAULT_RETURN;
	}

	private CommandElements undoAdd(int id) {
		logger.log(Level.INFO, "adding add undo");
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

	private CommandElements undoComplete(int id) {
		logger.log(Level.INFO, "adding delete undo");
		CommandElements next = new CommandElements(Command_Type.FINISH_TASK, id);
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

	private void undoChange() {

	}
	private CommandElements findRedoContent(CommandElements content){
		Command_Type type = content.getType();
		switch(type){
		case ADD_TASK:
			return new CommandElements(Command_Type.ADD_TASK,content.getID());
		case DELETE_TASK:
			return new CommandElements(Command_Type.DELETE_TASK,content.getID());
		case EDIT_TASK:
			String name = getInitialContent(content.getID(),content.getField());
			return new CommandElements(Command_Type.EDIT_TASK,content.getID(),content.getField(),name);
		case FINISH_TASK:
			return new CommandElements(Command_Type.FINISH_TASK,content.getID());
			//case DIRECTORY:
		case FLAG_TASK:
			return new CommandElements(Command_Type.FLAG_TASK,content.getID());
		case UNFLAG_TASK:
			return new CommandElements(Command_Type.UNFLAG_TASK,content.getID());
		default:
			break;
		}
		return new CommandElements();	
	}
}