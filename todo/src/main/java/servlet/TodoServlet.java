package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GetTodoListLogic;
import model.Todo;

@WebServlet("/Todo")
/**
 * Servlet implementation class TodoServlet
 */
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TodoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String move = request.getParameter("move"); 
		
		//TODOリストを取得して、リクエストスコープに保存
		GetTodoListLogic getTodoListLogic = new GetTodoListLogic();
		List<Todo> todoList = null;
		
		//セッションスコープからUserIDを取得（今はダミーのIDを取得)
		String userId = "ccc";
		
		todoList = getTodoListLogic.executeNotDone(userId);
		request.setAttribute("todoList",todoList);
		
		//STARTのデフォルト値として今日の日付をリクエストスコープに保存
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);
		request.setAttribute("today", today);
		
		
		if (move == null) {
			RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/todoNotDone.jsp");
			d.forward(request, response);
		} else if(move.equals("done")) {
			//完了済みのTodoリストを取得してリクエストスコープに保存
			List<Todo> doneTodoList = getTodoListLogic.executeDone(userId);
			request.setAttribute("doneTodoList",doneTodoList);
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/todoDone.jsp");
			d.forward(request, response);	
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}

