package logic;

import java.util.ArrayList;

import database.Storage;
import logic.Launch;
import utilities.TaskEvent;

public class Launch {
	static Storage storage;
	static Display display;
	static Operation op;
	
	public static void createObjects(){
		storage = new Storage();
		display = new Display();
		op = new Operation();
	}
	public void newFile(){
		createObjects();
		
	}
	public void loadFile(){
		createObjects();
	}
	public void changeDirectory(String oldDirectory, String newDirectory){
		
	}
	public static Storage getStorage(){
		return storage;
	}
	public static  Display getDisplay(){
		return display;
	}
	public ArrayList<TaskEvent> updateView(){
		return display.defaultView();
	}
	public String command(String input){
		return op.processOperation(input);
	}
}
