package entities.bike.feescalculator;

public interface FeesCalculatorInterface {

	public int calculateRentalFees(int bikeType, long unlockedTime, long returnTime);
	public int getDeposit(int bikeType);
}
