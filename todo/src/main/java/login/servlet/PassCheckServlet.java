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

@WebServlet("/User/PassCheckServlet")
/**
 * Servlet implementation class PassChangeServlet
 */
public class PassCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String move = request.getParameter("move");
		request.setAttribute("move",move);
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/passCheck.jsp");
		d.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//パスワードの確認
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			byte[] currentPass =user.getPass();
			
			request.setCharacterEncoding("UTF-8");
			String stringGetPass = request.getParameter("pass"); 
			String move = request.getParameter("move");
			
	        try {
        		MessageDigest digest = MessageDigest.getInstance("SHA-256");
        		byte[] getPass = digest.digest(stringGetPass.getBytes());
        		
        		Login login = new Login();
        		login.setId(user.getId());
        		login.setMail(user.getMail());
        		login.setPass(getPass);
        		
        		LoginLogic loginLogic = new LoginLogic();
        		User newUser = loginLogic.execute(login);
        		
        		
        		
    		    if(newUser == null) { 
    		        request.setAttribute("errorMsg", "パスワードが間違っています");
    		        request.setAttribute("getPass", stringGetPass);
    		        request.setAttribute("Pass", getPass);
    		        request.setAttribute("currentPass", currentPass);
    		        RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/passCheck.jsp?move=" +move);
    		        d.forward(request,response);
    		       } else if("pass".equals(move)) {
    		        RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/passChange.jsp");
    		        d.forward(request,response);
    		       } else {
    		        RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/delete.jsp");
    		        d.forward(request,response);
    		       }
        } catch(Exception e){
        		response.sendRedirect("/todo/login/error.jsp");	
        	}

		
	}

}
