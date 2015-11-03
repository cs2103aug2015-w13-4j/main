package utilities;

public class TaskTime {

	private int hour;
	private int minute;
	
	public TaskTime(int hr, int min) {
		hour = hr;
		minute = min;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public String printTime() {
		if (hour < 12) {
			return hour + ":" + minute + "AM";
		} else if (hour == 12) {
			return hour + ":" + minute + "PM";
		} else {
			return (hour - 12) + ":" + minute + "PM";
		}
	}
	
	public String toString() {
		return hour + ":" + minute;
	}
}
