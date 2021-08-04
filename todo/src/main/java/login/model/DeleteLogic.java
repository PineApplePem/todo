package login.model;

import login.dao.UserDAO;

public class DeleteLogic {
	public boolean execute(String id) {
		UserDAO dao = new UserDAO();
		boolean result = dao.Delete(id);
		return result;

	}

}
