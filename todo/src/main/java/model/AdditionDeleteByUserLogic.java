package model;

import dao.AdditionDAO;

public class AdditionDeleteByUserLogic {
	public boolean execute(String userId) {
		AdditionDAO dao = new AdditionDAO();
		Boolean result = dao.deleteUser(userId);
		return result;
	}
}
