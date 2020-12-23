package views.handler.rentbike;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.RentBikeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;
import views.handler.payment.PaymentFormHandler;

public class RentalOrderConfirmScreenHandler  extends BaseScreenHandler implements Initializable {

	public static Logger logger;
	
    @FXML
    private Button payButton;
    
	public RentalOrderConfirmScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new RentBikeController());
	}

	public RentalOrderConfirmScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(RentalOrderConfirmScreenHandler.class.getName());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
    @FXML
    void handlePayButtonClicked(ActionEvent event) {
		logger.info("User click payment request");
		PaymentFormHandler paymentHandler;
		try {
			paymentHandler = new PaymentFormHandler(this.getPrimaryStage(), Configs.PAYMENT_FORM_SCREEN);
			paymentHandler.setHomeScreenHandler(this.getHomeScreenHandler());
			paymentHandler.setPreviousHandler(this);
			paymentHandler.displayPaymentForm();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			logger.info("Error ocurred! " + ex.getMessage());
		}
    }

}
