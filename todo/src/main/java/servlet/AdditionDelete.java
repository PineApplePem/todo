package servlet;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Addition;
import model.AdditionDelelteLogic;
import model.Todo;

/**
 * Servlet implementation class AdditionDelete
 */
public class AdditionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AdditionDelete() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 	request.setCharacterEncoding("UTF-8");
	 	Addition addition = new Addition();
	 	int id = Integer.parseInt(request.getParameter("delete"));
	 	addition.setId(id);
	 	AdditionDelelteLogic logic = new AdditionDelelteLogic();
	 	boolean result = logic.execute(addition);
	 		if(result == true) {
	 	response.sendRedirect("/todo/Todo");
	 	} else {
			request.setAttribute("errorMsg","エラーが発生しました。コメントの削除ができませんでした。");
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			d.forward(request, response);	
	 	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
