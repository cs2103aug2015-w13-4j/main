package logic;
import java.util.ArrayList;

import utilities.Command_Type;
import utilities.TaskEvent;

public class Display {
	
	private static final String ADD = "%s has been added sucessfully\n";
	private static final String EDIT ="%s has been edited sucessfully\n";
	private static final String NOT_FOUND = "operation not found\n";
	
	//for the whole list
	public ArrayList<TaskEvent> defaultView(){
		return null;
		
	}
	public static  String operation(Command_Type op, String content){
		switch(op){
		case ADD_TASK:
			return String.format(ADD, content);
		case EDIT_TASK:
			return String.format(EDIT, content);
		}
		return NOT_FOUND;
	}
}
