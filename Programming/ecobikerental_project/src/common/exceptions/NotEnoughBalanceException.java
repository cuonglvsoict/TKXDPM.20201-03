package common.exceptions;

/**
 * The class {@code NotEnoughBalanceException} to notify credit card not enough
 * balance
 * 
 * @author vancuonglee
 *
 */
public class NotEnoughBalanceException extends EcoBikeRentalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2957737583769978537L;

	public NotEnoughBalanceException() {
		super("Error! Not enough balance exception.");
	}
}
