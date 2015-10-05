package logic;

import java.util.ArrayList;
import utilities.CommandElements;
import utilities.Command_Type;
import parser.ProcessInput;

public class Operation {
	private String processOperation(String input){
		//get the commandElements from parser
		CommandElements processed = ProcessInput(input);
		
		
		Display.operation(processed.getCommand(), processed.getContent().get(0));
	}
	private boolean performCommand(Command_Type command,ArrayList<String> content){
		switch(command){
		case ADD_TASK:
			//should call storage method
			return true;
		case EDIT_TASK:
			
			return true;
		}
		return false;
	}
}
