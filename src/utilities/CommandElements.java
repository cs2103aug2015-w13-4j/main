package utilities;

public class CommandElements {
	private Command_Type type;
	private String description;
	private TaskDate date;	
	private Command_Priority priority;
	private Command_Field field;
	private int id;
	
	//adding task
	public CommandElements(Command_Type cmd, String des, TaskDate dt, Command_Priority prio){
		this.type = cmd;
		this.date = dt;
		this.description = des;
		this.priority = prio;
	}
	//edit des field
	public CommandElements(Command_Type cmd, int id, Command_Field fld, String des){
		this.type = cmd;
		this.id = id;
		this.field = fld;
		this.description = des;
	}
	//edit date field
	public CommandElements(Command_Type cmd, int id, Command_Field fld, TaskDate dt){
		this.type = cmd;
		this.id = id;
		this.field = fld;
		this.date = dt;
	}
	//edit priority field
	public CommandElements(Command_Type cmd, int id, Command_Field fld, Command_Priority prio){
		this.type = cmd;
		this.id = id;
		this.field = fld;
		this.priority = prio;
	}
	
	public Command_Type getType(){
		return type;
	}
	
	public int getID() {
		return id;
	}
	
	public String getDescription(){
		return description;
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
