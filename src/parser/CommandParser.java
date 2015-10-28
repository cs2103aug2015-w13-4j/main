package parser;

import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.CommandElements;
import utilities.TaskDate;

public class CommandParser {

	public static CommandElements ProcessInput(String command) {
		Command_Type type;
		String name;
		TaskDate date[] = new TaskDate[2];
		Command_Priority priority;
		Command_Field field;
		int object;
		type = CommandSplitter.findType(command);
		name = CommandSplitter.findName(command);
		date = CommandSplitter.findDate(command);
		priority = CommandSplitter.findPriority(command);
		field = CommandSplitter.findField(command);
		if (type == Command_Type.ADD_TASK) {
			return new CommandElements(type, name, date, priority);
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
			} else {
				return null;
			}
		} else if (type == Command_Type.DELETE_TASK) {
			object = CommandSplitter.findObject(command);
			return new CommandElements(type, object);
		} else if (type == Command_Type.SEARCH_TASK) {
			object = CommandSplitter.findObject(command);
			return new CommandElements(type, name, date, priority);
		} else if (type == Command_Type.FINISH_TASK) {
			object = CommandSplitter.findObject(command);
			return new CommandElements(type, object);
		} else if (type == Command_Type.UNDO) {
			return new CommandElements(type);
		} else if (type == Command_Type.DIRECTORY) {
			return new CommandElements(type, name);
		} else {
			return null;
		}
	}
	

}
