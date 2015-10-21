package utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TaskEventTest {


	@Test
	public void testCreate() {
		TaskEvent task = new TaskEvent(1 , "Hello" , new TaskDate(10,10,2015) , new TaskDate(10,15,2015) , "high");
		assert task != null;
		TaskEvent taskTwo = new TaskEvent(1, "Bye" , new TaskDate(10,10,2015) , new TaskDate(10,15,2015) , 1);
		assert taskTwo != null;
	}
	@Test
	public void testGetter() {
		TaskEvent task = new TaskEvent(1 , "Hello" , new TaskDate(10,10,2015) , new TaskDate(10,15,2015) , "high");
		TaskEvent taskTwo = new TaskEvent(1, "Bye" , new TaskDate(10,10,2015) , new TaskDate(10,15,2015) , 1);
		
		assert task.getStartDate().equals(new TaskDate(10,10,2015) );
		assert taskTwo.getPriority().equals("high");		
	}
	
	

}
