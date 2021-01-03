package controller;

import entities.RentalOrder;
import entities.bike.Bike;

public class RentBikeController extends BaseController {
	
	public boolean checkBikeAvailbility(Bike bike) {
		return true;
	}
	
	public RentalOrder createOrder(Bike bike) {
		RentalOrder order = new RentalOrder();
		order.setBike(bike);
		return order;
	}

}