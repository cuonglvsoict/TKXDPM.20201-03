package controller;


import java.util.HashMap;
import java.util.List;

import db.DBInteraction;
import entities.Station;
import entities.bike.Bike;

public class HomeController extends BaseController {
	
	public static HashMap<String, Bike> getAllBikeByStationId(String stationId) {
		List<Bike> bikes = DBInteraction.getBikesByStation(stationId);

		HashMap<String, Bike> bikeList = new HashMap<String, Bike>();
		for (Bike b : bikes) {
			bikeList.put(b.getBikeId(), b);
		}

		return bikeList;
	}
	
	public static HashMap<String, Station> getAllStation() {
		List<Station> stations = DBInteraction.getAllStation();

		HashMap<String, Station> stationList = new HashMap<String, Station>();
		for (Station st : stations) {
			stationList.put(st.getStationId(), st);
		}

		return stationList;
	}

    public static Bike getBikeById(String bikeId) {
        return DBInteraction.getBikeById(bikeId);
    }

}
