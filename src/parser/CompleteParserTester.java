package parser;

import java.util.Scanner;

import org.junit.Test;

import utilities.CommandElements;

//@@author A0133965X Tianrui
/**
 * Junit testing for parser class
 * 
 * @author Tianrui
 *
 */
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
				e.printStackTrace();
			}
		}
	}
}
