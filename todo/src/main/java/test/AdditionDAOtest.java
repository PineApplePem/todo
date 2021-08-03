package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.AdditionDAO;
import model.Addition;


public class AdditionDAOtest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void 値が入力できるか() {
		Addition addition = new Addition();
		addition.setUserId("aaa");
		addition.setTodoId(2);
		addition.setComment("時間がかかった");
		addition.setType("その他");
		
		AdditionDAO dao = new AdditionDAO();
		boolean result = dao.create(addition);
		
		assertEquals(result,true);
		
	}
	
	@Test
	public void データを取り出せるか() {
		int Todo_id = 2;
		
		AdditionDAO dao = new AdditionDAO();
		
		List<Addition> additionList = dao.findAdditionList(Todo_id);
		
		for(Addition addition : additionList) {
		System.out.println(addition.getUserId());
		System.out.println(addition.getTodoId());
		System.out.println(addition.getType());
		System.out.println(addition.getComment());
		}
		
		/*for(Addition addition : additionList) {
			assertEquals("aaa",addition.getUserId());
			assertEquals(2,addition.getTodoId());
			assertEquals("時間がかかった",addition.getComment());
			assertEquals(3,addition.getId());
			assertEquals("その他",addition.getType());
		}
	  */
	}
	
	@Test
	public void 値が削除できるか() {
		
		Addition addition = new Addition();
		addition.setId(2);
		
		AdditionDAO dao = new AdditionDAO();

		boolean result = dao.delete(addition);
		
		assertEquals(result,true);
		
	}
	
	
}
