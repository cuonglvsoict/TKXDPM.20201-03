package controller;

import java.util.Date;

import db.DBInteraction;
import entities.RentalOrder;
import entities.bike.Bike;

public class ReturnBikeController extends BaseController {

	public static RentalOrder updateRentalOrderOnReturnBike(String bikeId) {
		Bike bike = DBInteraction.getBikeById(bikeId);
		RentalOrder order = DBInteraction.getRentingOrder(bikeId);
		order.setReturnTime(new Date().getTime());
		order.setRentalFees(Bike.getFeesCal().calculateRentalFees(bike.getBikeType(), order.getUnlockTime(),
				order.getReturnTime()));
		return order;
	}

	public static RentalOrder updateRentalOrderOnReturnBike(Bike bike) {
		RentalOrder order = DBInteraction.getRentingOrder(bike.getBikeId());
		order.setReturnTime(new Date().getTime());
		order.setRentalFees(RentBikeController.getRentalFees(order, bike));
		return order;
	}
}
