package model;

import java.sql.Date;

public class Todo {
	private int number;
	private String todo;
	private Date dead;
	private String detail;
	private boolean done;
	
	public void setNumber(int number) {this.number = number;}
	public void setTodo(String todo) {this.todo = todo; }
	public void setDead(Date dead) {this.dead =dead; }
	public void setDetail(String detail) {this.detail = detail;}
	public void setDone(boolean done) {this.done =done; }
	
	public int getNumber() {return number;}
	public String getTodo() {return todo;}
	public Date getDead() {return dead;}
	public String getDetail() {return detail; }
	public boolean getDone() {return done;}
}
