package ecobikerental.paymentsystem.creditcardpayment.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

import ecobikerental.paymentsystem.creditcardpayment.entity.CreditCard;
import ecobikerental.paymentsystem.creditcardpayment.entity.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreditCardPaymentFormController {

	@FXML
	private TextField cardCode;

	@FXML
	private TextField cardHolderName;

	@FXML
	private TextField dateExpired;

	@FXML
	private TextField cvvCode;

	@FXML
	private TextField appKey;

	@FXML
	private TextField secretKey;

	@FXML
	private TextField transactionContent;

	@FXML
	private Button backButton;

	@FXML
	private Button helpButton;

	@FXML
	private Button nextButton;

	@FXML
	void handleNextButtonAction(ActionEvent event) throws IOException {

		if (!this.verifyPaymentInput()) {
			// create alert to notify invalid input
		}

		URL url = Paths.get("src/ecobikerental/paymentsystem/creditcardpayment/boundary/CreditCardPaymentConfirm.fxml")
				.toUri().toURL();
		Parent confirmSceneParent = FXMLLoader.load(url);
		Scene confirmScene = new Scene(confirmSceneParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(confirmScene);
		window.setTitle("Confirm payment");

		// put user information to card intance and transaction intance
		CreditCard card = new CreditCard(cardCode.getText(), cardHolderName.getText(), cvvCode.getText(),
				dateExpired.getText());
		Transaction transaction = new Transaction("PAY", card, transactionContent.getText(),
				(int) Double.parseDouble((String) ((Map) window.getUserData()).get("amount")));

		// extract information to display in the confirm payment screen
		Text cardcode = (Text) confirmScene.lookup("#cardCode");
		Text cardHolderName = (Text) confirmScene.lookup("#cardHolderName");
		Text amount = (Text) confirmScene.lookup("#amount");
		Text transactionContent = (Text) confirmScene.lookup("#transactionContent");

		cardcode.setText(card.getCardCode());
		cardHolderName.setText(transaction.getTransactionContent());
		amount.setText(Integer.valueOf(transaction.getAmount()).toString());
		transactionContent.setText(transaction.getTransactionContent());

		window.show();
	}

	@FXML
	void handleBackButtonAction(ActionEvent event) throws IOException {
		URL url = Paths.get("src/ecobikerental/paymentsystem/creditcardpayment/Test/TestCreditCardPaymentSystem.fxml")
				.toUri().toURL();
		Parent preSceneParent = FXMLLoader.load(url);
		Scene preScene = new Scene(preSceneParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(preScene);
		window.setTitle("Test credit card Payment");
		window.show();
	}

	@FXML
	void handleHelpButtonAction(ActionEvent event) {

	}

	private boolean verifyPaymentInput() {
		return true;
	}

}
