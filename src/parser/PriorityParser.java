package parser;

import utilities.Command_Priority;

public class PriorityParser {

	public static Command_Priority PriorityDecoder(String part) {
		Command_Priority priority;
		if (part.equals("high")) {
			priority = Command_Priority.HIGH;
		} else if (part.equals("medium")) {
			priority = Command_Priority.MEDIUM;
		} else {
			priority = Command_Priority.LOW;
		}
		return priority;
	}
}
