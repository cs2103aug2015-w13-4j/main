package utilities;

public class TaskTime {
	private static final String DIVIDE = ":";
	private static final String AM = "AM";
	private static final String PM = "PM";
	private static final int INVALID = -1;
	private static final int NOON = 12;

	private int hour;
	private int minute;
	
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

	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public String printTime() {
		if (hour < NOON) {
			return hour + DIVIDE + minute + AM;
		} else if (hour == NOON) {
			return hour + DIVIDE + minute + PM;
		} else {
			return (hour - NOON) + DIVIDE + minute + PM;
		}
	}
	
	public String toString() { return hour + DIVIDE + minute; }
}
