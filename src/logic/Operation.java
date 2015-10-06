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
			action.addTask(content.getDescription(), content.getDate(),content.getPriority().toString());

			//action.addTask(content.get(0)+ content.get(1)+content.get(2)+content.get(3));
			return true;
		case EDIT_TASK:
			//get the prev taskEvent
			//replace the repective field
			//edit
			action.editTask(content.getID(),content.getField(),content);
			return true;
		}
		return false;
	}
}
