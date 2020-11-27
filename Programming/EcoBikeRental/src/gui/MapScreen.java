package gui;

import model.Customer;

public class MapScreen {

	/*
	 * Attr using along program
	 */
	int UserID;
	Customer customer;
	
	/*
	 * 
	 */
	public MapScreen(int ID) {
		// TODO Auto-generated constructor stub
		this.UserID = ID;
	}
	
	public boolean checkRentStatus() {
		if(customer.checkHiringStatus()==false) return false;
		else return true;
	}
	
	
	
	
	public static void main(String[] args) {
		
	}
	
	
	
}
