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
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;

public class CreditCardPaymentConfirmHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private Text cardCode;

	@FXML
	private Text cardHolderName;

	@FXML
	private Text amount;

	@FXML
	private Text transactionContent;

	@FXML
	private Button backButton;

	@FXML
	private Button confirmButton;

	public CreditCardPaymentConfirmHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new PaymentController());
	}

	public CreditCardPaymentConfirmHandler(Stage primaryStage, String fxmlPath, BaseController bController)
			throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(CreditCardPaymentConfirmHandler.class.getName());
	}

	public void show() {
		super.show();
	}

	@FXML
	void handleBackButtonAction(ActionEvent event) throws IOException {
		this.goToPreviousScreen();
	}

	@FXML
	void handleConfirmButtonAction(ActionEvent event) {
		PaymentController controller = (PaymentController) this.getbController();
		boolean result = controller.processPayOrder((PaymentInfo) AppData.getAttribute("payment_info"),
				(int) AppData.getAttribute("amount"));
		AppData.setAttribute("payment_result", result);
		
		CreditCardPaymentResultHandler paymentResultHandler;
		try {
			paymentResultHandler = new CreditCardPaymentResultHandler(this.getPrimaryStage(),
					Configs.CREDIT_CARD_PAYMENT_RESULT);
			paymentResultHandler.setHomeScreenHandler(this.getHomeScreenHandler());
			paymentResultHandler.setPreviousHandler(this);
			paymentResultHandler.show();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
				logger.info("Error ocurred! " + ex.getMessage());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		PaymentInfo info = (PaymentInfo) AppData.getAttribute("payment_info");
		int amount = (int) AppData.getAttribute("amount");

		cardCode.setText(info.getCardCode());
		cardHolderName.setText(info.getOwner());
		this.amount.setText(utils.Utils.formatCurrency(amount) + " " + Configs.CURRENCY);
		transactionContent.setText(info.getTransactionContent());
	}

}
