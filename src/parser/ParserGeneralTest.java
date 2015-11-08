package parser;

import static org.junit.Assert.*;

import org.junit.Test;

import utilities.CommandElements;
import utilities.Command_Field;
import utilities.Command_Priority;
import utilities.Command_Type;

// @@author A0133965X
public class ParserGeneralTest {
	
	@Test
	public void testCase0() throws Exception {
		String input = "add \"the test\" 11/12/2015 to 1/2/2016";
		String expectedName = "the test";
		String expectedStartDate = "11/12/2015";
		String expectedEndDate = "01/02/2016";
		Command_Priority expectedPriority = Command_Priority.UNFLAG;
		Command_Type expectedType = Command_Type.ADD_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedName, actual.getName());
		assertEquals(expectedStartDate, actual.getStartDate().printDate());
		assertEquals(expectedEndDate, actual.getEndDate().printDate());
		assertEquals(expectedPriority, actual.getPriority());
		assertEquals(expectedType, actual.getType());
	}
	
	@Test
	public void testCase1() throws Exception {
		String input = "edit 1 startdate 1/2/2016";
		int expectedObject = 1;
		Command_Field expectedField = Command_Field.START_DATE;
		String expectedStartDate = "01/02/2016";
		Command_Type expectedType = Command_Type.EDIT_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedObject, actual.getID());
		assertEquals(expectedStartDate, actual.getStartDate().printDate());
		assertEquals(expectedField, actual.getField());
		assertEquals(expectedType, actual.getType());
	}
	
	
	@Test
	public void testCase2() throws Exception {
		String input = "delete 2";
		int expectedObject = 2;
		Command_Type expectedType = Command_Type.DELETE_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedObject, actual.getID());
		assertEquals(expectedType, actual.getType());
	}
	
	@Test
	public void testCase3() throws Exception {
		String input = "search 1/2/16";
		String expectedEndDate = "01/02/2016";
		Command_Type expectedType = Command_Type.SEARCH_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedEndDate, actual.getEndDate().printDate());
		assertEquals(expectedType, actual.getType());
	}
	
	@Test
	public void testCase4() throws Exception {
		String input = "flag 1";
		int expectedObject = 1;
		Command_Type expectedType = Command_Type.FLAG_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
		assertEquals(expectedObject, actual.getID());
	}
	
	@Test
	public void testCase5() throws Exception {
		String input = "undo";
		Command_Type expectedType = Command_Type.UNDO;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
	}
	
	@Test
	public void testCase6() throws Exception {
		String input = "unflag 1";
		int expectedObject = 1;
		Command_Type expectedType = Command_Type.UNFLAG_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
		assertEquals(expectedObject, actual.getID());
	}
	
	@Test
	public void testCase7() throws Exception {
		String input = "redo";
		Command_Type expectedType = Command_Type.REDO;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
	}
	
	@Test
	public void testCase8() throws Exception {
		String input = "finish 1";
		int expectedObject = 1;
		Command_Type expectedType = Command_Type.FINISH_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
		assertEquals(expectedObject, actual.getID());
	}
	
	@Test
	public void testCase9() throws Exception {
		String input = "unfinish 1";
		int expectedObject = 1;
		Command_Type expectedType = Command_Type.UNFINISH_TASK;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedType, actual.getType());
		assertEquals(expectedObject, actual.getID());
	}
	
	@Test
	public void testCase10() throws Exception {
		String input = "cd \"User/xxx/Downloads\"";
		String expectedDir = "User/xxx/Downloads";
		Command_Type expectedType = Command_Type.DIRECTORY;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedDir, actual.getName());
		assertEquals(expectedType, actual.getType());
	}
	
	/*
	@Test
	public void testCase() throws Exception {
		String input = "add \"the test\" 11/12/2015 to 1/2/2016";
		String expectedName = "the test";
		String expectedStartDate = "11/12/2015";
		String expectedEndDate = "01/02/2016";
		int expectedObject = 1;
		Command_Priority expectedPriority = Command_Priority.UNFLAG;
		Command_Type expectedType = Command_Type.ADD_TASK;
		Command_Field expectedField = Command_Field.END_DATE;
		CommandElements actual = CommandParser.ProcessInput(input);
		assertEquals(expectedName, actual.getName());
		assertEquals(expectedStartDate, actual.getStartDate().printDate());
		assertEquals(expectedEndDate, actual.getEndDate().printDate());
		assertEquals(expectedField, actual.getField());
		assertEquals(expectedPriority, actual.getPriority());
		assertEquals(expectedType, actual.getType());
		assertEquals(expectedObject, actual.getID());
	}
	*/

}
