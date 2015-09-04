package cs2103t;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TestMain {
	
	public static ArrayList<TaskEvent> myEvents = new ArrayList<TaskEvent>();
	public static TaskEvent myEvent;
	public static String myCommand;
	public static FileWriter myFw;
	public static FileReader myFr;
	public static Scanner myScanner = new Scanner(System.in);
	public static BufferedWriter myBw;
	public static BufferedReader myBr;
	public static String filePath = "C:/Users/MasterFelix/Desktop/";
	public static String fileName = "my tasks.txt";
	public static FileContent myContent = new FileContent();

	public static void main(String[] args){
		ArrayList<String> rawContent = new ArrayList<String>();
		int i = 10;
		while (i>0) {
			rawContent.add(myScanner.nextLine());
			i--;
		}
		myEvents = FileContent.importContent(rawContent);
		PseudoUI.printResult(myEvents);
	}
}
