package database;

import org.junit.Test;
import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskEvent;

import java.util.ArrayList;

/**
 * Created by zhongwei-z on 27/10/15.
 */
public class StorageImpTest {

	static TaskDate d1 = new TaskDate(2015, 1, 1);
	static TaskDate d2 = new TaskDate(2015, 2, 2);
	static TaskDate d3 = new TaskDate(9999, 9, 9);
	
	@Test
	public void testGetTaskCount() throws Exception {
		Storage s = StorageImp.getInstance();
		System.out.println("taskcount (in test):"+s.getTaskCount());
	}

	@Test
	public void testAddTask() throws Exception {
		Storage s = StorageImp.getInstance();
		s.addTask("three", d1, d2, Command_Priority.HIGH);
	}

	@Test
	public void testEditTask() throws Exception {
		Storage s = StorageImp.getInstance();
		//s.editTask(1, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.END_DATE, d3.toString());
		s.editTask(4, Command_Field.PRIORITY, Command_Priority.LOW.toString());
	}

	@Test
	public void testDeleteTaskByID() throws Exception {
		Storage s = StorageImp.getInstance();
		s.deleteTaskByID(1);
	}

	//@Test
	public void testMarkTaskAsDone() throws Exception {
		Storage s = StorageImp.getInstance();
		s.markTaskAsDone(1);
	}

	@Test
	public void testMarkTaskAsUndone() throws Exception {
		Storage s = StorageImp.getInstance();
		s.markTaskAsUndone(1);
	}

	@Test
	public void testSearchTaskByID() throws Exception {

	}

	@Test
	public void testLoadAllTasks() throws Exception {
		Storage s = StorageImp.getInstance();
		ArrayList<TaskEvent> array = s.loadAllTasks();
		for (TaskEvent task: array) {
			System.out.println(task);
		}
	}

	@Test
	public void testCheckSaveFileState() throws Exception {

	}

	@Test
	public void testCheckPrefFileState() throws Exception {

	}

	@Test
	public void testChangeDirectory() throws Exception {

	}
}