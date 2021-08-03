package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DayReflection;
import model.Reflection;
import model.ReflectionChangeLogic;

@WebServlet("/ReflectionChange")

/**
 * Servlet implementation class ReflectionChangeServlet
 */
public class ReflectionChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReflectionChangeServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//編集画面に必要な要素を取得
		
		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/reflectionChange.jsp");
		d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userId ="ccc";
		
		//jspから値を取得
		request.setCharacterEncoding("UTF-8");
		DayReflection oldDayReflection = (DayReflection)request.getAttribute("dayReflection");
		
		//使う変数を準備
		Reflection newReflection = new Reflection();
		newReflection.setStartDate(oldDayReflection.getStartDate());
		newReflection.setEndDate(oldDayReflection.getEndDate());
		String progress;
		String comment;
		int type;
		ReflectionChangeLogic logic = new ReflectionChangeLogic();
		boolean result;
		
		String stringDead;
		Date dead;
		boolean todayDo;
		
		//編集をDBに保存する
		for (Reflection oldReflection : oldDayReflection.getNotDoneList()) {
			
			stringDead =request.getParameter("dead");
			if(stringDead != null && !stringDead.equals("")) {
			dead = java.sql.Date.valueOf(stringDead);
			//todoのdeadを変更する
			}
			
			type = 0;
			progress = request.getParameter("progress" + oldReflection.getId());
			comment = request.getParameter("comment" + oldReflection.getId());
			newReflection.setType(type);
			newReflection.setProgress(progress);
			newReflection.setComment(comment);
			result = logic.execute(newReflection, oldReflection);
		}
		
		for (Reflection oldReflection : oldDayReflection.getDoneList()) {
			type = 1;
			progress = request.getParameter("progress" + oldReflection.getId());
			comment = request.getParameter("comment" + oldReflection.getId());
			newReflection.setType(type);
			newReflection.setComment(comment);
			logic.execute(newReflection, oldReflection);
		}
		
		for (Reflection oldReflection : oldDayReflection.getProgressList()) {			
			type = 2;
			progress = request.getParameter("progress" + oldReflection.getId());
			comment = request.getParameter("comment" + oldReflection.getId());
			todayDo =Boolean.valueOf((request.getParameter("todayDo" + oldReflection.getId())));
			if (todayDo == false) {
				newReflection.setTodayDo(todayDo);
			}
			newReflection.setType(type);
			newReflection.setProgress(progress);
			newReflection.setComment(comment);
			logic.execute(newReflection, oldReflection);
		}
		
		for (Reflection oldReflection : oldDayReflection.getTommorowList()) {
			type = 3;
			progress = request.getParameter("progress" + oldReflection.getId());
			comment = request.getParameter("comment" + oldReflection.getId());
			newReflection.setType(type);
			newReflection.setComment(comment);
			logic.execute(newReflection, oldReflection);
		}
		
		for (Reflection oldReflection : oldDayReflection.getComment()) {
			type = 4;
			progress = request.getParameter("progress" + oldReflection.getId());
			comment = request.getParameter("comment" + oldReflection.getId());
			newReflection.setType(type);
			newReflection.setComment(comment);
			logic.execute(newReflection, oldReflection);
		}

		//編集成功したとき
		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/reflection.jsp");
		d.forward(request, response);
		 
		
		 /**編集失敗したとき
		  * RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/reflectionChange.jsp");
		  d.forward(request, response);
		  */
	}

}
