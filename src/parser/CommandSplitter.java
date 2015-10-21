package parser;

import java.util.Calendar;
import java.util.Date;

import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.TaskDate;

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
			return Command_Type.DELETE_TASK;
		} else if (contain("finish", command)) {
			return Command_Type.FINISH_TASK;
		} else if (contain("search", command)) {
			return Command_Type.SEARCH_TASK;
		} else if (contain("undo", command)) {
			return Command_Type.UNDO;
		} else if (contain("directory", command)) {
			return Command_Type.DIRECTORY;
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
	
	private static boolean isInteger(String c) {
	    try {
	        Integer.parseInt(c);
	        return true;
	    } catch (NumberFormatException nfe) {}
	    return false;
	}
	
	public static boolean dateChecker(String date) {
		int num = 0;
		int cha = 0;
		for (int i = 0; i < date.length(); i ++) {
			if (isInteger(date.charAt(i) + "")) {
				num ++;
			} else if (date.charAt(i) == '.' || date.charAt(i) == '/') {
				cha ++;
			}
		}
		if (cha + num == date.length()) {
			return true;
		}
		return false;
	}
	
	public static TaskDate[] findDate(String command) {
		TaskDate results[] = new TaskDate[2];
		int dateCount = 2;
		int today[] = getCurrentDate();
		if (contain("tomorrow", command)) {
			results[2-dateCount] = new TaskDate(today[0], today[1], today[2] + 1);
			dateCount --;
		} else if (contain("today", command)) {
			results[2-dateCount] = new TaskDate(today[0], today[1], today[2]);
			dateCount --;
		} else if (contain("yesterday", command)) {
			results[2-dateCount] = new TaskDate(today[0], today[1], today[2] - 1);
			dateCount --;
		}
		String parts[] = command.split(" ");
		for (int i = 0; i < parts.length; i ++) {
			if (dateChecker(parts[i])) {
				results[2 - dateCount] = DateParser.dateDecoder(parts[i]);
				dateCount --;
			}
		}
		return results;
	}
	
}
