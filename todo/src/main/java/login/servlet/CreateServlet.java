package login.servlet;

import java.io.IOException;
import java.security.MessageDigest;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import login.model.CreateLogic;
import login.model.User;
@WebServlet("/Create")
/**
 * Servlet implementation class CreateServlet
 */
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreateServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/create.jsp");
		d.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ユーザー登録
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id"); 
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass"); 
		
		User user = new User();
		user.setId(id);
		user.setMail(mail);
		//パスワードをハッシュ化する
        try {
        		MessageDigest digest = MessageDigest.getInstance("SHA-256");
        		byte[] hashPass = digest.digest(pass.getBytes());
        		user.setPass(hashPass);
        	} catch(Exception e){
        		response.sendRedirect("/todo/login/error.jsp");	
        	}
		
		
		//IDがメールアドレスの形でない、メールアドレスがメールアドレスの形のときのみDBへ保存する。
		String mailPattern ="^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*.)+[a-zA-Z]{2,}$";
		//エラーの場合のフォワードに映る目印、エラーの場合は1を入れる。
		int error = 0;
		if(mail.matches(mailPattern) && !id.matches(mailPattern)) {
			CreateLogic createLogic = new CreateLogic();
			int result = createLogic.execute(user);
			if(result == 1) {		//ユーザー登録に成功した場合
				//リダイレクト
				response.sendRedirect("/todo/Login");		
			} else if(result == 2) {
					//ユーザー登録に重複で失敗した場合
					//リクエストスコープにエラーメッセージを保存
					request.setAttribute("uniqueErrorMsg", "メールアドレスまたはIDが既に使用されています");
					//エラーの場合のフォワードに映る目印
					error = 1; 
			} else {
				response.sendRedirect("/todo/login/error.jsp");
			}
		} else { 
			request.setAttribute("mailErrorMsg", "メールアドレスまたはIDの形が不正です");
			error = 1;
		}
		
		if (error == 1) {
		//ユーザー登録に失敗した場合、各々のerrorMsgをためてここまでくる。
		//リクエストスコープにエラーメッセージを保存
		request.setAttribute("errorMsg", "ユーザー登録ができませんでした");
		//セッションスコープに入力値を保存(入力値をフォームの初期値とするため）
		request.setAttribute("user", user);
		//フォワード
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/create.jsp");
		d.forward(request,response);
		}
	}
}
