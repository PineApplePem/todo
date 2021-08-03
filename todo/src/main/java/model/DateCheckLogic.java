package model;

import dao.ReflectionDAO;
import java.sql.Date;

public class DateCheckLogic {
	public boolean execute(String userId,Date startDate,Date endDate) {
		ReflectionDAO dao = new ReflectionDAO();
		boolean result = dao.dateCheck(userId,startDate,endDate);
		return result;
	}
}
