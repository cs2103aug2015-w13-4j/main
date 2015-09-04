package cs2103t;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cs2103t.TaskEvent;

public class PixelMain {

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
	
	/*public static void main(String[] args){
		readFile();
		//displayEvents();
		while (true) {
			if (understandAndExecute() == -1) {
				break;
			}
		}
		writeFile();
	}
	*/
	
	public static void readFile() {
		String line;
		ArrayList<String> rawContent = new ArrayList<String>();
		try {
            // FileReader reads text files in the default encoding.
            myFr = new FileReader(filePath + fileName);

            // Always wrap FileReader in BufferedReader.
            myBr = new BufferedReader(myFr);

            while((line = myBr.readLine()) != null) {
                rawContent.add(line);
            }    
            myEvents = FileContent.importContent(rawContent);
            //rankEvents();

            // Always close files.
            myBr.close();            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");    
            File m_file = new File(filePath + fileName);
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                   
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
	
	public static void writeFile() {
		try {
			myFw = new FileWriter("fileName");
			myBw = new BufferedWriter(myFw);
			ArrayList<String> printJob = new ArrayList<String>();
			printJob = FileContent.exportContent(myEvents);
			for (int i = 0; i < printJob.size(); i ++) {
				myBw.write(printJob.get(i));
			}
			myBw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
