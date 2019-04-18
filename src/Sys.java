import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

//ToDo - Clean up code
public class Sys {
	private Boolean loggingon;
	private String userType;
	private String userName;
	private Boolean createWorkSchedule;	
	static int depotOption;				//used to determine current depot		
	static int option;					//Used to decide Menu options
	private Scanner usrIn = new Scanner(System.in);	
	private List<Driver> userAccounts = new ArrayList<Driver>();				//Used to store driver data in Sys
	private List<Vehicle> vehiclesFromDepot = new ArrayList<Vehicle>();		//Vehicle to store driver data in Sys
	private List<Vehicle> tempVehicles = new ArrayList<Vehicle>();			//Temporary Vehicle storage
	private List<WorkSchedule> tempSchedule = new ArrayList<WorkSchedule>();//Temporary Schedule
	private List<WorkSchedule> workScheduleFromDepot = new ArrayList<WorkSchedule>();

	public void mainMenu() {// Depot Selection
		// Depot selection options
		displayMessage("--Welcome pick depot--\n"
				+"1.Depot A\n"
				+"2.Depot B\n"
				+"3.Depot C\n"
				+"4.Exit\n");
		option = usrIn.nextInt();
		depotOption = option;
		new Depot(depotOption).depotB();
		switch (option) {					//Switch statement to decide which depot to go to
		case 1:// DepotA
			authenticateUser();
			break;
		case 2:// Depot B
			authenticateUser();
			break;
		case 3:// Depot C
			authenticateUser();
			break;
		case 4:// Exit
			displayMessage("System exiting");
			System.exit(0);
			break;
		default:// Invalid Input
			displayMessage("ERROR:Operation not recognised, please try again\n");
			mainMenu();
			break;
		}
	}

	public void driverMenu() {								// Driver Menu
		displayMessage("--Welcome to Driver Menu--\n"
				+ "Please choose from following options:\n"
				+ "1.View work schedule\n"  
				+ "2.Exit\n");
		option = usrIn.nextInt();			//Switch statement to show the driver menu
		switch (option) {
		case 1:// View work schedule
			break;
		case 2:// Exit
			System.exit(0);
			break;
		default:// Incorrect input
			displayMessage("ERROR:Operation not recognised, please try again");
			driverMenu();
			break;
		}
	}

	public void managerMenu() {				//Manager Menu
		displayMessage("\n--Welcome to Manager Menu--\n"
				+ "Please choose from following options:\n"
				+ "1.View work schedule\n" + "2.Create work schedule\n"
				+ "3.Move vehicle\n" + "4.Display vehicles\n"
				+ "5.Display my schedule\n" + "6.Create new account\n"
				+ "7.Display users\n"
				+ "8.Exit\n");

		option = usrIn.nextInt();
		switch (option) {
		case 1:
			// View work schedule
			getEveryOnesWorkSchedule();
			break;
		case 2:
			// Create work schedule
			createWorkSchedule();
			break;
		case 3:// Move Vehicle
			moveVehicle();
			break;
		case 4:// Display Vehicle
			//displayVehicle();
			System.out.print(vehiclesFromDepot);
			// managerMenu();
			break;
		case 5:// //Display my work schedule
			break;
		case 6:// Create new account
			createNewAccount();
			break;
		case 7:// Display users
			displayEmployees();
			break;
		case 8:// Exit
			System.exit(0);
			break;
		default:// Incorrect input
			displayMessage("ERROR:Operation not recognised, please try again");
			managerMenu();
			break;
		}
	}

	public void displayMessage(String message) { // Used to output messages
		System.out.print(message);
	}

