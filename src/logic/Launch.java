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

	private Launch() {
	}

	private static void createObjects() {
		System.out.println("creating");
		display = new Display();
		op = new Operation();
		search = new Search();
		launch = new Launch();
		System.out.println("next");
		storage = StorageImp.getInstance();
		tasks = storage.loadAllTasks();
		
		
	}

	/* getting objects */
	public static StorageImp getStorage() {
		return storage;
	}

	public Operation getOperation() {
		return op;
	}

	public static Display getDisplay() {
		return display;
	}

	public static Search getSearch() {
		return search;
	}

	public static Launch getInstance() {

		if (launch == null) {
			createObjects();
		}
		return launch;
	}

	/* UI main task view */
	public ArrayList<TaskEvent> updateView() {
		tasks = display.taskView();
		return tasks;
	}

	/* main method UI calls */
	public String command(String input) {
		return op.processOperation(input);
	}
}