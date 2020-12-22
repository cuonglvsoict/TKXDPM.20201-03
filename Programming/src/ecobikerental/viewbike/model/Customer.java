package ecobikerental.viewbike.model;


public class Customer {

	/*
	 * 
	 */
	int ID;
	String name;
	int PhoneNumber;
	boolean isHiring;
	Bike rentingBike;
	

	public Customer(int customerID) {
		this.ID=customerID;
	}

	public boolean getHiringStat() {
		return isHiring;
	}

	public Bike getBike() {
		// TODO Auto-generated method stub
		return rentingBike;
	}
	
	
	
	
}
