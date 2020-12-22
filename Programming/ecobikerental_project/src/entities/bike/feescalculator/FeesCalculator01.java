package entities.bike.feescalculator;

import entities.bike.Bike;

public class FeesCalculator01 implements  FeesCalculatorInterface {
	Bike bike;
	public long RentalTime( long unlockedTime, long returnTime) {
		long distanceTime= returnTime-unlockedTime;
		long day= distanceTime/(24*60*60*1000l);
		long minutes= (distanceTime-day*(24*60*60*1000l)/(1000*60));
		return minutes;
	}
	@Override
	public int calculateRentalFees(int bikeType, long unlockedTime, long returnTime) {
		
		long minutes= RentalTime(unlockedTime, returnTime);
		
		int fee = 0;
        if(minutes <= 10){
            return  fee;
        }else{
            if(minutes <= 30){
                fee+=10000;
            }else{
                fee+=10000;
                minutes-=30;
                while(minutes > 0){
                    fee+=3000;
                    minutes-=15;
                }
            }
        }
        return fee;
		
	}

	@Override
	public int getDeposit(int bikeType) {
		if(bikeType==1) {
			return 400000;
		}
		else if(bikeType==2) {
			return 550000;
		}else {
			return 700000;
		}
	}

}
