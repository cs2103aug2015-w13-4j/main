package utilities;

public class CommandElements {
	private Command_Type type;
	private String name;
	private TaskDate date[] = new TaskDate[2];
	private Command_Priority priority;
	private Command_Field field;
	private int id;
	
	public CommandElements(Command_Type cmd, String nm, TaskDate dt[], Command_Priority prio) {
		date[0] = new TaskDate(0,0,0);
		date[1] = new TaskDate(0,0,0);
		this.type = cmd;
		this.name = nm;
		this.date = dt;
		this.priority = prio;
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, String nm) {
		date[0] = new TaskDate(0,0,0);
		date[1] = new TaskDate(0,0,0);
		this.type = cmd;
		this.id = o;
		this.field = fld;
		this.name = nm;
	}
	public CommandElements(Command_Type cmd, int o, Command_Field fld, TaskDate dt) {
		date[0] = new TaskDate(0,0,0);
		date[1] = new TaskDate(0,0,0);
		this.type = cmd; 
		this.id = o;
		this.field = fld;
		this.date[0] = dt;
	}
	
	public CommandElements(Command_Type cmd, int o, Command_Field fld, Command_Priority prio) {
		date[0] = new TaskDate(0,0,0);
		date[1] = new TaskDate(0,0,0);
		this.type = cmd;
		this.id = o;
		this.field = fld;
		this.priority = prio;
	}
	
	public CommandElements(Command_Type cmd, int o) {
		date[0] = new TaskDate(0,0,0);
		date[1] = new TaskDate(0,0,0);
		this.type = cmd;
		this.id = o;
	}
	
	public CommandElements(Command_Type cmd) {
		date[0] = new TaskDate(0,0,0);
		date[1] = new TaskDate(0,0,0);
		this.type = cmd;
	}
	
	public CommandElements(Command_Type cmd, String directory) {
		date[0] = new TaskDate(0,0,0);
		date[1] = new TaskDate(0,0,0);
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
		if (date[0] != null) System.out.println(date[0].printDate());
		if (date[1] != null) System.out.println(date[1].printDate());
		if (priority != null) System.out.println(priority.toString());
		if (field == null) { 
			System.out.println("no field");
		} else {
			System.out.println(field.toString());
		}
	}
}
