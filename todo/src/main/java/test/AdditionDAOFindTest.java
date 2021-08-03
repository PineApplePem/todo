package test;

import java.util.List;

import dao.AdditionDAO;
import model.Addition;

public class AdditionDAOFindTest {

	public static void main(String[] args) {
		
	int Todo_id = 681;
	
	AdditionDAO dao = new AdditionDAO();
	
	List<Addition> additionList = dao.findAdditionList(Todo_id);
	
	for(Addition addition : additionList) {
	System.out.println(addition.getUserId());
	System.out.println(addition.getTodoId());
	System.out.println(addition.getType());
	System.out.println(addition.getComment());
	}
	
	System.out.println("Hello World");
	
}
}
