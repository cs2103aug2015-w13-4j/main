package logic;

import java.util.ArrayList;

import database.Storage;
import utilities.CommandElements;
import utilities.Command_Type;
import utilities.TaskDate;
import parser.CommandParser;
import parser.ProcessInput;

public class Operation {
	public String processOperation(String input){
		//get the commandElements from parser
		Display message;
		CommandParser parser;
		CommandElements processed = parser.ProcessInput(input);
		
		if(performCommand(processed.getType(),processed)){
			return message.operation(processed.getType(), processed.getDescription());	
		}
		else{
			return message.error(input);
		}
	}
	private boolean performCommand(Command_Type command,CommandElements content){
		Storage action;
		switch(command){
		case ADD_TASK:
			action.addTask(content.getName(), content.getDate(),content.getPriority().toString(),content.getDescription());

			//action.addTask(content.get(0)+ content.get(1)+content.get(2)+content.get(3));
			return true;
		case EDIT_TASK:		
			action.editTask(content.getID(),content.getField().toString(),getEditContent(content));
			return true;
		}
		return false;
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
		
	}
}
