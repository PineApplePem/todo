package model;

import dao.TodoDAO;
import java.sql.Date;
import java.util.List;

public class FindTodoByReflectionLogic {
	public TodoListByReflection execute(String userId ,Date startDate, Date endDate ) {
		TodoDAO dao = new TodoDAO();
		TodoListByReflection todoList = new TodoListByReflection();
		
		todoList.setUserId(userId);
		todoList.setStartDate(startDate);
		todoList.setEndDate(endDate);
		
		List<Todo> notDoneList = dao.findByReflection(userId,0,startDate,endDate);
		List<Todo> doneList = dao.findByReflection(userId,1,startDate,endDate);
		List<Todo> progressList = dao.findByReflection(userId,2,startDate,endDate);
		List<Todo> TommorowList = dao.findByReflection(userId,3,startDate,endDate);
		
		todoList.setDoneList(doneList);
		todoList.setNotDoneList(notDoneList);
		todoList.setProgressList(progressList);
		todoList.setTommorowList(TommorowList);

		return todoList;	
		
	}  
	
	

}
