package model;

import java.util.List;

import dao.AdditionDAO;

public class FindAdditionLogic {
	public List<Addition> execute(int todoId) {
		AdditionDAO dao = new AdditionDAO();
		List<Addition> additionList = dao.findAdditionList(todoId);
		return additionList;
		}
}
