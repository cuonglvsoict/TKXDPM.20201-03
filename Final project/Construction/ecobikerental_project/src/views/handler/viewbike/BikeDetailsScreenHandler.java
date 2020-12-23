package views.handler.viewbike;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.HomeController;
import entities.AppData;
import entities.bike.Bike;
import entities.bike.ETandemBike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;
import views.handler.payment.PaymentFormHandler;

public class BikeDetailsScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

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

	public BikeDetailsScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new HomeController());
	}

	public BikeDetailsScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController)
			throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(BikeDetailsScreenHandler.class.getName());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String[] bikeInfo = ((String) AppData.getAttribute("selectedBike")).split("-");
		Bike bike = HomeController.getBikeById(bikeInfo[0].strip());
		AppData.setAttribute("rented_bike", bike);
		
		bikeId.setText(bike.getBikeId());
		bikeType.setText(bike.bikeTypeToString());
		bikeName.setText(bike.getBikeName());
		deposit.setText(utils.Utils.formatCurrency(Bike.getFeesCal().getDeposit(bike.getBikeType())));
		
		if (bike.getBikeType()==3) {
			battery.setText(((ETandemBike)bike).getBateryStatus() + "%");
		} else {
			battery.setText("Not supported");
		}
		
		description.setText(bike.getDescription());
		description.setMaxWidth(180);
		description.setWrapText(true);

		File file = new File(bike.getImgPath());
		Image image = new Image(file.toURI().toString());
		bikePicture.setImage(image);
	}

	@FXML
	void handleRentDisplayBikeButtonClicked(ActionEvent event) {
		PaymentFormHandler paymentForm;
		try {
			paymentForm = new PaymentFormHandler(this.getPrimaryStage(), Configs.PAYMENT_FORM_SCREEN);
			paymentForm.setHomeScreenHandler(this.getHomeScreenHandler());
			paymentForm.setPreviousHandler(this);
			paymentForm.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
