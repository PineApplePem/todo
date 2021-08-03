package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class TodoListByReflection implements Serializable{
	
	private String userId;
	private Date startDate;
	private Date endDate;
	private List<Todo> notDoneList;
	private List<Todo> doneList;
	private List<Todo> progressList;
	private List<Todo> tomorrowList;
	
	public void setUserId(String userId) {this.userId = userId;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	public void setNotDoneList(List<Todo> notDoneList) {this.notDoneList = notDoneList;}
	public void setDoneList(List<Todo> doneList) {this.doneList = doneList;}	
	public void setProgressList(List<Todo> progressList) {this.progressList = progressList;}
	public void setTommorowList(List<Todo> tomorrowList) {this.tomorrowList = tomorrowList;}
	
	public String getUserId(){return userId; }
	public Date getStartDate() {return startDate;}
	public Date getEndDate() {return endDate;}
	public List<Todo> getNotDoneList() {return notDoneList;}
	public List<Todo> getDoneList() {return doneList;}
	public List<Todo> getProgressList() {return progressList;}
	public List<Todo> getTommorowList() {return tomorrowList;}

}
