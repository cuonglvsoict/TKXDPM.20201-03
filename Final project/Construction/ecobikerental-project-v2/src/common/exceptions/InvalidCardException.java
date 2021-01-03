package common.exceptions;

/**
 * The class {@code InvalidCardException} to notify invalid card exception
 * 
 * @author vancuonglee
 *
 */
public class InvalidCardException extends EcoBikeRentalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1258843222717859979L;
	
	public InvalidCardException() {
		super("Error! Invalid credit card exception.");
		// TODO Auto-generated constructor stub
	}

}
