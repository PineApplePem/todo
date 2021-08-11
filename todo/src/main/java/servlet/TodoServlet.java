package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.AdditionDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Addition;
import model.EditLogic;
import model.GetTodoListLogic;
import model.Todo;
import login.model.User;

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
		String addition = request.getParameter("viewAddition");
		
		//TODOリストを取得して、リクエストスコープに保存
		GetTodoListLogic getTodoListLogic = new GetTodoListLogic();
		List<Todo> todoList = null;
		List<Todo> doneTodoList = null;
		
		//セッションスコープからUserIDを取得
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");		
		String userId = user.getId();
		
		todoList = getTodoListLogic.executeNotDone(userId);
		request.setAttribute("todoList",todoList);
		doneTodoList = getTodoListLogic.executeDone(userId);
		request.setAttribute("doneTodoList",doneTodoList);
		
		//STARTのデフォルト値として今日の日付をリクエストスコープに保存
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);
		request.setAttribute("today", today);
		
		//Deadが過ぎているTodoのNumberをリストアップする
		if (todoList != null) {
			List<Integer> overDeadList = new ArrayList();
			LocalDate localDateDead; 
			LocalDate localDateToday = today.toLocalDate();
				for(Todo todo : todoList) {
					if(todo.getDead() != null) {
						localDateDead = todo.getDead().toLocalDate();
						if(localDateDead.isBefore(localDateToday)) {
							overDeadList.add(todo.getNumber());
						}
					}
				request.setAttribute("overDeadList", overDeadList);
			}
		}		
		 
		//編集がリクエストされていたら編集画面へ移動できるよう準備
		if(edit != null) {
			request.setAttribute("edit",edit);
		}
		
		//コメントの閲覧をリクエストされていたらコメント閲覧画面へ移動できるよう準備
		if(addition != null) {
			request.setAttribute("viewAddition",addition);
			model.FindAdditionLogic logic = new model.FindAdditionLogic();
			List<Addition> additionList = logic.execute(Integer.parseInt(addition));
			request.setAttribute("additionList",additionList);
		}
		
		
		if(move != null) {
			request.setAttribute("move",move);
		}
		
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		d.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//todoの編集およびコメントの作成
		
		request.setCharacterEncoding("UTF-8");
		
		//ユーザー情報を取得
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");		
		String userId = user.getId();
		
		//コメントのみか、todoも送られてるかの確認
		String onlyComment = request.getParameter("onlyComment");
		
		//コメント、Todoの両方で使う値を取得
		int oldNumber = Integer.parseInt(request.getParameter("oldNumber"));
		
		if(onlyComment == null) { 
		
			//newTodoに関する値を取得
			String todo = request.getParameter("todo");
			String deadString = request.getParameter("dead");
			String startString = request.getParameter("start");
			String detail = request.getParameter("detail");
			
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
			
			
			Todo newTodo = new Todo();
			if(todo != null) {
					newTodo.setTodo(todo);
				}else {
					newTodo.setTodo(oldTodoTodo);
				}
			//date型に変換
			Date dead = oldDead;
	 		if(deadString != null) {	
	 			if(deadString != "") {
	 				dead = java.sql.Date.valueOf(deadString);
	 			}
	 		}	
	 		newTodo.setDead(dead);
	 
	 		Date start = oldStart;
	 		if(startString != null) {	
	 			if(startString != "" ) {
	 				start = java.sql.Date.valueOf(startString);
	 			}
			}
	 		newTodo.setStart(start);
	 		
	 		if(detail != null) {
	 				newTodo.setDetail(detail);
	 			} else {
	 				newTodo.setDetail(oldDetail);
	 		}
			
	 		Todo oldTodo = new Todo();
	 		oldTodo.setNumber(oldNumber);
	 		
			EditLogic editLogic = new EditLogic();
			editLogic.execute(oldTodo, newTodo);
		}
		
		//コメントに関する値を取得
		String comment = request.getParameter("comment");
		String getType = request.getParameter("commentType");
		String overDead = request.getParameter("overDead");
		if(comment != null) {
			if(comment != "") {
				//DBに保存
				Addition addition = new Addition();
				addition.setComment(comment);
				
				String type= "その他";
				if(overDead != null) {
					type = "反省";
				} else {
					if(getType != null) {
						if(getType != "") {
							type = getType;
						}
					}		
				}
				
				addition.setType(type);
				addition.setTodoId(oldNumber);
				addition.setUserId(userId);
		
				AdditionDAO dao = new AdditionDAO();
				boolean result = dao.create(addition);
				if(result == false) {
					request.setAttribute("errorMsg","エラーが発生しました。コメントの登録ができませんでした。");
					RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
					d.forward(request, response);
					return;
				}
			}
		}
		
		if(onlyComment == null) {
			response.sendRedirect("/todo/Todo");
		} else {
			response.sendRedirect("/todo/Todo?viewAddition=" + oldNumber);
		}
		
	}
}

