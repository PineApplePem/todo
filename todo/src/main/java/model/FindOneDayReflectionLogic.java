package model;

import java.sql.Date;
import java.util.List;

import dao.ReflectionDAO;

public class FindOneDayReflectionLogic {
	public DayReflection execute(String userId,Date startDate, Date endDate) {
	
	
		ReflectionDAO dao = new ReflectionDAO();
	
		List<Reflection> notDoneList = dao.findReflection(userId,startDate,endDate,0);
		List<Reflection> doneList = dao.findReflection(userId,startDate,endDate,1);
		List<Reflection> progressList = dao.findReflection(userId,startDate,endDate,2);
		List<Reflection> tommorowList = dao.findReflection(userId,startDate,endDate,3);
		List<Reflection> comment = dao.findReflection(userId,startDate,endDate,4);
		DayReflection dayReflection = new DayReflection();

		dayReflection.setStartDate(startDate);
		dayReflection.setEndDate(endDate);
		dayReflection.setNotDoneList(notDoneList);
		dayReflection.setDoneList(doneList);
		dayReflection.setProgressList(progressList);
		dayReflection.setTommorowList(tommorowList);
		dayReflection.setComment(comment);
		
		return dayReflection;
	}
}
