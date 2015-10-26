package utilities;

public class CommandElements {
	private Command_Type type;
	private String name;
	private TaskDate date[] = new TaskDate[2];
	private Command_Priority priority;
	private Command_Field field;
	private int object;
	
	public CommandElements(Command_Type cmd, String nm, TaskDate dt[], Command_Priority prio){
		this.type = cmd;
		this.name = nm;
		this.date = dt;
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
		this.date[0] = dt;
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
		return date[0];
	}
	
	public TaskDate getEndDate() {
		return date[1];
	}
	
	public Command_Priority getPriority() {
		return priority;
	}
	
	public Command_Field getField() {
		return field;
	}
}
