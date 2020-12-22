package controller;

import db.DBConnection;
import entities.Station;
import entities.bike.Bike;

import java.util.HashMap;
import java.util.List;

public class HomeController extends BaseController {

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
    }
}
