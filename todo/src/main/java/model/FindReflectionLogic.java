package model;

import dao.ReflectionDAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class FindReflectionLogic {
	public List<DayReflection> execute(String userId) {
		ReflectionDAO dao = new ReflectionDAO();
		
		//このuserIdのDBにあるすべての日付を取り出す
		List<DateSet> dateSetList = dao.findDate(userId);
		List<DayReflection> dayReflectionList = new ArrayList<DayReflection>(); 
		
		//日付ごとにDayReflectionをDBから取り出し、dayReflectionListに格納
		for (DateSet dateSet : dateSetList) {
		
			Date startDate = dateSet.getStartDate();
			Date endDate = dateSet.getEndDate();		
			
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
			dayReflectionList.add(dayReflection);
	
			}
		
		return dayReflectionList;
	}	
}
