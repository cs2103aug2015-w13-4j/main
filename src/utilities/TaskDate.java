package utilities;

/**
 * This class is to store task's date
 * 
 * @author Tianrui
 *
 */
public class TaskDate implements Comparable<TaskDate> {
	// ================================================================
	// VARIABLES
	// ================================================================
	String myDate;
	int myYear, myDay, myMonth;

	// ================================================================
	// CONSTRUCTOR
	// ================================================================
	// @@author A0130909H Shirlene
	public TaskDate(int year, int month, int day) {
		myYear = year;
		myMonth = month;
		myDay = day;
	}

	public TaskDate(String date) {
		if (date.equals("null")) {
			myYear = 0;
			myMonth = 0;
			myDay = 0;
			return;
		}
		String[] split = date.split("/");
		myDay = Integer.parseInt(split[0]);
		myMonth = Integer.parseInt(split[1]);
		myYear = Integer.parseInt(split[2]);
	}

	public TaskDate() {
		myYear = -1;
		myMonth = -1;
		myDay = -1;
	}

	// ================================================================
	// SETTERS
	// ================================================================
	// @@author A0133965X Tianrui
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
