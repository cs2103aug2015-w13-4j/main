package parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.TaskDate;
import utilities.TaskTime;

//@@A0133965X
public class CommandSplitter {

	private static final Integer LAST_MONDAY = -6;
	private static final Integer LAST_TUESDAY = -5;
	private static final Integer LAST_WEDNESDAY = -4;
	private static final Integer LAST_THURSDAY = -3;
	private static final Integer LAST_FRIDAY = -2;
	private static final Integer LAST_SATURDAY = -1;
	private static final Integer LAST_SUNDAY = 0;
	private static final Integer NEXT_MONDAY = 8;
	private static final Integer NEXT_TUESDAY = 9;
	private static final Integer NEXT_WEDNESDAY = 10;
	private static final Integer NEXT_THURSDAY = 11;
	private static final Integer NEXT_FRIDAY = 12;
	private static final Integer NEXT_SATURDAY = 13;
	private static final Integer NEXT_SUNDAY = 14;
	private static final Integer THIS_MONDAY = 1;
	private static final Integer THIS_TUESDAY = 2;
	private static final Integer THIS_WEDNESDAY = 3;
	private static final Integer THIS_THURSDAY = 4;
	private static final Integer THIS_FRIDAY = 5;
	private static final Integer THIS_SATURDAY = 6;
	private static final Integer THIS_SUNDAY = 7;
	private static final Integer TMR = 1;
	private static final Integer TDY = 0;
	private static final Integer YTD = -1;

	public static boolean contain(String keyword, String domain) {
		domain = domain.toLowerCase();
		int numQ = findQuo(domain);
		String parts[] = domain.split(" ");
		for (int i = 0; i < parts.length; i ++) {
			if (parts[i].equals(keyword)) {
				if (numQ == 2) {
					String newParts[] = domain.split(keyword);
					if (findQuo(newParts[0]) == 1) {
						return false;
					}
				} 
				return true;
			}
		}
		return false;
	}
	
