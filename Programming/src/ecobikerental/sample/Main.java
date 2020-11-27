package ecobikerental.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(this.getClass().getResource("PayOrderInfo.fxml"));
		primaryStage.setTitle("Payment information");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
