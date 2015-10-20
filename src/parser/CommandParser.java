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
		TaskDate date;
		Command_Priority priority;
		Command_Field field;
		int object;
		String parts[] = command.split(" ");
		type = CommandSplitter.findType(command);
		if (type == Command_Type.ADD_TASK) {
			name = parts[1];
			date = DateParser.dateDecoder(parts[2]);
			priority = PriorityParser.PriorityDecoder(parts[3]);
			return new CommandElements(type, name, date, priority);
		} else if (type == Command_Type.EDIT_TASK) {
			object = Integer.parseInt(parts[1]);
			if (parts[2].equals("name")) {
				field = Command_Field.NAME;
				name = parts[3];
				return new CommandElements(type, object, field, name);
			} else if (parts[2].equals("start_date")) {
				field = Command_Field.START_DATE;
				date = DateParser.dateDecoder(parts[3]);
				return new CommandElements(type, object, field, date);
			} else if (parts[2].equals("end_date")) {
				field = Command_Field.END_DATE;
				date = DateParser.dateDecoder(parts[3]);
				return new CommandElements(type, object, field, date);
			} else if (parts[2].equals("priority")) {
				field = Command_Field.PRIORITY;
				priority = PriorityParser.PriorityDecoder(parts[3]);
				return new CommandElements(type, object, field, priority);
			} else {
				return null;
			}
		} else if (type == Command_Type.DELETE_TASK) {
			object = Integer.parseInt(parts[1]);
			return new CommandElements(type, object);
		} else if (type == Command_Type.FINISH_TASK) {
			object = Integer.parseInt(parts[1]);
			return new CommandElements(type, object);
		} else if (type == Command_Type.UNDO) {
			return new CommandElements(type);
		} else if (type == Command_Type.DIRECTORY) {
			name = parts[1];
			return new CommandElements(type, name);
		} else {
			return null;
		}
	}
	

}
