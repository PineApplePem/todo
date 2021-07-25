package model;

import dao.TodoDAO;

public class NewTodoLogic {
	public boolean execute(Todo todo) {
		TodoDAO dao = new TodoDAO();
		boolean create = dao.create(todo);
		return create;
	}
}
