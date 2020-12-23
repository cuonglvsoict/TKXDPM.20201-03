package entities;

public class Station {

	private String stationId;
	private String address;
	private String stationName;
	private int dockNo;
	private int freeDock;

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
