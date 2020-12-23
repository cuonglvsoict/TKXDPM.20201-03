package controller;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;

import db.DBInteraction;
import entities.Station;
import entities.bike.Bike;

=======
import db.DBConnection;
import entities.Station;
import entities.bike.Bike;

import java.util.HashMap;
import java.util.List;

>>>>>>> f74712872f23523ca509a80ce5ad845c88952a88
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


<<<<<<< HEAD
    public static Bike getBikeById(String bikeId) {
        return DBInteraction.getBikeById(bikeId);
=======
    public static HashMap<String, Bike> getAllBikeByStationId(String stationId) {
        DBConnection conn = DBConnection.getDBConnection();
        List<Bike> bikes = conn.getBikesByStation(stationId);

        HashMap<String, Bike> bikeList = new HashMap<String, Bike>();
        for (Bike b : bikes) {
            bikeList.put(b.getBikeId(), b);
        }

        return bikeList;
    }

    public static HashMap<String, Station> getAllStation() {
        DBConnection conn = DBConnection.getDBConnection();
        List<Station> stations = conn.getAllStation();

        HashMap<String, Station> stationList = new HashMap<String, Station>();
        for (Station st : stations) {
            stationList.put(st.getStationId(), st);
        }

        return stationList;
    }

    public static Bike getBikeById(String bikeId) {
        DBConnection conn = DBConnection.getDBConnection();
        return conn.getBikeById(bikeId);
>>>>>>> f74712872f23523ca509a80ce5ad845c88952a88
    }
}
