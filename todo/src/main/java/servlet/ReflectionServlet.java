package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import model.FindReflectionLogic;

import java.util.List;
import java.sql.Date;

import model.DayReflection;
import model.Reflection;
import model.DateCheckLogic;

@WebServlet("/Reflection")

/**
 * Servlet implementation class ReflectionServlet
 */
public class ReflectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReflectionServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String UserId ="ccc";
		
		
		FindReflectionLogic findReflectionLogic =new FindReflectionLogic();
		//一日の反省リストを取り出す
		List<DayReflection> dayReflectionList = findReflectionLogic.execute(UserId);
		request.setAttribute("dayReflectionList", dayReflectionList);			
		
		
		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/reflection.jsp");
		d.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		//新規作成の処理
		//  RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/reflection.jsp");
		 // d.forward(request, response);
		  
		 // 新規作成失敗の時
		 // RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/reflectionCreate.jsp");
		  //d.forward(request, response);
		
		//新規作成の内容が来る
		
	}

}
