package parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import utilities.TaskDate;

//@@A0133965X
public class DateParser {

	public static TaskDate dateDecoder(String dateStr) {
		int year = -1, month=-1, day=-1;
		TaskDate thisDate = getCurrentDate();

		// support format 03/09/2015
		if (dateStr.length() == 10) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[0]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[2]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}
		// support format 03/09/2015
		if (dateStr.length() == 9) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[0]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[2]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 03/09/2015
		if (dateStr.length() == 8) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[0]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[2]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 2015/09/03
		if (dateStr.length() == 10) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[2]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[0]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 2015/09/03
		if (dateStr.length() == 10) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[2]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[0]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}
		// support format 2015/09/03
		if (dateStr.length() == 9) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[2]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[0]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}
		// support format 2015/09/03
		if (dateStr.length() == 8) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[2]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[0]);
				if (isPossible(year, month, day) == 0) {
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
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 03/09/15
		if (dateStr.length() == 7) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[0]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[2]) + 2000;
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}
		// support format 03/09/15
		if (dateStr.length() == 6) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[0]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[2]) + 2000;
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 15/09/03
		if (dateStr.length() == 8) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[2]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[0]) + 2000;
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 15/09/03
		if (dateStr.length() == 7) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[2]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[0]) + 2000;
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 15/09/03
		if (dateStr.length() == 6) {
			String parts[] = dateStr.split("/");
			if (parts.length == 3){
				day = Integer.parseInt(parts[2]);
				month = Integer.parseInt(parts[1]);
				year = Integer.parseInt(parts[0]) + 2000;
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 09/03
		if (dateStr.length() == 5) {
			String parts[] = dateStr.split("/");
			if (parts.length == 2){
				year = thisDate.getYear();
				month = Integer.parseInt(parts[0]);
				day = Integer.parseInt(parts[1]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 09/03
		if (dateStr.length() == 4) {
			String parts[] = dateStr.split("/");
			if (parts.length == 2){
				year = thisDate.getYear();
				month = Integer.parseInt(parts[0]);
				day = Integer.parseInt(parts[1]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 09/03
		if (dateStr.length() == 3) {
			String parts[] = dateStr.split("/");
			if (parts.length == 2){
				year = thisDate.getYear();
				month = Integer.parseInt(parts[0]);
				day = Integer.parseInt(parts[1]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}
		// support format 03/09
		if (dateStr.length() == 5) {
			String parts[] = dateStr.split("/");
			if (parts.length == 2){
				year = thisDate.getYear();
				month = Integer.parseInt(parts[1]);
				day = Integer.parseInt(parts[0]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 03/09
		if (dateStr.length() == 5) {
			String parts[] = dateStr.split("/");
			if (parts.length == 2){
				year = thisDate.getYear();
				month = Integer.parseInt(parts[1]);
				day = Integer.parseInt(parts[0]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 03/09
		if (dateStr.length() == 4) {
			String parts[] = dateStr.split("/");
			if (parts.length == 2){
				year = thisDate.getYear();
				month = Integer.parseInt(parts[1]);
				day = Integer.parseInt(parts[0]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				}
			}
		}

		// support format 03/09
		if (dateStr.length() == 3) {
			String parts[] = dateStr.split("/");
			if (parts.length == 2){
				year = thisDate.getYear();
				month = Integer.parseInt(parts[1]);
				day = Integer.parseInt(parts[0]);
				if (isPossible(year, month, day) == 0) {
					return new TaskDate(year, month, day);
				} 
			}
		}

		return new TaskDate(-1, -1, -1);
	}

	public static int isPossible(int year, int month, int day) {

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

	public static TaskDate getCurrentDate() {
		Date date = new Date(); // your date
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int thisYear = cal.get(Calendar.YEAR);
	    int thisMonth = cal.get(Calendar.MONTH) + 1;
	    int thisDay = cal.get(Calendar.DAY_OF_MONTH);
	    return new TaskDate(thisYear, thisMonth, thisDay);
	}
	
	public static boolean dateChecker(String date) {
		if (date.length()<3) {
			return false;
		}
		int num = 0;
		int cha = 0;
		for (int i = 0; i < date.length(); i ++) {
			if (CommandSplitter.isInteger(date.charAt(i) + "")) {
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
	
}