	public void createNewAccount() {				//Create a new account option
		boolean done = false;
		displayMessage("Press:" 
				+ "\n1.To create Manager account"
				+ "\n2.To create driver account press\n"
				+ "3.to go back press\n");
		option = usrIn.nextInt();
		if (option == 1) {//Create manager's account
			userType = "manager";
		} else if (option == 2) {//Create driver account
			userType = "driver";
		} else if (option == 3) {//Account creation cancelled 
			displayMessage("User creation canceled\n");
			managerMenu();
		} else {//Operation not recognised
			displayMessage("ERROR:Operation not recognised, please try again\n");
			createNewAccount();
		}
		//Entering account details
		displayMessage("\nPlease enter the new username : ");
		String userName = usrIn.next();
		displayMessage("\nPlease enter the Password : ");
		String passWord = usrIn.next();
		displayMessage("\nPlease re-enter the Password : ");
		String reEnteredPassWord = usrIn.next();
		do {//
			if (passWord.equals(reEnteredPassWord)) {
				displayMessage("Create new user: " + userName
						+ "\nPress 1 to confirm"
						+ "\nPress 2 to cancel\n");
				option = usrIn.nextInt();
				if (option == 1) {
					// Create new user
					option = depotOption;
					new Depot(depotOption).addNewUser(userType, userName,
							passWord);
					managerMenu();
				} else if (option == 2) {
					displayMessage("User creation canceled\n");			//Cancel create account 
					managerMenu();
				} else {
					displayMessage("ERROR:Operation not recognised, please try again\n");	//Error displayed
					done = false;
				}
			} else {
				displayMessage("These passwords don't match. \nPress 1 to try again \n Press 2 to cancel \n");		//Passwords not matching
				option = usrIn.nextInt();
				if (option == 1) {
					createNewAccount();			//retry making an account
				} else if (option == 2) {
					displayMessage("User creation canceled\n");
					managerMenu();
				}
			}
		} while (done == false);

	}

	private void authenticateUser() {// Used to authenticate user
		displayMessage("\nPlease enter your Username : ");
		String userName = usrIn.next();
		displayMessage("\nPlease enter your Password : ");
		String passWord = usrIn.next();

		loggingon = new Depot(option).logOn(option, userName, passWord);// Passing user details along with depot chosen
		if (loggingon == true) {// Checking if authentication was successful
			displayMessage("\nUser authentication successful");
			userType(userName);
		} else {// Authentication unsuccessful, going back to depot selection menu
			displayMessage("\nERROR:Wrong Username or Password. Please try again!");
			mainMenu();
		}
	}

	public void displayVehicle() {// Displays vehicles in current depot
		for (int i = 0; i < vehiclesFromDepot.size(); i++) {//Goes through the loop outputting vehicles
			System.out.print(vehiclesFromDepot.get(i));
		}
		pressAnyKeyToContinue();
	}

	private void getEveryOnesWorkSchedule() {	//Managers option to show work schedule of any user
		System.out.print(userAccounts);			
		displayMessage("\nPlease enter the username of the employee that you want to see the work schedule for: ");
		userName = usrIn.next();		//Input the user name of the user to see their work schedule 
		workScheduleFromDepot = new Depot(depotOption).getWorkScheduleByUserName(userName);		

		if (workScheduleFromDepot != null){
			reInitialiseArrays2(tempSchedule);		//getting the array from the depot and adding it in
		}
		else{
			System.out.println("User not found/No work scheduled");	//Error message
			managerMenu();
		}
	}

	public void displayEmployees() {//Displays users in current depot
		displayMessage("List of users:\n");
		for (int i = 0; i < userAccounts.size(); i++) {
			System.out.print(userAccounts.get(i) + "\n");
		}
		pressAnyKeyToContinue();
	}

	public void pressAnyKeyToContinue() {//Press enter to continue
		System.out.println("\nPress Enter to continue...");
		try {
			System.in.read();
			managerMenu();
		} catch (Exception e) {
		}
		managerMenu();
	}

