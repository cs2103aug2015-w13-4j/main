package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import utilities.Command_Type;
import utilities.TaskEvent;

public class testingDisplay {
	Launch launch = Launch.getInstance();
	Display display = launch.getDisplay();

	@Test
	/**
	 * testing to see if there is a correct output when command exist
	 */
	public void testOperation() {
		Command_Type op = Command_Type.ADD_TASK;
		String content = "new task";
		String actual = display.operation(op,content);
		String expected = "new task has been added sucessfully";
		assertEquals(expected, actual);
	}
	/**
	 * testing to see if the feddback is correct when error method is called
	 */
	public void testError(){

		String actual = display.error("invalid operation");
		String expected = "invalid operation could not be performed";
		assertEquals(expected,actual);
	}
	public void testDisplay(){
		ArrayList<TaskEvent> actual = display.taskView();
		String ex
	}

}
