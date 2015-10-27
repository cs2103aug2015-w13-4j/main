package logic;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Storage;
import utilities.CommandElements;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.TaskDate;
import utilities.TaskEvent;
import parser.CommandParser;

public class Operation {

	private static Logger logger;
	private Stack<CommandElements> list;
	
	public Operation() {
		list = new Stack<CommandElements>();
		logger = Logger.getLogger("Operation");
	}

	/*
	 * Main method to process the user input
	 */
	public String processOperation(String input) {
		logger.log(Level.INFO, "input recieved");
		// get the commandElements from parser
		Display message = Launch.getDisplay();
		CommandElements processed = CommandParser.ProcessInput(input);
		String name = getName(processed);
		logger.log(Level.INFO, "input processed" + processed.getType());
		if (performCommand(processed.getType(), processed)) {
			logger.log(Level.INFO, "performing command " + processed.getType());
			return message.operation(processed.getType(), name);
			
		} else {
			logger.log(Level.INFO, "invalid input");
			return message.error(input);
		}
	}
	private String getName(CommandElements content){
		String name;
		if(content.getType().toString().equals(("ADD_TASK"))){
			name = content.getName();
		}else{
			name = getTaskName(content.getID());
		}
		return name;
	}
	private String getTaskName(int id){
		System.out.println("finding "+id);
		Display message = Launch.getDisplay();
		ArrayList<TaskEvent> tasks = message.taskView();
		System.out.println("size" + tasks.size());
		for(int i =0;i<tasks.size();i++){
			System.out.println("id "+ tasks.get(i).getTaskID() +"name "+tasks.get(i).getTaskName());
			if(tasks.get(i).getTaskID() == id){
				return tasks.get(i).getTaskName();
			}
		}
		return "does not exist";
	}

	/**
	 * perform the command
	 * 
	 * @param command
	 * @param content
	 * @return
	 */
	private boolean performCommand(Command_Type command, CommandElements content) {
		Storage action = Launch.getStorage();
		Search search = Launch.getSearch();
		boolean isSuccessful = false;
		switch (command) {
		case ADD_TASK:
			logger.log(Level.INFO, "command is add");
			try{
				isSuccessful = action.addTask(content.getName(), content.getStartDate(),content.getEndDate(), content.getPriority());
			} catch(Exception e){
				logger.log(Level.INFO,"exception caught :"+e.getMessage());
				return isSuccessful;
			}
			if(isSuccessful){
				undoAdd();
			}
			return isSuccessful;
		case EDIT_TASK:
			logger.log(Level.INFO, "command is edit");
			String name = getInitialContent(content.getID(),content.getField());
			isSuccessful = action.editTask(content.getID(), content.getField(), getEditContent(content));
			logger.log(Level.INFO, "success is " + isSuccessful);
			
			return isSuccessful;
		case DELETE_TASK:
			logger.log(Level.INFO, "command is delete");
			undoDelete(content.getID());
			isSuccessful = action.deleteTaskByID(content.getID());
			if(!isSuccessful){
				list.pop();
			}
			return isSuccessful;
		case FINISH_TASK:
			logger.log(Level.INFO, "command is completed");

		case SEARCH_TASK:
			logger.log(Level.INFO, "command is search");

			isSuccessful = search.searchWord(action.loadAllTasks(), content.getName());
			
		case UNDO: 
			//undo add, delete, edit, complete, directory 
			logger.log(Level.INFO, "command is undo");
			content = list.pop();
			return performCommand(content.getType(),content);
		case DIRECTORY:
			logger.log(Level.INFO, "command is change directory");
		default:
			// throw exception
		}
		return false;
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
			logger.log(Level.INFO, "edit end date to "+endDate.toString());
			return endDate.toString();
		case PRIORITY:
			logger.log(Level.INFO, "edit priority");
			return content.getPriority().toString();
		}
		return "";
	}
	private String getInitialContent(int id, )
	private void undoAdd(){
		Storage store = Launch.getStorage();
		int id = store.loadAllTasks().size();
		CommandElements next = new CommandElements(Command_Type.DELETE_TASK,id);
		list.push(next);
	}
	private void undoDelete(int id){
		/*Storage store = Launch.getStorage();
		ArrayList<TaskEvent> all = store.loadAllTasks();
		TaskEvent task = all.get(0);
		for(int i =0;i<all.size();i++){
			if(all.get(i).getTaskID() == id){
				task = all.get(i);
			}
		}*/
		CommandElements next = new CommandElements(Command_Type.ADD_TASK,id);
		list.push(next);
	}
	private void undoEdit(){
		
	}
	private void undoComplete(){
		
	}
	private void undoChange(){
		
	}
}