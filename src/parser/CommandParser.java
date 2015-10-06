package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.CommandElements;
import utilities.TaskDate;

public class CommandParser {

	public CommandElements ProcessInput(String command) {
		Command_Type type;
		String description;
		TaskDate date;
		Command_Priority priority;
		Command_Field field;
		int object;
		ArrayList<String> parts = split(command);
		if (parts.get(0).equals("add")) {
			type = Command_Type.ADD_TASK;
			description = parts.get(1);
			date = dateDecoder(parts.get(2));
			priority = PriorityDecoder(parts.get(3));
			return new CommandElements(type, description, date, priority);
		} else if (parts.get(0).equals("edit")) {
			type = Command_Type.EDIT_TASK;
			object = Integer.parseInt(parts.get(1));
			if (parts.get(2).equals("name")) {
				field = Command_Field.DESCRIPTION;
				description = parts.get(3);
				return new CommandElements(type, object, field, description);
			} else if (parts.get(2).equals("date")) {
				field = Command_Field.DATE;
				date = dateDecoder(parts.get(3));
				return new CommandElements(type, object, field, date);
			} else if (parts.get(2).equals("priority")) {
				field = Command_Field.PRIORITY;
				priority = PriorityDecoder(parts.get(3));
				return new CommandElements(type, object, field, priority);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private Command_Priority PriorityDecoder(String part) {
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
	
	private ArrayList<String> split(String command) {
		String parts[] = command.split(" ");
		ArrayList<String> commandParts = (ArrayList<String>) Arrays.asList(parts);
		return commandParts;
	}
	
	private static int[] getCurrentDate() {
		Date date = new Date(); // your date
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int thisYear = cal.get(Calendar.YEAR);
	    int thisMonth = cal.get(Calendar.MONTH) + 1;
	    int thisDay = cal.get(Calendar.DAY_OF_MONTH);
	    int currentDate[] = {thisYear, thisMonth, thisDay};
	    return currentDate;
	}
	
	private static TaskDate dateDecoder(String dateStr) {
		int year, month, day;
		int thisDate[] = new int[3];
		thisDate = getCurrentDate();
		
		// support format 03/09/2015
		if (dateStr.length() == 10) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[0]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[2]);
				if (checkDate(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}
		
		// support format 03/09/15
		if (dateStr.length() == 8) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[0]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[2]) + 2000;
				if (checkDate(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}
		
		// support format 09/03
		if (dateStr.length() == 5) {
			String parts[] = dateStr.split("/");
			if (parts.length == 2){
				year = thisDate[0];
				month = Integer.parseInt(parts[0]);
				day = Integer.parseInt(parts[1]);
				if (checkDate(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}
		// support format 03/09
		if (dateStr.length() == 5) {
			String parts[] = dateStr.split("/");
			if (parts.length == 2){
				year = thisDate[0];
				month = Integer.parseInt(parts[1]);
				day = Integer.parseInt(parts[0]);
				if (checkDate(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}
		
		return new TaskDate(0, 0, 0);
	}
	
	private static int checkDate(int year, int month, int day) {
		int thisDate[] = getCurrentDate();
		if (year*10000+month*100+day < thisDate[0]*10000+thisDate[1]*100+thisDate[2]) {
			
			return -1;
		}
		if (month < 1 || month > 12) {
			return -1;
		}
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if (day < 1 || day > 31) {
				return -1;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day < 1 || day > 30) {
				return -1;
			}
		} else if (month == 2) {
			if (year % 4 == 0 && year % 100 != 0) {
				if (day < 1 || day > 29) {
					return -1;
				}
			} else {
				if (day < 1 || day > 28) {
					return -1;
				}
			}
		} else {
			return -1;
		}
		return 0;
	}
}
