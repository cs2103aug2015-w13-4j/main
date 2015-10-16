package logic;

import database.Storage;
import utilities.CommandElements;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.TaskDate;
import parser.CommandParser;
import logic.Launch;

public class Operation {

	public Operation(){
	}
	/*
	 * Main method to process the user input
	 */
	public String processOperation(String input){
		//get the commandElements from parser
		Display message = Launch.getDisplay();
		
		CommandElements processed= CommandParser.ProcessInput(input);
		
		if(performCommand(processed.getType(),processed)){
			return message.operation(processed.getType(), processed.getName());	
		} else{
			return message.error(input);
		}
	}
	/**
	 * perform the command
	 * @param command
	 * @param content
	 * @return
	 */
	private boolean performCommand(Command_Type command,CommandElements content){
		Storage action = Launch.getStorage();
		Search search = Launch.getSearch();
		boolean isSuccessful;
		switch(command){
		case ADD_TASK:
			isSuccessful = action.addTask(content.getName(), content.getStartDate(),1,getPriority(content.getPriority()));
			return isSuccessful;
		case EDIT_TASK:		
			isSuccessful = action.editTask(content.getID(),content.getField().toString(),getEditContent(content));
			return isSuccessful;
		case DELETE_TASK:
			
		case FINISH_TASK:
		
		case SEARCH_TASK:
			isSuccessful = search.searchWord(action.load(),content.getName());
			return isSuccessful;
		case UNDO:
		
		case DIRECTORY:
		}
		return false;
	}
	/**
	 * Converting Command_Priority to String
	 * @param priority is the Command_Priority user input
	 * @return String equality of Command_Priority
	 */
	private String getPriority(Command_Priority priority){
		switch(priority){
		case HIGH:
			return "high";
		case MEDIUM:
			return "medium";
		case LOW:
			return "low";
		}
		return "";	
	}
	/*
	 * For the edit command. Get the field which user want to edit
	 */
	private String getEditContent(CommandElements content){
		switch(content.getField()){
		case NAME:
			return content.getName();
		case START_DATE :
			TaskDate startDate = content.getStartDate();
			return startDate.toString();
		case END_DATE:
			TaskDate endDate = content.getEndDate();
			return endDate.toString();
		case PRIORITY:
			return content.getPriority().toString();
		}
		return "";
	}
}
