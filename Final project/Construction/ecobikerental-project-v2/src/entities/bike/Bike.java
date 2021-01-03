package entities.bike;

import java.io.File;

import db.DBInteraction;
import javafx.scene.image.Image;

public abstract class Bike {
	
	private String bikeId;
	private String stationId;
	private String bikeName;
	private int bikeType;
	private boolean available;
	private String description;
	private String imgPath;
	
	public static Bike getBikeById(String bikeId) {
		return DBInteraction.getBikeById(bikeId);
	}
	
	public abstract int getDeposit();
	public abstract int getRentalFees(long startTime, long endTime);
	public abstract String getBatteryInfo();
	
	public String toString() {
		return bikeId + " - " + bikeName;
	}
	
	public Image getImage() {
		File file = new File(this.getImgPath());
		return new Image(file.toURI().toString());
	}
	
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
