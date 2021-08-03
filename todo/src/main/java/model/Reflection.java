package model;

import java.io.Serializable;
import java.sql.Date;


public class Reflection implements Serializable{

	private int id;
	private String userId;
	private String todo;
	private Date startDate;
	private Date endDate;
	private int type;
	private boolean todayDo;
	private String good;
	private String bad;
	private String stuck;
	private String progress;
	private String comment;
	
	public void setId(int id) {this.id = id; }
	public void setUserId(String userId) {this.userId = userId; }
	public void setTodo(String todo) {this.todo = todo;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	public void setType(int type) {this.type = type;}
	public void setTodayDo(boolean todayDo) {this.todayDo = todayDo;}
	public void setGood(String good) {this.good = good;}
	public void setBad(String bad) {this.bad = bad;}
	public void setStuck(String stuck) {this.stuck = stuck;}
	public void setProgress(String progress) {this.progress = progress;}
	public void setComment(String comment) {this.comment = comment;}
	
	public int getId() {return id;}
	public String getUserId() {return userId;}
	public String getTodo() {return todo;}
	public Date getStartDate() {return startDate;}
	public Date getEndDate() {return endDate;}
	public int getType() {return type;}
	public boolean getTodayDo() {return todayDo;}
	public String getGood() {return good;}
	public String getBad() {return bad;}
	public String getStuck() {return stuck;}
	public String getProgress() {return progress;}
	public String getComment() {return comment;}
}
