package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.model.User;
import model.DeleteLogic;
import model.DoneLogic;
import model.NewTodoLogic;
import model.Todo;
/**
 * Servlet implementation class ChangeServlet
 */
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 	request.setCharacterEncoding("UTF-8");
	 	Todo todo = new Todo();
	 	String id = request.getParameter("id"); 
	 	boolean result = false;
	 	
	 	if (id.equals("newTodo")) {
	 		NewTodoLogic newTodoLogic = new NewTodoLogic();
	 		String todoContent = request.getParameter("todo");
	 		String startString =request.getParameter("start");
	 		String deadString = request. getParameter("dead");//後で使う
	 		String detail =request.getParameter("detail");
	 		
	 		//セッションスコープからUserIDを取得（今はダミーのIDを入れる）
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");		
			String userId = user.getId();
	 		
	 		todo.setTodo(todoContent);
	 		todo.setDetail(detail);
	 			
	 		//deadとstartをDate型に変換して、TodoBeansに追加。
	 		if(deadString != "") {	
	 		Date deadDate = java.sql.Date.valueOf(deadString);
	 		todo.setDead(deadDate);
	 		}
	 		
	 		if(startString != "") {	
	 		Date startDate = java.sql.Date.valueOf(startString);
	 		todo.setStart(startDate);
	 		}
	 		
	 		todo.setUserId(userId);
	 		
	 		result = newTodoLogic.execute(todo);	
	 		
	 		
	 		
	 	} else if(id.equals("delete")) {
	 		DeleteLogic deleteLogic = new DeleteLogic();
	 		int number = Integer.parseInt(request.getParameter("number"));
	 		todo.setNumber(number);
	 		result = deleteLogic.execute(todo);
	 	}
	 	
	 	
	 	else if(id.equals("done")) {
	 		DoneLogic doneLogic = new DoneLogic();
	 		int number = Integer.parseInt(request.getParameter("number"));
	 		boolean done = Boolean.parseBoolean(request.getParameter("done"));
	 		todo.setNumber(number);
	 		todo.setDone(done);
	 		result = doneLogic.execute(todo);
	 		} 
	 	
	 	if(result == true) {
	 		response.sendRedirect("/todo/Todo");
	 	} else {
			request.setAttribute("errorMsg","エラーが発生しました。");
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			d.forward(request, response);	
	 	}
	 } 
		
}

