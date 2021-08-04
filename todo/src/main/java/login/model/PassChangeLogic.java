package login.model;

import login.dao.UserDAO;

public class PassChangeLogic {
	public boolean execute(String id, byte[] pass) {
		UserDAO dao = new UserDAO();
		boolean result = dao.PassChange(id,pass);
		return result;

	}
}
