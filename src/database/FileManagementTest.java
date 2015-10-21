package database;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

public class FileManagementTest {
	static String directory = "testFileManagement.txt";
	static String task1 = "name:name1&&date:date1&&priority:priority1&&description:des1";
	static String task2 = "name:name2&&date:date2&&priority:priority2&&description:des2";
	static String task3 = "name:name3&&date:date3&&priority:priority3&&description:des3";

	private BufferedReader br;
	
	@Test
	public void testAddTask() throws Exception {
		FileManager fm = new FileManager(directory);
		fm.clearFile();
		fm.addTask(1, task1);
		br = new BufferedReader(new FileReader(new File(directory)));
		String str = br.readLine();
		assertEquals("1&&name:name1&&date:date1&&priority:priority1&&description:des1", str);
	}

	@Test
	public void testEditTask() throws Exception {
		FileManager fm = new FileManager(directory);
		fm.clearFile();
		fm.addTask(1, task1);
		fm.addTask(2, task2);
		fm.addTask(3, task3);
		fm.editTask(3, "date", "date123");
		br = new BufferedReader(new FileReader(new File(directory)));
		br.readLine();
		assertEquals("3&&name:name3&&date:date123&&priority:priority3&&description:des3",br.readLine());
	}

	@Test
	public void testDeleteTask() throws Exception {
		FileManager fm = new FileManager(directory);
		fm.clearFile();
		fm.addTask(1, task1);
		fm.addTask(2, task2);
		fm.deleteTask(1);
		br = new BufferedReader(new FileReader(new File(directory)));
		String str = br.readLine();
		assertEquals("2&&"+task2, str);
	}
}
