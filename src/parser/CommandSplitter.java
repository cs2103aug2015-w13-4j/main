package parser;

import java.util.Calendar;
import java.util.Date;

import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.TaskDate;
import utilities.TaskTime;

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
		} else if (contain("flag", command)) {
			return Command_Type.FLAG_TASK;
		} else if (contain("unflag", command)) {
			return Command_Type.UNFLAG_TASK;
		} else  if(contain("help",command)){
			return Command_Type.HELP;
		}else if (command.toLowerCase().contains("view completed")) {
			
			return Command_Type.VIEW_COMPLETED;
		} else {
			return null;
		}
	}
	
	public static Command_Field findField(String command) {
		if (contain("name", command)) {
			return Command_Field.NAME;
		} else if (contain("startdate", command)) {
			return Command_Field.START_DATE;
		} else if (contain("enddate", command)) {
			return Command_Field.END_DATE;
		} else if (contain("startdate", command)) {
			return Command_Field.START_TIME;
		} else if (contain("enddate", command)) {
			return Command_Field.END_TIME;
		} else if (contain("priority", command)) {
			return Command_Field.PRIORITY;
		} else {
			return null;
		}
	}
	
	public static Command_Priority findPriority(String command) {
		if (contain("flag", command)) {
			return Command_Priority.FLAG;
		} else if (contain("unflag", command)) {
			return Command_Priority.UNFLAG;
		} else {
			return Command_Priority.UNFLAG;
		}
	}
	
	public static int findObject(String command) {
		String parts[] = command.split(" ");
		for (int i = 0; i < parts.length; i ++) {
			if (isInteger(parts[i])) {
				return Integer.parseInt(parts[i]);
			}
		}
		return -1;
	}
	
	private static TaskDate getCurrentDate() {
		Date date = new Date(); // your date
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int thisYear = cal.get(Calendar.YEAR);
	    int thisMonth = cal.get(Calendar.MONTH) + 1;
	    int thisDay = cal.get(Calendar.DAY_OF_MONTH);
	    return new TaskDate(thisYear, thisMonth, thisDay);
	}
	
	public static int getDayOfWeek() {
		Date date = new Date(); // your date
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int index = cal.get(Calendar.DAY_OF_WEEK);
	    if (index == 1) {
	    	return 7;
	    } else {
	    	return index - 1;
	    }
	}
	
	private static boolean isInteger(String c) {
	    try {
	        Integer.parseInt(c);
	        return true;
	    } catch (NumberFormatException nfe) {}
	    return false;
	}
	
	public static boolean dateChecker(String date) {
		if (date.length()<3) {
			return false;
		}
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
		TaskDate today = getCurrentDate();
		int todayOfWeek = getDayOfWeek();
		int dateCount = 2;
		if (contain("tomorrow", command)) {
			results[2-dateCount] = today.dayTrans(1);
			dateCount --;
		}
		if (contain("today", command)) {
			results[2-dateCount] = today.dayTrans(0);
			dateCount --;
		}
		if (contain("yesterday", command)) {
			results[2-dateCount] = today.dayTrans(-1);
			dateCount --;
		} 
		if (command.toLowerCase().contains("last monday")) {
			results[2-dateCount] = today.dayTrans(1-todayOfWeek - 7);
			dateCount --;
		}
		if (command.toLowerCase().contains("last tuesday")) {
			results[2-dateCount] = today.dayTrans(2-todayOfWeek - 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("last wednesday")) {
			results[2-dateCount] = today.dayTrans(3-todayOfWeek - 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("last thursday")) {
			results[2-dateCount] = today.dayTrans(4-todayOfWeek - 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("last friday")) {
			results[2-dateCount] = today.dayTrans(5-todayOfWeek - 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("last saturday")) {
			results[2-dateCount] = today.dayTrans(6-todayOfWeek - 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("last sunday")) {
			results[2-dateCount] = today.dayTrans(7-todayOfWeek - 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("next monday")) {
			results[2-dateCount] = today.dayTrans(1-todayOfWeek + 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("next tuesday")) {
			results[2-dateCount] = today.dayTrans(2-todayOfWeek + 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("next wednesday")) {
			results[2-dateCount] = today.dayTrans(3-todayOfWeek + 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("next thursday")) {
			results[2-dateCount] = today.dayTrans(4-todayOfWeek + 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("next friday")) {
			results[2-dateCount] = today.dayTrans(5-todayOfWeek + 7);
			dateCount --;
		} 
		if (command.toLowerCase().contains("next saturday")) {
			results[2-dateCount] = today.dayTrans(6-todayOfWeek + 7);
			dateCount --;
		}
		if (command.toLowerCase().contains("next sunday")) {
			results[2-dateCount] = today.dayTrans(7-todayOfWeek + 7);
			dateCount --;
		} 
		if (contain("monday", command)||command.toLowerCase().contains("this monday")) {
			results[2-dateCount] = today.dayTrans(1-todayOfWeek);
			dateCount --;
		} 
		if (contain("tuesday", command)||command.toLowerCase().contains("this tuesday")) {
			results[2-dateCount] = today.dayTrans(2-todayOfWeek);
			dateCount --;
		} 
		if (contain("wednesday", command)||command.toLowerCase().contains("this wednesday")) {
			results[2-dateCount] = today.dayTrans(3-todayOfWeek);
			dateCount --;
		} 
		if (contain("thursday", command)||command.toLowerCase().contains("this thursday")) {
			results[2-dateCount] = today.dayTrans(4-todayOfWeek);
			dateCount --;
		} 
		if (contain("firday", command)||command.toLowerCase().contains("this friday")) {
			results[2-dateCount] = today.dayTrans(5-todayOfWeek);
			dateCount --;
		} 
		if (contain("saturday", command)||command.toLowerCase().contains("this saturday")) {
			results[2-dateCount] = today.dayTrans(6-todayOfWeek);
			dateCount --;
		} 
		if (contain("sunday", command)||command.toLowerCase().contains("this sunday")) {
			results[2-dateCount] = today.dayTrans(7-todayOfWeek);
			dateCount --;
		}
		if (dateCount > 0) {
			String parts[] = command.split(" ");
			for (int i = 0; i < parts.length; i ++) {
				if (dateChecker(parts[i])) {
					results[2 - dateCount] = DateParser.dateDecoder(parts[i]);
					dateCount --;
				}
			}
		}
		if (dateCount == 2) {
			results[0] = new TaskDate(0,0,0);
			results[1] = new TaskDate(0,0,0);
		} else if (dateCount == 1) {
			results[1] = new TaskDate(0,0,0);
		} else {
			/*if (results[0].compareTo(results[1]) == 1) {
				TaskDate med = results[1];
				results[1] = results[0];
				results[0] = med;
			}*/
		}
		return results;
	}
	
	public static TaskDate[] extractDate(String command) {
		TaskDate myDate[] = findDate(command);
		int validDate = getValidDate(command);
		if (validDate == 2) {
			if (myDate[0].compareTo(myDate[1]) == 1) {
				TaskDate med = myDate[1];
				myDate[1] = myDate[0];
				myDate[0] = med;
			}
		}
		return myDate;
	}
	
	public static TaskTime[] extractTime(String command) {
		TaskDate myDate[] = findDate(command);
		int validDate = getValidDate(command);
		int pos = -1;
		TaskTime timeToken[] = new TaskTime[2];
		String parts[] = command.split(" ");
		int timeCount = 2;
		for (int i = 0; i < parts.length; i ++) {
			TaskTime temp;
			try {
				temp = timeCheck(parts[i]);
			} catch (NumberFormatException e) {
				temp = null;
			}
			if (temp != null) {
				timeToken[2 - timeCount] = temp;
				timeCount -= 1;
				pos = i;
			}
		}
		if (validDate == 0) {
			timeToken[0] = new TaskTime(0,0);
			timeToken[1] = new TaskTime(0,0);
		} else if (validDate == 1) {
			if (timeCount == 2) {
				timeToken[0] = new TaskTime(0,0);
				timeToken[1] = new TaskTime(0,0);
			} else if (timeCount == 1) {
				timeToken[1] = new TaskTime(0,0);
			}
		} else {
			if (timeCount == 2) {
				timeToken[0] = new TaskTime(0,0);
				timeToken[1] = new TaskTime(0,0);
			} else if (timeCount == 1) {
				String newParts[] = command.split(parts[pos]);
				if (getValidDate(newParts[0]) == 1) {
					timeToken[1] = new TaskTime(0,0);
				} else {
					timeToken[1] = timeToken[0];
					timeToken[0] = new TaskTime(0,0);
				}
			} 
		}
		
		if (validDate == 2) {
			if (myDate[0].compareTo(myDate[1]) == 1) {
				TaskTime med = timeToken[0];
				timeToken[0] = timeToken[1];
				timeToken[1] = med;
			}
		}
		return timeToken;
	}

	private static int getValidDate(String command) {
		TaskDate myDate[] = findDate(command);
		int validDate = 0;
		for (int i = 0; i < 2; i ++) {
			if (myDate[i].getYear()!=0) {
				validDate ++;
			}
		}
		return validDate;
	}
	
	public static TaskTime timeCheck(String piece) throws NumberFormatException{
		if (piece.toLowerCase().contains("am")) {
			if (piece.length() == 3) {
				int hour = Integer.parseInt(piece.charAt(0) + "");
				if (hour > 0) {
					return new TaskTime(hour, 0);
				}
			} else if (piece.length() == 4) {
				int hour = Integer.parseInt(piece.substring(0,2));
				if (hour < 12) {
					return new TaskTime(hour, 0);
				} else if (hour == 12) {
					return new TaskTime(0,0);
				}
			}
		} else if (piece.toLowerCase().contains("pm")) {
			if (piece.length() == 3) {
				int hour = Integer.parseInt(piece.charAt(0) + "");
				if (hour > 0) {
					return new TaskTime(hour + 12, 0);
				}
			} else if (piece.length() == 4) {
				int hour = Integer.parseInt(piece.substring(0,2));
				if (hour < 12) {
					return new TaskTime(hour + 12, 0);
				} else if (hour == 12) {
					return new TaskTime(12, 0);
				}
			}
		}
		return null;
	} 
	
	public static String findName(String command) {
		if (command.contains("\"")) {
			String parts[] = command.split("\"");
			return parts[1];
		} else {
			return null;
		}
	}
	
}
