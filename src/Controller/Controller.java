import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	private static Scanner scanner = new Scanner(System.in);
	public static void welcome(){
		File file = new File(getFileName());
		ArrayList<Task> tasks = readFile(file);
	}
	public static String getFileName(){
		System.out.println("Welcome to Pixelist. Enter your file name");
		String file = scanner.next();
		return file;
	}
	public static void command(){
		int command = getCommandType();
		if(command == 0){
			add();
		} else if(command == 1){
			display();
		}
		else {
			
		}
	}
	public static void add(){
		Task newTask = getContext();
		
		
		defaultView();
	}
	public static void display(){
		String word = getWord();
		//all
		if(word =="all"){
			defaultView();
		}
		
	}
	
	//to update the whole list in default
	public static void defaultView(){
		
	}
}
