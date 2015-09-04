package cs2103t;

import java.util.ArrayList;
import java.util.Scanner;

public class PseudoUI {
	
	public static Scanner myScanner = new Scanner(System.in);
	
	public final static void clearConsole(){
	    try {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows")) {
	            Runtime.getRuntime().exec("cls");
	        } else {
	            Runtime.getRuntime().exec("clear");
	        }
	    } catch (final Exception e) {
	        //  Handle any exceptions.
	    }
	}
	
	public static void printResult(ArrayList<TaskEvent> events) {
		for(int i = 0; i < 50; i ++) {
			System.out.print("_");
		}
		System.out.print("\n");
		for(int i = 0; i < 20; i ++) {
			String thisLine = "| " + String.valueOf(i + 1) + ". " + events.get(i).getName();
			System.out.print(thisLine);
			for (int j = 0; j < 49 - thisLine.length(); j++) {
				System.out.print(" ");
			}
			System.out.print("|");
			System.out.print("\n");
		}
		for(int i = 0; i < 50; i ++) {
			System.out.print("-");
		}
		System.out.print("\n");
	}
	
	public static String readCommand() {
		System.out.print("command:");
		return myScanner.nextLine();
	}
}
