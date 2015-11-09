package utilities;

/**
 * manage user input for easy usage in other component
 * 
 * @author Tianrui
 *
 */
public class CommandElements {
	// ================================================================
	// VARIABLES
	// ================================================================
	private Command_Type type;
	private String name;
	private TaskDate date[] = new TaskDate[2];
	private TaskTime time[] = new TaskTime[2];
	private Command_Priority priority;
	private Command_Field field;
	private int id;

	// ================================================================
	// CONSTRUCTOR
	// ================================================================
	// @@author A0130909H Shirlene
	/**
	 * Commands that does not exist
	 */
	public CommandElements() {
		type = Command_Type.NOT_FOUND;
	}

	/**
	 * Add of tasks
	 * 
	 * @param cmd
	 *            users enters
	 * @param nm
	 *            name of tasks
	 * @param dt
	 *            date user input
	 * @param prio
	 *            priority which user sets
	 * @param tm
	 *            time user input
	 */
	public CommandElements(Command_Type cmd, String nm, TaskDate dt[], Command_Priority prio, TaskTime tm[]) {
		this.id = -1;
		this.type = cmd;
		this.name = nm;
		this.date = dt;
		this.priority = prio;
		this.time = tm;
	}

	/**
	 * Editing of date field
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 *            id of task
	 * @param fld
	 *            field to be edited
	 * @param dt
	 *            date to change to
	 */
	public CommandElements(Command_Type cmd, int id, Command_Field fld, TaskDate dt) {
		this.type = cmd;
		this.id = id;
		this.field = fld;
		if (fld == Command_Field.END_DATE) {
			this.date[1] = dt;
			this.date[0] = new TaskDate(0, 0, 0);
		} else {
			this.date[0] = dt;
			this.date[1] = new TaskDate(0, 0, 0);
		}
	}

	/**
	 * Editing of time
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 *            id of task
	 * @param fld
	 *            field to be edited
	 * @param tm
	 *            time to change to
	 */
	public CommandElements(Command_Type cmd, int id, Command_Field fld, TaskTime tm) {
		this.type = cmd;
		this.id = id;
		this.field = fld;
		if (fld == Command_Field.END_TIME) {
			this.time[1] = tm;
			this.time[0] = new TaskTime(0, 0);
		} else {
			this.time[0] = tm;
			this.time[1] = new TaskTime(0, 0);
		}
	}

	// @@author A0133965X Tianrui
	/**
	 * Edit name
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 *            task id
	 * @param fld
	 *            field to edit
	 * @param nm
	 *            name to edit to
	 */
	public CommandElements(Command_Type cmd, int id, Command_Field fld, String nm) {
		this.type = cmd;
		this.id = id;
		this.field = fld;
		this.name = nm;
	}

	/**
	 * Edit priority
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 *            tasks id
	 * @param fld
	 *            field to edit
	 * @param prio
	 *            priority to edit to
	 */
	public CommandElements(Command_Type cmd, int id, Command_Field fld, Command_Priority prio) {
		this.type = cmd;
		this.id = id;
		this.field = fld;
		this.priority = prio;
	}

	/**
	 * for commands that takes in id only(delete, finish, flag..)
	 * 
	 * @param cmd
	 *            command user input
	 * @param id
	 */
	public CommandElements(Command_Type cmd, int id) {
		this.type = cmd;
		this.id = id;
	}

	/**
	 * for command that uses only command like view completed
	 * 
	 * @param cmd
	 *            command user input
	 */
	public CommandElements(Command_Type cmd) {
		this.id = -1;
		this.type = cmd;
	}

	/**
	 * changing of directory
	 * 
	 * @param cmd
	 *            command user input
	 * @param directory
	 *            location to change to
	 */
	public CommandElements(Command_Type cmd, String directory) {
		this.id = -1;
		this.type = cmd;
		this.name = directory;
	}

	// ================================================================
	// GETTERS
	// ================================================================
	public Command_Type getType() {
		return type;
	}

	public int getID() {
		return id;
	}

	public void setID(int o) {
		id = o;
	}

	public String getName() {
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

	// ================================================================
	// PRINT
	// ================================================================
	public void debugPrint() {
		System.out.println(type.toString());
		System.out.println(name);
		System.out.println(id);
		if (date[0] != null && time[0] != null)
			System.out.println(date[0].printDate() + " " + time[0].toString());
		if (date[1] != null && time[1] != null)
			System.out.println(date[1].printDate() + " " + time[1].toString());
		if (priority != null)
			System.out.println(priority.toString());
		if (field == null) {
			System.out.println("no field");
		} else {
			System.out.println(field.toString());
		}
	}
}
