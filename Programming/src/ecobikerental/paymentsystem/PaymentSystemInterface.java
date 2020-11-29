package ecobikerental.paymentsystem;

/**
 * The {@code PaymentSystemInterface} ...
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public interface PaymentSystemInterface {

	public boolean processRentBikePayOrder(String bikeId, int amount);

	public boolean processReturnBikePayOrder(String bikeId, int amount);

	public void processCheckBalanceRequest();
}
