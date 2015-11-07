package parser;

import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.CommandElements;
import utilities.TaskDate;
import utilities.TaskTime;
import utilities.Exceptions.CommandNotFound;
import utilities.Exceptions.EditFieldNotFound;

public class CommandParser {
	
	private static final String CMD_NOT_FOUND = "command not found";
	private static final String EDIT_NOT_FOUND = "edit field not found";
	private static final Integer SINGLE = 1;
	

	public static CommandElements ProcessInput(String command) throws Exception {
		CommandNotFound command_exception = new CommandNotFound(CMD_NOT_FOUND);
		EditFieldNotFound edit_exception = new EditFieldNotFound(EDIT_NOT_FOUND);
		Command_Type type;
		String name;
		TaskDate date[] = new TaskDate[2];
		TaskTime time[] = new TaskTime[2];
		Command_Priority priority;
		Command_Field field;
		int object;
		type = CommandSplitter.findType(command);
		name = CommandSplitter.findName(command);
		date = CommandSplitter.extractDate(command);
		time = CommandSplitter.extractTime(command);
		priority = CommandSplitter.findPriority(command);
		field = CommandSplitter.findField(command);
		switch (type)
		{
		case ADD_TASK: 
			return new CommandElements(type, name, date, priority, time);
		case EDIT_TASK: 
			object = CommandSplitter.findObject(command);
			switch (field)
			{
			case NAME: return new CommandElements(type, object, field, name);
			case START_DATE:
			case END_DATE:return new CommandElements(type, object, field, date[SINGLE]);
			case START_TIME:
			case END_TIME:return new CommandElements(type, object, field, time[SINGLE]);
			default:
				throw edit_exception;
			}
		case SEARCH_TASK:
			object = CommandSplitter.findObject(command);
			CommandElements thisC = new CommandElements(type, name, date, priority, time);
			thisC.setID(object);
			return thisC;
		case DELETE_TASK:
		case FINISH_TASK:
		case UNFINISH_TASK:
		case FLAG_TASK:
		case UNFLAG_TASK:
			object = CommandSplitter.findObject(command);
			return new CommandElements(type, object);
		case UNDO:
		case REDO:
		case VIEW_COMPLETED:
		case HELP: return new CommandElements(type);
		case DIRECTORY: return new CommandElements(type, name);
		default:
			throw command_exception;
		}
	}
}
