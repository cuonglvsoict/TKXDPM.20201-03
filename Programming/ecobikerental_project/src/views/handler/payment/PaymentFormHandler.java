package views.handler.payment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.PaymentController;
import entities.AppData;
import entities.payment.PaymentInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;
import views.handler.viewbike.HomeScreenHandler;

public class PaymentFormHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;
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

	public PaymentFormHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new PaymentController());
	}

	public PaymentFormHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(HomeScreenHandler.class.getName());
		paymentInfo = new PaymentInfo();
	}

	@FXML
	void handleNextButtonAction(ActionEvent event) throws IOException {
		PaymentController controller = ((PaymentController) this.getbController());

		if (!controller.validatePaymentInfor(paymentInfo)) {
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
//				logger.info("Error ocurred! " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	public void displayPaymentForm() {
		this.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.disableSearch();
		// TODO Auto-generated method stub
		paymentInfo = new PaymentInfo();

		this.cardCode.setOnKeyReleased(e -> {
			paymentInfo.setCardCode(cardCode.getText());
		});

		this.cardHolderName.setOnKeyReleased(e -> {
			paymentInfo.setOwner(cardHolderName.getText());
		});

		this.cvvCode.setOnKeyReleased(e -> {
			paymentInfo.setCvvCode(cvvCode.getText());
		});

		this.dateExpired.setOnKeyReleased(e -> {
			paymentInfo.setDateExpired(dateExpired.getText());
		});

		this.transactionContent.setOnKeyReleased(e -> {
			paymentInfo.setTransactionContent(transactionContent.getText());
		});
	}

}
