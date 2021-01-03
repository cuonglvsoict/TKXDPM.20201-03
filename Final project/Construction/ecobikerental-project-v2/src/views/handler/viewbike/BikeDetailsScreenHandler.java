package views.handler.viewbike;

import java.io.IOException;
import java.util.logging.Logger;

import controller.HomeController;
import controller.RentBikeController;
import entities.RentalOrder;
import entities.bike.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;
import views.handler.payment.PaymentFormHandler;

public class BikeDetailsScreenHandler extends BaseScreenHandler {

	public static Logger logger;
	private Bike bike;

	@FXML
	private ImageView bikePicture;

	@FXML
	private Button rentDisplayedBikeButton;

	@FXML
	private Label bikeType;

	@FXML
	private Label battery;

	@FXML
	private Label deposit;

	@FXML
	private Label description;

	@FXML
	private Label feesCalculation;

	@FXML
	private Label bikeId;

	@FXML
	private Label bikeName;

	public BikeDetailsScreenHandler(Stage primaryStage, String fxmlPath, Bike bike) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(new HomeController());
		logger = utils.Utils.getLogger(BikeDetailsScreenHandler.class.getName());
		this.bike = bike;
	}
	
	public void show() {
		bikeId.setText(bike.getBikeId());
		bikeType.setText("bike type");
		bikeName.setText(bike.getBikeName());
		deposit.setText(utils.Utils.formatCurrency(bike.getDeposit()));
		battery.setText(bike.getBatteryInfo());
		description.setText(bike.getDescription());
		bikePicture.setImage(bike.getImage());
		
		description.setMaxWidth(180);
		description.setWrapText(true);
		
		super.show();
	}

	@FXML
	void handleRentDisplayBikeButtonClicked(ActionEvent event) {
		try {
			RentBikeController rbController = new RentBikeController();
			if (!rbController.checkBikeAvailbility(bike)) {
				BaseScreenHandler.createAlert(AlertType.ERROR, "Bike not available", "Please select another bike.");
			} else {
				RentalOrder order = rbController.createOrder(bike);
				PaymentFormHandler paymentForm = new PaymentFormHandler(this.getPrimaryStage(), Configs.PAYMENT_FORM_SCREEN, order);
				paymentForm.setHomeScreenHandler(this.getHomeScreenHandler());
				paymentForm.setPreviousHandler(this);
				paymentForm.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
