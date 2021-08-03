package model;

import dao.ReflectionDAO;

public class NewReflectionLogic {
	public boolean execute(Reflection reflection) {
		ReflectionDAO dao = new ReflectionDAO();
		boolean result = dao.create(reflection);
		return result;
	}
}
