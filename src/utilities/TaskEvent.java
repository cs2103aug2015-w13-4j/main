package utilities;

public class TaskEvent {

    protected int taskID;
    protected String taskName;
    protected TaskDate date;
    protected int priority;
    protected String description;

    public TaskEvent(int taskID, String name, TaskDate date, int prio, String des) {
        this.taskID = taskID;
        this.taskName = name;
        this.date = date;
        this.priority = prio;
        this.description = des;
    }

    public TaskEvent(String[] split) {
		taskID = Integer.parseInt(split[0]);
		taskName = split[1];
		date = new TaskDate(split[2]);
		priority = Integer.parseInt(split[3]);
		description = split[4];
	}

	public int getTaskID() {
		return taskID;
    }

    public String getTaskName() {
		return taskName;
    }

    public int getPriority() {
		return priority;
    }

    public TaskDate getDate() {
		return date;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getPriorirty() {
        return priority;
    }

    @Override
    public String toString() {
		String str = "";
		str += ":taskID:" + taskID;
		str += ":taskName:" + taskName;
		str += ":priority:" + priority;
		str += ":description:" + description;
		str += ":date:" + date.toString();
		return str;
    }
}
