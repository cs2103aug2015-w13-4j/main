//@@author Shirlene A0130909H
package logic;

import database.StorageImp;
import utilities.TaskEvent;

import java.util.ArrayList;

public class Launch {
	private static StorageImp storage;
	private static Display display;
	private static Operation op;
	private static Search search;
	private static ArrayList<TaskEvent> tasks;
	private static Launch launch;
	private static Undo undo;
	private static Redo redo;

	private Launch() {
	}

	private static void createObjects() {
		display = new Display();
		op = new Operation();
		search = new Search();
		launch = new Launch();
		storage = StorageImp.getInstance();
		tasks = storage.loadAllTasks();
		undo = new Undo();
		redo = new Redo();
	}

	/* getting objects */
	public static StorageImp getStorage() {
		return storage;
	}

	public static Operation getOperation() {
		return op;
	}

	public static Display getDisplay() {
		return display;
	}

	public static Search getSearch() {
		return search;
	}
	public static Undo getUndo(){
		return undo;
	}
	public static Launch getInstance() {

		if (launch == null) {
			createObjects();
		}
		return launch;
	}
	public static Redo getRedo(){
		return redo;
	}

	/* UI main task view */
	public ArrayList<TaskEvent> updateView() {
		tasks = display.taskView();
		return tasks;
	}

	/* UI search View */
	public ArrayList<TaskEvent> searchView(){
		return display.searchView();
	}
	/* main method UI calls */
	public String command(String input) {
		return op.processOperation(input);
	}
}