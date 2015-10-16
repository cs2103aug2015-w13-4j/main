package logic;

import java.util.ArrayList;

import database.Storage;
import logic.Launch;
import utilities.TaskEvent;

public class Launch {
	static Storage storage;
	static Display display;
	static Operation op;
	static Search search;
	static ArrayList<TaskEvent> tasks;
	private static void createObjects(){
		storage = new Storage();
		display = new Display();
		op = new Operation();
		search = new Search();
		tasks = storage.load();
	}
	public void newFile(){
		createObjects();
	}
	public void loadFile(){
		createObjects();
	}
	
	/*getting obejcts*/
	public static Storage getStorage(){
		return storage;
	}
	public static  Display getDisplay(){
		return display;
	}
	public static Search getSearch(){
		return search;
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
