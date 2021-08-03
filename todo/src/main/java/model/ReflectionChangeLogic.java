package model;

import dao.ReflectionDAO;

public class ReflectionChangeLogic {
	public boolean execute(Reflection newReflection,Reflection oldReflection) {
		ReflectionDAO dao = new ReflectionDAO();
		boolean result = dao.change(oldReflection,newReflection);
		return result;
	}
}
