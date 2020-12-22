package controller;

import db.DBConnection;
import entities.bike.Bike;

public class RentBikeController extends BaseController {

	
	public Bike getBikeById(String bikeId) {
		DBConnection conn = DBConnection.getDBConnection();
		return conn.getBikeById(bikeId);
	}
	
	public int getDeposit(Bike bike) {
		return Bike.getFeesCal().getDeposit(bike.getBikeType());
	}
}
