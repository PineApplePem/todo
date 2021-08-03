package test;

import java.sql.Date;
import java.util.List;

import dao.AdditionDAO;
import dao.TodoDAO;
import model.Addition;
import model.Todo;

public class TodoDAOCreateTest {
	

	
	public static void main(String[] args) {
		testCreate1(); //普通に
		testCreate2(); //DeadとDetailが未入力
	}
	
	public static void testCreate1() {
	
	Date dead = new Date(159721599999L);
	Todo todo = new Todo();
	todo.setTodo("本を読む");
	todo.setDead(dead);
	todo.setDetail("趣味の本");
	
	TodoDAO dao = new TodoDAO();
	boolean result = dao.create(todo);
	System.out.println(result);
	}

	public static void testCreate2() {
	
	Todo todo = new Todo();
	todo.setTodo("本を読む");
	todo.setDead(null);
	todo.setDetail("");

	TodoDAO dao = new TodoDAO();
	boolean result = dao.create(todo);
	System.out.println(result);
	}
	
}