	public void createWorkSchedule() {			//Create work schedule main area in Sys
		displayMessage("\nInput Client Name: ");		//Input Client name
		String clientName = usrIn.next();
		displayMessage("\nStart Date Must be 2 days in advance of current date.(FORMAT: DD-MM-YYYY): ");	//Input Date in certain format
		String startDate = usrIn.next();
		displayMessage("\nEnd date Must be 3 days in advance of current date.(FORMAT: DD-MM-YYYY): ");		//Input Date in certain format
		String endDate = usrIn.next();
		System.out.print(vehiclesFromDepot);	//Print out vehicles in in this depot
		displayMessage("\nPlease Enter Vehicle RegNo (FROMAT: XXXXXX): ");	//Input regno of a vehicle in a certain format 
		String regNo = usrIn.next();
		System.out.print(userAccounts);			//Print out employees in in this depot
		displayMessage("\nPlease Enter Driver to go on Job (Driver Username): ");	//Input Driver user name 
		String driverUserName = usrIn.next();

		createWorkSchedule = new Depot(depotOption).setupWorkSchedule(clientName, startDate, endDate, regNo, driverUserName);	//Passing down the information gotten from the user into the Depot

		if (createWorkSchedule == true) {
			System.out.println("Work Schedule Created!");	//Work schedule created

		} else {
			System.out.println("Work Schedule not created");	//Not created Error message
		}
	}

	//Error messages that are show if something specific has gone wrong during the creation of the work schedule
	public void errorMessage() {
		System.out.println("Inccorect Date! the date must be 2 days ahead and the finish must be 3 days ahead of the current time");
		managerMenu();
	}
	public void errorMessage1() {
		System.out.println("The Reg Number you have entered does not exist! Please Try again.");
		managerMenu();
	}
	public void errorMessage2() {
		System.out.println("The vehicle you have chosen is on a Job/Unavailable");
		managerMenu();
	}
	public void errorMessage3() {
		System.out.println("The Driver username you have entered does not exist! Please Try again.");
		managerMenu();
	}
	public void errorMessage4() {
		System.out.println("The Driver you have chosen is on a Job/Unavailable");
		managerMenu();
	}

	public void moveVehicle() {	//Moves vehicle
		String regNo;
		System.out.print(tempVehicles);
		displayMessage("Please input the vehicles registration number:");
		regNo = usrIn.next();

		new Depot(depotOption).getVehicleByRegNo(regNo, depotOption);
		displayMessage("\nSelect depot to which vehicle will be transfered"
				+ "\n1.DepotA" + "\n2.DepotB" + "\n3.DepotC" + "\n4.Go Back\n");
		option = usrIn.nextInt();
		switch (option) {
		case 1:
			new Depot(depotOption).moveVehicle(depotOption,regNo, "a");
		case 2:
			new Depot(depotOption).moveVehicle(depotOption,regNo, "b");
			break;
		case 3:
			new Depot(depotOption).moveVehicle(depotOption,regNo, "c");
			break;
		case 4:
			managerMenu();
			break;
		default:
			displayMessage("\nERROR:Operation not recognised, please try again");
			break;
		}
	}

	public void myWorkSchedule() {//toDo Display Current users work schedule 
	}

	private void userType(String userName) {// Recognises type of user
		Driver driver = new Depot(option).getDriverByUserName(userName);
		Manager manager = null;
		if (Manager.class.isInstance(driver)) {	//If manager it will cast a driver onto it
			manager = Manager.class.cast(driver);
			option = depotOption;	
			new Depot(option).sendinitialseArrays(option);
		} else {
			driverMenu();
		}
	}

	public void initialiseArrays(List<Driver> drivers, List<Vehicle> vehicles, List<WorkSchedule> schedule) {		//Initialises driver, vehicle and workSchedule that is used in Sys
		userAccounts.addAll(drivers);
		vehiclesFromDepot.addAll(vehicles);
		workScheduleFromDepot.addAll(schedule);
		tempVehicles.addAll(vehiclesFromDepot);
		tempSchedule.addAll(schedule);
		managerMenu();
	}

	public void reInitialiseArrays(List<Vehicle> vehicles) {//Re initialisation of vehicle array
		vehiclesFromDepot.clear();
		vehiclesFromDepot.addAll(vehicles);
		displayMessage("Vehicle transfer complete");
		pressAnyKeyToContinue();
	}

	public void reInitialiseArrays2(List<WorkSchedule> schedule2) { //Re initialisation of Work Schedule Array
		tempSchedule.clear();
		tempSchedule.addAll(schedule2);
		System.out.println(tempSchedule);
		pressAnyKeyToContinue();
	}

	public void addUser(List<Driver> drivers) {// Adds new users
		userAccounts.removeAll(drivers);
		userAccounts.addAll(drivers);
		managerMenu();
	}
}
