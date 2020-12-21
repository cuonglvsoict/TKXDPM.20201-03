package entities.bike;

import db.DBConnection;
import entities.bike.feescalculator.FeesCalculatorInterface;

public abstract class Bike {
	
	private String bikeId;
	private String stationId;
	private String bikeName;
	private int bikeType;
	private boolean available;
	private String description;
	private String imgPath;
	protected static FeesCalculatorInterface feesCal;
	
	public static Bike getBikeById(String bikeId) {
		DBConnection conn = DBConnection.getDBConnection();
		return conn.getBikeById(bikeId);
	}
	
	public abstract String bikeTypeToString();
	
	public Bike(int bikeType) {
		this.bikeType = bikeType;
	}
	
	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public int getBikeType() {
		return bikeType;
	}

	public void setBikeType(int bikeType) {
		this.bikeType = bikeType;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public static FeesCalculatorInterface getFeesCal() {
		return feesCal;
	}

	public static void setFeesCal(FeesCalculatorInterface feesCal) {
		Bike.feesCal = feesCal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
