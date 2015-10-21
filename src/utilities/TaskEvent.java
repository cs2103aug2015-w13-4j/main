package utilities;

public class TaskEvent {
	private static final int LO_PRIO = 3;
	private static final int MI_PRIO = 2;
	private static final int HI_PRIO = 1;

	protected int taskID;
	protected String taskName;
	protected TaskDate startDate;
	protected TaskDate endDate;
	protected Priority priority;
	//startDate & endDate - events
	//endDate - deadlines
	//no date - floating

	public enum Priority {
		HIGH, MEDIUM, LOW
	}

	public enum Field {
		NAME, START_DATE, END_DATE, PRIORITY
	}

	public TaskEvent(int taskID, String name, TaskDate startDate, TaskDate endDate, int prio) {
		this.taskID = taskID;
		this.taskName = name;
		this.startDate = startDate;
		this.endDate = endDate;
		switch (prio) {
		case LO_PRIO:
			this.priority = Priority.LOW;
			break;
		case MI_PRIO:
			this.priority = Priority.MEDIUM;
			break;
		case HI_PRIO:
			this.priority = Priority.HIGH;
			break;
		default: break; //TODO defensive
		}
	}

	public TaskEvent(int taskID, String name, TaskDate startDate, TaskDate endDate, String prio) {
		this.taskID = taskID;
		this.taskName = name;
		this.startDate = startDate;
		this.endDate = endDate;
		switch (prio.toUpperCase()) {
		case "LOW":
			this.priority = Priority.LOW;
			break;
		case "MEDIUM":
			this.priority = Priority.MEDIUM;
			break;
		case "HIGH":
			this.priority = Priority.HIGH;
			break;
		default:
			break;
		}
	}

	public TaskEvent(String[] split) {
		this(Integer.parseInt(split[0]), split[1], new TaskDate(split[2]), new TaskDate(split[3]), split[4]);
	}
	
	public static String priorityToIntString(String string) {
		switch (string.toUpperCase()) {
		case "LOW":
			return "3";
		case "MEDIUM":
			return "2";
		case "HIGH":
			return "1";
		default:
			return "0";
		}
	}

	public static String priorityToString(Priority p) {
		switch (p) {
		case LOW:
			return "Low";
		case MEDIUM:
			return "Medium";
		case HIGH:
			return "High";
		default:
			return "";
		}
	}

	public int getTaskID() {
		return taskID;
	}
	public String getTaskName() {
		return taskName;
	}
	public TaskDate getStartDate() {
		return startDate;
	}
	public TaskDate getEndDate(){
		return endDate;
	}
	public String getPriority() {
		switch (priority) {
		case LOW:
			return "Low";
		case MEDIUM:
			return "Medium";
		case HIGH:
			return "High";
		default:
			return "";
		}
	} 

	@Override
	public String toString() {
		String str = "";
		str += ":taskID:" + taskID;
		str += ":taskName:" + taskName;
		str += ":priority:" + priorityToString(priority);	
		if(endDate.getYear()==0 && startDate.getYear()==0){
			//floating
			//str += ":date:" + startDate.toString();
		}else if(startDate.getYear() == 0){ 
			//deadlines
			str+=":date:" + endDate.toString();
		} else{
			//events
			str+=":date:" + startDate.toString() + "to" + endDate.toString();
		}
		return str;
	}
}
