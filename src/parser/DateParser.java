package parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import utilities.TaskDate;

// @@author A0133965X
public class DateParser {

	private static TaskDate NULL_DATE = new TaskDate(-1,-1,-1);
	private static String SPLITTERS[] = {"-", "/", "_", "\\."};
	/**
	 * Translate date Strings in number format
	 * 
	 * @param the String containing date information
	 * @return TaskDate object
	 */
	public static TaskDate dateDecoder(String dateStr) {
		int year = -1, month=-1, day=-1;
		TaskDate thisDate = getCurrentDate();

		// support format dd/mm/yyyy
		if (dateStr.length() == 10) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[0]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[2]);
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}
		// support format d/mm/yyyy or dd/m/yyyy
		if (dateStr.length() == 9) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[0]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[2]);
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format d/m/yyyy
		if (dateStr.length() == 8) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[0]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[2]);
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format yyyy/mm/dd
		if (dateStr.length() == 10) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[2]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[0]);
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format yyyy/mm/d or yyyy/m/dd
		if (dateStr.length() == 9) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[2]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[0]);
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format yyyy/m/d
		if (dateStr.length() == 8) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[2]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[0]);
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format dd/mm/yy
		if (dateStr.length() == 8) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[0]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[2]) + 2000;
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format d/mm/yy or dd/m/yy
		if (dateStr.length() == 7) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[0]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[2]) + 2000;
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}
		
		// support format d/m/yy
		if (dateStr.length() == 6) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[0]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[2]) + 2000;
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format yy/mm/dd
		if (dateStr.length() == 8) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[2]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[0]) + 2000;
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format yy/mm/d or yy/m/dd
		if (dateStr.length() == 7) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[2]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[0]) + 2000;
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format yy/m/d
		if (dateStr.length() == 6) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3){
					day = Integer.parseInt(parts[2]);
					month = Integer.parseInt(parts[1]);
					year = Integer.parseInt(parts[0]) + 2000;
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format mm/dd
		if (dateStr.length() == 5) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 2){
					year = thisDate.getYear();
					month = Integer.parseInt(parts[0]);
					day = Integer.parseInt(parts[1]);
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format mm/d or m/dd
		if (dateStr.length() == 4) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 2){
					year = thisDate.getYear();
					month = Integer.parseInt(parts[0]);
					day = Integer.parseInt(parts[1]);
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}

		// support format m/d
		if (dateStr.length() == 3) {
			for (int i = 0; i < SPLITTERS.length; i ++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 2){
					year = thisDate.getYear();
					month = Integer.parseInt(parts[0]);
					day = Integer.parseInt(parts[1]);
					if (isPossible(year, month, day)) {
						return new TaskDate(year, month, day);
					}
				}
			}
		}
		return NULL_DATE;
	}

	/**
	 * Determine whether a date is valid
	 * 
	 * @param numerical value of year
	 * @param numerical value of month
	 * @param numerical value of day
	 * @return boolean value, valid or invalid
	 */
	private static boolean isPossible(int year, int month, int day) {

		if (month < 1 || month > 12) {
			return false;
		}
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if (day < 1 || day > 31) {
				return false;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day < 1 || day > 30) {
				return false;
			}
		} else if (month == 2) {
			if (year % 4 == 0 && year % 100 != 0) {
				if (day < 1 || day > 29) {
					return false;
				}
			} else {
				if (day < 1 || day > 28) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * Get today's date
	 * 
	 * @return today as TaskDate object
	 */
	public static TaskDate getCurrentDate() {
		Date date = new Date(); // your date
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int thisYear = cal.get(Calendar.YEAR);
	    int thisMonth = cal.get(Calendar.MONTH) + 1;
	    int thisDay = cal.get(Calendar.DAY_OF_MONTH);
	    return new TaskDate(thisYear, thisMonth, thisDay);
	}
	
	/**
	 * Determine whether a String contain date information
	 * 
	 * @param the domain String
	 * @return boolean value, contain or not
	 */
	public static boolean dateChecker(String date) {
		if (date.length()<3) {
			return false;
		}
		int num = 0;
		int cha = 0;
		for (int i = 0; i < date.length(); i ++) {
			if (CommandSplitter.isInteger(date.charAt(i) + "")) {
				num ++;
			} else if (date.charAt(i) == '.' || date.charAt(i) == '/' ||
					date.charAt(i) == '_' || date.charAt(i) == '-') {
				cha ++;
			}
		}
		if (cha + num == date.length()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Rearrange the user command, omit useless information
	 * 
	 * @param the user input String
	 * @return ArrayList of Strings containing date information
	 */
	public static ArrayList<String> decompose(String command) {
		ArrayList<String> result = new ArrayList<String>();
		String parts[] = command.toLowerCase().split(" ");
		for (int i = 0; i < parts.length; i ++) {
			switch (parts[i]) 
			{
			case "tomorrow":
			case "today": 
			case "yesterday":
			case "monday":
			case "tuesday":
			case "wednesday":
			case "thursday":
			case "friday":
			case "saturday":
			case "sunday":
				result.add(parts[i]);
				break;
			case "last":
			case "next":
				if (i <parts.length - 1) {
					switch (parts[i + 1]) 
					{
					case "monday":
					case "tuesday":
					case "wednesday":
					case "thursday":
					case "friday":
					case "saturday":
					case "sunday":
						result.add(parts[i] + " " + parts[i + 1]);
						i ++;
						break;
					default: break;
					}
				}
			default: 
				if (dateChecker(parts[i])) {
					result.add(parts[i]);
				}
				break;
			}
		} 
		return result;
	}
	
}
