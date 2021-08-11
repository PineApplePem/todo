package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Todo;


public class TodoDAO {
 //データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/todo";
	private final String DB_USER = "sa";
	private final String DB_PASS ="sasasa";	
	
public List<Todo> findTodoList(boolean doneJudge,String userId) {
	List<Todo> todoList = new ArrayList<>();
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		
		//SELECT文を準備(完了済みのtodoを降順に取り出す)
		String sql ="SELECT NUMBER,TODO, DEAD, START,DETAIL, DONE, DONEDATE, USER_ID FROM TODO WHERE DONE = ? AND USER_ID = ? ORDER BY DEAD ASC";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setBoolean(1,doneJudge);
		pStmt.setString(2, userId);
		
		//SELECT文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		
		//TODOリストを格納するTODOインスタンスを作成
		while(rs.next()) {
			//結果票からデータを取得
			Todo todo = new Todo();
			todo.setNumber(rs.getInt("NUMBER"));
			todo.setTodo(rs.getString("TODO"));
			todo.setDead(rs.getDate("DEAD"));
			todo.setStart(rs.getDate("START"));
			todo.setDetail(rs.getString("DETAIL"));
			todo.setDone(rs.getBoolean("DONE"));
			todo.setDoneDate(rs.getDate("DONEDATE"));
			todo.setUserId(rs.getString("USER_ID"));
			todoList.add(todo);
			
		}
	} catch (SQLException e) {
			e.printStackTrace();
			return null;
	}
	return todoList; 
	}


public boolean create(Todo todo) {
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		
		String sql ="INSERT INTO TODO(TODO,DEAD,START,DETAIL,USER_ID) VALUES(?,?,?,?,?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,todo.getTodo());
		pStmt.setDate(2, todo.getDead());
		pStmt.setDate(3, todo.getStart());
		pStmt.setString(4,todo.getDetail());
		pStmt.setString(5,todo.getUserId());
		
		//DELETE文を実行
		int result = pStmt.executeUpdate();
		if(result != 1) {
			return false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	return true;
}

public boolean edit(Todo oldTodo,Todo newTodo) {
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		
		String sql ="UPDATE TODO SET TODO = ? , DEAD = ?, START = ?, DETAIL = ? WHERE NUMBER = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,newTodo.getTodo());
		pStmt.setDate(2, newTodo.getDead());
		pStmt.setDate(3, newTodo.getStart());
		pStmt.setString(4,newTodo.getDetail());
		pStmt.setInt(5,oldTodo.getNumber());
		
		//DELETE文を実行
		int result = pStmt.executeUpdate();
		if(result != 1) {
			return false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	return true;
}


public boolean delete(Todo todo) {
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		
		String sql ="DELETE FROM TODO WHERE NUMBER = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, todo.getNumber());
		
		//DELETE文を実行
		int result = pStmt.executeUpdate();
		if(result != 1) {
			return false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	return true;
}

public boolean done(Todo todo) {
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		
		String sql ="UPDATE TODO SET DONE = ?, DONEDATE = ? WHERE NUMBER = ? ";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		if (todo.getDone() == true) {
			pStmt.setBoolean(1, false);
		} else if(todo.getDone() == false){
			pStmt.setBoolean(1, true);
		}
		//現在の日付を取得して、DONEDATEに入れる
		long miliseconds = System.currentTimeMillis();
		Date currentDate = new Date(miliseconds);
		pStmt.setDate(2,currentDate);
		pStmt.setInt(3, todo.getNumber());
		
		//UPDATE文を実行
		int result = pStmt.executeUpdate();
		if(result != 1) {
			return false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	return true;
}

public List<Todo> findByReflection(String userId,int reflectionType,Date startDate,Date endDate) {
	List<Todo> todoList = new ArrayList<>();
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		PreparedStatement pStmt = null;
		
		//ReflectionType毎にSELECT文を準備(完了済みのtodoを降順に取り出す)
		switch(reflectionType) {
			case 0:
				String sql0 ="SELECT NUMBER,TODO, DEAD, START,DETAIL, DONE, DONEDATE, USER_ID FROM TODO WHERE DONE = 0 AND USER_ID = ? AND DEAD < ? ORDER BY DEAD ASC";
				pStmt = conn.prepareStatement(sql0);
				pStmt.setString(1,userId);
				pStmt.setDate(2, endDate);
				break;
			case 1:
				String sql1 ="SELECT NUMBER,TODO, DEAD, START,DETAIL, DONE, DONEDATE, USER_ID FROM TODO WHERE DONE = 1 AND USER_ID = ? AND DONEDATE >= ? AND DONEDATE <= ? ORDER BY DEAD ASC";
				pStmt = conn.prepareStatement(sql1);
				pStmt.setString(1,userId); 
				pStmt.setDate(2, startDate);
				pStmt.setDate(3, endDate);
				break;
			case 2:
				String sql2 ="SELECT NUMBER,TODO, DEAD, START,DETAIL, DONE, DONEDATE, USER_ID FROM TODO WHERE DONE = 0 AND USER_ID = ? AND START <= ? AND ? < DEAD  ORDER BY DEAD ASC";
				pStmt = conn.prepareStatement(sql2);
				pStmt.setString(1,userId);
				pStmt.setDate(2, startDate);
				pStmt.setDate(3, endDate);
				break;
			case 3:
				String sql3 ="SELECT NUMBER,TODO, DEAD, START,DETAIL, DONE, DONEDATE, USER_ID FROM TODO WHERE DONE = 0 AND USER_ID = ? AND DEAD = ? ORDER BY DEAD ASC";
				pStmt = conn.prepareStatement(sql3);
				pStmt.setString(1,userId);
				//dateの次の日の日付を取得
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(endDate);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				Date tommorowDate = new Date(calendar.getTime().getTime());
				pStmt.setDate(2, tommorowDate);
				break;
		}

		//SELECT文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		
		//TODOリストを格納するTODOインスタンスを作成
		while(rs.next()) {
			//結果票からデータを取得
			Todo todo = new Todo();
			todo.setNumber(rs.getInt("NUMBER"));
			todo.setTodo(rs.getString("TODO"));
			todo.setDead(rs.getDate("DEAD"));
			todo.setStart(rs.getDate("START"));
			todo.setDetail(rs.getString("DETAIL"));
			todo.setDone(rs.getBoolean("DONE"));
			todo.setDoneDate(rs.getDate("DONEDATE"));
			todo.setUserId(rs.getString("USER_ID"));
			todoList.add(todo);
			
		}
	} catch (SQLException e) {
			e.printStackTrace();
			return null;
	}
	return todoList; 
	}

public boolean changeUser(String oldUserId,String newUserId) {
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		
		String sql ="UPDATE TODO SET USER_ID = ?  WHERE USER_ID = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,newUserId);
		pStmt.setString(2, oldUserId);
		
		//DELETE文を実行
		int result = pStmt.executeUpdate();
		if(result != 1) {
			return false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	return true;
}


public boolean deleteUser(String userId) {
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		
		String sql ="DELETE FROM TODO WHERE USER_ID = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, userId);
		
		//DELETE文を実行
		int result = pStmt.executeUpdate();
		if(result != 1) {
			return false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	return true;
}	


}