	private static int findQuo(String domain) {
		int num = 0;
		for (int i = 0; i < domain.length(); i ++) {
			if (domain.charAt(i) == '\"') {
				num ++;
			}
		}
		return num;
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
	
	public static boolean isInteger(String c) {
	    try {
	        Integer.parseInt(c);
	        return true;
	    } catch (NumberFormatException nfe) {}
	    return false;
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
	
	public static String findName(String command) {
		if (command.contains("\"")) {
			String parts[] = command.split("\"");
			return parts[1];
		} else {
			return null;
		}
	}
	
	public static Command_Type findType(String command) {
		if (contain("add", command) || contain("a", command)) {
			return Command_Type.ADD_TASK;
		} else if (contain("edit", command) || contain("e", command)) {
			return Command_Type.EDIT_TASK;
		} else if (contain("delete", command) || contain("d", command) || contain("remove", command)) {
			return Command_Type.DELETE_TASK;
		} else if (contain("finish", command) || contain("f", command) || contain("complete", command)) {
			return Command_Type.FINISH_TASK;
		} else if(contain("unfinish",command)){
			return Command_Type.UNFINISH_TASK;
		} else if (contain("search", command) || contain("s", command)) {
			return Command_Type.SEARCH_TASK;
		} else if (contain("undo", command) || contain("u", command)) {
			return Command_Type.UNDO;
		}else if(contain("redo",command)){
			return Command_Type.REDO;
		}else if (contain("directory", command) || contain("cd", command)) {
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
		} else if (contain("starttime", command)) {
			return Command_Field.START_TIME;
		} else if (contain("endtime", command)) {
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
	
	public static TaskDate[] findDate(String command) {
		TaskDate results[] = new TaskDate[2];
		TaskDate today = DateParser.getCurrentDate();
		int todayOfWeek = getDayOfWeek();
		ArrayList<String> decomposed = DateParser.decompose(command);
		for (int i = 0; i < 2; i ++) {
			try {
				switch (decomposed.get(i)) 
				{
				case "tomorrow":
					results[i] = today.dayTrans(TMR);
					break;
				case "today": 
					results[i] = today.dayTrans(TDY);
					break;
				case "yesterday":
					results[i] = today.dayTrans(YTD);
					break;
				case "last monday":
					results[i] = today.dayTrans(LAST_MONDAY - todayOfWeek);
					break;
				case "last tuesday":
					results[i] = today.dayTrans(LAST_TUESDAY - todayOfWeek);
					break;
				case "last wednesday":
					results[i] = today.dayTrans(LAST_WEDNESDAY - todayOfWeek);
					break;
				case "last thursday":
					results[i] = today.dayTrans(LAST_THURSDAY - todayOfWeek);
					break;
				case "last friday":
					results[i] = today.dayTrans(LAST_FRIDAY - todayOfWeek);
					break;
				case "last saturday":
					results[i] = today.dayTrans(LAST_SATURDAY - todayOfWeek);
					break;
				case "last sunday":
					results[i] = today.dayTrans(LAST_SUNDAY - todayOfWeek);
					break;
				case "next monday":
					results[i] = today.dayTrans(NEXT_MONDAY - todayOfWeek);
					break;
				case "next tuesday":
					results[i] = today.dayTrans(NEXT_TUESDAY - todayOfWeek);
					break;
				case "next wednesday":
					results[i] = today.dayTrans(NEXT_WEDNESDAY - todayOfWeek);
					break;
				case "next thursday":
					results[i] = today.dayTrans(NEXT_THURSDAY - todayOfWeek);
					break;
				case "next friday":
					results[i] = today.dayTrans(NEXT_FRIDAY - todayOfWeek);
					break;
				case "next saturday":
					results[i] = today.dayTrans(NEXT_SATURDAY - todayOfWeek);
					break;
				case "next sunday":
					results[i] = today.dayTrans(NEXT_SUNDAY - todayOfWeek);
					break;
				case "monday":
					results[i] = today.dayTrans(THIS_MONDAY - todayOfWeek);
					break;
				case "tuesday":
					results[i] = today.dayTrans(THIS_TUESDAY - todayOfWeek);
					break;
				case "wednesday":
					results[i] = today.dayTrans(THIS_WEDNESDAY - todayOfWeek);
					break;
				case "thursday":
					results[i] = today.dayTrans(THIS_THURSDAY - todayOfWeek);
					break;
				case "friday":
					results[i] = today.dayTrans(THIS_FRIDAY - todayOfWeek);
					break;
				case "saturday":
					results[i] = today.dayTrans(THIS_SATURDAY - todayOfWeek);
					break;
				case "sunday":
					results[i] = today.dayTrans(THIS_SUNDAY - todayOfWeek);
					break;
				default:
					if (DateParser.dateChecker(decomposed.get(i))){
						results[i] = DateParser.dateDecoder(decomposed.get(i));
					} else {
						results[i] = new TaskDate(0,0,0);
					}
				}
			} catch (Exception e) {
				results[i] = new TaskDate(0,0,0);
			}
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
		} else if (validDate == 1) {
			myDate[1] = myDate[0];
			myDate[0] = new TaskDate(0,0,0);
		}
		return myDate;
	}
	
	public static TaskTime[] findTime(String command) {
		int validDate = getValidDate(command);
		int pos = -1;
		TaskTime timeToken[] = new TaskTime[2];
		String parts[] = command.split(" ");
		int timeCount = 2;
		for (int i = 0; i < parts.length; i ++) {
			TaskTime temp;
			try {
				temp = TimeParser.timeCheck(parts[i]);
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
			if (timeCount == 2) {
				timeToken[0] = new TaskTime(0,0);
				timeToken[1] = new TaskTime(0,0);
			} else if (timeCount == 1) {
				timeToken[1] = timeToken[0];
				timeToken[0] = new TaskTime(0,0);
			}
		} else if (validDate == 1) {
			if (timeCount == 2) {
				timeToken[0] = new TaskTime(0,0);
				timeToken[1] = new TaskTime(0,0);
			} else if (timeCount == 1) {
				timeToken[1] = timeToken[0];
				timeToken[0] = new TaskTime(0,0);
			}
		} else {
			if (timeCount == 2) {
				timeToken[0] = new TaskTime(0,0);
				timeToken[1] = new TaskTime(0,0);
			} else if (timeCount == 1) {
				String newParts[] = command.split(parts[pos]);
				if (getValidDate(newParts[0]) != 1) {
					timeToken[1] = timeToken[0];
					timeToken[0] = new TaskTime(0,0);
				} else {
					timeToken[1] = new TaskTime(0,0);
				}
			} 
		}
		return timeToken;
	}
	
	public static TaskTime[] extractTime(String command) {
		int validDate = getValidDate(command);
		TaskDate myDate[] = findDate(command);
		TaskTime timeToken[] = findTime(command);
		if (validDate == 2) {
			if (myDate[0].compareTo(myDate[1]) == 1) {
				TaskTime med = timeToken[0];
				timeToken[0] = timeToken[1];
				timeToken[1] = med;
			} else if (myDate[0].compareTo(myDate[1]) == 0) {
				if (timeToken[0].compareTo(timeToken[1]) == 1) {
					TaskTime med = timeToken[0];
					timeToken[0] = timeToken[1];
					timeToken[1] = med;
				}
			}
		}
		return timeToken;
	}
}
