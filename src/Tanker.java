public class Tanker extends Vehicle {
	
	private int liquidCapacity;
	private String liquidType;
	
	public Tanker(String make, String model, int weight, String regNo, int liquidCapacity, String liquidType){
		super(make, model, weight, regNo);
		this.liquidCapacity=liquidCapacity;
		this.liquidType=liquidType;
	}
	public void setLiquidCapacity(int liquidCapacity){	//liquidCapacity
		this.liquidCapacity = liquidCapacity;
	}
	public int getLiquidCapacity(){
		return liquidCapacity;
	}
	
	public void setLiquidType(String liquidType){	//liquidType
		this.liquidType = liquidType;
	}
	public String getLiquidType(){
		return liquidType;
	}
	
	@Override
	public String toString() {
	    return  "RegNo: " + this.getRegNo() +" Make:" + this.getMake() + 
	           " Model:" + this.getModel() + " Weight:" + this.getWeight()
	           +"(kg)"+" LiquidCapacity:"+  this.getLiquidCapacity() + " LiquidType:"+this.getLiquidType()+"(L)"+  "\n";
	}

}
