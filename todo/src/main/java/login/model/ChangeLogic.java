package login.model;

import login.dao.UserDAO;

public class ChangeLogic {
	public int execute(User currentUser,User newUser) {
		UserDAO dao = new UserDAO();
		int result = dao.Change(currentUser,newUser);
		return result;

	}

}
