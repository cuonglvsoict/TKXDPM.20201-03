package ecobikerental.paymentsystem.creditcardpayment.Test;

import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		URL url = Paths.get("src/ecobikerental/paymentsystem/creditcardpayment/Test/TestCreditCardPaymentSystem.fxml").toUri().toURL();
		Parent root = FXMLLoader.load(url);
		primaryStage.setTitle("Test credit card payment");
		primaryStage.setScene(new Scene(root, 600, 400));
		
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("amount", "100000");
		data.put("paymentCode", "PAY");
		primaryStage.setUserData(data);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
