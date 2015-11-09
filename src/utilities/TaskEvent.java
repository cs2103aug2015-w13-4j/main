package utilities;

public class TaskEvent {
	public static final int ELEMENTS_COUNT = 9;
	private static final String TOK = "&&";
	private static final String DIV = "``";

	private static String TASK_STR
			= "%1$s"                      + TOK
			+ "name"       + DIV + "%2$s" + TOK
			+ "start_date" + DIV + "%3$s" + TOK
			+ "start_time" + DIV + "%4$s" + TOK
			+ "end_date"   + DIV + "%5$s" + TOK
			+ "end_time"   + DIV + "%6$s" + TOK
			+ "priority"   + DIV + "%7$s" + TOK
			+ "completed"  + DIV + "%8$s" + TOK
			+ "available"  + DIV + "%9$s" + TOK ;

	protected int taskID;
	protected String taskName;
	protected TaskDate startDate;
	protected TaskDate endDate;
	protected TaskTime startTime;
	protected TaskTime endTime;
	protected Command_Priority flag;
	protected boolean completed; // has the task been completed, default to false
	protected boolean available; // default to true; false to indicate deleted

	public int              getTaskID()    { return taskID; }
	public String           getTaskName()  { return taskName; }
	public TaskDate         getStartDate() { return startDate; }
	public TaskDate         getEndDate()   { return endDate; }
	public TaskTime         getEndTime()   { return endTime; }
	public TaskTime         getStartTime() { return startTime; }
	public Command_Priority getPriority()  { return flag; }
	public boolean          isCompleted()  { return completed; }
	public boolean          isAvailable()  { return available; }


	public void            setTaskID(int taskID)                  { this.taskID = taskID; }
	public void             setTaskName(String taskName)           { this.taskName = taskName; }
	public void             setStartDate(TaskDate startDate)       { this.startDate = startDate; }
	public void             setEndDate(TaskDate endDate)           { this.endDate = endDate; }
	public void             setEndTime(TaskTime endTime)           { this.endTime = endTime; }
	public void             setStartTime(TaskTime startTime)       { this.startTime = startTime; }
	public void             setPriority(Command_Priority flag)     { this.flag = flag; }
	public void             setCompleted(boolean completed)        { this.completed = completed; }
	public void             setAvailable(boolean available)        { this.available = available; }

	public TaskEvent(int taskID, String name, TaskDate startDate, TaskTime startTime,
	                 TaskDate endDate, TaskTime endTime, Command_Priority priority) {
		setTaskID(taskID);
		setTaskName(name);
		setStartDate(startDate);
		setStartTime(startTime);
		setEndDate(endDate);
		setEndTime(endTime);
		setPriority(priority);
		setCompleted(false);
		setAvailable(true);
	}

	@Override
	public String toString() {
		return String.format(
				TASK_STR,
				taskID,
				taskName,
				startDate.toString(),
				startTime.toString(),
				endDate.toString(),
				endTime.toString(),
				flag.toString(),
				String.valueOf(completed),
				String.valueOf(available)
		);
	}
}