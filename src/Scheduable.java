import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public interface Scheduable {			//We implemented the Interface but the is Available class doesn't seems to work
	List<WorkSchedule> schedule = new LinkedList<WorkSchedule>();
	
	public boolean isAvaliable(Date startDate, Date endDate);
	public void setSchedule(WorkSchedule schedule);
}
