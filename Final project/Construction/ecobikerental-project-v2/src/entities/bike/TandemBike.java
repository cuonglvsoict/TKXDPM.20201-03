package entities.bike;

public class TandemBike extends Bike {

	public TandemBike() {
		super(2);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "- Tandem";
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
