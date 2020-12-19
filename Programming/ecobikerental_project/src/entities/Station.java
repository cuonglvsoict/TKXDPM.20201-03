package entities;

import java.util.HashMap;
import java.util.List;

import db.DBConnection;
import entities.bike.Bike;

public class Station {

	private String stationId;
	private String address;
	private String stationName;
	private HashMap<String, Bike> bikeList;
	
	public Station() {
		
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public HashMap<String, Bike> getBikeList() {
		return bikeList;
	}

	public void setBikeList(HashMap<String, Bike> bikeList) {
		this.bikeList = bikeList;
	}
	
	public static void main(String[] args) {
		DBConnection conn = DBConnection.getDBConnection();
		List<Station> stations = conn.getAllStation();
		for (Station st: stations) {
			System.out.println(st.getStationId());
		}
		
		List<Bike> bikes = conn.getBikesByStation("S002");;
		for (Bike bike: bikes) {
			System.out.println(bike.getBikeId());
		}
	}
	
}
