package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Addition implements Serializable{
	private int id;
	private String userId;
	private int todoId;
	private String comment;
	private String type;
	private Timestamp createTime;
	
	public void setId(int id) {this.id = id;}
	public void setUserId(String userId) {this.userId = userId;}
	public void setTodoId(int todoId) {this.todoId = todoId;}
	public void setComment(String comment) {this.comment = comment;}
	public void setType(String type) {this.type = type;}
	public void setCreateTime(Timestamp createTime) {this.createTime = createTime;}
	
	public int getId() {return id;}
	public String getUserId() {return userId;}
	public int getTodoId() {return todoId;}
	public String getComment() {return comment;}
	public String getType() {return type;}
	public Timestamp getCreateTime() {return createTime;}

}