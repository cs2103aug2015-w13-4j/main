//@@author Shirlene A0130909H
package logic;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Storage;
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
	ArrayList<TaskEvent> allView;
	ArrayList<Integer> idList;
	int size;

	public Operation() {
		undoList = new Stack<CommandElements>();
		logger = Logger.getLogger("Operation");
		searchView = new ArrayList<TaskEvent>();
		redoList = new Stack<CommandElements>();
		idList = new ArrayList<Integer>();
		allView = new ArrayList<TaskEvent>();
	}

	public ArrayList<TaskEvent> getArray() {
		ArrayList<TaskEvent >list = searchView;
		
		
		return list;
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
		if (!checkInput(processed)) {
			return message.error(input);
		}
		String name = getName(processed);
		processed = changeId(processed);
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
	private CommandElements changeId(CommandElements processed){
		if(processed.getID()!= -1){
			int index = processed.getID()-1;
			System.out.println("size " + allView.size());
			//System.out.println("before  " +index + "found "+ idList.get(index));
			processed.setID(allView.get(index).getTaskID());
		}
		return processed;
	}
	
	private boolean checkInput(CommandElements content) {
		if(content.getEndDate() == null || content.getStartDate() == null){
			return true;
		}
		
		if (content.getEndDate().getDay() == -1 || content.getStartDate().getDay() == -1) {
			return false;
		}
		return true;
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
				isSuccessful = action.addTask(content.getName(), content.getStartDate(), content.getStartTime(),
						content.getEndDate(), content.getEndTime(), content.getPriority());
			} catch (Exception e) {
				logger.log(Level.INFO, "exception caught :" + e.getMessage());
				return isSuccessful;
			}
			if (isSuccessful) {
				int id = findID(content);
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
			isSuccessful = action.deleteTaskByID(content.getID() );
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
		case UNFINISH_TASK:
			 logger.log(Level.INFO, "command is unfinish");
			 isSuccessful = action.markTaskAsUndone(content.getID());
			 if(isSuccessful){
				 undoList.push(undoUnfinish(content.getID()));
			 }
			 return isSuccessful;
		case SEARCH_TASK:
			// working for basic names
			logger.log(Level.INFO, "command is search");
			searchView = action.searchTaskByString(getSearchString(content));
			size = searchView.size();
			return isSuccessful = true;
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
			return isSuccessful;
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
		case END_TIME:
			logger.log(Level.INFO, "edit endtime");
			return content.getEndTime().toString();
		case START_TIME:
			logger.log(Level.INFO, "edit starttime to "+ content.getEndTime());
			return content.getStartTime().toString();
		default:
			break;
		}
		return DEFAULT_RETURN;
	}

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
		case END_TIME:
			break;
		case START_TIME:
			break;
		default:
			break;
		}
		return DEFAULT_RETURN;
	}
	private int findID(CommandElements content){
		Storage storage = Launch.getStorage();
		ArrayList<TaskEvent> tasks = storage.loadAllTasks();
		int id = 0;
		for(int i =0;i<tasks.size();i++){
			if(tasks.get(i).getTaskName().equals(content.getName())){
				id = tasks.get(i).getTaskID();
				break;
			}
		}
		return id;
	}
	private int locateID(int input){
		return idList.get(input-1);
	}

	private CommandElements undoAdd(int id) {
		logger.log(Level.INFO, "adding add undo" + id);
		CommandElements next = new CommandElements(Command_Type.ADD_TASK, id);
		return next;
	}
	private CommandElements undoUnfinish(int id){
		logger.log(Level.INFO, "adding unfinish");
		CommandElements next = new CommandElements(Command_Type.UNFINISH_TASK,id);
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

	private CommandElements undoChange(String old) {
		logger.log(Level.INFO, "adding undo change directory");
		CommandElements next = new CommandElements(Command_Type.DIRECTORY, old);
		return next;
	}
	public ArrayList<TaskEvent> fullView(){
		Storage storage = Launch.getStorage();
		allView =  sortArray(storage.loadAllTasks());
		
		/*idList = new ArrayList<Integer>();
		for(int i =0;i<list.size();i++){
			System.out.println("i "+i+" name "+list.get(i).getTaskName());
			idList.add(list.get(i).getTaskID());
			list.get(i).setTaskID(i+1);
		}*/
		return allView;
	}
	private ArrayList<TaskEvent> sortArray(ArrayList<TaskEvent> list){
		System.out.println("size "+list.size());
		ArrayList<TaskEvent> sorted = new ArrayList<TaskEvent>();
		ArrayList<TaskEvent> flag = new ArrayList<TaskEvent>();
		ArrayList<TaskEvent> others = new ArrayList<TaskEvent>();
		ArrayList<TaskEvent> floating = new ArrayList<TaskEvent>();
		for(int i =0;i<list.size();i++){
			if(list.get(i).getPriority().toString().equals("FLAG")){
				System.out.println("flag "+list.get(i).getTaskName());
				flag.add(list.get(i));
			} else if(list.get(i).getEndDate().getDay()==0 && list.get(i).getStartDate().getDay()==0){
				System.out.println("floating "+list.get(i).getTaskName());
				floating.add(list.get(i));
			} else{
				System.out.println("others " +list.get(i).getTaskName());
				others.add(list.get(i));
			}	
		}
		System.out.println("done");
		sorted.addAll(flag);
		sorted.addAll(others);
		sorted.addAll(floating);
		return sorted;
	}
	private CommandElements findRedoContent(CommandElements content) {
		Command_Type type = content.getType();
		Storage action = Launch.getStorage();
		switch (type) {
		case ADD_TASK:
			return new CommandElements(Command_Type.ADD_TASK, content.getID());
		case DELETE_TASK:
			return new CommandElements(Command_Type.DELETE_TASK, content.getID());
		case EDIT_TASK:
			String name = getInitialContent(content.getID(), content.getField());
			return new CommandElements(Command_Type.EDIT_TASK, content.getID(), content.getField(), name);
		case FINISH_TASK:
			return new CommandElements(Command_Type.FINISH_TASK, content.getID());
		case UNFINISH_TASK:
			return new CommandElements(Command_Type.UNFINISH_TASK,content.getID());
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