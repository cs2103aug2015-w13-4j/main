package parser;

import utilities.Command_Priority;
import utilities.Command_Type;

public class CommandSplitter {
	
	private static boolean contain(String keyword, String domain) {
		domain = domain.toLowerCase();
		String parts[] = domain.split(" ");
		for (int i = 0; i < parts.length; i ++) {
			if (parts[i].equals(keyword)) {
				return true;
			}
		}
		return false;
	}
	
	public static Command_Type findType(String command) {
		if (contain("add", command)) {
			return Command_Type.ADD_TASK;
		} else if (contain("edit", command)) {
			return Command_Type.EDIT_TASK;
		} else if (contain("delete", command)) {
			return Command_Type.EDIT_TASK;
		} else if (contain("finish", command)) {
			return Command_Type.EDIT_TASK;
		} else if (contain("search", command)) {
			return Command_Type.EDIT_TASK;
		} else if (contain("undo", command)) {
			return Command_Type.EDIT_TASK;
		} else if (contain("directory", command)) {
			return Command_Type.EDIT_TASK;
		} else {
			return null;
		}
	}
	
	public static Command_Priority findPriority(String command) {
		if (contain("high", command)) {
			return Command_Priority.HIGH;
		} else if (contain("medium", command)) {
			return Command_Priority.MEDIUM;
		} else if (contain("low", command)) {
			return Command_Priority.LOW;
		} else {
			return null;
		}
	}
	
}
