package cs2103t;

public class TaskEvent {
	
	public String myName;
	public int myPriority;
	public String myDescription;
	public boolean isRecurring;
	public TaskDate myDate;

	// constructor
	public TaskEvent(String name) {
		myName = name;
		myPriority = 0;
		myDescription = "";
		isRecurring = false;
		myDate = new TaskDate();
	}
	
	public TaskEvent(String name, TaskDate date, int prio, String des, boolean recur) {
		myName = name;
		myDate = date;
		myPriority = prio;
		myDescription = des;
		isRecurring = recur;
	}
	
	public String getName() {
		return myName;
	}
	
	public void setDate(int year, int month, int day) {
		myDate = new TaskDate(year, month, day);
	}
	
	public void setPriority(int prio) {
		myPriority = prio;
	}
	
	public void setDescription(String des) {
		myDescription = des;
	}
	
	public void setRecurring() {
		isRecurring = true;
	}
	
	public void cancelRecurring() {
		isRecurring = false;
	}
	
	public TaskDate getDate() {
		return myDate;
	}
	
	public int getPriority() {
		return myPriority;
	}
	
	public String printPriority() {
		switch(myPriority) {
			case 2: return "high    ";
			case 1: return "medium  ";
			default: return "low     ";
		}
	}
	
	public String getDescription() {
		return myDescription;
	}
	
	public boolean isRecurring() {
		return isRecurring;
	}
	
	public String printToFile() {
		return myName + "\n" + myDate.printDate() + "\n" + String.valueOf(myPriority) 
				+ "\n" + String.valueOf(isRecurring) + "\n" + myDescription;
	}
}
