package utilities;

import java.util.ArrayList;

public class TaskDate implements Comparable<TaskDate> {

	String myDate;
	//static int myYear;
	int myYear, myDay, myMonth;

	// constructor 
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

	//no date
	//represent null
	public TaskDate() {
		myYear = -1;
		myMonth = -1;
		myDay = -1;
	}	

	public void setYear(int year) {
		myYear = year;
	}

	public void setMonth(int month) {
		myMonth = month;
	}

	public void setDay(int day) {
		myDay = day;
	}

	public int getYear() {
		return myYear;
	}

	public int getMonth() {
		return myMonth;
	}

	public int getDay() {
		return myDay;
	}
	public String toString(){
		if(myYear==-1){
			return "null";
		}
		return myDay + "/" + myMonth +"/" + myYear;
	}

	public String printDate() {
		if (myDay < 10 && myMonth < 10) {
			return "0" + Integer.toString(myDay) + "/" + "0" + Integer.toString(myMonth) + "/" + Integer.toString(myYear);
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
			if (absoluteDay > 0 && absoluteDay <= getDaysInMonth(iniMonth, iniYear) && iniMonth>0 && iniMonth<13) {
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
			} else if (absoluteDay > 0){
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
		case 1:  return 31;
		case 2:  if (year%4 == 0 && year%100!=0) {
			return 29;
		} else {
			return 28;
		}
		case 3:  return 31;
		case 4:  return 30;
		case 5:  return 31;
		case 6:  return 30;
		case 7:  return 31;
		case 8:  return 31;
		case 9:  return 30;
		case 10: return 31;
		case 11: return 30;
		case 12: return 31;
		default: return 0;
		}
	}
	
	public ArrayList<String> getMultipleDate() {
		ArrayList<String> date_formats = new ArrayList<String>();
		String format_1 = String.valueOf(myDay)+String.valueOf(myMonth)+String.valueOf(myYear);
		String format_2 = String.valueOf(myDay)+String.valueOf(myMonth)+formatChange(String.valueOf(myYear));
		String format_3 = String.valueOf(myDay)+"/"+String.valueOf(myMonth)+"/"+String.valueOf(myYear);
		String format_4 = String.valueOf(myYear)+String.valueOf(myMonth)+String.valueOf(myDay);
		String format_5 = formatChange(String.valueOf(myYear))+String.valueOf(myMonth)+String.valueOf(myDay);
		String format_6 = String.valueOf(myYear)+"."+String.valueOf(myMonth)+"."+String.valueOf(myDay);
		String format_7 = String.valueOf(myDay)+String.valueOf(myMonth);
		String format_8 = String.valueOf(myDay)+"/"+String.valueOf(myMonth);
		String format_9 = String.valueOf(myMonth)+String.valueOf(myDay);
		String format_10 = String.valueOf(myMonth)+"."+String.valueOf(myDay);
		String format_11 = formatChange(String.valueOf(myDay))+formatChange(String.valueOf(myMonth))+String.valueOf(myYear);
		String format_12 = formatChange(String.valueOf(myDay))+formatChange(String.valueOf(myMonth))+formatChange(String.valueOf(myYear));
		String format_13 = formatChange(String.valueOf(myDay))+"/"+formatChange(String.valueOf(myMonth))+"/"+String.valueOf(myYear);
		String format_14 = String.valueOf(myYear)+formatChange(String.valueOf(myMonth))+formatChange(String.valueOf(myDay));
		String format_15 = formatChange(String.valueOf(myYear))+formatChange(String.valueOf(myMonth))+formatChange(String.valueOf(myDay));
		String format_16 = String.valueOf(myYear)+"."+formatChange(String.valueOf(myMonth))+"/"+formatChange(String.valueOf(myDay));
		String format_17 = formatChange(String.valueOf(myDay))+formatChange(String.valueOf(myMonth));
		String format_18 = formatChange(String.valueOf(myDay))+"/"+formatChange(String.valueOf(myMonth));
		String format_19 = formatChange(String.valueOf(myMonth))+formatChange(String.valueOf(myDay));
		String format_20 = formatChange(String.valueOf(myMonth))+"."+formatChange(String.valueOf(myDay));
		date_formats.add(format_1);
		date_formats.add(format_2);
		date_formats.add(format_3);
		date_formats.add(format_4);
		date_formats.add(format_5);
		date_formats.add(format_6);
		date_formats.add(format_7);
		date_formats.add(format_8);
		date_formats.add(format_9);
		date_formats.add(format_10);
		date_formats.add(format_11);
		date_formats.add(format_12);
		date_formats.add(format_13);
		date_formats.add(format_14);
		date_formats.add(format_15);
		date_formats.add(format_16);
		date_formats.add(format_17);
		date_formats.add(format_18);
		date_formats.add(format_19);
		date_formats.add(format_20);
		return date_formats;
	}
	
	@Override
	public int compareTo(TaskDate arg0) {
		int thisVal = this.myYear * 10000 + this.myMonth * 100 + this.myDay;
		int thatVal = arg0.getYear() * 10000 + arg0.getMonth() * 100 + arg0.getDay();
		if (thisVal > thatVal) {
			return 1;
		} else {
			return 0;
		}
	}

	private String formatChange(String date) {
		if (date.length() == 2) {
			return date;
		} else if (date.length() == 4) {
			return date.substring(2, 4);
		} else if (date.length() == 1) {
			return "0" + date;
		} else {
			return date;
		}
	}
}
