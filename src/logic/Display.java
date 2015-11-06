package logic;


import database.StorageImp;
import resources.view.TaskDisplayController;
import utilities.Command_Type;
import utilities.TaskEvent;

import java.util.ArrayList;

//@@Shirlene
public class Display {

	/* Feedback */
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
	

	private Command_Type nextCommand = Command_Type.UNDO;
	private ArrayList<TaskEvent> view;
	private ArrayList<TaskEvent> search;

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
	public ArrayList<TaskEvent> resultView(){
		Operation op = Launch.getOperation();
		
		search = op.getArray();
		return search;
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
			//if(content)
			return String.format(MESSAGE_FINISHED, content);
		case UNFINISH_TASK:
			return String.format(MESSAGE_UNFINISH, content);
		case SEARCH_TASK:
			view = operation.getArray();
			taskDisplay.triggerResultView();
			if(view.size()==0){
				return MESSAGE_NO_SEARCH_RESULT;
			} else{
				return String.format(MESSAGE_SEARCH,view.size());
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
			view = operation.getArray();
			taskDisplay.triggerResultView();
			if(view.size()==0){
				return MESSAGE_NO_COMPLETED;
			}
			return String.format(MESSAGE_VIEW_COMPLETED,view.size());
		case HELP:
			taskDisplay.triggerHelpView();
			return MESSAGE_HELP;
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
