package parser;

import static org.junit.Assert.*;

import org.junit.Test;

import utilities.Command_Field;
import utilities.Command_Type;
import utilities.TaskDate;
import utilities.TaskTime;

public class ParserMethodTest {

	@Test
	public void FindNameTest() {
		String input = "add \"drink coffee\" tomorrow";
		String expected = "drink coffee";
		String actual = CommandSplitter.findName(input);
		assertEquals(expected, actual);
	}
	
	@Test
	public void FindTypeTest() {
		String input = "tomorrow add \"study\" until next friday";
		Command_Type expected = Command_Type.ADD_TASK;
		Command_Type actual = CommandSplitter.findType(input);
		assertEquals(expected, actual);
	}
	
	@Test
	public void FindFieldTest() {
		String input = "2 enddate edit to tomorrow";
		Command_Field expected = Command_Field.END_DATE;
		Command_Field actual = CommandSplitter.findField(input);
		assertEquals(expected, actual);
	}

	@Test
	public void ExtactDateTest() {
		String input = "11/12/2015 blah blah 3.9";
		TaskDate expectedEndDate = new TaskDate(2015, 12, 11);
		TaskDate expectedStartDate = new TaskDate(2015, 3, 9);
		TaskDate actual[] = CommandSplitter.extractDate(input);
		assertEquals(expectedStartDate.printDate(), actual[0].printDate());
		assertEquals(expectedEndDate.printDate(), actual[1].printDate());
	}
	
	@Test
	public void ExtactTimeTest() {
		String input = "tomorrow 5am today 17:33";
		TaskTime expectedFirstTime = new TaskTime(17, 33);
		TaskTime expectedSecondTime = new TaskTime(5,0);
		TaskTime actual[] = CommandSplitter.extractTime(input);
		assertEquals(expectedFirstTime.toString(), actual[0].toString());
		assertEquals(expectedSecondTime.toString(), actual[1].toString());
	}
}
