package login.model;

import login.dao.UserDAO;

public class LoginLogic {
	public User execute(Login login) {
		UserDAO dao = new UserDAO();
		User user = dao.FindByLogin(login);
		return user;
	}

}
