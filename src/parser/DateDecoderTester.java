package parser;

import java.util.Scanner;

import org.junit.Test;

import utilities.CommandElements;
import utilities.TaskDate;

public class DateDecoderTester {

	Scanner sc = new Scanner(System.in);

	@Test
	public void test() {
		while (true) {
			String test = sc.nextLine();
			TaskDate result;
			try {
				result = DateParser.dateDecoder(test);
				System.out.println(result.printDate());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
