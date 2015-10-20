package utilities;

public class TaskEvent {
	
    protected int taskID;
    protected String taskName;
    protected TaskDate startDate;
    protected TaskDate endDate;
    protected String priority;
  //startDate & endDate - events
  //endDate - deadlines
  //no date - floating
    
    public TaskEvent(int taskID, String name, TaskDate startDate, TaskDate endDate, String prio) {
        this.taskID = taskID;
        this.taskName = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = prio;
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
    public TaskDate getStartDate() {
		return startDate;
    }
    public TaskDate getEndDate(){
    	return endDate;
    }
    public String getPriority() {
		return priority;
    }

    @Override
    public String toString() {
		String str = "";
		str += ":taskID:" + taskID;
		str += ":taskName:" + taskName;
		str += ":priority:" + priority;	
		if(endDate.equals("") && startDate.equals("")){
			//floating
			//str += ":date:" + startDate.toString();
		}else if(startDate.equals("")){ 
			//deadlines
			str+=":date:" + endDate.toString();
		} else{
			//events
			str+=":date:" + startDate.toString() + "to" + endDate.toString();
		}
		return str;
    }
}
