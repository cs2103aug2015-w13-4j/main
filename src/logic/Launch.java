package logic;

import database.Storage;

public class Launch {
	static Storage storage;
	static Display display;
	
	private void createObjects(){
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
