package login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.model.Login;
import login.model.User;

public class UserDAO {
	public final String JDBC_URL = "jdbc:h2:tcp://localhost/~/todo";
	private final String DB_USER ="sa";
	private final String DB_PASS = "sasasa";
	
	
	//ログインおよび該当のユーザー情報の取得
	public User FindByLogin(Login login) {
		User user = null;
		
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			//SELECT文を準備
			String sql = "SELECT ID, PASS, MAIL FROM USER WHERE (ID=? OR MAIL = ?) AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getId());
			pStmt.setString(2,login.getMail() );
			pStmt.setBytes(3, login.getPass()); //暗号化を戻す
			
			//SELECT文を実行し結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			//一致したユーザーが存在した場合
			//そのユーザーを表すUserインスタンス"user"を生成
			if(rs.next()) {
				String id = rs.getString("ID");
				byte[] pass = rs.getBytes("pass");
				String mail = rs.getString("MAIL");
				user = new User();
				user.setId(id);
				user.setPass(pass);
				user.setMail(mail);
			}
	
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}		
			 //見つかったユーザーまたはNullを返す
			return user;
	}
	
	
	//新しいユーザーの登録
	/* ユーザー登録にID,mailの重複以外で失敗したら0、成功したら1、ID,mailの重複により失敗したら2を返す**/
	public int Create(User user) {
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			
			//INSERT文を準備
			String sql = "INSERT INTO USER (ID, PASS, MAIL) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getId());
			pStmt.setBytes(2, user.getPass());
			pStmt.setString(3, user.getMail());
			
			//INSERT文を実行し結果票を取得
			int result = pStmt.executeUpdate();
			//INSERT文が実行されていたら1、失敗していたら0を返す
			if(result != 1) {
				return 0;
			}
		} catch(SQLException e) {
			//UNIQUEもしくはPRIMARY KEYの重複制約エラーが起きていた場合に2を返す
			int errorCode = e.getErrorCode();
			if(errorCode == 23505) {
				return 2;
			} else {
			e.printStackTrace();
			return 0;
			}
		}
			return 1;
	}

	
	//ユーザーの削除
	public boolean Delete(String id) {
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
		
			//UPDATE文を準備
			String sql = "DELETE FROM USER WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
		
			//UPDATE文を実行し結果票を取得
			int result = pStmt.executeUpdate();
			//UPDATE文が実行されていたら1、失敗していたら0を返す
			if(result != 1) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	
	//ユーザー情報の変更
/* ユーザー登録にID,mailの重複以外で失敗したら0、成功したら1、ID,mailの重複により失敗したら2を返す**/
	public int Change(User currentUser,User newUser) {
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
		
			//UPDATE文を準備
			String sql = "UPDATE USER SET ID =?, MAIL=? WHERE ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, newUser.getId());
			pStmt.setString(2, newUser.getMail());
			pStmt.setString(3,currentUser.getId());
		
			//UPDATE文を実行し結果票を取得
			int result = pStmt.executeUpdate();
			//UPDATE文が実行されていたら1、失敗していたら0を返す
			if(result != 1) {
			return 0;
			}	
		} catch(SQLException e) {
			//UNIQUEもしくはPRIMARY KEYの重複制約エラーが起きていた場合に2を返す
			int errorCode = e.getErrorCode();
			if(errorCode == 23505) {
				return 2;
		} else {
			e.printStackTrace();
			return 0;
		}
	}
		return 1;
	}	

	//パスワードの変更
	public boolean PassChange(String id,byte[] pass) {
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
		
			//UPDATE文を準備
			String sql = "UPDATE USER SET pass =? WHERE ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setBytes(1, pass);
			pStmt.setString(2, id);
		
			//UPDATE文を実行し結果票を取得
			int result = pStmt.executeUpdate();
			//UPDATE文が実行されていたら1、失敗していたら0を返す
			if(result != 1) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}



