package model;

import dao.TodoDAO;

public class TodoUserChangeLogic {
	public boolean execute(String oldUserId,String newUserId) {
		TodoDAO dao = new TodoDAO();
		Boolean result = dao.changeUser(oldUserId, newUserId);
		return result;
	}

}
