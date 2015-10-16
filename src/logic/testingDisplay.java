package logic;

import static org.junit.Assert.*;

import org.junit.Test;

import utilities.Command_Type;

public class testingDisplay {
	Display display = new Display();
	
	@Test
	public void testOperation() {
		Command_Type op = Command_Type.ADD_TASK;
		String content = "new task";
		String actual = display.operation(op,content);
		String expected = "new task has been added sucessfully";
		assertEquals(expected, actual);
	}
	public void testError(){
		
		String actual = display.error("invalid operation");
		String expected = "invalid operation could not be performed";
		assertEquals(expected,actual);
	}

}
