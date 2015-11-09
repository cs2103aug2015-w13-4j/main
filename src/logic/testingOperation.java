package logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class testingOperation {
	
	Launch launch = Launch.getInstance();
	Operation operation =Launch.getOperation();
	@Test
	public void testAll(){
		testAdd();
		testInvalidInput();
		testEdit();
		testDelete();
	}
	
	/**
	 * DEADLINE
	 * testing for base case, check if everything is initialize properly
	 */
	public void testAdd() {
		String input = "add \"a new task\" 10/10/2015 flag";
		String actual = operation.processOperation(input);
		String expected ="a new task has been added sucessfully";
		assertEquals(expected,actual);
	}
	/**
	 * test to check if invalid command 
	 */
	public void testInvalidInput(){
		String input = "bye";
		String actual = operation.processOperation(input);
		String expected = "bye bye could not be performed";
		assertEquals(expected,actual);
	}
	/**
	 * edit
	 */
	public void testEdit(){
		String input = "edit 2 end_date 14/10/2015";
		String actual = operation.processOperation(input);
		String expected = "a new_task has been edited successfully";
		assertEquals(expected,actual);
	}
	
	/**
	 * delete
	 */
	public void testDelete(){
		String input =  "delete 2";
		String actual = operation.processOperation(input);
		String expected = "a new task has been deleted successfully";
		assertEquals(expected,actual);
	}
}
