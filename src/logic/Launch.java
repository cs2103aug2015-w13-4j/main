package logic;

import database.Storage;

public class Launch {
	Storage loading;
	public void newFile(){
		loading = new Storage("");
	}
	public void loadFile(String directory){
		loading = new Storage(directory);
	}
	public void changeDirectory(String oldDirectory, String newDirectory){
		
	}
}
