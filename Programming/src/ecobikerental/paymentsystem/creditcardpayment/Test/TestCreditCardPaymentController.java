package ecobikerental.paymentsystem.creditcardpayment.Test;

import ecobikerental.paymentsystem.creditcardpayment.CreditCardPaymentBoundary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TestCreditCardPaymentController {

    @FXML
    private Button testPaymentButton;

    @FXML
    void onTestButtonAction(ActionEvent event) {
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	CreditCardPaymentBoundary paymentBoundary = new CreditCardPaymentBoundary();
    	Boolean paymentResult = paymentBoundary.processRentBikePayOrder(window);
    }

}
