package logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class testingOperation {
	
	Launch launch = Launch.getInstance();
	Operation operation =Launch.getOperation();
	
	/**
	 * DEADLINE
	 * testing for base case, check if everything is initialize properly
	 */
	//@Test
	public void testAdd() {
		String input = "add this_a_new_task 10/10/2015 high";
		String actual = operation.processOperation(input);
		String expected ="this_a_new_task has been added sucessfully";
		assertEquals(expected,actual);
	}
	/**
	 * test to check if invalid command 
	 */
	//@Test
	//need to change commandelements
	public void testInvalidInput(){
		String input = "bye";
		String actual = operation.processOperation(input);
		String expected = "bye bye could not be performed";
		assertEquals(expected,actual);
	}
	/**
	 * edit
	 */
	//@Test
	public void testEdit(){
		String input = "edit 2 end_date 14/10/2015";
		String actual = operation.processOperation(input);
		String expected = "this_a_new_task has been edited successfully";
		assertEquals(expected,actual);
	}
	
	/**
	 * delete
	 */
	@Test
	public void testDelete(){
		String input =  "delete 2";
		String actual = operation.processOperation(input);
		String expected = "this_a_new_task has been deleted successfully";
		assertEquals(expected,actual);
	}

}
