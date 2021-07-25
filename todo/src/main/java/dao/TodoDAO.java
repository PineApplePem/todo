package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Todo;

public class TodoDAO {
 //データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/todo";
	private final String DB_USER = "sa";
	private final String DB_PASS ="sasasa";	
	
public List<Todo> findTodoList(boolean doneJudge) {
	List<Todo> todoList = new ArrayList<>();
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		
		//SELECT文を準備(完了済みのtodoを降順に取り出す)
		String sql ="SELECT NUMBER,TODO, DEAD, DETAIL, DONE FROM TODO WHERE DONE = ? ORDER BY DEAD ASC";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setBoolean(1,doneJudge);
		
		//SELECT文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		
		//TODOリストを格納するTODOインスタンスを作成
		while(rs.next()) {
			//結果票からデータを取得
			Todo todo = new Todo();
			todo.setNumber(rs.getInt("number"));
			todo.setTodo(rs.getString("todo"));
			todo.setDead(rs.getDate("dead"));
			todo.setDetail(rs.getString("detail"));
			todo.setDone(rs.getBoolean("done"));
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
		
		String sql ="INSERT INTO TODO(TODO,DEAD,DETAIL) VALUES(?,?,?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,todo.getTodo());
		pStmt.setDate(2, todo.getDead());
		pStmt.setString(3,todo.getDetail());
		
		
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
		
		String sql ="UPDATE TODO SET DONE = ? WHERE NUMBER = ? ";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		if (todo.getDone() == true) {
			pStmt.setBoolean(1, false);
		} else if(todo.getDone() == false){
			pStmt.setBoolean(1, true);
		}
		pStmt.setInt(2, todo.getNumber());
		
		
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
