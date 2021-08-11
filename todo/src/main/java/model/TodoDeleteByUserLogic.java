package model;

import dao.TodoDAO;

public class TodoDeleteByUserLogic {
	public boolean execute(String userId) {
		TodoDAO dao = new TodoDAO();
		Boolean result = dao.deleteUser(userId);
		return result;
	}
}
