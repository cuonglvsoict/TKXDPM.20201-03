package views.handler.payment;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import controller.PaymentController;
import entities.Invoice;
import entities.bike.Bike;
import entities.payment.PaymentCreditCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;

/**
 * The class {@code PaymentConfirmHandler} takes responsibilities to handle the
 * payment confirmation
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class PaymentConfirmHandler extends BaseScreenHandler {

	/**
	 * logger for logging to the console
	 */
	public static Logger logger;

	private Invoice invoice;

	@FXML
	private Text cardCode;

	@FXML
	private Text cardHolderName;

	@FXML
	private Text amount;

	@FXML
	private Text transactionContent;

	@FXML
	private Button confirmButton;

	/**
	 * Initializes a newly created {@code PaymentConfirmHandler}
	 * 
	 * @param primaryStage: the stage of the application
	 * @param fxmlPath:     url to fxml resource of the screen
	 * @param bController:  the payment controller
	 * @throws IOException
	 */
	public PaymentConfirmHandler(Stage primaryStage, String fxmlPath, Invoice invoice) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(new PaymentController());
		logger = utils.Utils.getLogger(PaymentConfirmHandler.class.getName());
		this.invoice = invoice;
	}

	@FXML
	void handleConfirmButtonAction(ActionEvent event) {
		PaymentController controller = (PaymentController) this.getbController();

		try {
			// call to payment system
			HashMap<String, Object> paymentResult = controller.processPayOrder(invoice.getPaymentInfo(),
					invoice.getOrder().getBike().getDeposit());

			if ((boolean) paymentResult.get("status")) {
				invoice.saveInvoice();
			}

			// switch to payment result screen
			PaymentResultHandler paymentResultHandler = new PaymentResultHandler(this.getPrimaryStage(),
					Configs.PAYMENT_RESULT_SCREEEN, (boolean) paymentResult.get("status"),
					(String) paymentResult.get("notification"));
			paymentResultHandler.setHomeScreenHandler(this.getHomeScreenHandler());
			paymentResultHandler.setPreviousHandler(this);
			paymentResultHandler.show();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
//			logger.info("Error ocurred! " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void show() {
		PaymentCreditCard card = (PaymentCreditCard) invoice.getPaymentInfo().getPaymentMode();
		Bike bike = invoice.getOrder().getBike();
		int amount = bike.getDeposit();
		
		// setup confirm screen
		cardCode.setText(card.getCardID());
		cardHolderName.setText(card.getCardHolderName());
		this.amount.setText(utils.Utils.formatCurrency(amount) + " " + Configs.CURRENCY);
		transactionContent.setText(invoice.getPaymentInfo().getPaymentContent());
		super.show();
	}

}
