package logic;
import java.util.ArrayList;

import database.Storage;
import utilities.Command_Type;
import utilities.TaskEvent;

public class Display {
	
	private static final String MESSAGE_ADD = "%s has been added sucessfully";
	private static final String MESSAGE_EDIT ="%s has been edited sucessfully";
	private static final String MESSAGE_ERROR = "%s could not be performed";
	
	
	//for the whole list
	public ArrayList<TaskEvent> defaultView(){
		Storage store = new Storage();
		return store.load();
	}
	public String operation(Command_Type op, String content){
		switch(op){
		case ADD_TASK:
			return String.format(MESSAGE_ADD, content);
		case EDIT_TASK:
			return String.format(MESSAGE_EDIT, content);
		}
		return "";
	}
	public String error(String input){
		return String.format(MESSAGE_ERROR, input);
	}
}
