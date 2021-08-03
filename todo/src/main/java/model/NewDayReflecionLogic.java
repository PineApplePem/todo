package model;

import java.sql.Date;

import dao.ReflectionDAO;

public class NewDayReflecionLogic {
	public boolean execute(String userId,Date startDate, Date endDate) {
		
		//STARTDATE,ENDDATE,USER_ID,TYPE,TODOでDBにデータを作成する。
		
		FindTodoByReflectionLogic logic = new FindTodoByReflectionLogic();
		TodoListByReflection todoList = logic.execute(userId,startDate,endDate);
		
		Reflection reflection = new Reflection();
		ReflectionDAO dao = new ReflectionDAO();
		reflection.setStartDate(startDate);
		reflection.setEndDate(endDate);
		reflection.setUserId(userId);
		boolean result;
		
		reflection.setType(4);
		result =dao.create(reflection);
		if(result == false) {
			return result;
		}
		
		for(Todo todo : todoList.getNotDoneList()) {
			reflection.setType(0);
			reflection.setTodo(todo.getTodo());
			result =dao.create(reflection);
			if(result == false) {
				return result;
			}
		}
		
		for(Todo todo : todoList.getDoneList()) {
			reflection.setType(1);
			reflection.setTodo(todo.getTodo());
			result =dao.create(reflection);
			if(result == false) {
				return result;
			}
		}
		
		for(Todo todo : todoList.getProgressList()) {
			reflection.setType(2);
			reflection.setTodo(todo.getTodo());
			result =dao.create(reflection);
			if(result == false) {
				return result;
			}
		}
		
		for(Todo todo : todoList.getTommorowList()) {
			reflection.setType(3);
			reflection.setTodo(todo.getTodo());
			result =dao.create(reflection);
			if(result == false) {
				return result;
			}
		}
		

		
		return result;
	}
}
