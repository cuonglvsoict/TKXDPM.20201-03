package views.handler.returnbike;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.PaymentController;
import controller.ReturnBikeController;
import db.DBConnection;
import entities.AppData;
import entities.bike.Bike;
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
import views.handler.payment.PaymentResultHandler;

public class GetBarcodeReturnBikeScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private Button returnButton;

	@FXML
	private TextField bikeBarCode;
	
	@FXML
	private TextField cardCode;

	public GetBarcodeReturnBikeScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new ReturnBikeController());
	}

	public GetBarcodeReturnBikeScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(GetBarcodeReturnBikeScreenHandler.class.getName());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	void handleReturnButtonClicked(ActionEvent event) {
		// validate here

		if (DBConnection.getDBConnection().checkRentalOrder(cardCode.getText(), bikeBarCode.getText())) {
			AppData.setAttribute("card_return_bike", cardCode.getText());
			AppData.setAttribute("returned_bike", bikeBarCode.getText());
			
			PaymentController controller = (PaymentController) this.getbController();
			Bike bike = Bike.getBikeById(bikeBarCode.getText());
			
			// process refund here;
			String paymentResultNotif = null;
			
			AppData.setAttribute("payment_result_notif", paymentResultNotif);

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
		} else {
			BaseScreenHandler.createAlert(AlertType.ERROR, "Invalid barcode or card ID",
					"The barcode or card code is not valid!");
		}
	}

}
