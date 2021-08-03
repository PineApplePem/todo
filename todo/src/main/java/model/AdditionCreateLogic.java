package model;

import dao.AdditionDAO;

public class AdditionCreateLogic {
	public boolean execute(Addition addition) {
		AdditionDAO dao = new AdditionDAO();
		Boolean result = dao.create(addition);
		return result;
		}

}
