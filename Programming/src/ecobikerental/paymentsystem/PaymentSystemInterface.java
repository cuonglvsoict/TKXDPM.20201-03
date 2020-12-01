package ecobikerental.paymentsystem;

import javafx.stage.Stage;

/**
 * The {@code PaymentSystemInterface} ...
 * @author vancuonglee
 * @since 1.0
 *
 */
public interface PaymentSystemInterface {
	
	public boolean processRentBikePayOrder(Stage primaryStage);
	public boolean processReturnBikePayOrder(Stage primaryStage);
	public void processCheckBalanceRequest(Stage primaryStage);
}
