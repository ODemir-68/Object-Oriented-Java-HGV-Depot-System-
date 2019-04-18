import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public abstract class Vehicle implements Scheduable {	//the interface implements the scheduable class 
	private String make;
	private String model;
	private int weight;
	private String regNo;
	
	List<WorkSchedule> schedule = new LinkedList<WorkSchedule>();

	public Vehicle(String make, String model, int weight,String regNo){
		this.make=make;
		this.model=model;
		this.weight=weight;
		this.regNo=regNo;	
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
	
	public void setMake(String make){	//Make
		this.make = make;
	}
	public String getMake(){
		return make;
	}
	
	public void setModel(String model){	//Model
		this.model = model;
	}
	public String getModel(){
		return model;
	}
	
	public void setWeight(int weight){	//Weight
		this.weight = weight;
	}
	public int getWeight(){
		return weight;
	}
	
	public void setRegNo(String regNo){	//RegNo
		this.regNo = regNo;
	}
	public String getRegNo(){
		return regNo;
	}
	
	public void setSchedule(WorkSchedule schedule){
		this.schedule.add(schedule);
	}
	
	public List<WorkSchedule> getSchedule(){
		return schedule;
	}
}
