package controller;


import java.util.List;

import db.DBInteraction;
import entities.Station;
import entities.bike.Bike;

public class HomeController extends BaseController {
	
	public static List<Bike> getAllBikeByStationId(String stationId) {
		return DBInteraction.getBikesByStation(stationId);
	}
	
	public static List<Station> getAllStation() {
		return DBInteraction.getAllStation();
	}

    public static Bike getBikeById(String bikeId) {
        return DBInteraction.getBikeById(bikeId);
    }
    
    public static List<Station> searchStation(String input){
    	return DBInteraction.searchStation(input);
    }
    
    public static List<Bike> searchBike(String input) {
    	return DBInteraction.searchBike(input);
    }
}
