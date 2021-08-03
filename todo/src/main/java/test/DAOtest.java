package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.TodoDAO;
import model.GetTodoListLogic;
import model.Todo;

public class DAOtest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void 値が入力できるか() {
		Date date = new Date(159721599999L);
		Todo todo = new Todo();
		todo.setUserId("penguin");
		todo.setTodo("本を読む");
		todo.setDead(date);
		todo.setStart(date);
		todo.setDetail("趣味の本");
		
		TodoDAO dao = new TodoDAO();
		boolean result = dao.create(todo);
		
		assertEquals(result,true);
		
	}
	
	@Test
	public void データを取り出せるか() {
		GetTodoListLogic logic = new GetTodoListLogic();
		String userId = "penguin";
		
		List<Todo> doneTodoList = logic.executeDone(userId);
		
		Date date = new Date(159721599999L);
		
		for(Todo todo : doneTodoList) {
			assertEquals("penguin",todo.getUserId());
			assertEquals("本を読む",todo.getTodo());
			assertEquals(date,todo.getDead());
			assertEquals(date,todo.getStart());
			assertEquals("趣味の本",todo.getDetail());
		}
	}	

	@Test
	public void データの編集ができるか() {
		Date date = new Date(159721599999L);
		Todo todo = new Todo();
		todo.setUserId("ccc");
		todo.setTodo("洗濯やーめた");
		todo.setDead(date);
		todo.setStart(date);
		todo.setDetail("つかれたのら");
		
		Todo oldTodo= new Todo();
		oldTodo.setNumber(681);
		
		TodoDAO dao = new TodoDAO();
		boolean result = dao.edit(oldTodo,todo);
		
		assertEquals(result,true);
		
	}
	
	@Test
	public void 完了未完の切り替え() {
		int number = 641;
			
		Todo todo = new Todo();
		todo.setNumber(number);
				
		TodoDAO dao = new TodoDAO();
		boolean result = dao.done(todo);
		
		assertEquals(true,result);
	}
	
	@Test
	public void 反省用データの取り出し0() {
		String userId = "ccc";
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);		
		
		TodoDAO dao = new TodoDAO();
		
		
		List<Todo> todoList = dao.findByReflection(userId,0,today,today);
		
		
		for(Todo todo : todoList) {
			assertEquals("ccc",todo.getUserId());
			assertEquals("さささ",todo.getTodo());
		}
	}

	@Test
	public void 反省用データの取り出し1() {
		String userId = "aaa";
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);		
		
		TodoDAO dao = new TodoDAO();
		
		
		List<Todo> todoList = dao.findByReflection(userId,1,today,today);
		
		
		for(Todo todo : todoList) {
			assertEquals("aaa",todo.getUserId());
			assertEquals("買い出し",todo.getTodo());
		}
	}
	
	@Test
	public void 反省用データの取り出し2() {
		String userId = "aaa";
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);		
		
		TodoDAO dao = new TodoDAO();
		
		
		List<Todo> todoList = dao.findByReflection(userId,2,today,today);
		
		
		for(Todo todo : todoList) {
			assertEquals("aaa",todo.getUserId());
			assertEquals("勉強",todo.getTodo());
		}
	}
	
	@Test
	public void 反省用データの取り出し3() {
		String userId = "aaa";
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);		
		
		TodoDAO dao = new TodoDAO();
		
		
		List<Todo> todoList = dao.findByReflection(userId,3,today,today);
		
		
		for(Todo todo : todoList) {
			assertEquals("aaa",todo.getUserId());
			assertEquals("洗濯",todo.getTodo());
		}
	}
}	
