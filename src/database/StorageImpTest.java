package database;

import org.junit.Test;

import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskEvent;
import utilities.TaskTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

// @@author A0130503B Zhongwei
/**
 * Junit testing of StorageImp
 * 
 * @author Zhongwei
 *
 */
public class StorageImpTest {

	static TaskDate d1 = new TaskDate(2015, 1, 1);
	static TaskDate d2 = new TaskDate(2015, 2, 2);
	static TaskDate d3 = new TaskDate(2014, 2, 28);
	static TaskDate d4 = new TaskDate(2017, 4, 1);
	static TaskDate d6 = new TaskDate(9999, 9, 9);
	static TaskTime t1 = new TaskTime(10, 10);
	static TaskTime t2 = new TaskTime(11, 11);
	static TaskTime t3 = new TaskTime(12, 12);
	static TaskTime t4 = new TaskTime(8, 00);
	static String n1 = "visit grandma";
	static String n2 = "visit zoo";
	static String n3 = "pay bill $10";
	static String n4 = "pay clyde $10";
	static final Command_Priority F = Command_Priority.FLAG;
	static final Command_Priority U = Command_Priority.UNFLAG;
	StorageImp s = StorageImp.getInstance();

	@Test
	public void testGetTaskCount() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		assertEquals(4, s.getTaskCount());
	}

	@Test
	public void testAddTask() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		s.addTask("three", d1, t1, d2, t2, Command_Priority.FLAG);
		assertEquals(1, s.getTaskCount());
	}

	@Test
	public void testEditTask() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		s.editTask(1, Command_Field.NAME, "new");
		s.editTask(1, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.PRIORITY, Command_Priority.FLAG.toString());
		s.editTask(4, Command_Field.START_TIME, t3.toString());
		ArrayList<TaskEvent> list = s.loadAllTasks();
		assertEquals("new", list.get(0).getTaskName());
		assertEquals(d3, list.get(0).getEndDate());
		assertEquals(d3, list.get(3).getEndDate());
		assertEquals(F, list.get(3).getPriority());
		assertEquals(t3, list.get(3).getStartTime());
	}

	@Test
	public void testDeleteTaskByID() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		s.deleteTaskByID(1);
		ArrayList<TaskEvent> list = s.loadAllTasks();
		assertNotSame(1, list.get(0).getTaskID());
	}

	@Test
	public void testMarkTaskAsDone() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		s.markTaskAsDone(1);
		ArrayList<TaskEvent> list = s.loadAllTasks();
		assertEquals(true, list.get(0).isCompleted());
	}

	@Test
	public void testMarkTaskAsUndone() throws Exception {
		FileUtils.clearFileContent(s.getDirectory());
		generateTasks();
		s.markTaskAsUndone(1);
		ArrayList<TaskEvent> list = s.loadAllTasks();
		assertEquals(false, list.get(0).isCompleted());
	}

	@Test
	public void testPathTypeOfAnEmptyString() {
		assertFalse(s.changeDirectory(""));
	}

	@Test
	public void testChangeDirectory() throws Exception {
		assertTrue(s.changeDirectory("/Users/zhongwei-z/Desktop/new folder"));
		assertTrue(s.changeDirectory("/Users/zhongwei-z/Desktop/task2.txt"));
		assertTrue(s.changeDirectory("."));
		assertFalse(s.changeDirectory("dgegeae.aegaa.aega.aeg/aeg/ar//rg"));
	}

	private void generateTasks() {
		s.addTask(n1, d1, t1, d2, t2, F);
		s.addTask(n2, d2, t2, d3, t3, U);
		s.addTask(n3, d3, t3, d4, t4, U);
		s.addTask(n4, d4, t4, d1, t1, U);
	}
}