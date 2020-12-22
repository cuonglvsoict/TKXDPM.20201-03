package entities;

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
	 * The code of a credit card that was used for renting the bike
	 */
	private String cardCode;
	
	/**
	 * Rental bike id
	 */
	private String bikeId;
	
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

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
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
}
