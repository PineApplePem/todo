package model;

import dao.AdditionDAO;

public class AdditionDelelteLogic {
	public boolean execute(Addition addition) {
		AdditionDAO dao = new AdditionDAO();
		Boolean result = dao.delete(addition);
		return result;
		}

}
