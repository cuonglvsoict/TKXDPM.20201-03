package controller;

import db.DBInteraction;
import entities.RentalOrder;
import entities.bike.Bike;

public class RentBikeController extends BaseController {

	public static Bike getBikeById(String bikeId) {
		return DBInteraction.getBikeById(bikeId);
	}

	public static int getDeposit(Bike bike) {
		return Bike.getFeesCal().getDeposit(bike.getBikeType());
	}

	public static int getRentalFees(RentalOrder order, Bike bike) {
		return Bike.getFeesCal().calculateRentalFees(bike.getBikeType(), order.getUnlockTime(), order.getReturnTime());
	}

	public static int getRefundAmount(RentalOrder order, Bike bike) {
		return getDeposit(bike) - getRentalFees(order, bike);
	}

}
