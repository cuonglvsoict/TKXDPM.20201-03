package ecobikerental.paymentsystem.creditcardpayment;

import java.net.URL;
import java.nio.file.Paths;

import ecobikerental.paymentsystem.PaymentSystemInterface;
import ecobikerental.paymentsystem.creditcardpayment.entity.CreditCard;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreditCardPaymentBoundary implements PaymentSystemInterface {
	
	public void checkCardInUsed(CreditCard card) {
		
	}

	@Override
	public boolean processRentBikePayOrder(Stage primaryStage) {
		// TODO Auto-generated method stub
		URL url;
		try {
			url = Paths.get("src/ecobikerental/paymentsystem/creditcardpayment/boundary/CreditCardPaymentForm.fxml").toUri().toURL();
			Parent formParent = FXMLLoader.load(url);
			Scene formScene = new Scene(formParent);
			
			primaryStage.setScene(formScene);
			primaryStage.setTitle("Payment information");
			primaryStage.show();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return false;
	}

	@Override
	public boolean processReturnBikePayOrder(Stage primaryStage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processCheckBalanceRequest(Stage primaryStage) {
		// TODO Auto-generated method stub
		
	}
}
