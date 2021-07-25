package test;

import java.util.List;

import model.GetTodoListLogic;
import model.Todo;

public class GetTodoLogicTest {
	public static void main(String[] args) {
		GetTodoListLogic logic = new GetTodoListLogic();
		List<Todo> doneTodoList = logic.executeDone();
		
		List<Todo> notDoneTodoList = logic.executeNotDone();
		
		for(Todo todo : doneTodoList) {
		System.out.println(todo.getNumber());
		System.out.println(todo.getTodo());
		System.out.println(todo.getDead());
		System.out.println(todo.getDetail());
		System.out.println(todo.getDone());
		}
		
		for(Todo todo : notDoneTodoList) {
		System.out.println(todo.getNumber());
		System.out.println(todo.getTodo());
		System.out.println(todo.getDead());
		System.out.println(todo.getDetail());
		System.out.println(todo.getDone());
		}
	}
}
