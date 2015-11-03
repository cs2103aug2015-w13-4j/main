package database;

import database.StorageImp;
import database.StorageImp.PathType;

import org.junit.Test;
import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskEvent;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by zhongwei-z on 27/10/15.
 */
public class StorageImpTest {

	static TaskDate d1 = new TaskDate(2015, 1, 1);
	static TaskDate d2 = new TaskDate(2015, 2, 2);
	static TaskDate d3 = new TaskDate(9999, 9, 9);
	Storage s = StorageImp.getInstance();
	
	@Test
	public void testGetTaskCount() throws Exception {
		System.out.println("taskcount (in test):"+s.getTaskCount());
	}

	@Test
	public void testAddTask() throws Exception {
		s.addTask("three", d1, d2, Command_Priority.FLAG);
	}

	@Test
	public void testEditTask() throws Exception {
		//s.editTask(1, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.PRIORITY, Command_Priority.UNFLAG.toString());
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