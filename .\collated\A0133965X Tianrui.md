# A0133965X Tianrui
###### ./src/parser/CommandParser.java
``` java
/**
 * This class is process the users input into CommandElements for easy usage in
 * other component
 * 
 * @author Tianrui
 *
 */
public class CommandParser {
	// ================================================================
	// CONSTANT STRING
	// ================================================================
	private static final String CMD_NOT_FOUND = "command not found";
	private static final String EDIT_NOT_FOUND = "edit field not found";
	private static final Integer SINGLE = 1;

	// ================================================================
	// METHOD
	// ================================================================
	/**
	 * Translate the user input
	 * 
	 * @param the
	 *            String command user enters
	 * @return processed information of the user command
	 */
	public static CommandElements ProcessInput(String command) throws CommandNotFound, EditFieldNotFound {
		CommandNotFound command_exception = new CommandNotFound(CMD_NOT_FOUND);
		EditFieldNotFound edit_exception = new EditFieldNotFound(EDIT_NOT_FOUND);
		Command_Type type;
		String name;
		TaskDate date[] = new TaskDate[2];
		TaskTime time[] = new TaskTime[2];
		Command_Priority priority;
		Command_Field field;
		int object;
		type = CommandSplitter.findType(command);
		name = CommandSplitter.findName(command);
		date = CommandSplitter.extractDate(command);
		time = CommandSplitter.extractTime(command);
		priority = CommandSplitter.findPriority(command);
		field = CommandSplitter.findField(command);
		switch (type) {
		case ADD_TASK:
			return new CommandElements(type, name, date, priority, time);
		case EDIT_TASK:
			object = CommandSplitter.findObject(command);
			switch (field) {
			case NAME:
				return new CommandElements(type, object, field, name);
			case START_DATE:
			case END_DATE:
				return new CommandElements(type, object, field, date[SINGLE]);
			case START_TIME:
			case END_TIME:
				return new CommandElements(type, object, field, time[SINGLE]);
			default:
				throw edit_exception;
			}
		case SEARCH_TASK:
			object = CommandSplitter.findObject(command);
			CommandElements thisC = new CommandElements(type, name, date, priority, time);
			thisC.setID(object);
			return thisC;
		case DELETE_TASK:
		case FINISH_TASK:
		case UNFINISH_TASK:
		case FLAG_TASK:
		case UNFLAG_TASK:
			object = CommandSplitter.findObject(command);
			return new CommandElements(type, object);
		case UNDO:
		case REDO:
		case VIEW_COMPLETED:
		case HELP:
			return new CommandElements(type);
		case DIRECTORY:
			return new CommandElements(type, name);
		default:
			throw command_exception;
		}
	}
}
```
###### ./src/parser/CommandSplitter.java
``` java
/**
 * This class is to find each command field for CommandParser class
 * 
 * @author Tianrui
 *
 */
public class CommandSplitter {

	// ================================================================
	// CONTANT INTEGER
	// ================================================================
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
	private static final TaskDate NULL_DATE = new TaskDate(0, 0, 0);
	private static final TaskTime NULL_TIME = new TaskTime(0, 0);
	private static final Integer NULL_OBJECT = -1;

	// ================================================================
	// METHODS
	// ================================================================
	/**
	 * determine whether a keyword exists in a String
	 * 
	 * @param the
	 *            keyword to look for
	 * @param the
	 *            domain String
	 * @return boolean value exist or not
	 */
	public static boolean contain(String keyword, String domain) {
		domain = domain.toLowerCase();
		int numQ = findQuo(domain);
		String parts[] = domain.split(" ");
		for (int i = 0; i < parts.length; i++) {
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

	/**
	 * Find the number of quotation marks in a String
	 * 
	 * @param the
	 *            domain String
	 * @return number of quotation marks
	 */
	private static int findQuo(String domain) {
		int num = 0;
		for (int i = 0; i < domain.length(); i++) {
			if (domain.charAt(i) == '\"') {
				num++;
			}
		}
		return num;
	}

	/**
	 * Find out which day today is in a week
	 * 
	 * @return numerical value of today in a week
	 */
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

	/**
	 * Determine whether a String is in Integer form
	 * 
	 * @param the
	 *            String to check
	 * @return boolean value yes or no
	 */
	public static boolean isInteger(String c) {
		try {
			Integer.parseInt(c);
			return true;
		} catch (NumberFormatException nfe) {
		}
		return false;
	}

	/**
	 * Find out how many valid dates are specified in a command
	 * 
	 * @param the
	 *            user input command
	 * @return number of valid dates
	 */
	private static int getValidDate(String command) {
		TaskDate myDate[] = findDate(command);
		int validDate = 0;
		for (int i = 0; i < 2; i++) {
			if (myDate[i].getYear() != 0) {
				validDate++;
			}
		}
		return validDate;
	}

	/**
	 * Find out how many valid times are specified in a command
	 * 
	 * @param the
	 *            user input command
	 * @return number of valid times
	 */
	private static int getValidTime(String command) {
		TaskTime myTime[] = findTime(command);
		int validTime = 0;
		for (int i = 0; i < 2; i++) {
			if (myTime[i].getHour() != 0 || myTime[i].getMinute() != 0) {
				validTime++;
			}
		}
		return validTime;
	}

	/**
	 * Find the task name in the user input String
	 * 
	 * @param the
	 *            user input command
	 * @return the task name or null if there isn't any
	 */
	public static String findName(String command) {
		if (command.contains("\"")) {
			String parts[] = command.split("\"");
			return parts[1];
		} else {
			return null;
		}
	}

	/**
	 * Determine the type of this command
	 * 
	 * @param the
	 *            user input command
	 * @return the type of command
	 */
	public static Command_Type findType(String command) {
		if (contain("add", command) || contain("a", command)) {
			return Command_Type.ADD_TASK;
		} else if (contain("edit", command) || contain("e", command)) {
			return Command_Type.EDIT_TASK;
		} else if (contain("delete", command) || contain("d", command) || contain("remove", command)) {
			return Command_Type.DELETE_TASK;
		} else if (contain("finish", command) || contain("f", command) || contain("complete", command)) {
			return Command_Type.FINISH_TASK;
		} else if (contain("unfinish", command)) {
			return Command_Type.UNFINISH_TASK;
		} else if (contain("search", command) || contain("s", command)) {
			return Command_Type.SEARCH_TASK;
		} else if (contain("undo", command) || contain("u", command)) {
			return Command_Type.UNDO;
		} else if (contain("redo", command)) {
			return Command_Type.REDO;
		} else if (contain("directory", command) || contain("cd", command)) {
			return Command_Type.DIRECTORY;
		} else if (contain("flag", command)) {
			return Command_Type.FLAG_TASK;
		} else if (contain("unflag", command)) {
			return Command_Type.UNFLAG_TASK;
		} else if (contain("help", command)) {
			return Command_Type.HELP;
		} else if (command.toLowerCase().contains("view completed") || contain("vc", command)) {
			return Command_Type.VIEW_COMPLETED;
		} else {
			return null;
		}
	}

	/**
	 * Find the user specified edit field in the command if any
	 * 
	 * @param the
	 *            user input command
	 * @return the edit field
	 */
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

	/**
	 * Find the priority information in the command if any
	 * 
	 * @param the
	 *            user input command
	 * @return the priority information
	 */
	public static Command_Priority findPriority(String command) {
		if (contain("flag", command)) {
			return Command_Priority.FLAG;
		} else if (contain("unflag", command)) {
			return Command_Priority.UNFLAG;
		} else {
			if (contain("search", command)) {
				return Command_Priority.UNDEFINED;
			}
			return Command_Priority.UNFLAG;
		}
	}

	/**
	 * Find the user specified operation object if any
	 * 
	 * @param the
	 *            user input command
	 * @return the object or -1 if none
	 */
	public static int findObject(String command) {
		String parts[] = command.split(" ");
		for (int i = 0; i < parts.length; i++) {
			if (isInteger(parts[i])) {
				return Integer.parseInt(parts[i]);
			}
		}
		return NULL_OBJECT;
	}

	/**
	 * Find the dates specified in the command
	 * 
	 * @param the
	 *            user input command
	 * @return array of TaskDate objects
	 */
	public static TaskDate[] findDate(String command) {
		TaskDate results[] = new TaskDate[2];
		TaskDate today = DateParser.getCurrentDate();
		int todayOfWeek = getDayOfWeek();
		ArrayList<String> decomposed = DateParser.decompose(command);
		for (int i = 0; i < 2; i++) {
			try {
				switch (decomposed.get(i)) {
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
					if (DateParser.dateChecker(decomposed.get(i))) {
						results[i] = DateParser.dateDecoder(decomposed.get(i));
					} else {
						results[i] = NULL_DATE;
					}
				}
			} catch (Exception e) {
				results[i] = NULL_DATE;
			}
		}
		return results;
	}

	/**
	 * Adjust the dates to the correct ordering
	 * 
	 * @param the
	 *            user input command
	 * @return array of TaskDate objects
	 */
	public static TaskDate[] extractDate(String command) {
		TaskDate myDate[] = findDate(command);
		TaskDate today = DateParser.getCurrentDate();
		int validTime = getValidTime(command);
		int validDate = getValidDate(command);
		if (validDate == 2) {
			if (myDate[0].compareTo(myDate[1]) == 1) {
				TaskDate med = myDate[1];
				myDate[1] = myDate[0];
				myDate[0] = med;
			}
		} else if (validDate == 1) {
			if (validTime == 2) {
				if (today.compareTo(myDate[0]) != 1) {
					myDate[1] = myDate[0];
					myDate[0] = today;
				} else {
					myDate[1] = today;
				}
			} else {
				myDate[1] = myDate[0];
				myDate[0] = NULL_DATE;
			}
		} else if (validDate == 0) {
			System.out.println("no date" + validTime);
			if (validTime == 1) {
				myDate[1] = today;
				myDate[0] = NULL_DATE;
			} else if (validTime == 2) {
				myDate[0] = today;
				myDate[1] = today;
			}
		}
		return myDate;
	}

	/**
	 * Find the times specified in the command
	 * 
	 * @param the
	 *            user input command
	 * @return array of TaskTime objects
	 */
	public static TaskTime[] findTime(String command) {
		int validDate = getValidDate(command);
		int pos = -1;
		TaskTime timeToken[] = new TaskTime[2];
		String parts[] = command.split(" ");
		int timeCount = 2;
		for (int i = 0; i < parts.length; i++) {
			TaskTime temp;
			try {
				temp = TimeParser.timeDecoder(parts[i]);
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
				timeToken[0] = NULL_TIME;
				timeToken[1] = NULL_TIME;
			} else if (timeCount == 1) {
				timeToken[1] = timeToken[0];
				timeToken[0] = NULL_TIME;
			}
		} else if (validDate == 1) {
			if (timeCount == 2) {
				timeToken[0] = NULL_TIME;
				timeToken[1] = NULL_TIME;
			} else if (timeCount == 1) {
				timeToken[1] = timeToken[0];
				timeToken[0] = NULL_TIME;
			}
		} else {
			if (timeCount == 2) {
				timeToken[0] = NULL_TIME;
				timeToken[1] = NULL_TIME;
			} else if (timeCount == 1) {
				String newParts[] = command.split(parts[pos]);
				if (getValidDate(newParts[0]) != 1) {
					timeToken[1] = timeToken[0];
					timeToken[0] = NULL_TIME;
				} else {
					timeToken[1] = NULL_TIME;
				}
			}
		}
		return timeToken;
	}

	/**
	 * Adjust the times to the correct ordering
	 * 
	 * @param the
	 *            user input command
	 * @return array of TaskTime objects
	 */
	public static TaskTime[] extractTime(String command) {
		int validDate = getValidDate(command);
		int validTime = getValidTime(command);
		TaskDate today = DateParser.getCurrentDate();
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
		} else if (validDate == 1) {
			if (validTime == 2) {
				if (today.compareTo(myDate[0]) == 1) {
					TaskTime med = timeToken[0];
					timeToken[0] = timeToken[1];
					timeToken[1] = med;
				}
			} else {
				myDate[1] = myDate[0];
				myDate[0] = NULL_DATE;
			}
		} else if (validDate == 0) {
			if (timeToken[0].compareTo(timeToken[1]) == 1) {
				TaskTime med = timeToken[0];
				timeToken[0] = timeToken[1];
				timeToken[1] = med;
			}
		}
		return timeToken;
	}
}
```
###### ./src/parser/CompleteParserTester.java
``` java
/**
 * Junit testing for parser class
 * 
 * @author Tianrui
 *
 */
public class CompleteParserTester {
	Scanner sc = new Scanner(System.in);

	@Test
	public void test() {
		while (true) {
			String test = sc.nextLine();
			CommandElements result;
			try {
				result = CommandParser.ProcessInput(test);
				result.debugPrint();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
```
###### ./src/parser/DateParser.java
``` java
/**
 * This class is to find date from user input
 * 
 * @author Tianrui
 *
 */
public class DateParser {
	// ================================================================
	// CONSTANTS
	// ================================================================
	private static TaskDate NULL_DATE = new TaskDate(-1, -1, -1);
	private static String SPLITTERS[] = { "-", "/", "_", "\\." };

	// ================================================================
	// METHODS
	// ================================================================
	/**
	 * Translate date Strings in number format
	 * 
	 * @param the
	 *            String containing date information
	 * @return TaskDate object
	 */
	public static TaskDate dateDecoder(String dateStr) {
		int year = -1, month = -1, day = -1;
		TaskDate thisDate = getCurrentDate();

		// support format dd/mm/yyyy
		if (dateStr.length() == 10) {
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 3) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 2) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 2) {
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
			for (int i = 0; i < SPLITTERS.length; i++) {
				String parts[] = dateStr.split(SPLITTERS[i]);
				if (parts.length == 2) {
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
	 * @param numerical
	 *            value of year
	 * @param numerical
	 *            value of month
	 * @param numerical
	 *            value of day
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
	 * @param the
	 *            domain String
	 * @return boolean value, contain or not
	 */
	public static boolean dateChecker(String date) {
		if (date.length() < 3) {
			return false;
		}
		int num = 0;
		int cha = 0;
		for (int i = 0; i < date.length(); i++) {
			if (CommandSplitter.isInteger(date.charAt(i) + "")) {
				num++;
			} else if (date.charAt(i) == '.' || date.charAt(i) == '/' || date.charAt(i) == '_'
					|| date.charAt(i) == '-') {
				cha++;
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
	 * @param the
	 *            user input String
	 * @return ArrayList of Strings containing date information
	 */
	public static ArrayList<String> decompose(String command) {
		ArrayList<String> result = new ArrayList<String>();
		String parts[] = command.toLowerCase().split(" ");
		for (int i = 0; i < parts.length; i++) {
			switch (parts[i]) {
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
				if (i < parts.length - 1) {
					switch (parts[i + 1]) {
					case "monday":
					case "tuesday":
					case "wednesday":
					case "thursday":
					case "friday":
					case "saturday":
					case "sunday":
						result.add(parts[i] + " " + parts[i + 1]);
						i++;
						break;
					default:
						break;
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
```
###### ./src/parser/ParserGeneralTest.java
``` java
/**
 * Junit testing for parser
 * 
 * @author Tianrui
 *
 */
public class ParserGeneralTest {

	@Test
	public void testCase0() throws Exception {
		String input = "add \"the test\" 11/12/2015 to 1/2/2016";
		String expectedName = "the test";
		String expectedStartDate = "11/12/2015";
		String expectedEndDate = "01/02/2016";
		Command_Priority expectedPriority = Command_Priority.UNFLAG;
		Command_Type expectedType = Command_Type.ADD_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedName, actual.getName());
		assertEquals(expectedStartDate, actual.getStartDate().printDate());
		assertEquals(expectedEndDate, actual.getEndDate().printDate());
		assertEquals(expectedPriority, actual.getPriority());
		assertEquals(expectedType, actual.getType());
	}

	@Test
	public void testCase1() throws Exception {
		String input = "edit 1 startdate 1/2/2016";
		int expectedObject = 1;
		Command_Field expectedField = Command_Field.START_DATE;
		String expectedStartDate = "01/02/2016";
		Command_Type expectedType = Command_Type.EDIT_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedObject, actual.getID());
		assertEquals(expectedStartDate, actual.getStartDate().printDate());
		assertEquals(expectedField, actual.getField());
		assertEquals(expectedType, actual.getType());
	}

	@Test
	public void testCase2() throws Exception {
		String input = "delete 2";
		int expectedObject = 2;
		Command_Type expectedType = Command_Type.DELETE_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedObject, actual.getID());
		assertEquals(expectedType, actual.getType());
	}

	@Test
	public void testCase3() throws Exception {
		String input = "search 1/2/16";
		String expectedEndDate = "01/02/2016";
		Command_Type expectedType = Command_Type.SEARCH_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedEndDate, actual.getEndDate().printDate());
		assertEquals(expectedType, actual.getType());
	}

	@Test
	public void testCase4() throws Exception {
		String input = "flag 1";
		int expectedObject = 1;
		Command_Type expectedType = Command_Type.FLAG_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
		assertEquals(expectedObject, actual.getID());
	}

	@Test
	public void testCase5() throws Exception {
		String input = "undo";
		Command_Type expectedType = Command_Type.UNDO;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
	}

	@Test
	public void testCase6() throws Exception {
		String input = "unflag 1";
		int expectedObject = 1;
		Command_Type expectedType = Command_Type.UNFLAG_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
		assertEquals(expectedObject, actual.getID());
	}

	@Test
	public void testCase7() throws Exception {
		String input = "redo";
		Command_Type expectedType = Command_Type.REDO;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
	}

	@Test
	public void testCase8() throws Exception {
		String input = "finish 1";
		int expectedObject = 1;
		Command_Type expectedType = Command_Type.FINISH_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
		assertEquals(expectedObject, actual.getID());
	}

	@Test
	public void testCase9() throws Exception {
		String input = "unfinish 1";
		int expectedObject = 1;
		Command_Type expectedType = Command_Type.UNFINISH_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
		assertEquals(expectedObject, actual.getID());
	}

	@Test
	public void testCase10() throws Exception {
		String input = "cd \"User/xxx/Downloads\"";
		String expectedDir = "User/xxx/Downloads";
		Command_Type expectedType = Command_Type.DIRECTORY;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedDir, actual.getName());
		assertEquals(expectedType, actual.getType());
	}

}
```
###### ./src/parser/ParserMethodTest.java
``` java
/**
 * Junit testing for each parser method
 * 
 * @author Tianrui
 *
 */
public class ParserMethodTest {

	@Test
	public void FindNameTest() {
		String input = "add \"drink coffee\" tomorrow";
		String expected = "drink coffee";
		String actual = CommandSplitter.findName(input);
		assertEquals(expected, actual);
	}

	@Test
	public void FindTypeTest() {
		String input = "tomorrow add \"study\" until next friday";
		Command_Type expected = Command_Type.ADD_TASK;
		Command_Type actual = CommandSplitter.findType(input);
		assertEquals(expected, actual);
	}

	@Test
	public void FindFieldTest() {
		String input = "2 enddate edit to tomorrow";
		Command_Field expected = Command_Field.END_DATE;
		Command_Field actual = CommandSplitter.findField(input);
		assertEquals(expected, actual);
	}

	@Test
	public void ExtactDateTest() {
		String input = "11/12/2015 blah blah 3.9";
		TaskDate expectedEndDate = new TaskDate(2015, 12, 11);
		TaskDate expectedStartDate = new TaskDate(2015, 3, 9);
		TaskDate actual[] = CommandSplitter.extractDate(input);
		assertEquals(expectedStartDate.printDate(), actual[0].printDate());
		assertEquals(expectedEndDate.printDate(), actual[1].printDate());
	}

	@Test
	public void ExtactTimeTest() {
		String input = "tomorrow 5am today 17:33";
		TaskTime expectedFirstTime = new TaskTime(17, 33);
		TaskTime expectedSecondTime = new TaskTime(5, 0);
		TaskTime actual[] = CommandSplitter.extractTime(input);
		assertEquals(expectedFirstTime.toString(), actual[0].toString());
		assertEquals(expectedSecondTime.toString(), actual[1].toString());
	}
}
```
###### ./src/parser/TimeAndDateParserTester.java
``` java
/**
 * Junit testing for parsing time and date
 * 
 * @author Tianrui
 *
 */
public class TimeAndDateParserTester {
	Scanner sc = new Scanner(System.in);

	@Test
	public void test() {
		while (true) {
			String test = sc.nextLine();
			TaskTime[] result = CommandSplitter.extractTime(test);
			TaskDate[] rd = CommandSplitter.extractDate(test);
			System.out.println(rd[0].toString() + "  " + result[0].toString());
			System.out.println(rd[1].toString() + "  " + result[1].toString());
			System.out.println("");
		}
	}
}
```
###### ./src/utilities/Command_Type.java
``` java
/**
 * enum for all possible commands
 * 
 * @author Tianrui
 *
 */
public enum Command_Type {
	ADD_TASK, EDIT_TASK, DELETE_TASK, FINISH_TASK, UNFINISH_TASK, SEARCH_TASK, UNDO, REDO, DIRECTORY, NOT_FOUND, VIEW_COMPLETED, HELP, FLAG_TASK, UNFLAG_TASK
};
```
###### ./src/utilities/CommandElements.java
``` java
	/**
	 * Edit name
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 *            task id
	 * @param fld
	 *            field to edit
	 * @param nm
	 *            name to edit to
	 */
	public CommandElements(Command_Type cmd, int id, Command_Field fld, String nm) {
		this.type = cmd;
		this.id = id;
		this.field = fld;
		this.name = nm;
	}

	/**
	 * Edit priority
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 *            tasks id
	 * @param fld
	 *            field to edit
	 * @param prio
	 *            priority to edit to
	 */
	public CommandElements(Command_Type cmd, int id, Command_Field fld, Command_Priority prio) {
		this.type = cmd;
		this.id = id;
		this.field = fld;
		this.priority = prio;
	}

	/**
	 * for commands that takes in id only(delete, finish, flag..)
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 */
	public CommandElements(Command_Type cmd, int id) {
		this.type = cmd;
		this.id = id;
	}

	/**
	 * for command that uses only command like view completed
	 * 
	 * @param cmd
	 *            command user input
	 */
	public CommandElements(Command_Type cmd) {
		this.id = -1;
		this.type = cmd;
	}

	/**
	 * changing of directory
	 * 
	 * @param cmd
	 *            command user input
	 * @param directory
	 *            location to change to
	 */
	public CommandElements(Command_Type cmd, String directory) {
		this.id = -1;
		this.type = cmd;
		this.name = directory;
	}

	// ================================================================
	// GETTERS
	// ================================================================
	public Command_Type getType() {
		return type;
	}

	public int getID() {
		return id;
	}

	public void setID(int o) {
		id = o;
	}

	public String getName() {
		return name;
	}

	public TaskDate getStartDate() {
		return date[0];
	}

	public TaskDate getEndDate() {
		return date[1];
	}

	public TaskTime getStartTime() {
		return time[0];
	}

	public TaskTime getEndTime() {
		return time[1];
	}

	public Command_Priority getPriority() {
		return priority;
	}

	public Command_Field getField() {
		return field;
	}

	// ================================================================
	// PRINT
	// ================================================================
	public void debugPrint() {
		System.out.println(type.toString());
		System.out.println(name);
		System.out.println(id);
		if (date[0] != null && time[0] != null)
			System.out.println(date[0].printDate() + " " + time[0].toString());
		if (date[1] != null && time[1] != null)
			System.out.println(date[1].printDate() + " " + time[1].toString());
		if (priority != null)
			System.out.println(priority.toString());
		if (field == null) {
			System.out.println("no field");
		} else {
			System.out.println(field.toString());
		}
	}
}
```
###### ./src/utilities/TaskDate.java
``` java
	public void setYear(int year) {
		myYear = year;
	}

	public void setMonth(int month) {
		myMonth = month;
	}

	public void setDay(int day) {
		myDay = day;
	}

	// ================================================================
	// GETTERS
	// ================================================================
	public int getYear() {
		return myYear;
	}

	public int getMonth() {
		return myMonth;
	}

	public int getDay() {
		return myDay;
	}

	// ================================================================
	// METHODS
	// ================================================================
	public String toString() {
		if (myYear == -1) {
			return "null";
		}
		return myDay + "/" + myMonth + "/" + myYear;
	}

	public String printDate() {
		if (myDay < 10 && myMonth < 10) {
			return "0" + Integer.toString(myDay) + "/" + "0" + Integer.toString(myMonth) + "/"
					+ Integer.toString(myYear);
		} else if (myDay >= 10 && myMonth < 10) {
			return Integer.toString(myDay) + "/" + "0" + Integer.toString(myMonth) + "/" + Integer.toString(myYear);
		} else if (myDay < 10 && myMonth >= 10) {
			return "0" + Integer.toString(myDay) + "/" + Integer.toString(myMonth) + "/" + Integer.toString(myYear);
		} else {
			return Integer.toString(myDay) + "/" + Integer.toString(myMonth) + "/" + Integer.toString(myYear);
		}
	}

	public TaskDate dayTrans(int days) {
		int iniYear = myYear;
		int iniMonth = myMonth;
		int iniDay = myDay;
		int absoluteDay = iniDay + days;
		while (true) {
			if (absoluteDay > 0 && absoluteDay <= getDaysInMonth(iniMonth, iniYear) && iniMonth > 0 && iniMonth < 13) {
				break;
			}
			if (absoluteDay < 0) {
				if (iniMonth > 1) {
					iniMonth -= 1;
					absoluteDay += getDaysInMonth(iniMonth, iniYear);
				} else {
					iniMonth = 12;
					iniYear -= 1;
					absoluteDay += getDaysInMonth(iniMonth, iniYear);
				}
			} else if (absoluteDay > 0) {
				if (iniMonth < 12) {
					absoluteDay -= getDaysInMonth(iniMonth, iniYear);
					iniMonth += 1;
				} else {
					absoluteDay -= getDaysInMonth(iniMonth, iniYear);
					iniMonth = 1;
					iniYear += 1;
				}
			} else {
				if (days > 0) {
					absoluteDay = 1;
				} else {
					if (iniMonth > 1) {
						iniMonth -= 1;
						absoluteDay += getDaysInMonth(iniMonth, iniYear);
					} else {
						iniMonth = 12;
						iniYear -= 1;
						absoluteDay += getDaysInMonth(iniMonth, iniYear);
					}
				}
			}
		}
		return new TaskDate(iniYear, iniMonth, absoluteDay);
	}

	public int getDaysInMonth(int month, int year) {
		switch (month) {
		case 1:
			return 31;
		case 2:
			if (year % 4 == 0 && year % 100 != 0) {
				return 29;
			} else {
				return 28;
			}
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			return 0;
		}
	}

	// ================================================================
	// COMPARATOR
	// ================================================================
	@Override
	public int compareTo(TaskDate arg0) {
		int thisVal = this.myYear * 10000 + this.myMonth * 100 + this.myDay;
		int thatVal = arg0.getYear() * 10000 + arg0.getMonth() * 100 + arg0.getDay();
		if (thisVal > thatVal) {
			return 1;
		} else if (thisVal < thatVal) {
			return -1;
		} else {
			return 0;
		}
	}
}
```
###### ./src/utilities/TaskTime.java
``` java
/**
 * This class is to store the time for each tasks
 * 
 * @author Tianrui
 *
 */
public class TaskTime implements Comparable<TaskTime> {
	// ================================================================
	// CONSTANT STRING
	// ================================================================
	private static final String DIVIDE = ":";
	private static final String AM = "AM";
	private static final String PM = "PM";
	private static final int INVALID = -1;
	private static final int NOON = 12;
	private static DecimalFormat df = new DecimalFormat("00");

	// ================================================================
	// VARIABLES
	// ================================================================
	private int hour;
	private int minute;

	// ================================================================
	// CONSTRUCTOR
	// ================================================================
	public TaskTime(int hr, int min) {
		hour = hr;
		minute = min;
	}

	public TaskTime(String endTime) {
		if (endTime == null) {
			hour = INVALID;
			minute = INVALID;
		}
		String[] split = endTime.split(DIVIDE);
		try {
			hour = Integer.parseInt(split[0]);
			minute = Integer.parseInt(split[1]);
		} catch (Exception e) {
			hour = INVALID;
			minute = INVALID;
		}
	}

	// ================================================================
	// GETTERS
	// ================================================================
	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	// ================================================================
	// PRINTING
	// ================================================================
	public String printTime() {
		if (hour < NOON) {
			return hour + DIVIDE + minute + AM;
		} else if (hour == NOON) {
			return hour + DIVIDE + minute + PM;
		} else {
			return (hour - NOON) + DIVIDE + minute + PM;
		}
	}

	public String toString() {
		return hour + DIVIDE + df.format(minute);
	}

	// ================================================================
	// COMPARATOR
	// ================================================================
	@Override
	public int compareTo(TaskTime o) {
		int thisVal = this.hour * 100 + this.minute;
		int thatVal = o.getHour() * 100 + o.getMinute();
		if (thisVal > thatVal) {
			return 1;
		} else if (thisVal < thatVal) {
			return -1;
		} else {
			return 0;
		}
	}
}
```
