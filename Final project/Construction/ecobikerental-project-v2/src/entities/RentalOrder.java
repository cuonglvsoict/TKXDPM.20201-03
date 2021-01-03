package entities;

import entities.bike.Bike;

/**
 * The {@code RentalOrder} object contains infomation of a rental order,
 * including card code used for renting, bike id, start and return time, and
 * rental fees;
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class RentalOrder {
	/**
	 * Rental order in db
	 */
	private int rentalId;
	
	/**
	 * Rental bike
	 */
	private Bike bike;
	
	/**
	 * rental starting time
	 */
	private long unlockTime;
	
	/**
	 * end of renting time
	 */
	private long returnTime;
	
	/**
	 * The rental fees
	 */
	private int rentalFees;

	public RentalOrder() {

	}

	public int getRentalId() {
		return rentalId;
	}

	public long getUnlockTime() {
		return unlockTime;
	}

	public void setUnlockTime(long unlockTime) {
		this.unlockTime = unlockTime;
	}

	public long getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(long returnTime) {
		this.returnTime = returnTime;
	}

	public int getRentalFees() {
		return rentalFees;
	}

	public void setRentalFees(int rentalFees) {
		this.rentalFees = rentalFees;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

}
