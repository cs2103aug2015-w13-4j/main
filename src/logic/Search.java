package logic;

import java.util.ArrayList;

import utilities.TaskEvent;

public class Search {
	private ArrayList<TaskEvent> result = new ArrayList<TaskEvent>();
	Search(){
	}
	
	public boolean searchWord(ArrayList<TaskEvent> current, String word){
		result = current;
		int count = performSearch(word);
		if(count == 0){
			return false;
		} else {
			return true;
		}
	}
	private int performSearch(String word){
		int count =0;
		for(int i =0;i<result.size();i++){
			if(result.get(i).getTaskName().contains(word)){
				count++;
			}
		}
		return count;
	}
	public ArrayList<TaskEvent> getResult (){
		return result;
	}
}
