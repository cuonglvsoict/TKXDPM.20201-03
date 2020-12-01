package ecobikerental.paymentsystem.creditcardpayment.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreditCardPaymentConfirmController {

    @FXML
    private Button backButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button confirmButton;

    @FXML
    void handleBackButtonAction(ActionEvent event) throws IOException {
    	URL url = Paths.get("src/ecobikerental/paymentsystem/creditcardpayment/boundary/CreditCardPaymentForm.fxml").toUri().toURL();
		Parent preSceneParent = FXMLLoader.load(url);
		Scene preScene = new Scene(preSceneParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(preScene);
		window.setTitle("Payment information");
		window.show();
    }

    @FXML
    void handleConfirmButtonAction(ActionEvent event) {

    }

    @FXML
    void handleHelpButtonAction(ActionEvent event) {

    }

}
