import java.util.ArrayList;

public class Data {

	public static ArrayList<Task> getAll(){
		ArrayList<Task> wholeFile = getfile();
		
		return wholeFile;
	}
	public static ArrayList<Task> add(Task newTask){
		ArrayList<Task> file = getAll();
		file.add(newTask);
		updateFile();//for storage
		
		return file;
		
	}
	public static ArrayList<Task> display(String word){
		ArrayList<Task> viewList = new ArrayList<Task>();
		ArrayList<Task> file = getAll();
		for(int i =0;i<file.size();i++){
			if(file.get(i).getTask().contains(word)){
				viewList.add(file.get(i));
			}
		}
		return viewList;
		
	}
	
}
