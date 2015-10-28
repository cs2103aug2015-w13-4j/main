package utilities;

//import static org.junit.Assert.*;

//import org.junit.Before;
import org.junit.Test;

public class TaskEventTest {


	@Test
	public void testCreate() {
		TaskEvent task = new TaskEvent(1 , "Hello" , new TaskDate(10,10,2015) , new TaskDate(10,15,2015) , Command_Priority.HIGH);
		assert task != null;
		TaskEvent taskTwo = new TaskEvent(1, "Bye" , new TaskDate(10,10,2015) , new TaskDate(10,15,2015) , Command_Priority.HIGH);
		assert taskTwo != null;
		TaskEvent task3 = new TaskEvent(1, "task3", null, null, Command_Priority.LOW);
		System.out.println("task3:" + task3.toString());
	}
	@Test
	public void testGetter() {
		TaskEvent task = new TaskEvent(1 , "Hello" , new TaskDate(10,10,2015) , new TaskDate(10,15,2015) , Command_Priority.HIGH);
		TaskEvent taskTwo = new TaskEvent(1, "Bye" , new TaskDate(10,10,2015) , new TaskDate(10,15,2015) , Command_Priority.HIGH);
		
		assert task.getStartDate().equals(new TaskDate(10,10,2015) );
		assert taskTwo.getPriority().equals("high");		
	}
	
	

}
