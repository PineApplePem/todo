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
import login.model.PassChangeLogic;
import login.model.User;
@WebServlet("/User/PassChangeServlet")
/**
 * Servlet implementation class passChangeServlet
 */
public class passChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public passChangeServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//セッションスコープから現在ログインしているユーザー情報の取得
		HttpSession session =request.getSession();
		User currentUser = (User)session.getAttribute("user");
		
		//変更後のユーザー情報の取得
		request.setCharacterEncoding("UTF-8");
		String stringNewPass = request.getParameter("pass"); 
		
		try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		byte[] newPass = digest.digest(stringNewPass.getBytes());
    		PassChangeLogic passChangeLogic = new PassChangeLogic();
    		boolean result = passChangeLogic.execute(currentUser.getId(),newPass);
    		if (result == true) {
    			String id = currentUser.getId();
				String mail = currentUser.getMail();
				User newUser = new User();
				newUser.setId(id);
				newUser.setMail(mail);
				newUser.setPass(newPass);
				session.setAttribute("user", newUser);
    			response.sendRedirect("/todo/Todo");
    		} else  {
    			request.setAttribute("errorMsg", "パスワードが変更できませんでした");
    			RequestDispatcher d = request.getRequestDispatcher("/todo/login/passChange.jsp");
    			d.forward(request,response);
    		}
    	} catch(Exception e){
    		response.sendRedirect("/todo/login/error.jsp");	
    	}
	}

}
