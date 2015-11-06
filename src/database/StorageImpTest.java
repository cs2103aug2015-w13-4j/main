package database;

import org.junit.Test;
import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskTime;

import static org.junit.Assert.*;

// @@author A0130503B

public class StorageImpTest {

	static TaskDate d1 = new TaskDate(2015, 1, 1);
	static TaskDate d2 = new TaskDate(2015, 2, 2);
	static TaskDate d3 = new TaskDate(9999, 9, 9);
	static TaskTime t1 = new TaskTime(10, 10);
	static TaskTime t2 = new TaskTime(11, 11);
	static TaskTime t3 = new TaskTime(12, 12);
	Storage s = StorageImp.getInstance();

	@Test
	public void testGetTaskCount() throws Exception {
		System.out.println("taskcount (in test):"+s.getTaskCount());
	}

	@Test
	public void testAddTask() throws Exception {
		s.addTask("three", d1, t1, d2, t2, Command_Priority.FLAG);
	}

	@Test
	public void testEditTask() throws Exception {
		s.editTask(1, Command_Field.NAME, "new");
		s.editTask(1, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.PRIORITY, Command_Priority.FLAG.toString());
		s.editTask(4, Command_Field.START_TIME, t3.toString());
	}

	@Test
	public void testDeleteTaskByID() throws Exception {
		s.deleteTaskByID(1);
	}

	@Test
	public void testMarkTaskAsDone() throws Exception {
		s.markTaskAsDone(1);
	}

	@Test
	public void testMarkTaskAsUndone() throws Exception {
		s.markTaskAsUndone(1);
	}

	@Test
	public void testSearchTaskByID() throws Exception {

	}

	@Test
	public void testLoadAllTasks() throws Exception {

	}

	@Test
	public void testPathTypeOfAnEmptyString() {
		assertFalse(s.changeDirectory(""));
	}

	@Test
	public void testChangeDirectory() throws Exception {
		assertTrue(s.changeDirectory("/Users/zhongwei-z/Desktop/new folder"));
		assertTrue(s.changeDirectory("/Users/zhongwei-z/Desktop/task2.txt"));
		assertFalse(s.changeDirectory("dgegeae.aegaa.aega.aeg/aeg/ar//rg"));
	}
}