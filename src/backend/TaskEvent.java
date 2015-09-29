package backend;

public class TaskEvent {

    protected long taskID;
    protected String taskName;
    protected int priority;
    protected String description;
    protected boolean isRecurring
    protected TaskDate date;

    public TaskEvent(String name, TaskDate date, int prio, String des, boolean recur) {
        this.taskID = System.currentTimeMillis();
        this.name = name;
        this.date = date;
        this.priority = prio;
        this.description = des;
        this.isRecurring = recur;
    }
}
