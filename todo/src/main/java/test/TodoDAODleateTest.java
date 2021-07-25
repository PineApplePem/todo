package test;

import dao.TodoDAO;
import model.Todo;

public class TodoDAODleateTest {
		
		public static void main(String[] args) {
			testDelete();
		}
	
	
		public static void testDelete() {
		
		Todo todo = new Todo();
		todo.setNumber(35);
		
		TodoDAO dao = new TodoDAO();
		boolean result = dao.delete(todo);
		System.out.println(result);
		}
	
}
