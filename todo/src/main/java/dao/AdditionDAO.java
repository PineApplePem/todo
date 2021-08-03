package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Addition;

public class AdditionDAO {
	 //データベース接続に使用する情報
		private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/todo";
		private final String DB_USER = "sa";
		private final String DB_PASS ="sasasa";	
	
	public boolean create(Addition addition) {
		
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection
				(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql ="INSERT INTO ADDITION(TODO_ID,USER_ID,COMMENT,TYPE) VALUES(?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,addition.getTodoId());
			pStmt.setString(2, addition.getUserId());
			pStmt.setString(3, addition.getComment());
			pStmt.setString(4,addition.getType());
			
			//INSERT文を実行
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
	
	public boolean delete(Addition addition) {
		
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection
				(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql ="DELETE FROM ADDITION WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, addition.getId());
			
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
	
	
public List<Addition> findAdditionList(int todoId) {
	List<Addition> additionList = new ArrayList<>();
	
	
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection
			(JDBC_URL,DB_USER,DB_PASS)){
		
		//SELECT文を準備(完了済みのtodoを降順に取り出す)
		String sql ="SELECT ID,TODO_ID, USER_ID, COMMENT,TYPE,CREATETIME FROM ADDITION WHERE TODO_ID = ? ORDER BY CREATETIME ASC";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1,todoId);
		
		//SELECT文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		
		//TODOリストを格納するTODOインスタンスを作成
		while(rs.next()) {
			//結果票からデータを取得
			Addition addition = new Addition();
			addition.setId(rs.getInt("ID"));
			addition.setTodoId(rs.getInt("TODO_ID"));
			addition.setUserId(rs.getString("USER_ID"));
			addition.setComment(rs.getString("COMMENT"));
			addition.setType(rs.getString("TYPE"));
			addition.setCreateTime(rs.getTimestamp("CREATETIME"));
			additionList.add(addition);
		}
		
	} catch (SQLException e) {
			e.printStackTrace();
			return null;
	}
	return additionList; 
	}

	
}
