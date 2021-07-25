package model;

import dao.TodoDAO;

public class DoneLogic {
	public boolean execute(Todo todo) {
		TodoDAO dao = new TodoDAO();
		boolean done = dao.done(todo);
		return done;
	}
}

	