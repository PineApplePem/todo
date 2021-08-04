package test;

import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import login.dao.UserDAO;
import login.model.Login;
import login.model.User;

public class UserDAOTest {
	
	@Test
	public void アカウントの作成成功() {
		User user = new User();
		user.setId("penguin");
		String pass ="1234";
        try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		byte[] hashPass = digest.digest(pass.getBytes());
    		user.setPass(hashPass);
    		user.setMail("penguin@umi.com");
    		UserDAO dao = new UserDAO();
    		int result = dao.Create(user);
    		assertEquals(result, 1);
    } catch(Exception e){	
    	}
	}
	
	@Test
	public void IDメールの重複によるアカウントの作成失敗() {
		User user = new User();
		user.setId("penguin");
		String pass ="1234";
        try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		byte[] hashPass = digest.digest(pass.getBytes());
    		user.setPass(hashPass);
    		user.setMail("penguin@umi.com");
    		UserDAO dao = new UserDAO();
    		int result = dao.Create(user);
    		assertEquals(result, 2);
        } catch(Exception e){	
    	}

	}

	@Test
	public void IDでログインできるか() {
		Login login = new Login();
		login.setId("penguin");
		String pass ="1234";
        try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		byte[] hashPass = digest.digest(pass.getBytes());
    		login.setPass(hashPass);
    		UserDAO dao = new UserDAO();
    		User result = dao.FindByLogin(login);
    		assertEquals(result.getId(),"penguin");
    		assertEquals(result.getPass(),"hashPass");
    		assertEquals(result.getMail(),"penguin@umi.com");
        } catch(Exception e){	
    	}

	}

	@Test
	public void メールアドレスでログインできるか() {
		Login login = new Login();
		login.setMail("penguin@umi.com");
		String pass ="1234";
        try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		byte[] hashPass = digest.digest(pass.getBytes());
    		login.setPass(hashPass);
    		UserDAO dao = new UserDAO();
    		User result = dao.FindByLogin(login);
    		assertEquals(result.getId(),"penguin");
    		assertEquals(result.getPass(),"hashPass");
    		assertEquals(result.getMail(),"penguin@umi.com");
        } catch(Exception e){	
    	}

	}
	
	@Test
	public void パスワードが間違っている場合にログイン失敗するか() {
		Login login = new Login();
		login.setId("penguin");
		String pass ="12345";
        try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		byte[] hashPass = digest.digest(pass.getBytes());
    		login.setPass(hashPass);
    		UserDAO dao = new UserDAO();
    		User result = dao.FindByLogin(login);
    		assertNull(result);
        } catch(Exception e){	
    	}

	}
	
	@Test
	public void ユーザー内容の変更成功() {
		User currentUser = new User();
		currentUser.setId("shirasu");
		User newUser = new User();
		newUser.setId("fish");
		newUser.setMail("fish@umi.com");
		UserDAO dao = new UserDAO();
		int result = dao.Change(currentUser,newUser);
		assertEquals(result,1);
	}

	@Test
	public void ユーザー内容の変更失敗() {
		User currentUser = new User();
		currentUser.setId("bbb");
		User newUser = new User();
		newUser.setId("aaa");
		newUser.setMail("aaa@aaa.com");
		UserDAO dao = new UserDAO();
		int result = dao.Change(currentUser,newUser);
		assertEquals(result,0);
	}
	
	@Test
	public void ユーザー内容の変更重複による失敗() {
		User currentUser = new User();
		currentUser.setId("aaa");
		User newUser = new User();
		newUser.setId("penguin");
		newUser.setMail("aaa@aaa.com");
		UserDAO dao = new UserDAO();
		int result = dao.Change(currentUser,newUser);
		assertEquals(result,2);
	}
	
	@Test
	public void パスワードの変更成功() {
		String id = "penguin"; 
		String pass ="1234";
        try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		byte[] hashPass = digest.digest(pass.getBytes());
    		UserDAO dao = new UserDAO();
    		boolean result = dao.PassChange(id,hashPass);
    		assertEquals(result,true);
        } catch(Exception e){	
    	}

	}
	
	@Test
	public void パスワードの変更失敗() {
		String id = "bbb"; 
		String pass ="1234";
        try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		byte[] hashPass = digest.digest(pass.getBytes());
    		UserDAO dao = new UserDAO();
    		boolean result = dao.PassChange(id,hashPass);
    		assertEquals(result,false);
        } catch(Exception e){
        }
        }
	
	@Test
	public void アカウントの削除成功() {
		String id ="";
		UserDAO dao = new UserDAO();
		boolean result = dao.Delete(id);
		assertEquals(result,true);
	}
	
	@Test
	public void アカウントの削除失敗() {
		String id ="bbb";
		UserDAO dao = new UserDAO();
		boolean result = dao.Delete(id);
		assertEquals(result,false);
	}
	
}
