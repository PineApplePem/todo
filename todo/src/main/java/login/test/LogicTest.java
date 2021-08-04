package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import login.model.CreateLogic;
import login.model.Login;
import login.model.LoginLogic;
import login.model.User;

public class LogicTest {

	@Test
	public void ログイン成功() {
		Login login = new Login();
		login.setId("penguin");
		byte[] a = new byte[2];
		a[1] = 10;
		a[2] = 20;
		login.setPass(a);
		LoginLogic loginlogic = new LoginLogic();
		User user = loginlogic.execute(login);
		boolean result = (user != null) ;
		assertEquals(true,result);
	}
	
	@Test
	public void ログイン失敗() {
		Login login = new Login();
		login.setId("penguin");
		byte[] a = new byte[2];
		a[1] = 10;
		a[2] = 10;
		login.setPass(a);
		LoginLogic loginlogic = new LoginLogic();
		User user = loginlogic.execute(login);
		boolean result = (user != null) ;
		assertEquals(false,result);
	}	
	
	@Test
	public void アカウント作成成功() {
		User user = new User();
		user.setId("Shell");
		byte[] a = new byte[2];
		a[1] = 10;
		a[2] = 20;
		user.setPass(a);
		user.setMail("shell@umi.com");
		CreateLogic createLogic = new CreateLogic();
		int result = createLogic.execute(user);
		assertEquals(1,result);
	}
	
	@Test
	public void 重複によるアカウント作成失敗() {
		User user = new User();
		user.setId("penguin");
		byte[] a = new byte[2];
		a[1] = 10;
		a[2] = 20;
		user.setPass(a);
		user.setMail("shell@umi.com");
		CreateLogic createLogic = new CreateLogic();
		int result = createLogic.execute(user);
		assertEquals(2,result);
	}
	

}
