package parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.TaskDate;
import utilities.TaskTime;

public class testest {
	Scanner sc = new Scanner(System.in);

	@Test
	public void test() {
		while (true) {
			String test = sc.nextLine();
			TaskTime[] result = CommandSplitter.extractTime(test);
			TaskDate[] rd = CommandSplitter.extractDate(test);
			System.out.println(rd[0].toString() + "  " +result[0].toString());
			System.out.println(rd[1].toString() + "  " +result[1].toString());
			System.out.println("");
		}
	}
}

