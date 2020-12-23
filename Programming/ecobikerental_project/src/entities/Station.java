package entities;

public class Station {

	private String stationId;
	private String address;
	private String stationName;
	private int dockNo;
	private int freeDock;

	public Station() {

	}

<<<<<<< HEAD
=======
//	public static HashMap<String, Bike> getAllBikeByStationId(String stationId) {
//		DBConnection conn = DBConnection.getDBConnection();
//		List<Bike> bikes = conn.getBikesByStation(stationId);
//
//		HashMap<String, Bike> bikeList = new HashMap<String, Bike>();
//		for (Bike b : bikes) {
//			bikeList.put(b.getBikeId(), b);
//		}
//
//		return bikeList;
//	}
//
//	public static HashMap<String, Station> getAllStation() {
//		DBConnection conn = DBConnection.getDBConnection();
//		List<Station> stations = conn.getAllStation();
//
//		HashMap<String, Station> stationList = new HashMap<String, Station>();
//		for (Station st : stations) {
//			stationList.put(st.getStationId(), st);
//		}
//
//		return stationList;
//	}

>>>>>>> f74712872f23523ca509a80ce5ad845c88952a88
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

	public int getDockNo() {
		return dockNo;
	}

	public void setDockNo(int dockNo) {
		this.dockNo = dockNo;
	}

	public int getFreeDock() {
		return freeDock;
	}

	public void setFreeDock(int freeDock) {
		this.freeDock = freeDock;
	}

}
