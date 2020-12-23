package views.handler.payment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.PaymentController;
import entities.AppData;
import entities.bike.Bike;
import entities.payment.PaymentInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class PaymentConfirmHandler extends BaseScreenHandler implements Initializable {

	/**
	 * logger for logging to the console
	 */
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
	private Button confirmButton;

	/**
	 * Initializes a newly created {@code PaymentConfirmHandler}
	 * 
	 * @param primaryStage: the stage of the application
	 * @param fxmlPath:     url to fxml resource of the screen
	 * @throws IOException
	 */
	public PaymentConfirmHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new PaymentController());
	}

	/**
	 * Initializes a newly created {@code PaymentConfirmHandler}
	 * 
	 * @param primaryStage: the stage of the application
	 * @param fxmlPath:     url to fxml resource of the screen
	 * @param bController: the payment controller
	 * @throws IOException
	 */
	public PaymentConfirmHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(PaymentConfirmHandler.class.getName());
	}

	@FXML
	void handleConfirmButtonAction(ActionEvent event) {
		PaymentController controller = (PaymentController) this.getbController();
		Bike bike = (Bike) AppData.getAttribute("rented_bike");

		// call to payment system
		String paymentResultNotif = controller.processPayOrder((PaymentInfo) AppData.getAttribute("payment_info"),
				Bike.getFeesCal().getDeposit(bike.getBikeType()));
		AppData.setAttribute("payment_result_notif", paymentResultNotif);

		// switch to payment result screen
		PaymentResultHandler paymentResultHandler;
		try {
			paymentResultHandler = new PaymentResultHandler(this.getPrimaryStage(), Configs.PAYMENT_RESULT_SCREEEN);
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
		
		// extract infomation to display on confirm 
		PaymentInfo info = (PaymentInfo) AppData.getAttribute("payment_info");
		Bike bike = (Bike) AppData.getAttribute("rented_bike");
		int amount = Bike.getFeesCal().getDeposit(bike.getBikeType());

		// setup confirm screen
		cardCode.setText(info.getCard().getCardCode());
		cardHolderName.setText(info.getCard().getCardHolderName());
		this.amount.setText(utils.Utils.formatCurrency(amount) + " " + Configs.CURRENCY);
		transactionContent.setText(info.getTransactionContent());
	}
}
