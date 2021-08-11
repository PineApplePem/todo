package model;

import dao.AdditionDAO;

public class AdditionUserChangeLogic {
	public boolean execute(String oldUserId,String newUserId) {
		AdditionDAO dao = new AdditionDAO();
		Boolean result = dao.changeUser(oldUserId,newUserId);
		return result;
	}

}
