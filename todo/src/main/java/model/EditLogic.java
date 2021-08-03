package model;

import dao.TodoDAO;

public class EditLogic {
	public boolean execute(Todo oldTodo,Todo newTodo) {
		TodoDAO dao = new TodoDAO();
		boolean result = dao.edit(oldTodo,newTodo);
		return result;
	}	
}
