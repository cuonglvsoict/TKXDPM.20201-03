package common.exceptions;

/**
 * The class {@code InternalServerErrorException} to notify internal server
 * error
 * 
 * @author vancuonglee
 *
 */
public class InternalServerErrorException extends EcoBikeRentalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalServerErrorException() {
		super("Error! Internal ecobike rental server exception.");
		// TODO Auto-generated constructor stub
	}

}
