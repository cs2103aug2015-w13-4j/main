package utilities;

public class TaskEvent {

    protected int taskID;
    protected String taskName;
    protected int priority;
    protected String description;
    protected TaskDate date;

    public TaskEvent(int taskID, String name, TaskDate date, int prio, String des) {
        this.taskID = taskID;
        this.taskName = name;
        this.date = date;
        this.priority = prio;
        this.description = des;
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
    
    @Override
    public String toString() {
    	String str = "";
    	str += "taskID:" + taskID;
    	str += "taskName:" + taskName;
    	str += "priority:" + priority;
    	str += "description:" + description;
    	str += "date:" + date.toString();
    	return str;
    }
}
