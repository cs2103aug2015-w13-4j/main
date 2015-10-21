package logic;

import java.util.ArrayList;

import database.Storage;
import logic.Launch;
import utilities.TaskEvent;

public class Launch {
	private static Storage storage;
	private static Display display;
	private static Operation op;
	private static Search search;
	private static ArrayList<TaskEvent> tasks;
	private static Launch launch;
	
	private static void createObjects(){
		storage = new Storage();
		display = new Display();
		op = new Operation();
		search = new Search();
		tasks = storage.load();
		launch = new Launch();
	}

	/*getting objects*/
	public static Storage getStorage(){
		return storage;
	}
	public Operation getOperation(){
		return op;
	}
	public static  Display getDisplay(){
		return display;
	}
	public static Search getSearch(){
		return search;
	}
	public static Launch getInstance(){
		
		if(launch == null){
			createObjects();
		}	
		return launch;
	}
	
	/*UI main task view*/
	public ArrayList<TaskEvent> updateView(){
		tasks = display.taskView();
		return tasks;
	}
	/*main method UI calls*/
	public String command(String input){
		return op.processOperation(input);
	}
}