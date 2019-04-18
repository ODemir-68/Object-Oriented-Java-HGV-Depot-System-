import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Depot {
	private List<Driver> userAccounts = new ArrayList<Driver>();
	private List<Vehicle> vehicles = new LinkedList<Vehicle>();
	private List<Vehicle> tempVehicles = new ArrayList<Vehicle>();
	private List<Vehicle> tempVehicles2 = new ArrayList<Vehicle>();
	private List<WorkSchedule> workSchedule = new LinkedList<WorkSchedule>();
	private List<WorkSchedule> tempWorkSchedule = new LinkedList<WorkSchedule>();

	public Depot(int option) {// Selecting appropriate depot
		if (option == 1) {
			depotA();
		} else if (option == 2) {
			depotB();
		} else if (option == 3) {
			depotC();
		} else;
	}

	public void depotA() {// Hard coded data for depotA
		Truck vehicle0 = new Truck("Man", "TGA26 ", 2000, "ASK293", 150);
		Truck vehicle1 = new Truck("Mercedes-Benz", "2527 ", 2300, "AAL129",200);
		Tanker vehicle2 = new Tanker("Man", "TG360A ", 2200, "AJG0I0", 1000,"Petrol");
		Tanker vehicle3 = new Tanker("Mercedes-Benz", "2628 ", 1800, "ATL9G9",1500, "Oil");
		vehicles.add(vehicle0);
		vehicles.add(vehicle1);
		vehicles.add(vehicle2);
		vehicles.add(vehicle3);
		userAccounts.add(new Driver("Mark", "123"));
		userAccounts.add(new Driver("Oz", "123"));
		userAccounts.add(new Manager("Jonny", "33"));
		workSchedule.add(new WorkSchedule("Client1", "Oz","09-04-2016", "10-04-2016"));
		workSchedule.add(new WorkSchedule("Client2", "Mat","12-04-2016", "13-04-2016"));
	}

	public void depotB() {// Hard coded data for depotB
		Truck vehicle5 = new Truck("Man", "TGA26 ", 2000, "BAJ128", 150);
		Truck vehicle6 = new Truck("Mercedes-Benz", "2527 ", 2300, "BJB012",200);
		Tanker vehicle7 = new Tanker("Man", "TG360A ", 2200, "BJG0I0", 1000,"Petrol");
		Tanker vehicle8 = new Tanker("Mercedes-Benz", "2628 ", 1800, "BTL8G9",1500, "Oil");
		vehicles.add(vehicle5);
		vehicles.add(vehicle6);
		vehicles.add(vehicle7);
		vehicles.add(vehicle8);
		userAccounts.add(new Driver("Zac", "123"));
		userAccounts.add(new Driver("Oz", "123"));
		userAccounts.add(new Manager("Mat", "123"));
		workSchedule.add(new WorkSchedule("Client1", "Zac","09-04-2016", "10-04-2016"));
		workSchedule.add(new WorkSchedule("Client2", "Mat","12-04-2016", "13-04-2016"));
	}

	public void depotC() {// Hard coded data for depotC
		Truck vehicle9 = new Truck("Man", "TGA26 ", 2000, "CIO868", 150);
		Truck vehicle10 = new Truck("Mercedes-Benz", "2527 ", 2300, "CIU590",200);
		Tanker vehicle11 = new Tanker("Man", "TG360A ", 2200, "CJG0I0", 1000,"Petrol");
		Tanker vehicle12 = new Tanker("Mercedes-Benz", "2628 ", 1800, "CTL9G9",1500, "Oil");
		vehicles.add(vehicle9);
		vehicles.add(vehicle10);
		vehicles.add(vehicle11);
		vehicles.add(vehicle12);
		userAccounts.add(new Driver("Ryan", "123"));
		userAccounts.add(new Driver("Oz", "123"));
		userAccounts.add(new Manager("Mat", "123"));
		workSchedule.add(new WorkSchedule("Client1", "Oz","09-04-2016", "10-04-2016"));
		workSchedule.add(new WorkSchedule("Client2", "Mat","12-04-2016", "13-04-2016"));
	}

	public boolean logOn(int option, String userName, String validatePassword) {// Validates user
		if (option == 1 | option == 2 | option == 3) {// retrieves data from appropriate depot
			for (Driver driver : userAccounts) {
				if (userName.equals(driver.getUserName())) {
					return driver.CheckPassword(validatePassword);
				}
			}
		}return false;
	}

	public Driver getDriverByUserName(String userName) { // Compares entered username to hard coded username
		for (Driver currentUser : userAccounts) {
			if (currentUser.getUserName().equals(userName)) {
				return currentUser;
			}
		}return null;
	}

	public Vehicle getVehicleByRegNo(String regNo, int option) {// Retrieves vehicle based on it's registration number
		for (Vehicle currentVehicle : vehicles) {// loop iterating through vehicle array
			if (currentVehicle.getRegNo().equals(regNo)) {// compares registration number
				return currentVehicle;
			}
		}
		sendinitialseArrays(option);
		return null;
	}
	
	public Vehicle getVehicleByRegNo2(String regNo) {// Retrieves vehicle based on it's registration number
		for (Vehicle currentVehicle : vehicles) {// loop iterating through vehicle array
			if (currentVehicle.getRegNo().equals(regNo)) {// compares registration number
				return currentVehicle;
			}
		}return null;
	}
	
	public List<WorkSchedule> getWorkScheduleByUserName(String userName2) { //Gets the workSchecudle by the user name entered
		for (WorkSchedule item : workSchedule) {
			if (item.getUserName().equals(userName2)) {		//If it equals then do this
				tempWorkSchedule.add(item);
				new Sys().reInitialiseArrays2(tempWorkSchedule);
				return tempWorkSchedule;
			}
		}return null;
	}

	public boolean setupWorkSchedule(String clientName, String startDate, String endDate, String regNo, String driverUserName) {	//Setting up Work Schedule 
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String clientName1 = clientName;
		Date startDate1 = null;	//The variables used for the DATE formatted strings
		Date endDate1 = null;
		
		try {		//Try and catch for any errors
			startDate1 = dateFormatter.parse(startDate);	//parsing the String into a Date format 
		} catch (ParseException e) {
			e.printStackTrace();
		}try {
			endDate1 = dateFormatter.parse(endDate);		//parsing the String into a Date format 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Boolean checkDate = new WorkSchedule(null, null,null, null).validation(startDate, endDate);	//Validating the date making sure it is 2 days in advance and end date is 3 days in advance 
		if (checkDate == false) {
			new Sys().errorMessage(); // Error
			return false;
		} else {
		}
		boolean checkRegNo = setupWorkScheduleCheckRegNo(regNo);	//Checks if the regNo exists 
		
		if (checkRegNo == false) {
			new Sys().errorMessage1(); // Error
			return false;
		} else {
		}
		// Can't use a abstract class so I've done this which will allow me to
		// use the is available from an abstract class
		Vehicle checkIsAvaliable = new Vehicle(null, null, 0, regNo) {
			@Override
			public boolean isAvaliable(Date startDate, Date endDate) {
				for (WorkSchedule Ws : schedule) {	//This comparison of the Dates entered and dates in the array actually works but I'm not sure why, but we tried to attempt using it 
					if ((Ws.getStartDate().before(startDate))&& (Ws.getEndDate().after(startDate))) {
						return false;
					}else if ((Ws.getStartDate().before(endDate))&& (Ws.getEndDate().after(endDate))) {
						return false;
					}else if ((Ws.getStartDate().after(startDate))&& (Ws.getEndDate().before(endDate))) {
						return false;
					}else if ((Ws.getStartDate().before(startDate))&& (Ws.getEndDate().after(endDate))) {
						return false;
					}
				}return true;
			}
		};
		if (checkIsAvaliable.isAvaliable(startDate1, endDate1) == false) {
			new Sys().errorMessage2(); // Error
			return false;
		} else {
		}
		boolean checkDriverUserName = setupWorkScheduleCheckUserName(driverUserName);
		
		if (checkDriverUserName == false) {
			new Sys().errorMessage3(); // Error
			return false;
		} else {
		}
		Driver checkIsAvaliable2 = new Driver(driverUserName, null) {
			@Override
			public boolean isAvaliable(Date startDate, Date endDate) {
				// Can't use a abstract class so I've done this which will allow me to
				// use the is available from an abstract class
				for (WorkSchedule Ws : schedule) {	//This comparison of the Dates entered and dates in the array actually works but I'm not sure why, but we tried to attempt using it 
					if ((Ws.getStartDate().before(startDate))&& (Ws.getEndDate().after(startDate))) {
						return false;
					}else if ((Ws.getStartDate().before(endDate))&& (Ws.getEndDate().after(endDate))) {
						return false;
					}else if ((Ws.getStartDate().after(startDate))&& (Ws.getEndDate().before(endDate))) {
						return false;
					}else if ((Ws.getStartDate().before(startDate))&& (Ws.getEndDate().after(endDate))) {
						return false;
					}
				}return true;	
			}
		};
		if (checkIsAvaliable2.isAvaliable(startDate1, endDate1) == false) {
			new Sys().errorMessage4(); // Error
			return false;
		} else {
			workSchedule.add(new WorkSchedule(clientName1, driverUserName, startDate, endDate));
			return true;
		}
	}
	
	public boolean setupWorkScheduleCheckRegNo(String regNo) {// Getting the registration number for the is available
		String regNo1 = regNo;
		Vehicle checkRegNo = getVehicleByRegNo2(regNo1);
		if (checkRegNo != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setupWorkScheduleCheckUserName(String driverUserName) {
		String driverUserName1 = driverUserName;
		Driver checkUserName = getDriverByUserName(driverUserName1);
		if (checkUserName != null) {
			return true;
		} else {
			return false;
		}
	}

	public List sendinitialseArrays(int option) {// Returns vehicles in depot
		if (option == 1 | option == 2 | option == 3) {
			for (Vehicle item : vehicles) {
			}for (Driver item : userAccounts) {
			}for (WorkSchedule item : workSchedule){
				new Sys().initialiseArrays(userAccounts, vehicles,workSchedule);// Passes vehicles to system array
			}
		}return null;
	}
	
	public List<Vehicle> moveVehicle(int option,String regNo, String depot) {// Move vehicle
		if (option == 1) {
			if (depot.equals("a")){
				new Sys().pressAnyKeyToContinue();
			}
			if(depot.equals("b")){
				for (Vehicle currentVehicle : vehicles) {
					if (currentVehicle.getRegNo().equals(regNo)) {
						tempVehicles.add(currentVehicle);//Add current to temp
						vehicles.remove(currentVehicle);//remove current from vehicles
						tempVehicles2.addAll(vehicles);//adds vehicles to temp2 creating a copy - return this
						vehicles.removeAll(vehicles);//Clears vehicles
						tempVehicles.addAll(tempVehicles2);
						depotB();
						vehicles.add(currentVehicle);
						System.out.print("\nVehicles in depot B:\n" + vehicles);
						vehicles.removeAll(vehicles);
						vehicles.addAll(tempVehicles2);
						System.out.print("\n\n Vehicles in depot A:\n"+vehicles+"\n");
						break;
					} else {
						System.out.println("Vehicle with regNo: " + regNo + " not found");
						new Sys().reInitialiseArrays(vehicles);
					}
				}
				new Sys().reInitialiseArrays(vehicles);
			}
			if(depot.equals("c")){
				for (Vehicle currentVehicle : vehicles) {
					if (currentVehicle.getRegNo().equals(regNo)) {
						tempVehicles.add(currentVehicle);//Add current to temp
						vehicles.remove(currentVehicle);//remove current from vehicles
						tempVehicles2.addAll(vehicles);//adds vehicles to temp2 creating a copy - return this
						vehicles.removeAll(vehicles);//Clears vehicles
						tempVehicles.addAll(tempVehicles2);
						depotC();
						vehicles.add(currentVehicle);
						System.out.print("\nVehicles in depot C:\n" + vehicles);
						vehicles.removeAll(vehicles);
						vehicles.addAll(tempVehicles2);
						System.out.print("\n\n Vehicles in depot A:\n"+vehicles+"\n");
						break;
					} else {
						System.out.println("Vehicle with regNo: " + regNo + " not found");
						new Sys().reInitialiseArrays(vehicles);
					}
				}
				new Sys().reInitialiseArrays(vehicles);
			}
		}
		// Option b
		if (option == 2) {
			if (depot.equals("b")){
				new Sys().pressAnyKeyToContinue();
			}if(depot.equals("a")){
				for (Vehicle currentVehicle : vehicles) {
					if (currentVehicle.getRegNo().equals(regNo)) {
						tempVehicles.add(currentVehicle);//Add current to temp
						vehicles.remove(currentVehicle);//remove current from vehicles
						tempVehicles2.addAll(vehicles);//adds vehicles to temp2 creating a copy - return this
						vehicles.removeAll(vehicles);//Clears vehicles
						tempVehicles.addAll(tempVehicles2);
						depotA();
						vehicles.add(currentVehicle);
						System.out.print("\nVehicles in depot A:\n" + vehicles);
						vehicles.removeAll(vehicles);
						vehicles.addAll(tempVehicles2);
						System.out.print("\n\n Vehicles in depot B:\n"+vehicles+"\n");
						break;
					} else {
						System.out.println("Vehicle with regNo: " + regNo + " not found");
						new Sys().reInitialiseArrays(vehicles);
					}
				}
				new Sys().reInitialiseArrays(vehicles);
			}if(depot.equals("c")){
				for (Vehicle currentVehicle : vehicles) {
					if (currentVehicle.getRegNo().equals(regNo)) {
						tempVehicles.add(currentVehicle);//Add current to temp
						vehicles.remove(currentVehicle);//remove current from vehicles
						tempVehicles2.addAll(vehicles);//adds vehicles to temp2 creating a copy - return this
						vehicles.removeAll(vehicles);//Clears vehicles
						tempVehicles.addAll(tempVehicles2);
						depotC();
						vehicles.add(currentVehicle);
						System.out.print("\nVehicles in depot C:\n" + vehicles);
						vehicles.removeAll(vehicles);
						vehicles.addAll(tempVehicles2);
						System.out.print("\n\n Vehicles in depot B:\n"+vehicles+"\n");
						break;
					} else {
						System.out.println("Vehicle with regNo: " + regNo + " not found");
						new Sys().reInitialiseArrays(vehicles);
					}
				}
				new Sys().reInitialiseArrays(vehicles);
			}
		}
		// Option c
		if (option == 3) {
			if (depot.equals("c")){
				new Sys().pressAnyKeyToContinue();
			}if(depot.equals("b")){
				for (Vehicle currentVehicle : vehicles) {
					if (currentVehicle.getRegNo().equals(regNo)) {
						tempVehicles.add(currentVehicle);//Add current to temp
						vehicles.remove(currentVehicle);//remove current from vehicles
						tempVehicles2.addAll(vehicles);//adds vehicles to temp2 creating a copy - return this
						vehicles.removeAll(vehicles);//Clears vehicles
						tempVehicles.addAll(tempVehicles2);
						depotB();
						vehicles.add(currentVehicle);
						System.out.print("\nVehicles in depot B:\n" + vehicles);
						vehicles.removeAll(vehicles);
						vehicles.addAll(tempVehicles2);
						System.out.print("\n\n Vehicles in depot C:\n"+vehicles+"\n");
						break;
					} else {
						System.out.println("Vehicle with regNo: " + regNo + " not found");
						new Sys().reInitialiseArrays(vehicles);
					}
				}
				new Sys().reInitialiseArrays(vehicles);
			}if(depot.equals("a")){
				for (Vehicle currentVehicle : vehicles) {
					if (currentVehicle.getRegNo().equals(regNo)) {
						tempVehicles.add(currentVehicle);//Add current to temp
						vehicles.remove(currentVehicle);//remove current from vehicles
						tempVehicles2.addAll(vehicles);//adds vehicles to temp2 creating a copy - return this
						vehicles.removeAll(vehicles);//Clears vehicles
						tempVehicles.addAll(tempVehicles2);
						depotA();
						vehicles.add(currentVehicle);
						System.out.print("\nVehicles in depot A:\n" + vehicles);
						vehicles.removeAll(vehicles);
						vehicles.addAll(tempVehicles2);
						System.out.print("\n\n Vehicles in depot C:\n"+vehicles+"\n");
						break;
					} else {
						System.out.println("Vehicle with regNo: " + regNo + " not found");
						new Sys().reInitialiseArrays(vehicles);
					}
				}
				new Sys().reInitialiseArrays(vehicles);
			}
		}
		return vehicles;
	}

	public void addNewUser(String userType, String userName, String passWord) {//Adds new user
		if (userType.equals("manager")) {// Checks if user wants to create driver or manager account
			userAccounts.add(new Manager(userName, passWord));
			new Sys().addUser(userAccounts);
		} else if (userType.equals("driver")) {
			userAccounts.add(new Driver(userName, passWord));
			new Sys().addUser(userAccounts);
		}
	}
}
