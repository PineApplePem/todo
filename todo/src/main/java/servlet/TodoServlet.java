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
import model.EditLogic;
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
		request.setCharacterEncoding("UTF-8");
		String move = request.getParameter("move"); 
		String edit = request.getParameter("edit"); 
		
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
		
		//編集がリクエストされていたら編集画面へ移動できるよう準備
		if(edit != null) {
			request.setAttribute("edit",edit);
		}
		
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
		//todoの編集およびコメントの作成
		
		request.setCharacterEncoding("UTF-8");
		
		//newTodoに関する値を取得
		String todo = request.getParameter("todo");
		String deadString = request.getParameter("dead");
		String startString = request.getParameter("start");
		String detail = request.getParameter("deatail");
		
		//oldTodoに関する値を取得
		String oldTodoTodo = request.getParameter("oldTodo");
		String oldDeadString =  request.getParameter("oldDead");
		Date oldDead = null;
		if(oldDeadString != null) {
			if(oldDeadString != "" ) {
				oldDead = java.sql.Date.valueOf(oldDeadString);
			} 
		}
		String oldStartString =  request.getParameter("oldStart");
		Date oldStart = null;
		if(oldStartString != null) {
			if(oldStartString != "" ) {
				oldStart = java.sql.Date.valueOf(oldStartString);
			} 
		}
		String oldDetail = request.getParameter("oldDetail");
		int oldNumber = Integer.parseInt(request.getParameter("oldNumber"));
		
		Todo newTodo = new Todo();
		if(todo != null) {
				newTodo.setTodo(todo);
			}else {
				newTodo.setTodo(oldTodoTodo);
			}
		//date型に変換
 		if(deadString != null) {	
 			if(deadString != "") {
 				Date dead = java.sql.Date.valueOf(deadString);
 				newTodo.setDead(dead);
 			} else {
 				newTodo.setDead(oldDead);
 			}
 		} else {
 			newTodo.setDead(oldDead);
		}
 		if(startString != null) {	
 			if(startString != "" ) {
 				Date start = java.sql.Date.valueOf(startString);
 				newTodo.setStart(start);
 			} else {
 				newTodo.setStart(oldStart);
 			}
 		} else {
 			newTodo.setStart(oldStart);
		}
 		if(detail != null) {
 				newTodo.setDetail(detail);
 			} else {
 				newTodo.setDetail(oldDetail);
 		}
		
 		Todo oldTodo = new Todo();
 		oldTodo.setTodo(oldTodoTodo);
 		oldTodo.setDead(oldDead);
 		oldTodo.setStart(oldStart);
 		oldTodo.setDetail(oldDetail);
 		oldTodo.setNumber(oldNumber);
 		
		EditLogic editLogic = new EditLogic();
		editLogic.execute(oldTodo, newTodo);
		
		//コメントに関する値を取得
		String comment = request.getParameter("comment");
		String tag = request.getParameter("commentTag");
		//DBに保存
		
		response.sendRedirect("/todo/Todo");
		
	}
}

