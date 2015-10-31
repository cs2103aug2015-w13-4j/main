package logic;

import database.StorageImp;
import utilities.Command_Type;
import utilities.TaskEvent;

import java.util.ArrayList;

public class Display {

	/* Feedback */
	private static final String MESSAGE_ADD = "%s has been added successfully";
	private static final String MESSAGE_EDIT = "%s has been edited successfully";
	private static final String MESSAGE_ERROR = "%s could not be performed";
	private static final String MESSAGE_DELETE = "%s has been deleted successfully";
	private static final String MESSAGE_DIRECTORY = "file has been relocated to %s";
	private static final String MESSAGE_FINISHED = "%s has been completed!";
	private static final String MESSAGE_SEARCH = "tasks has been found with your search";
	private static final String MESSAGE_UNDO = "previous task has been undone";
	private static final String MESSAGE_FLAG = "%s has been flagged";
	private static final String MESSAGE_UNFLAG = "%s has been unflagged";
	private static final String MESSAGE_VIEW_COMPLETED = "list of completed task";

	private Command_Type nextCommand = Command_Type.UNDO;

	public Display() {
	}

	/**
	 * update the view on the screen of tasks
	 * 
	 * @return ArrayList of task to show user
	 */
	public ArrayList<TaskEvent> taskView() {
		StorageImp store = Launch.getStorage();
		Operation op = Launch.getOperation();
		ArrayList<TaskEvent> view;
		if (nextCommand == Command_Type.SEARCH_TASK || nextCommand.equals(Command_Type.VIEW_COMPLETED)) {
			view = op.getArray();
		} else {
			view = store.loadAllTasks();
		}
		return view;
	}

	/**
	 * Provide user with a feedback on the current operation
	 * 
	 * @param op
	 *            user input command
	 * @param content
	 *            user input
	 * @return feedback to user
	 */
	public String operation(Command_Type op, String content) {
		nextCommand = op;
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
			//if(content)
			return String.format(MESSAGE_FINISHED, content);
		case SEARCH_TASK:
			return String.format(MESSAGE_SEARCH);
		case UNDO:
			return MESSAGE_UNDO;
		case FLAG_TASK:
			return String.format(MESSAGE_FLAG, content);
		case UNFLAG_TASK:
			return String.format(MESSAGE_UNFLAG, content);
		case VIEW_COMPLETED:
			return MESSAGE_VIEW_COMPLETED;
		default:
			break;
		}
		return "";
	}

	/**
	 * Provide warning when input is not valid
	 * 
	 * @param input
	 *            user input content
	 * @return error message
	 */
	public String error(String input) {
		return String.format(MESSAGE_ERROR, input);
	}
}
