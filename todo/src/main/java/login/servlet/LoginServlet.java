package login.servlet;

import java.io.IOException;
import java.security.MessageDigest;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.model.Login;
import login.model.LoginLogic;
import login.model.User;
@WebServlet("/Login")
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//既にログインされていたらトップ画面にリダイレクト
		HttpSession session =((HttpServletRequest)request).getSession();
		User user = (User)session.getAttribute("user");
		if(user != null) {
			((HttpServletResponse)response).sendRedirect("/todo/Todo");
		} else {
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/login.jsp");
		d.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ログインに成功した場合 
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String idMail = request.getParameter("idMail");
		String pass = request.getParameter("pass"); //暗号化する
		
		//ログイン処理の実行
		Login login = new Login();
		//メールアドレスの型の時にメールアドレスに。それ以外はidに格納する。
		String mailPattern ="^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*.)+[a-zA-Z]{2,}$";
		if (idMail.matches(mailPattern)) {
			login.setMail(idMail);
		} else {
			login.setId(idMail);
		}
		//パスワードをハッシュ化する
        try {
        		MessageDigest digest = MessageDigest.getInstance("SHA-256");
        		byte[] hashPass = digest.digest(pass.getBytes());
        		login.setPass(hashPass);
        } catch(Exception e){
        		response.sendRedirect("/login/error.jsp");	
        	}
		LoginLogic loginLogic = new LoginLogic();
		User user = loginLogic.execute(login); 
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		//ログイン処理の成否によって処理を分岐
		if(user != null) { //ログイン成功時	
			response.sendRedirect("/todo/Todo");
		} else {
		//ログインに失敗した場合
		//エラーメッセージの取得
		request.setAttribute("errorMsg", "ログインできませんでした。IDかパスワードが間違っていませんか？");
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/login.jsp");
		d.forward(request,response);
		}
	}
}
