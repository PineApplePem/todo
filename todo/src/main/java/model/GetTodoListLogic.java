package model;

import java.util.List;

import dao.TodoDAO;


public class GetTodoListLogic {
	public List<Todo> executeDone(String userId) {
		List<Todo> todoList = execute(true,userId);
		return todoList;
	}
	
	public List<Todo> executeNotDone(String userId) {
		List<Todo> todoList = execute(false,userId);
		return todoList;
	}
		
	public List<Todo> execute(boolean doneJudge,String userId) {
		TodoDAO dao = new TodoDAO();
		List<Todo> todoList = dao.findTodoList(doneJudge,userId);
		return todoList;
	}
	
}
