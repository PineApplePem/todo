package model;

import java.sql.Date;

public class ChangeDayReflection {
	public void execute(DayReflection oldDayReflection,DayReflection newDayReflection) {
		//使う変数を準備
		Reflection newReflection = new Reflection();
		newReflection.setStartDate(oldDayReflection.getStartDate());
		newReflection.setEndDate(oldDayReflection.getEndDate());
		String progress;
		String comment;
		int type;
		ReflectionChangeLogic logic = new ReflectionChangeLogic();
		boolean result;

		boolean todayDo;
				
		//編集をDBに保存する
		for (Reflection oldReflection : oldDayReflection.getNotDoneList()) {
			type = 0;
			progress = newReflection.getProgress();
			comment = newReflection.getComment();
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
	}
}
