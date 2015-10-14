package utilities;

public class CommandElements {
	private Command_Type type;
	private String name;
	private TaskDate date1;	
	private TaskDate date2;
	private Command_Priority priority;
	private Command_Field field;
	private int ID;
	/*ADD*/
	//normal task
	public CommandElements(Command_Type cmd, String nm, Command_Priority prio){
		this.type = cmd;
		this.name = nm;
		this.priority = prio;
	}
	//deadline
	public CommandElements(Command_Type cmd, String nm, TaskDate dt, Command_Priority prio){
		this.type = cmd;
		this.name = nm;
		this.date2 = dt;
		this.priority = prio;
	}
	//event 
	public CommandElements(Command_Type cmd, String nm, TaskDate dt1, TaskDate dt2, Command_Priority prio){
		this.type = cmd;
		this.name = nm;
		this.date1 = dt1;
		this.date2 = dt2;
		this.priority = prio;
	}
	
	/*EDIT*/
	//name
	public CommandElements(Command_Type cmd, int id, Command_Field fld, String nm){
		this.type = cmd;
		this.ID = id;
		this.field = fld;
		this.name = nm;
	}
	//date
	public CommandElements(Command_Type cmd, int id, Command_Field fld, TaskDate dt){
		this.type = cmd;
		this.ID = id;
		this.field = fld;
		if (field == Command_Field.START_DATE) {
			this.date1 = dt;
		} else {
			this.date2 = dt;
		}
	}
	//priority
	public CommandElements(Command_Type cmd, int id, Command_Field fld, Command_Priority prio){
		this.type = cmd;
		this.ID = id;
		this.field = fld;
		this.priority = prio;
	}
	
	/*DELETE*/
	public CommandElements(Command_Type cmd, int id) {
		this.type = cmd;
		this.ID = id;
	}
	
	/*UNDO*/
	public CommandElements(Command_Type cmd) {
		this.type = cmd;
	}
	
	/*DIRECTORY*/
	public CommandElements(Command_Type cmd, String directory) {
		this.type = cmd;
		this.name = directory;
	}
	
	/*GET methods*/
	public Command_Type getType(){
		return type;
	}
	
	public int getID() {
		return ID;
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
