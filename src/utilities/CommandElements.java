package utilities;

public class CommandElements {
	private Command_Type type;
	private String name;
	private TaskDate date[] = new TaskDate[2];
	private TaskTime time[] = new TaskTime[2];
	private Command_Priority priority;
	private Command_Field field;
	private int id;
	
	public CommandElements(){
		type = Command_Type.NOT_FOUND;
	}
	
	public CommandElements(Command_Type cmd, String nm, TaskDate dt[], Command_Priority prio, TaskTime tm[]){
		this.id = -1;
		this.type = cmd;
		this.name = nm;
		this.date = dt;
		this.priority = prio;
		this.time = tm;
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, String nm){
		this.type = cmd;
		this.id = o;
		this.field = fld;
		this.name = nm;
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, TaskDate dt){
		this.type = cmd;
		this.id = o;
		this.field = fld;
		if (fld == Command_Field.END_DATE) {
			this.date[1] = dt;
			this.date[0] = new TaskDate(0,0,0);
		} else {
			this.date[0] = dt;
			this.date[1] = new TaskDate(0,0,0);
		}
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, TaskTime tm){
		this.type = cmd;
		this.id = o;
		this.field = fld;
		if (fld == Command_Field.END_TIME) {
			this.time[1] = tm;
			this.time[0] = new TaskTime(0,0);
		} else {
			this.time[0] = tm;
			this.time[1] = new TaskTime(0,0);
		}
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, Command_Priority prio){
		this.type = cmd;
		this.id = o;
		this.field = fld;
		this.priority = prio;
	}
	
	public CommandElements(Command_Type cmd, int o) {
		this.type = cmd;
		this.id = o;
	}
	
	public CommandElements(Command_Type cmd) {
		this.id = -1;
		this.type = cmd;
	}
	
	public CommandElements(Command_Type cmd, String directory) {
		this.id = -1;
		this.type = cmd;
		this.name = directory;
	}

	public Command_Type getType(){
		return type;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int o) {
		id = o;
	} 
	
	public String getName(){
		return name;
	}
	
	public TaskDate getStartDate() {
		return date[0];
	}
	
	public TaskDate getEndDate() {
		return date[1];
	}
	
	public TaskTime getStartTime() {
		return time[0];
	}
	
	public TaskTime getEndTime() {
		return time[1];
	}
	
	public Command_Priority getPriority() {
		return priority;
	}
	
	public Command_Field getField() {
		return field;
	}
	
	public void debugPrint() {
		System.out.println(type.toString());
		System.out.println(name);
		System.out.println(id);
		if (date[0] != null && time[0] != null) System.out.println(date[0].printDate() + " " + time[0].toString());
		if (date[1] != null && time[1] != null) System.out.println(date[1].printDate() + " " + time[1].toString());
		if (priority != null) System.out.println(priority.toString());
		if (field == null) { 
			System.out.println("no field");
		} else {
			System.out.println(field.toString());
		}
	}
}
