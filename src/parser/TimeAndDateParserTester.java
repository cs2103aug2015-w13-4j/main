package parser;

import java.util.Scanner;

import org.junit.Test;

import utilities.TaskDate;
import utilities.TaskTime;

// @@author A0133965X Tianrui
/**
 * Junit testing for parsing time and date
 * 
 * @author Tianrui
 *
 */
public class TimeAndDateParserTester {
	Scanner sc = new Scanner(System.in);

	@Test
	public void test() {
		while (true) {
			String test = sc.nextLine();
			TaskTime[] result = CommandSplitter.extractTime(test);
			TaskDate[] rd = CommandSplitter.extractDate(test);
			System.out.println(rd[0].toString() + "  " + result[0].toString());
			System.out.println(rd[1].toString() + "  " + result[1].toString());
			System.out.println("");
		}
	}
}
