package entities.bike.feescalculator;

public class FeesCalculator01 implements  FeesCalculatorInterface {

	@Override
	public int calculateRentalFees(int bikeType, long unlockedTime, long returnTime) {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public int getDeposit(int bikeType) {
		// TODO Auto-generated method stub
		return 10000;
	}

}
