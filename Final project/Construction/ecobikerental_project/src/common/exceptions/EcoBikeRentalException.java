package common.exceptions;

/**
 * The {@code EcoBikeRentalException} wraps all unchecked exceptions You can use this
 * exception to inform
 * @author vancuonglee
 *
 */
public class EcoBikeRentalException extends RuntimeException {

	/**
	 * default serial version uid
	 */
	private static final long serialVersionUID = 1L;

	public EcoBikeRentalException() {

	}

	public EcoBikeRentalException(String message) {
		super(message);
	}

}
