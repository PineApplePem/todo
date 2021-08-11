package login.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.model.ChangeLogic;
import login.model.User;
import model.AdditionUserChangeLogic;
import model.TodoUserChangeLogic;

@WebServlet("/User/ChangeServlet")
/**
 * Servlet implementation class ChangeServlet
 */
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ChangeServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//フォワード
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/change.jsp");
		d.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		//ID、メールアドレスの変更
		//セッションスコープから現在ログインしているユーザー情報の取得
		HttpSession session =request.getSession();
		User currentUser = (User)session.getAttribute("user");
		
		//変更後のユーザー情報の取得
		request.setCharacterEncoding("UTF-8");

		String newId = request.getParameter("id"); 
		String newMail = request.getParameter("mail");
		
		User newUser = new User();
		newUser.setId(newId);
		newUser.setMail(newMail);
					
		  //IDがメールアドレスの形でない、メールアドレスがメールアドレスの形のときのみDBへ保存する。
		  String mailPattern ="^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*.)+[a-zA-Z]{2,}$";
		  if(newMail.matches(mailPattern) && !newId.matches(mailPattern)) {
		   ChangeLogic changeLogic = new ChangeLogic();
		   int result = changeLogic.execute(currentUser,newUser);
		   
		   
		   		if(result == 1) {  //ユーザー情報の変更に成功した場合
		   			//Todo、Additionのユーザーの変更
		   			TodoUserChangeLogic todoLogic = new TodoUserChangeLogic();
		   			todoLogic.execute(currentUser.getId(),newUser.getId());
		   			AdditionUserChangeLogic additionLogic = new AdditionUserChangeLogic();
		   			additionLogic.execute(currentUser.getId(),newUser.getId());
		   			//セッションスコープに新しいユーザー情報を入れる
		   			session.setAttribute("user", newUser);
		   			//リダイレクト
		   			response.sendRedirect("/todo/login/changeTop.jsp");  
		   			return;
		   		} else if(result == 2) {
		   			//ユーザー情報の変更に重複で失敗した場合
		   			//リクエストスコープにエラーメッセージを保存
		   			request.setAttribute("uniqueErrorMsg", "メールアドレスまたはIDが既に使用されています");
		   		}
		  } else { 
		   request.setAttribute("mailErrorMsg", "メールアドレスまたはIDの形が不正です");
		  }
		  
		  //ユーザー情報の変更に失敗した場合、各々のerrorMsgをためてここまでくる。
		  //リクエストスコープにエラーメッセージを保存
		  request.setAttribute("errorMsg", "ユーザー登録ができませんでした");
		  //フォワード
		  RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/change.jsp");
		  d.forward(request,response);


	} 
	}

