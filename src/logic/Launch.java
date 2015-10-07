package logic;

import database.Storage;
import logic.Launch;

public class Launch {
	static Storage storage;
	static Display display;
	
	public static void createObjects(){
		storage = new Storage();
		display = new Display();
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
}
