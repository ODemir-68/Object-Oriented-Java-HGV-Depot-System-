import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class WorkSchedule {

	private String Client;
	private String startDate;
	private String endDate;
	private String userName;
	
	public WorkSchedule(String Client, String userName,String startDate, String endDate){
		this.Client=Client;
		this.userName=userName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

	public Date getStartDate(){
		Date startDate1 = null;	
		try {
			startDate1 = dateFormatter.parse(startDate);	//parsing the String into a date format
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate1;
	}

	public Date getEndDate(){		
		Date endDate1 = null;
		try {
			endDate1 = dateFormatter.parse(endDate);		//parsing the String into a date format
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate1;
	}
	
	public String getClient(){
		return Client;
	}
	
	public String getUserName() {
		return userName;
	}	
	
	public Boolean validation(String startDate, String endDate) {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");	//Date formatter to format the date
		Date startDate2 = null;	//where the String variable will be stored as a date 
		Date endDate2 = null;
		Calendar cal = Calendar.getInstance();		//Using a calendar to check the dates 
		Calendar cal2 = Calendar.getInstance();
		try {
			startDate2 = format1.parse(startDate);	//parsing the String into a date format
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			endDate2 = format1.parse(endDate);	//parsing the String into a date format
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DATE, 2);	//2 days in advance (48 hours)
		cal2.add(Calendar.DATE, 3);	//3 days in advance (72 hours)
		String add2Days = format1.format(cal.getTime());	//formatting the Calendar
		String add3Days = format1.format(cal2.getTime());	//formatting the Calendar
		String formattedStartDate = format1.format(startDate2);
		String formattedEndDate = format1.format(endDate2);
		if (formattedStartDate.equals(add2Days) && formattedEndDate.equals(add3Days)) {	//if the rules are met then it is true
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return this.Client +" "+this.userName +" "+ this.startDate +" "+ this.endDate;
	}
}