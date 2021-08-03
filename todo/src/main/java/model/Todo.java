package model;

import java.io.Serializable;
import java.sql.Date;

public class Todo implements Serializable{
	private int number;
	private String userId;
	private String todo;
	private Date start;
	private Date dead;
	private String detail;
	private boolean done;
	private Date doneDate;
	
	public void setNumber(int number) {this.number = number;}
	public void setUserId(String userId) {this.userId = userId;}
	public void setTodo(String todo) {this.todo = todo; }
	public void setDead(Date dead) {this.dead =dead; }
	public void setStart(Date start) {this.start =start; }
	public void setDetail(String detail) {this.detail = detail;}
	public void setDone(boolean done) {this.done =done; }
	public void setDoneDate(Date doneDate) {this.doneDate =doneDate; }
	
	public int getNumber() {return number;}
	public String getUserId() {return userId;}
	public String getTodo() {return todo;}
	public Date getDead() {return dead;}
	public Date getStart() {return start;}
	public String getDetail() {return detail; }
	public boolean getDone() {return done;}
	public Date getDoneDate() {return doneDate;}
}
