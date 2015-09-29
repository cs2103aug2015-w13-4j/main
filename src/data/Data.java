package data;
import java.util.ArrayList;

import Object.TaskEvent;

public class Data {

	public static ArrayList<TaskEvent> getAll(){
		ArrayList<TaskEvent> wholeFile = getfile();
		
		return wholeFile;
	}
	public static ArrayList<TaskEvent> add(TaskEvent newTask){
		ArrayList<TaskEvent> file = getAll();
		file.add(newTask);
		updateFile();//for storage
		
		return file;
		
	}
	public static ArrayList<TaskEvent> display(String word){
		ArrayList<TaskEvent> viewList = new ArrayList<TaskEvent>();
		ArrayList<TaskEvent> file = getAll();
		for(int i =0;i<file.size();i++){
			if(file.get(i).getTask().contains(word)){
				viewList.add(file.get(i));
			}
		}
		return viewList;
		
	}
	
}

