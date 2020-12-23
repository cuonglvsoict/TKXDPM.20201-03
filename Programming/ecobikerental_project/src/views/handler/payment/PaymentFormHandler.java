package views.handler.payment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import common.exceptions.InvalidCardException;
import controller.BaseController;
import controller.PaymentController;
import entities.AppData;
import entities.payment.Card;
import entities.payment.PaymentInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import subsystem.payment.PaymentInfoValidation;
import utils.Configs;
import views.handler.BaseScreenHandler;

/**
 * The class {@code PaymentFormHandler} takes responsibilities for handle
 * payment form
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class PaymentFormHandler extends BaseScreenHandler implements Initializable {

	/**
	 * the logger to log to the console
	 */
	public static Logger logger;

	/**
	 * The object contains payment information, including credit card, card holder
	 * name, date expired, cvv code
	 */
	private PaymentInfo paymentInfo;

	@FXML
	private TextField cardCode;

	@FXML
	private TextField cardHolderName;

	@FXML
	private TextField dateExpired;

	@FXML
	private TextField cvvCode;

	@FXML
	private TextField transactionContent;

	@FXML
	private Button nextButton;

	/**
	 * Initializes a newly created Payment form handler
	 * 
	 * @param primaryStage: primary stage of the application
	 * @param fxmlPath:     path to the fxml resource of the form
	 * @throws IOException
	 */
	public PaymentFormHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new PaymentController());
	}

	/**
	 * Initializes a newly created Payment form handler
	 * 
	 * @param primaryStage: primary stage of the application
	 * @param fxmlPath:     path to the fxml resource of the form
	 * @param bController:  paymentController
	 * @throws IOException
	 */
	public PaymentFormHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(PaymentFormHandler.class.getName());
		paymentInfo = new PaymentInfo();
	}

	@FXML
	void handleNextButtonAction(ActionEvent event) throws IOException {
		// extract user input infomation
		Card card = new Card();
		card.setCardCode(cardCode.getText());
		card.setCardHolderName(this.cardHolderName.getText());
		card.setDateExpired(this.dateExpired.getText());
		card.setCvvCode(this.cvvCode.getText());
		paymentInfo.setCard(card);

		// validate user input information
		// if information is not valid, create an alert to notify to the user
		// in contrast, switch to the confirm screen
		if (!PaymentInfoValidation.validatePaymentInfor(paymentInfo)) {
			logger.info("Invalid payment info");
			BaseScreenHandler.createAlert(AlertType.ERROR, "Invalid input",
					"Check the payment information and try again!");
		} else {
			logger.info("payment info is valid");
			AppData.setAttribute("payment_info", paymentInfo);

			PaymentConfirmHandler confirmPaymentHandler;
			try {
				confirmPaymentHandler = new PaymentConfirmHandler(this.getPrimaryStage(),
						Configs.PAYMENT_CONFIRM_SCREEN);
				confirmPaymentHandler.setHomeScreenHandler(this.getHomeScreenHandler());
				confirmPaymentHandler.setPreviousHandler(this);
				confirmPaymentHandler.show();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				logger.info("Error ocurred! " + ex.getMessage());
			}
		}
	}

	/**
	 * function to display payment form
	 */
	public void displayPaymentForm() {
		this.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		paymentInfo = new PaymentInfo();
		paymentInfo.setCard(new Card());

		this.cardCode.setOnKeyReleased(e -> {
			paymentInfo.getCard().setCardCode(cardCode.getText());
		});

		this.cardHolderName.setOnKeyReleased(e -> {
			paymentInfo.getCard().setCardHolderName(cardHolderName.getText());
		});

		this.cvvCode.setOnKeyReleased(e -> {
			paymentInfo.getCard().setCvvCode(cvvCode.getText());
		});

		this.dateExpired.setOnKeyReleased(e -> {
			paymentInfo.getCard().setDateExpired(dateExpired.getText());
		});

		this.transactionContent.setOnKeyReleased(e -> {
			paymentInfo.setTransactionContent(transactionContent.getText());
		});
	}

}
