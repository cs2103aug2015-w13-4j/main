package logic;

import java.util.ArrayList;

import database.Storage;
import utilities.CommandElements;
import utilities.Command_Type;
import utilities.TaskDate;
import parser.ProcessInput;

public class Operation {
	private String processOperation(String input){
		//get the commandElements from parser
		Display message;
		CommandElements processed = ProcessInput(input);
		
		if(performCommand(processed.getCommand(),processed.getContent())){
			return message.operation(processed.getCommand(), processed.getContent().get(0));	
		}
		else{
			return message.error(input);
		}
	}
	private boolean performCommand(Command_Type command,ArrayList<String> content){
		Storage action;
		switch(command){
		case ADD_TASK:
			TaskDate date = content.getDate();
			
			action.addTask(content.get(0), date, content.get(2), content.get(3));
			return true;
		case EDIT_TASK:
			
			return true;
		}
		return false;
	}
}
