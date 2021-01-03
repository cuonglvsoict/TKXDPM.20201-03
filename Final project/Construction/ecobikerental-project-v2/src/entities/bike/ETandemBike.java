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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " Electric tandem";
	}

	@Override
	public int getDeposit() {
		// TODO Auto-generated method stub
		return 200000;
	}

	@Override
	public int getRentalFees(long startTime, long endTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getBatteryInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
