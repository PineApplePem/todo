package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reflection;
import model.DateSet;

public class ReflectionDAO {

 //データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/todo";
	private final String DB_USER = "sa";
	private final String DB_PASS ="sasasa";		
	
	public List<Reflection> findReflection(String userId,Date startDate,Date endDate, int type ) {
		List<Reflection> reflectionList = new ArrayList<>();
		
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection
				(JDBC_URL,DB_USER,DB_PASS)){
			
			
			//SELECT文を準備(完了済みのtodoを降順に取り出す)
			String sql ="SELECT ID, USER_ID, STARTDATE, ENDDATE, TYPE, TODAYDO, TODO, GOOD, BAD, STUCK, PROGRESS, COMMENT FROM REFLECTION WHERE USER_ID= ? AND STARTDATE = ? AND ENDDATE = ? AND TYPE = ? ORDER BY ENDDATE ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,userId);
			pStmt.setDate(2,startDate);
			pStmt.setDate(3,endDate);
			pStmt.setInt(4,type);
			
			//SELECT文を実行し結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			//Reflectionリストに格納するReflectionインスタンスを作成
			while(rs.next()) {
				//結果票からデータを取得
				Reflection reflection = new Reflection();
				reflection.setId(rs.getInt("ID"));
				reflection.setUserId(rs.getString("USER_ID"));
				reflection.setStartDate(rs.getDate("STARTDATE"));
				reflection.setEndDate(rs.getDate("ENDDATE"));
				reflection.setType(rs.getInt("TYPE"));
				reflection.setTodayDo(rs.getBoolean("TODAYDO"));
				reflection.setTodo(rs.getString("TODO"));
				reflection.setGood(rs.getString("GOOD"));
				reflection.setBad(rs.getString("BAD"));
				reflection.setStuck(rs.getString("STUCK"));
				reflection.setProgress(rs.getString("PROGRESS"));
				reflection.setComment(rs.getString("COMMENT"));
				reflectionList.add(reflection);
			}
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
		return reflectionList; 
		}
	
	public boolean create(Reflection reflection) {
		
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection
				(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql ="INSERT INTO REFLECTION (USER_ID,STARTDATE,ENDDATE, TYPE, TODAYDO, TODO, GOOD, BAD, STUCK, PROGRESS, COMMENT) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,reflection.getUserId());
			pStmt.setDate(2,reflection.getStartDate());
			pStmt.setDate(3,reflection.getEndDate());
			pStmt.setInt(4,reflection.getType());
			pStmt.setBoolean(5,reflection.getTodayDo());
			pStmt.setString(6,reflection.getTodo());
			pStmt.setString(7,reflection.getGood());
			pStmt.setString(8,reflection.getBad());
			pStmt.setString(9,reflection.getStuck());
			pStmt.setString(10,reflection.getProgress());
			pStmt.setString(11,reflection.getComment());
			
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
	
	public boolean change(Reflection oldReflection, Reflection newReflection) {
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
		
			//UPDATE文を準備
			String sql = "UPDATE REFLECTION SET TODAYDO = ?, GOOD = ?, BAD = ?, STUCK = ?, PROGRESS = ?, COMMENT = ?  WHERE ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setBoolean(1,newReflection.getTodayDo());
			pStmt.setString(2,newReflection.getGood());
			pStmt.setString(3,newReflection.getBad());
			pStmt.setString(4,newReflection.getStuck());
			pStmt.setString(5,newReflection.getProgress());
			pStmt.setString(6,newReflection.getComment());
			pStmt.setInt(7,oldReflection.getId());
			//UPDATE文を実行し結果票を取得
			int result = pStmt.executeUpdate();
			//UPDATE文が実行されていたら1、失敗していたら0を返す
			if(result != 1) {
			return 	false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//DBに同じユーザー、同じ日付の反省が作成されていないかの確認
	public boolean dateCheck(String userId,Date startDate, Date endDate) {
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			
			//SELECT文を準備
			String sql ="SELECT ID FROM REFLECTION WHERE USER_ID = ? AND STARTDATE = ? AND ENDDATE = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,userId);
			pStmt.setDate(2,startDate);
			pStmt.setDate(3,endDate);
			List<String> idList = new ArrayList<String>();
			//SELECT文を実行し結果票を取得
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				idList.add(rs.getString("ID"));
			}
			if(idList.size() == 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	//SELECTを日付順に取得するために、日付を別で取得
	public List<DateSet> findDate(String userId) {
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			
			//SELECT文を準備
			String sql ="SELECT DISTINCT STARTDATE, ENDDATE FROM REFLECTION WHERE USER_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,userId);

			List<DateSet> dateList = new ArrayList<DateSet>();
			//SELECT文を実行し結果票を取得
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				DateSet dateSet = new DateSet(rs.getDate("STARTDATE"),rs.getDate("ENDDATE"));
				dateList.add(dateSet);
			}
			if(dateList.size() != 0) {
				return dateList;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}	


