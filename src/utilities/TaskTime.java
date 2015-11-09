package utilities;

import java.text.DecimalFormat;

//@@author A0133965X Tianrui
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
