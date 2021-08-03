package model;

import java.io.Serializable;
import java.sql.Date;

public class DateSet implements Serializable{

	
	private Date startDate;
	private Date endDate;
		
	public DateSet() {}
	public DateSet(Date startDate,Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Date getStartDate() {return startDate;}
	public Date getEndDate() {return endDate;}
		

}
