package utilities;

public class CommandElements {
	private Command_Type type;
	private String name;
	private TaskDate date1;	
	private TaskDate date2;
	private Command_Priority priority;
	private Command_Field field;
	private int object;
	
	public CommandElements(Command_Type cmd, String nm, Command_Priority prio){
		this.type = cmd;
		this.name = nm;
		this.priority = prio;
	}
	
	public CommandElements(Command_Type cmd, String nm, TaskDate dt, Command_Priority prio){
		this.type = cmd;
		this.name = nm;
		this.date2 = dt;
		this.priority = prio;
	}
	
	public CommandElements(Command_Type cmd, String nm, TaskDate dt1, TaskDate dt2, Command_Priority prio){
		this.type = cmd;
		this.name = nm;
		this.date1 = dt1;
		this.date2 = dt2;
		this.priority = prio;
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, String nm){
		this.type = cmd;
		this.object = o;
		this.field = fld;
		this.name = nm;
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, TaskDate dt){
		this.type = cmd;
		this.object = o;
		this.field = fld;
		if (field == Command_Field.START_DATE) {
			this.date1 = dt;
		} else {
			this.date2 = dt;
		}
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, Command_Priority prio){
		this.type = cmd;
		this.object = o;
		this.field = fld;
		this.priority = prio;
	}
	
	public CommandElements(Command_Type cmd, int o) {
		this.type = cmd;
		this.object = o;
	}
	
	public CommandElements(Command_Type cmd) {
		this.type = cmd;
	}
	
	public CommandElements(Command_Type cmd, String directory) {
		this.type = cmd;
		this.name = directory;
	}

	public Command_Type getType(){
		return type;
	}
	
	public int getObject() {
		return object;
	}
	
	public String getName(){
		return name;
	}
	
	public TaskDate getStartDate() {
		return date1;
	}
	
	public TaskDate getEndDate() {
		return date2;
	}
	
	public Command_Priority getPriority() {
		return priority;
	}
	
	public Command_Field getField() {
		return field;
	}
}
