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

	public static CommandElements ProcessInput(String command) throws Exception {
		CommandNotFound command_exception = new CommandNotFound("command not found");
		EditFieldNotFound edit_exception = new EditFieldNotFound("edit field not found");
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
		if (type == Command_Type.ADD_TASK) {
			return new CommandElements(type, name, date, priority, time);
		} else if (type == Command_Type.EDIT_TASK) {
			object = CommandSplitter.findObject(command);
			if (field == Command_Field.NAME) {
				return new CommandElements(type, object, field, name);
			} else if (field == Command_Field.START_DATE) {
				return new CommandElements(type, object, field, date[0]);
			} else if (field == Command_Field.END_DATE) {
				return new CommandElements(type, object, field, date[0]);
			} else if (field == Command_Field.PRIORITY) {
				return new CommandElements(type, object, field, priority);
			} else if (field == Command_Field.START_TIME) {
				return new CommandElements(type, object, field, priority);
			} else if (field == Command_Field.END_TIME) {
				return new CommandElements(type, object, field, priority);
			} else {
				throw edit_exception;
			}
		} else if (type == Command_Type.DELETE_TASK) {
			object = CommandSplitter.findObject(command);
			return new CommandElements(type, object);
		} else if (type == Command_Type.SEARCH_TASK) {
			object = CommandSplitter.findObject(command);
			CommandElements thisC = new CommandElements(type, name, date, priority, time);
			thisC.setID(object);
			return thisC;
		} else if (type == Command_Type.FINISH_TASK) {
			object = CommandSplitter.findObject(command);
			return new CommandElements(type, object);
		} else if (type == Command_Type.UNDO) {
			return new CommandElements(type);
		} else if (type == Command_Type.REDO) {
			return new CommandElements(type);
		} else if (type == Command_Type.DIRECTORY) {
			return new CommandElements(type, name);
		} else if (type == Command_Type.VIEW_COMPLETED) {
			return new CommandElements(type);
		} else if (type == Command_Type.FLAG_TASK) {
			object = CommandSplitter.findObject(command);
			return new CommandElements(type, object);
		} else if (type == Command_Type.UNFLAG_TASK) {
			object = CommandSplitter.findObject(command);
			return new CommandElements(type, object);
		} else if(type == Command_Type.HELP){
			return new CommandElements(type);
		} else {
			throw command_exception;
		}
	}
}
