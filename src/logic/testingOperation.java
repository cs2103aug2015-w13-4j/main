package logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class testingOperation {
	
	Launch launch = Launch.getInstance();
	Operation operation =launch.getOperation();
	
	@Test
	/**
	 * testing for base case, check if everything is initialize properly
	 */
	public void testProcessOperation() {
		String input = "add this_is_a_task 10/10/2015 high";
		String actual = operation.processOperation(input);
		String expected ="this_is_a_task has been added sucessfully";
		assertEquals(expected,actual);
	}
	/**
	 * test to check if invalid command 
	 */
	@Test
	public void testInvalidInput(){
		String input = "bye";
		String actual = operation.processOperation(input);
		String expected = "bye bye could not be performed";
		assertEquals(expected,actual);
	}

}
