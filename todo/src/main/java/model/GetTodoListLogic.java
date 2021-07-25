package model;

import java.util.List;

import dao.TodoDAO;

public class GetTodoListLogic {
	public List<Todo> executeDone() {
		List<Todo> todoList = execute(true);
		return todoList;
	}
	
	public List<Todo> executeNotDone() {
		List<Todo> todoList = execute(false);
		return todoList;
	}
		
	public List<Todo> execute(boolean doneJudge) {
		TodoDAO dao = new TodoDAO();
		List<Todo> todoList = dao.findTodoList(doneJudge);
		return todoList;
	}
	
}
