package views.handler.viewbike;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.HomeController;
import entities.AppData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;
import views.handler.payment.CreditCardPaymentFormHandler;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private Button testPaymentButton;

	public HomeScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new HomeController());
	}

	public HomeScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		this.setHomeScreenHandler(this);
		logger = utils.Utils.getLogger(HomeScreenHandler.class.getName());
	}

	public HomeController getBController() {
		return (HomeController) super.getbController();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.testPaymentButton.setOnAction(e -> {
			logger.info("User click payment request");
			CreditCardPaymentFormHandler paymentHandler;
			try {
				// for dev only
				AppData.setAttribute("amount", 400000);
				
				// switch to payment form
				paymentHandler = new CreditCardPaymentFormHandler(this.getPrimaryStage(),
						Configs.CREDIT_CARD_PAYMENT_FORM);
				paymentHandler.setHomeScreenHandler(this);
				paymentHandler.setPreviousHandler(this);
				paymentHandler.displayPaymentForm();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				logger.info("Error ocurred! " + ex.getMessage());
			}
		});
	}
}
