package parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.TaskDate;
import utilities.TaskTime;

public class CommandSplitter {
	
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
	
	public static Command_Type findType(String command) {
		if (contain("add", command) || contain("a", command)) {
			return Command_Type.ADD_TASK;
		} else if (contain("edit", command) || contain("e", command)) {
			return Command_Type.EDIT_TASK;
		} else if (contain("delete", command) || contain("d", command) || contain("remove", command)) {
			return Command_Type.DELETE_TASK;
		} else if (contain("finish", command) || contain("f", command) || contain("complete", command)) {
			return Command_Type.FINISH_TASK;
		} else if (contain("search", command) || contain("s", command)) {
			return Command_Type.SEARCH_TASK;
		} else if (contain("undo", command) || contain("u", command)) {
			return Command_Type.UNDO;
		} else if (contain("directory", command) || contain("cd", command)) {
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
		ArrayList<String> decomposed = decompose(command);
		for (int i = 0; i < 2; i ++) {
			try {
				if (decomposed.get(i).equals("tomorrow")) {
					results[i] = today.dayTrans(1);
				} else if (decomposed.get(i).equals("today")) {
					results[i] = today.dayTrans(0);
				} else if (decomposed.get(i).equals("yesterday")) {
					results[i] = today.dayTrans(-1);
				} else if (decomposed.get(i).equals("last monday"))  {
					results[i] = today.dayTrans(1-todayOfWeek - 7);
				} else if (decomposed.get(i).equals("last tuesday"))  {
					results[i] = today.dayTrans(2-todayOfWeek - 7);
				} else if (decomposed.get(i).equals("last wednesday"))  {
					results[i] = today.dayTrans(3-todayOfWeek - 7);
				} else if (decomposed.get(i).equals("last thursday"))  {
					results[i] = today.dayTrans(4-todayOfWeek - 7);
				} else if (decomposed.get(i).equals("last friday"))  {
					results[i] = today.dayTrans(5-todayOfWeek - 7);
				} else if (decomposed.get(i).equals("last saturday"))  {
					results[i] = today.dayTrans(6-todayOfWeek - 7);
				} else if (decomposed.get(i).equals("last sunday"))  {
					results[i] = today.dayTrans(7-todayOfWeek - 7);
				} else if (decomposed.get(i).equals("next monday"))  {
					results[i] = today.dayTrans(1-todayOfWeek + 7);
				} else if (decomposed.get(i).equals("next tuesday"))  {
					results[i] = today.dayTrans(2-todayOfWeek + 7);
				} else if (decomposed.get(i).equals("next wednesday"))  {
					results[i] = today.dayTrans(3-todayOfWeek + 7);
				} else if (decomposed.get(i).equals("next thursday"))  {
					results[i] = today.dayTrans(4-todayOfWeek + 7);
				} else if (decomposed.get(i).equals("next friday"))  {
					results[i] = today.dayTrans(5-todayOfWeek + 7);
				} else if (decomposed.get(i).equals("next saturday"))  {
					results[i] = today.dayTrans(6-todayOfWeek + 7);
				} else if (decomposed.get(i).equals("next sunday"))  {
					results[i] = today.dayTrans(7-todayOfWeek + 7);
				} else if (decomposed.get(i).equals("monday"))  {
					results[i] = today.dayTrans(1-todayOfWeek);
				} else if (decomposed.get(i).equals("tuesday"))  {
					results[i] = today.dayTrans(2-todayOfWeek);
				} else if (decomposed.get(i).equals("wednesday"))  {
					results[i] = today.dayTrans(3-todayOfWeek);
				} else if (decomposed.get(i).equals("thursday"))  {
					results[i] = today.dayTrans(4-todayOfWeek);
				} else if (decomposed.get(i).equals("friday"))  {
					results[i] = today.dayTrans(5-todayOfWeek);
				} else if (decomposed.get(i).equals("saturday"))  {
					results[i] = today.dayTrans(6-todayOfWeek);
				} else if (decomposed.get(i).equals("sunday"))  {
					results[i] = today.dayTrans(7-todayOfWeek);
				} else if (dateChecker(decomposed.get(i))){
					results[i] = DateParser.dateDecoder(decomposed.get(i));
				} else {
					results[i] = new TaskDate(0,0,0);
				}
			} catch (Exception e) {
				results[i] = new TaskDate(0,0,0);
			}
		}
		return results;
	}
	
	public static ArrayList<String> decompose(String command) {
		ArrayList<String> result = new ArrayList<String>();
		String parts[] = command.toLowerCase().split(" ");
		for (int i = 0; i < parts.length; i ++) {
			if (parts[i].equals("tomorrow") || parts[i].equals("yesterday") ||
					parts[i].equals("today") || parts[i].equals("sunday") ||
					parts[i].equals("monday") || parts[i].equals("tuesday") ||
					parts[i].equals("wednesday") || parts[i].equals("thursday") ||
					parts[i].equals("friday") || parts[i].equals("saturday")) {
				result.add(parts[i]);
			} else if (parts[i].equals("last") || parts[i].equals("next")) {
				if (i <parts.length - 1) {
					if (parts[i + 1].equals("monday") || parts[i + 1].equals("tuesday") ||
							parts[i + 1].equals("wednesday") || parts[i + 1].equals("thursday") ||
							parts[i + 1].equals("friday") || parts[i + 1].equals("saturday") ||
							parts[i + 1].equals("sunday")) {
						result.add(parts[i] + " " + parts[i + 1]);
						i ++;
					}
				}
			} else {
				if (dateChecker(parts[i])) {
					result.add(parts[i]);
				}
			}
		} 
		return result;
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
		if (piece.toLowerCase().contains("am") && !piece.toLowerCase().contains(":")) {
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
		} else if (piece.toLowerCase().contains("pm") && !piece.toLowerCase().contains(":")) {
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
		} else if (piece.toLowerCase().contains(":")) {
			int pos = piece.indexOf(":");
			if (pos == 1) {
				int hour = Integer.parseInt(piece.charAt(0) + "");
				if (piece.length() == 4) {
					int minute = Integer.parseInt(piece.substring(2,4));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 3) {
					int minute = Integer.parseInt(piece.substring(2,3));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 6) {
					if (piece.toLowerCase().substring(4,6).equals("pm")) {
						if (hour != 12) {
							hour += 12;
						}
					}
					int minute = Integer.parseInt(piece.substring(2,4));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 5) {
					if (piece.toLowerCase().substring(3,5).equals("pm")) {
						if (hour != 12) {
							hour += 12;
						}
					}
					int minute = Integer.parseInt(piece.substring(2,3));
					return new TaskTime(hour, minute);
				}
			} else if (pos == 2) {
				int hour = Integer.parseInt(piece.substring(0,2));
				if (piece.length() == 5) {
					int minute = Integer.parseInt(piece.substring(3,5));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 4) {
					int minute = Integer.parseInt(piece.substring(3,4));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 7) {
					if (piece.toLowerCase().substring(5,7).equals("pm")) {
						if (hour != 12) {
							hour += 12;
						}
					}
					int minute = Integer.parseInt(piece.substring(3,5));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 6) {
					if (piece.toLowerCase().substring(4,6).equals("pm")) {
						if (hour != 12) {
							hour += 12;
						}
					}
					int minute = Integer.parseInt(piece.substring(3,4));
					return new TaskTime(hour, minute);
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
