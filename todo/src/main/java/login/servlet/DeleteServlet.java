package login.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.model.DeleteLogic;
import login.model.User;
import model.AdditionDeleteByUserLogic;
import model.TodoDeleteByUserLogic;
@WebServlet("/User/DeleteServlet")
/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeleteServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String move = request.getParameter("move"); 
		if(move.equals("yes") ) {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			//DBから削除
			DeleteLogic deleteLogic = new DeleteLogic();
			boolean idResult = deleteLogic.execute(user.getId());
			
			//TodoとAdditionの削除
			TodoDeleteByUserLogic todoLogic = new TodoDeleteByUserLogic();
			boolean todoResult =todoLogic.execute(user.getId());
			
			AdditionDeleteByUserLogic AdditionLogic = new AdditionDeleteByUserLogic();
			boolean additionResult =AdditionLogic.execute(user.getId());
			
			//セッションスコープから削除
			session.invalidate();
			response.sendRedirect("/todo/Login");
			
		} else {
		
		request.setAttribute("deleteErrorMsg","アカウントが削除できませんでした");
		//フォワード
		 RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/loginJsp/change.jsp");
		 d.forward(request,response);
		}
	}

}
