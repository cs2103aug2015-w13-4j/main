package utilities;

public class CommandElements {
	private Command_Type type;
	private String name;
	private String description;
	private TaskDate date;	
	private Command_Priority priority;
	private Command_Field field;
	private int object;
	
	public CommandElements(Command_Type cmd, String nm, TaskDate dt, Command_Priority prio, String des){
		this.type = cmd;
		this.name = nm;
		this.date = dt;
		this.description = des;
		this.priority = prio;
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, String str){
		this.type = cmd;
		this.object = o;
		this.field = fld;
		if (field == Command_Field.DESCRIPTION) {
			this.description = str;
		} else {
			this.name = str;
		}
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, TaskDate dt){
		this.type = cmd;
		this.object = o;
		this.field = fld;
		this.date = dt;
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, Command_Priority prio){
		this.type = cmd;
		this.object = o;
		this.field = fld;
		this.priority = prio;
	}
	
	public Command_Type getType(){
		return type;
	}
	
	public int getObject() {
		return object;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getName(){
		return name;
	}
	
	public TaskDate getDate() {
		return date;
	}
	
	public Command_Priority getPriority() {
		return priority;
	}
	
	public Command_Field getField() {
		return field;
	}
}
