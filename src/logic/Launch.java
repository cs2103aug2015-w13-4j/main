package logic;

import database.StorageImp;

import utilities.TaskEvent;

import java.util.ArrayList;

//@@author A0130909H Shirlene
public class Launch {
	private static StorageImp storage;
	private static Display display;
	private static Operation op;
	private static ArrayList<TaskEvent> tasks;
	private static Launch launch;
	private static Undo undo;
	private static Redo redo;

	// ================================================================
	// INITIALIZATION
	// ================================================================
	private Launch() {
	}

	private static void createObjects() {
		display = new Display();
		op = new Operation();
		launch = new Launch();
		storage = StorageImp.getInstance();
		tasks = storage.loadAllTasks();
		undo = new Undo();
		redo = new Redo();
	}

	// ================================================================
	// GETTERS
	// ================================================================
	public static StorageImp getStorage() {
		return storage;
	}

	public static Operation getOperation() {
		return op;
	}

	public static Display getDisplay() {
		return display;
	}

	public static Undo getUndo() {
		return undo;
	}

	public static Launch getInstance() {

		if (launch == null) {
			createObjects();
		}
		return launch;
	}

	public static Redo getRedo() {
		return redo;
	}

	// ================================================================
	// METHODS
	// ================================================================

	/**
	 * Provide UI with all the active tasks
	 * 
	 * @return ArrayList<TaskEvent> of Tasks
	 */
	public ArrayList<TaskEvent> updateView() {
		tasks = display.taskView();
		return tasks;
	}

	/**
	 * Provide UI with a list of tasks matching user's request
	 * 
	 * @return ArrayList<TaskEvent> containing tasks that match request
	 */
	public ArrayList<TaskEvent> resultView() {
		return display.resultView();
	}

	/**
	 * Main method which UI calls to perform user input
	 * 
	 * @param input
	 *            user's command
	 * @return String which acts as a form of feedback
	 */
	public String command(String input) {
		return op.processOperation(input);
	}
}