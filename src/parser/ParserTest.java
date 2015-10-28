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
		String test = "sunday 2016/01/01";
		TaskDate exDate1 = new TaskDate(2015, 11, 01);
		TaskDate exDate2 = new TaskDate(2016, 01, 01);
		TaskDate results[] = CommandSplitter.findDate(test);
		//System.out.println(results[0].printDate());
		//System.out.println(results[1].printDate());
		
		assertEquals(exDate1.printDate(), results[0].printDate());
		assertEquals(exDate2.printDate(), results[1].printDate());
	}
	
	@Test
	public void testFindDate2() {
		String test = "add \"this shit\" monday to next friday high";
		TaskDate exDate1 = new TaskDate(2015, 10, 26);
		TaskDate exDate2 = new TaskDate(2015, 11, 06);
		TaskDate results[] = CommandSplitter.findDate(test);
		//System.out.println(results[0].printDate());
		//System.out.println(results[1].printDate());
		
		
		assertEquals(exDate1.printDate(), results[0].printDate());
		assertEquals(exDate2.printDate(), results[1].printDate());
	}
	
	@Test
	public void testFindDate3() {
		String test = "edit 1 tomorrow";
		TaskDate results[] = CommandSplitter.findDate(test);
		//System.out.println(results[0].printDate());
		//System.out.println(results[1].printDate());
		
	}
	
	
	@Test
	public void testFindType() {
		String test1 = "21/12/2013 add doomsday";
		assertEquals(Command_Type.ADD_TASK, CommandSplitter.findType(test1));
		
		String test2 = "hello world delete";
		assertEquals(Command_Type.DELETE_TASK, CommandSplitter.findType(test2));
	}
	
	@Test
	public void testDateParser() {
		String test[] = new String[5];
		test[0] = "2015/09/13";
		test[1] = "13/09/2015";
		test[2] = "13/09/15";
		test[3] = "09/13";
		test[4] = "13/09";
		for (int i = 0; i < 5; i ++) {
			//TaskDate result = DateParser.dateDecoder(test[i]);
			//System.out.println(result.printDate());
		}
	}
	
	@Test
	public void testDateTrans() {
		TaskDate origin = new TaskDate(2015,1,1);
		for (int i = -100; i < 100; i ++) {
			//System.out.println(i+ ": " + origin.dayTrans(i).printDate());
		}
	}
	
	@Test
	public void testCase1() {
		String input = "edit 1 startdate tomorrow";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}
	
	
	@Test
	public void testCase2() {
		String input = "edit 1 enddate tomorrow";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}
	
	@Test
	public void testCase3() {
		String input = "edit 1 name \"test\"";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}
	
	@Test
	public void testCase4() {
		String input = "edit 1 priority high";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}
	
	@Test
	public void testCase5() {
		String input = "undo";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}
	
	@Test
	public void testCase6() {
		String input = "search high";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}
	
	@Test
	public void testCase7() {
		String input = "search tomorrow";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}
	
	@Test
	public void testCase8() {
		String input = "finish 1";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}
	
	@Test
	public void testCase9() {
		String input = "directory \"Users/Felix/Desktop/\"";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}
	
	@Test
	public void testCase10() {
		String input = "delete 1";
		CommandParser.ProcessInput(input).debugPrint();
		System.out.println("\n");
	}

}
