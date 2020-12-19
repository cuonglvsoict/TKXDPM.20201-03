package entities.bike;

import java.util.Random;

public class ETandemBike extends Bike {
	
	private int bateryStatus;
	
	public ETandemBike() {
		super(3);
		bateryStatus = 50 + new Random().nextInt(51);
	}
	
	public int getBateryStatus() {
		return bateryStatus;
	}

	public void setBateryStatus(int bateryStatus) {
		this.bateryStatus = bateryStatus;
	}
}
