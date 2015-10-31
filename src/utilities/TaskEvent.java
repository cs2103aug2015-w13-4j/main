package utilities;

public class TaskEvent {

	public  static final int ELEMENTS_COUNT = 7;

	private static String TASK_STR = "%1$s&&" +
			"name:%2$s&&" +
			"start_date:%3$s&&" +
			"end_date:%4$s&&" +
			"priority:%5$s&&" +
			"completed:%6$s&&" +
			"available:%7$s&&";

	protected int taskID;
	protected String taskName;
	protected TaskDate startDate;
	protected TaskDate endDate;
	protected Command_Priority flag;
	protected boolean completed; // has the task been completed, default to false
	protected boolean available; // default to true; false to indicate deleted

	public int              getTaskID()    { return taskID; }
	public String           getTaskName()  { return taskName; }
	public TaskDate         getStartDate() { return startDate; }
	public TaskDate         getEndDate()   { return endDate; }
	public Command_Priority getPriority()  { return flag; }
	public boolean          isCompleted()  { return completed; }
	public boolean          isAvailable()  { return available; }

	private void            setTaskID(int taskID)                  { this.taskID = taskID; }
	public void             setTaskName(String taskName)           { this.taskName = taskName; }
	public void             setStartDate(TaskDate startDate)       { this.startDate = startDate; }
	public void             setEndDate(TaskDate endDate)           { this.endDate = endDate; }
	public void             setPriority(Command_Priority flag) { this.flag = flag; }
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
		return String.format(TASK_STR, taskID, taskName, startDate.toString(), endDate.toString(),
				flag.toString(), String.valueOf(completed), String.valueOf(available));
	}

}
