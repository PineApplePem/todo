package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import model.DateCheckLogic;
import model.DayReflection;
import model.FindOneDayReflectionLogic;
import model.FindTodoByReflectionLogic;
import model.NewDayReflecionLogic;
import model.NewReflectionLogic;
import model.Reflection;
import model.Todo;
import model.TodoListByReflection;

@WebServlet("/ReflectionDayCheck")
/**
 * Servlet implementation class ReflectionDayCheckServlet
 */
public class ReflectionDayCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReflectionDayCheckServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);	
		request.setAttribute("startDate",today); 
		request.setAttribute("endDate",today); 
	
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/dayCheck.jsp");
		d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String userId = "ccc";
		
		String stringStartDate = request.getParameter("startDate"); 
		String stringEndDate = request.getParameter("endDate");

			
		//入力値チェック
		if(stringStartDate != null & stringEndDate != null) {
			
			Date startDate = java.sql.Date.valueOf(stringStartDate);
			Date endDate = java.sql.Date.valueOf(stringEndDate);
				
			request.setAttribute("startDate",startDate);
			request.setAttribute("endDate",endDate);
			
			//LocalDate型にして、endDateとstartDateを比較。startDate=<endDateであるかを確認。
			LocalDate localDateStartDate = startDate.toLocalDate();
			LocalDate localDateEndDate = endDate.toLocalDate();
			
			if(localDateStartDate.isBefore(localDateEndDate) || localDateStartDate.isEqual(localDateEndDate)) {
				
				DateCheckLogic dateCheckLogic = new DateCheckLogic();
				boolean result = dateCheckLogic.execute(userId, startDate, endDate);
				if(result == true) {
					//編集画面を準備する
					
					NewDayReflecionLogic  logic = new  NewDayReflecionLogic ();
					logic.execute(userId, startDate, endDate);
					
					FindOneDayReflectionLogic findReflection = new FindOneDayReflectionLogic();
					DayReflection dayReflection = findReflection.execute(userId, startDate, endDate);
					
					request.setAttribute("dayReflection",dayReflection);
					
					//編集画面へ進む	
					RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/reflectionCreate.jsp");
					d.forward(request, response);
					return;
					
					
			//エラーメッセージを入れる		
				} else {
					request.setAttribute("errorMsg","この期間の反省は既に登録されています");
				}	
			} else {
				request.setAttribute("errorMsg","初めの日付と終わりの日付が反対になっています");
			}	
		} else {
				request.setAttribute("errorMsg","日付が入力されていません");	
			}
				//dateCheckの失敗の時
				RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/dayCheck.jsp");
				d.forward(request, response);
	}		

}
