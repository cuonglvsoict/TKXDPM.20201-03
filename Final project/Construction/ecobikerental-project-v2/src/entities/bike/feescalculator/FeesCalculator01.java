package entities.bike.feescalculator;

public class FeesCalculator01 implements FeesCalculatorInterface {

	@Override
	public int calculateRentalFees(int bikeType, long unlockedTime, long returnTime) {
		// TODO Auto-generated method stub
		long minutes = utils.Utils.getRentalTime(unlockedTime, returnTime);

		int fee = 0;
		if (minutes <= 10) {
			return fee;
		} else {
			if (minutes <= 30) {
				fee += 10000;
			} else {
				fee += 10000;
				minutes -= 30;
				while (minutes > 0) {
					fee += 3000;
					minutes -= 15;
				}
			}
		}
		return fee;
	}

	@Override
	public int getDeposit(int bikeType) {
		// TODO Auto-generated method stub
		if (bikeType == 1) {
			return 400000;
		} else if (bikeType == 2) {
			return 550000;
		} else {
			return 700000;
		}
	}

}
