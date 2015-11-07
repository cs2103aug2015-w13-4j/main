package parser;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import utilities.CommandElements;
import utilities.TaskDate;
import utilities.TaskTime;

public class CompleteParserTester {
	Scanner sc = new Scanner(System.in);

	@Test
	public void test() {
		while (true) {
			String test = sc.nextLine();
			CommandElements result;
			try {
				result = CommandParser.ProcessInput(test);
				result.debugPrint();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
