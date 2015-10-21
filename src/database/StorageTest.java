package database;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import utilities.TaskDate;

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
		assertTrue(s.editTask(1,"name", "DOGEDOGE"));
		assertTrue(s.editTask(13,"name", "CHANGED"));
	}
	
	@Test
	public void testDeleteTask() {
		assertTrue(s.delete(1));
	}

}
