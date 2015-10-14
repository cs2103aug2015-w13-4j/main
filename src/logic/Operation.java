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
	public String processOperation(String input){
		//Launch.createObjects();
		//get the commandElements from parser
		Display message = Launch.getDisplay();
		CommandElements processed= CommandParser.ProcessInput(input);
		
		if(performCommand(processed.getType(),processed)){
			return message.operation(processed.getType(), processed.getName());	
		}
		else{
			return message.error(input);
		}
	}
	private boolean performCommand(Command_Type command,CommandElements content){
		Storage action = Launch.getStorage();
		switch(command){
		case ADD_TASK:
			
			action.addTask(content.getName(), content.getStartDate(),getPriority(content.getPriority()));

			//action.addTask(content.get(0)+ content.get(1)+content.get(2)+content.get(3));
			return true;
		case EDIT_TASK:		
			action.editTask(content.getID(),content.getField().toString(),getEditContent(content));
			return true;
		case DELETE_TASK:
			
		case FINISH_TASK:
		case SEARCH_TASK:
		case UNDO:
		case DIRECTORY:
		}
		return false;
	}
	private int getPriority(Command_Priority priority){
		switch(priority){
		case HIGH:
			return 1;
		case MEDIUM:
			return 2;
		case LOW:
			return 3;
		}
		return 0;
		
		
		
	}
	private String getEditContent(CommandElements content){
		//		NAME, START_DATE, END_DATE, PRIORITY

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
