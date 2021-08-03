package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class DayReflection implements Serializable{

	private String userId;
	private Date startDate;
	private Date endDate;
	private List<Reflection> notDoneList;
	private List<Reflection> doneList;
	private List<Reflection> progressList;
	private List<Reflection> tomorrowList;
	private List<Reflection> comment;
	
	public void setUserId(String userId) {this.userId = userId;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	public void setNotDoneList(List<Reflection> notDoneList) {this.notDoneList = notDoneList;}
	public void setDoneList(List<Reflection> doneList) {this.doneList = doneList;}	
	public void setProgressList(List<Reflection> progressList) {this.progressList = progressList;}
	public void setTommorowList(List<Reflection> tomorrowList) {this.tomorrowList = tomorrowList;}
	public void setComment(List<Reflection> comment) {this.comment = comment;}
	
	public String getUserId(){return userId; }
	public Date getStartDate() {return startDate;}
	public Date getEndDate() {return endDate;}
	public List<Reflection> getNotDoneList() {return notDoneList;}
	public List<Reflection> getDoneList() {return doneList;}
	public List<Reflection> getProgressList() {return progressList;}
	public List<Reflection> getTommorowList() {return tomorrowList;}
	public List<Reflection> getComment() {return comment;}
}
