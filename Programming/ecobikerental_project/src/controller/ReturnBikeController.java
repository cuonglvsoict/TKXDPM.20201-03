package controller;

import java.sql.Timestamp;

import db.DBConnection;
import entities.RentalOrder;
import entities.bike.Bike;
import entities.bike.feescalculator.FeesCalculator01;
import entities.payment.Transaction;

public class ReturnBikeController extends BaseController {
	Transaction transaction ;
	DBConnection connectin;
	Bike bike;
	RentalOrder order;
	FeesCalculator01 feescaculator= new FeesCalculator01();
	
	/**
	 * tinh tong chi phi thue xe = phis dat coc -phi thue
	 * @param bikeid 
	 * @param end thoi gian ket thuc thue xe;
	 * @return
	 */
	public int getAmount(String bikeid, Timestamp end){
		order=connectin.getRentalOrderBikeIsRent(bikeid);
		bike= connectin.getBikeById(bikeid);
		int fee= feescaculator.calculateRentalFees(bike.getBikeType(), order.getStartTime().getTime(),end.getTime() );
		int amount= feescaculator.getDeposit(bike.getBikeType())-fee;
		return amount;
	}
	
	public void processReturnBike(String bikeid,String stationid, Timestamp end ) {
		bike= connectin.getBikeById(bikeid);
		order=connectin.getRentalOrderBikeIsRent(bikeid);
//		int fee=feescaculator.calculateRentalFees(bike.getBikeType(),(order.getStartTime()).getTime(), end.getTime();
		connectin.updateBike(bikeid, stationid, true);
	}
	
}
