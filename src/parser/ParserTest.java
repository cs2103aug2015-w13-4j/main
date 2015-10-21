package parser;

import static org.junit.Assert.*;

import org.junit.Test;

import utilities.Command_Type;
import utilities.TaskDate;

public class ParserTest {

	@Test
	public void testDateChecker() {
		String test1 = "12/22/12";
		String test2 = "q12/123";
		assertEquals(true, CommandSplitter.dateChecker(test1));
		assertEquals(false, CommandSplitter.dateChecker(test2));
	}
	
	@Test
	public void testFindDate() {
		String test = "the show is going on from 21/12/2012 to 01/01/2013";
		TaskDate exDate1 = new TaskDate(2012, 12, 21);
		TaskDate exDate2 = new TaskDate(2013, 01, 01);
		TaskDate results[] = CommandSplitter.findDate(test);
		
		assertEquals(exDate1.printDate(), results[0].printDate());
		assertEquals(exDate2.printDate(), results[1].printDate());
	}
	
	@Test
	public void testFindType() {
		String test1 = "21/12/2013 add doomsday";
		assertEquals(Command_Type.ADD_TASK, CommandSplitter.findType(test1));
		
		String test2 = "hello world delete";
		assertEquals(Command_Type.DELETE_TASK, CommandSplitter.findType(test2));
	}

}
