package login.model;

import login.dao.UserDAO;

public class CreateLogic {
	public int execute(User user) {
		UserDAO dao = new UserDAO();
		int result = dao.Create(user);
		return result;

	}
}
