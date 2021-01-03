package entities.bike;

public class RoadBike extends Bike {

	public RoadBike() {
		super(1);
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
	
	public String toString() {
		return super.toString() + " - Road bike";
	}
}
