package model;

public class Customer {

	private int hiringBike=-1;
	
	
	public boolean checkHiringStatus() {
		if(hiringBike==-1) return false;
		else return true;
	}
	
}
