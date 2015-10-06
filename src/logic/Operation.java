package logic;

import database.Storage;
import utilities.CommandElements;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.TaskDate;
import parser.CommandParser;

public class Operation {
	public String processOperation(String input){
		//get the commandElements from parser
		Display message = new Display();
		CommandParser parser = new CommandParser();
		CommandElements processed = parser.ProcessInput(input);
		
		if(performCommand(processed.getType(),processed)){
			return message.operation(processed.getType(), processed.getDescription());	
		}
		else{
			return message.error(input);
		}
	}
	private boolean performCommand(Command_Type command,CommandElements content){
		Storage action = new Storage();
		switch(command){
		case ADD_TASK:
			
			action.addTask(content.getDescription(), content.getDate(),getPriority(content.getPriority()),content.getDescription());

			//action.addTask(content.get(0)+ content.get(1)+content.get(2)+content.get(3));
			return true;
		case EDIT_TASK:		
			action.editTask(content.getID(),content.getField().toString(),getEditContent(content));
			return true;
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
		switch(content.getField()){
		case DATE:
			TaskDate date = content.getDate();
			return date.toString();
		case DESCRIPTION:
			return content.getDescription();
		case PRIORITY:
			return content.getPriority().toString();
		}
		return "";
		
	}
}
