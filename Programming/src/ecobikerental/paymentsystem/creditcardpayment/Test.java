package ecobikerental.paymentsystem.creditcardpayment;

import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		URL url = Paths.get("src/ecobikerental/paymentsystem/creditcardpayment/boundary/CreditCardPaymentForm.fxml").toUri().toURL();
		Parent root = FXMLLoader.load(url);
		primaryStage.setTitle("Thông tin thanh toán");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
