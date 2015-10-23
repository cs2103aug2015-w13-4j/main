package utilities;

import com.google.gson.Gson;

public class TaskEvent {

	private static final int LO_PRIO = 3;
	private static final int MI_PRIO = 2;
	private static final int HI_PRIO = 1;

	protected int taskID;
	protected String taskName;
	protected TaskDate startDate;
	protected TaskDate endDate;
	protected Command_Priority priority;
	protected boolean completed; // has the task been completed, default to false
	protected boolean available; // default to true; false to indicate deleted

	public int              getTaskID()    { return taskID; }
	public String           getTaskName()  { return taskName; }
	public TaskDate         getStartDate() { return startDate; }
	public TaskDate         getEndDate()   { return endDate; }
	public Command_Priority getPriority()  { return priority; }
	public boolean          isCompleted()  { return completed; }
	public boolean          isAvailable()  { return available; }

	private void            setTaskID(int taskID)                  { this.taskID = taskID; }
	public void             setTaskName(String taskName)           { this.taskName = taskName; }
	public void             setStartDate(TaskDate startDate)       { this.startDate = startDate; }
	public void             setEndDate(TaskDate endDate)           { this.endDate = endDate; }
	public void             setPriority(Command_Priority priority) { this.priority = priority; }
	public void             setCompleted(boolean completed)        { this.completed = completed; }
	public void             setAvailable(boolean available)        { this.available = available; }

	public TaskEvent(int taskID, String name, TaskDate from, TaskDate to, Command_Priority priority) {
		setTaskID(taskID);
		setTaskName(name);
		setStartDate(from);
		setEndDate(to);
		setPriority(priority);
		setCompleted(false);
		setAvailable(true);
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

//	public TaskEvent(int taskID, String name, TaskDate startDate, TaskDate endDate, int prio) {
//		this.taskID = taskID;
//		this.taskName = name;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		switch (prio) {
//		case LO_PRIO:
//			this.priority = Dispatch.Priority.LOW;
//			break;
//		case MI_PRIO:
//			this.priority = Priority.MEDIUM;
//			break;
//		case HI_PRIO:
//			this.priority = Priority.HIGH;
//			break;
//		default: break;
//		}
//	}
//
//	public TaskEvent(int taskID, String name, TaskDate startDate, TaskDate endDate, String prio) {
//		this.taskID = taskID;
//		this.taskName = name;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		switch (prio.toUpperCase()) {
//		case "LOW":
//			this.priority = Priority.LOW;
//			break;
//		case "MEDIUM":
//			this.priority = Priority.MEDIUM;
//			break;
//		case "HIGH":
//			this.priority = Priority.HIGH;
//			break;
//		default:
//			break;
//		}
//	}

//	public TaskEvent(String[] split) {
//		this(Integer.parseInt(split[0]), split[1], new TaskDate(split[2]), new TaskDate(split[3]), split[4]);
//	}

//	public static String priorityToIntString(String string) {
//		switch (string.toUpperCase()) {
//		case "LOW":
//			return "3";
//		case "MEDIUM":
//			return "2";
//		case "HIGH":
//			return "1";
//		default:
//			return "0";
//		}
//	}

//	public static String priorityToString(Priority p) {
//		switch (p) {
//		case LOW:
//			return "Low";
//		case MEDIUM:
//			return "Medium";
//		case HIGH:
//			return "High";
//		default:
//			return "";
//		}
//	}
//	@Override
//	public String toString() {
//		String str = "";
//		str += ":taskID:" + taskID;
//		str += ":taskName:" + taskName;
//		str += ":priority:" + priorityToString(priority);
//		if(endDate.getYear()==0 && startDate.getYear()==0){
//			//floating
//			//str += ":date:" + startDate.toString();
//		}else if(startDate.getYear() == 0){
//			//deadlines
//			str+=":date:" + endDate.toString();
//		} else{
//			//events
//			str+=":date:" + startDate.toString() + "to" + endDate.toString();
//		}
//		return str;
//	}
}
