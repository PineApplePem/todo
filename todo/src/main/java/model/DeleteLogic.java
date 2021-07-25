package model;

import dao.TodoDAO;

public class DeleteLogic {
	public boolean execute(Todo todo) {
		TodoDAO dao = new TodoDAO();
		Boolean delete = dao.delete(todo);
		return delete;
	}
}
