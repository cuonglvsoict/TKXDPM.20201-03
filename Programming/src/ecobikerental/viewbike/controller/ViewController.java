package ecobikerental.viewbike.controller;

import ecobikerental.viewbike.gui.BikeInfoScreen;
import ecobikerental.viewbike.gui.MapScreen;
import ecobikerental.viewbike.model.Bike;
import ecobikerental.viewbike.model.Customer;

public class ViewController {

	
	public boolean checkRentStatus(int cuustomerID) {
		return true;
	}
	
	public void displayInfo(int customerID) {
		Customer customer = new Customer(customerID);
		// đã thuê xe, hiện thông tin 1 xe
		if (customer.getBike()!=null) {
			displayBike(customerID);
		} else {
		// chưa thuê xe, hiện danh sách Dock
			displayMap();			
		}
	}

	private void displayMap() {
		MapScreen.display();	
	}
	
	private void displayBike(int customerID) {
		Bike bike = new Bike(customerID);
		BikeInfoScreen BikeScreen = new BikeInfoScreen(bike);
		BikeInfoScreen.display();
	}
	
}
