package test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import model.FindReflectionLogic;
import model.NewDayReflecionLogic;

public class LogicTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String userId = "aaa";
		FindReflectionLogic findReflectionLogic = new FindReflectionLogic(); 
		findReflectionLogic.execute(userId);		
	}
	
	public void 作成できるか() {
		String userId = "ccc";
		Date date = new Date(159721599999L);
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);		
		NewDayReflecionLogic logic = new NewDayReflecionLogic();
		boolean result = logic.execute(userId, today, today);
		assertEquals(result,true);
	}
	

}
