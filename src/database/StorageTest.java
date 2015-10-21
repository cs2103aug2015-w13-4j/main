package database;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;

import utilities.TaskDate;
import utilities.TaskEvent;

public class StorageTest {
	Storage s = new Storage();
	String n = "n";
	TaskDate sd = new TaskDate(2016, 2, 28);
	TaskDate ed = new TaskDate(2016, 3, 29);
	String p = "high";
	private BufferedReader r;
	
	//@Test
	public void testAddOneTask() throws Exception {
		//s.clearSavingFile();
		assertTrue(s.addTask(n, sd, ed, p));
		r = new BufferedReader(new FileReader(new File("user_tasks.txt")));
		assertEquals("1", r.readLine());
		r.close();
	}
	
	@Test
	public void testAddMultipleTasks() throws Exception {
		r = new BufferedReader(new FileReader(new File("user_tasks.txt")));
		assertTrue(s.addTask(n,sd,ed,p));
		assertTrue(s.addTask(n,sd,ed,p));
		assertTrue(s.addTask(n,sd,ed,p));
		assertTrue(s.addTask(n,sd,ed,p));
	}
	
	@Test
	public void testEditTask() {
		assertTrue(s.editTask(2,"name", "DOGEDOGE"));
		assertTrue(s.editTask(3,"name", "CHANGED"));
		assertTrue(s.editTask(3, "startdate", "11/11/2011"));
		assertTrue(s.editTask(4, "enddate", "11/11/11"));
		assertTrue(s.editTask(1, "priority", "low"));
	}
	
	//@Test
	public void testDeleteTask() {
		assertTrue(s.delete(1));
	}
	
	//@Test
	public void testReload() {
		ArrayList<TaskEvent> arr = s.load();
		for (TaskEvent t: arr) {
			System.out.println(t.toString());
		}
	}

}
