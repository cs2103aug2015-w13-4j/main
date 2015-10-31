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
	private Stack<CommandElements> list;
	private static final String DEFAULT_RETURN = "not found";
	ArrayList<TaskEvent> searchView;

	public Operation() {
		list = new Stack<CommandElements>();
		logger = Logger.getLogger("Operation");
		searchView = new ArrayList<TaskEvent> ();
	}
	public ArrayList<TaskEvent> getArray(){
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
				undoAdd();
			}
			return isSuccessful;
		case EDIT_TASK:
			logger.log(Level.INFO, "command is edit");
			String name = getInitialContent(content.getID(), content.getField());
			isSuccessful = action.editTask(content.getID(), content.getField(), getEditContent(content));
			logger.log(Level.INFO, "success is " + isSuccessful);
			if (isSuccessful) {
				undoEdit(content.getID(), content.getField(), name);
			}
			return isSuccessful;
		case DELETE_TASK:
			logger.log(Level.INFO, "command is delete");
			undoDelete(content.getID());
			isSuccessful = action.deleteTaskByID(content.getID());
			if (!isSuccessful) {
				list.pop();
			}
			return isSuccessful;
		case FINISH_TASK:
			logger.log(Level.INFO, "command is completed");
			isSuccessful = action.markTaskAsDone(content.getID());
			if (isSuccessful) {
				undoComplete(content.getID());
			}
			return isSuccessful;
		case SEARCH_TASK:
			//not working!!
			logger.log(Level.INFO, "command is search");
			searchView = action.searchTaskByString(content.getName());
			if(searchView.size()==0){
				return isSuccessful;
			}
			else{
				return isSuccessful = true;
			}
		case UNDO:
			// undo add, delete, edit, complete, directory
			logger.log(Level.INFO, "command is undo");
			content = list.pop();
			logger.log(Level.INFO, "undoing "+content.getType().toString());
			return undo.UndoTask(content);
		case DIRECTORY:
			//not implemented!!
			logger.log(Level.INFO, "command is change directory");
		case FLAG_TASK:
			logger.log(Level.INFO,"command is flag");
			isSuccessful = action.flagTask(content.getID());
			undoFlag(content.getID());
			
			
			return isSuccessful;
			
		case UNFLAG_TASK:
			logger.log(Level.INFO, "command is unflag");
			isSuccessful = action.unflagTask(content.getID());
			undoUnflag(content.getID());
				
			return isSuccessful;
		case VIEW_COMPLETED:
			logger.log(Level.INFO, "command is view completed");
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

	private void undoAdd() {
		logger.log(Level.INFO, "adding add undo");
		StorageImp store = Launch.getStorage();
		int id = store.loadAllTasks().size();
		CommandElements next = new CommandElements(Command_Type.ADD_TASK, id);
		list.push(next);
	}

	private void undoDelete(int id) {
		/*
		 * Storage store = Launch.getStorage(); ArrayList<TaskEvent> all =
		 * store.loadAllTasks(); TaskEvent task = all.get(0); for(int i
		 * =0;i<all.size();i++){ if(all.get(i).getTaskID() == id){ task =
		 * all.get(i); } }
		 */
		logger.log(Level.INFO, "adding delete undo");
		CommandElements next = new CommandElements(Command_Type.DELETE_TASK, id);
		list.push(next);
	}

	private void undoEdit(int id, Command_Field field, String content) {
		logger.log(Level.INFO, "adding edit undo");
		CommandElements next = new CommandElements(Command_Type.EDIT_TASK, id, field, content);
		list.push(next);
	}

	private void undoComplete(int id) {
		logger.log(Level.INFO, "adding delete undo");
		CommandElements next = new CommandElements(Command_Type.FINISH_TASK, id);
		list.push(next);
	}
	private void undoFlag(int id){
		logger.log(Level.INFO, "adding flag undo");
		CommandElements next = new CommandElements(Command_Type.FLAG_TASK,id);
		list.push(next);
	}
	private void undoUnflag(int id){
		logger.log(Level.INFO, "adding unflag undo");
		CommandElements next = new CommandElements(Command_Type.UNFLAG_TASK,id);
		list.push(next);
	}

	private void undoChange() {

	}
}