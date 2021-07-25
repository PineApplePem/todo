package test;

import dao.TodoDAO;
import model.Todo;

//二回行う(true,falseの入れ替えができているかの確認のため）

public class TodoDAODoneTest {
	public static void main(String[] args) {
		testDone();
	}
		
	public static void testDone() {
		
	int number = 422;
			
	Todo todo = new Todo();
	todo.setNumber(number);
		
	TodoDAO dao = new TodoDAO();
	boolean result = dao.done(todo);
	System.out.println(result);
	}
}
