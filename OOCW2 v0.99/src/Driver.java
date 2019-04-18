import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Driver implements Scheduable{		//the interface implements the scheduable class
	private String userName;
	private String passWord;
	List<WorkSchedule> schedule = new LinkedList<WorkSchedule>();
	
	public Driver (String userName, String passWord){
		this.userName= userName;
		this.passWord= passWord;
	}
	
	//Check the password
	public boolean CheckPassword(String validatePassword){	
		if (passWord.equals(validatePassword)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isAvaliable(Date startDate, Date endDate){
		for (WorkSchedule Ws : schedule){
			if((Ws.getStartDate().before(startDate)) && (Ws.getEndDate().after(startDate))){
				return false;
			}else if((Ws.getStartDate().before(endDate)) && (Ws.getEndDate().after(endDate))){
				return false;
			}else if((Ws.getStartDate().after(startDate)) && (Ws.getEndDate().before(endDate))){
				return false;
			}else if((Ws.getStartDate().before(startDate)) && (Ws.getEndDate().after(endDate))){
				return false;
			}
		}return true;
	}

	public String getUserName(){	
		return userName;
	}
	
	@Override
	public String toString() {
		return this.userName + (" ");
	}					 
	
	@Override
	public void setSchedule(WorkSchedule schedule){
		this.schedule.add(schedule);
	}
}
