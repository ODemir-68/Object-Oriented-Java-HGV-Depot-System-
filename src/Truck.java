public class Truck extends Vehicle{
	
	private int cargoCapacity;
	
	public Truck(String make, String model, int weight, String regNo, int cargoCapacity){
		super(make, model, weight, regNo);
		this.cargoCapacity=cargoCapacity;
	}
	
	public void setCargoCapacity(int cargoCapacity){	//cargo Capacity
		this.cargoCapacity = cargoCapacity;
	}
	public int getCargoCapacity(){
		return cargoCapacity;
	}
	
	@Override
	public String toString() {
	    return "RegNo:" + this.getRegNo() + " Make:" + this.getMake() + 
	           " Model:" + this.getModel() + " Weight:" + this.getWeight()
	           +"(kg)"+ " CargoCapacity:"+this.getCargoCapacity()+"(kg)" + "\n";
	}

}
