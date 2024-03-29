package views.handler.rentbike;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.RentBikeController;
import entities.AppData;
import entities.bike.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;
import views.handler.viewbike.BikeDetailsScreenHandler;

public class GetBarcodeScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private Button confirmBarcode;

	@FXML
	private TextField bikeBarCode;

	public GetBarcodeScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new RentBikeController());
	}

	public GetBarcodeScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(GetBarcodeScreenHandler.class.getName());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	void handleConfirmBarcodeClicked(ActionEvent event) {
		// validate here

		Bike bike = RentBikeController.getBikeById(bikeBarCode.getText());
		if (bike != null && bike.isAvailable()) {
			AppData.setAttribute("selectedBike", bike.getBikeId());

			BikeDetailsScreenHandler viewBikeScreenHandler;
			try {
				viewBikeScreenHandler = new BikeDetailsScreenHandler(this.getPrimaryStage(),
						Configs.BIKE_DETAILS_SCREEN);
				viewBikeScreenHandler.setHomeScreenHandler(this.getHomeScreenHandler());
				viewBikeScreenHandler.setPreviousHandler(this);
				viewBikeScreenHandler.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			BaseScreenHandler.createAlert(AlertType.ERROR, "Invalid barcode",
					"The barcode is not valid or the corresponding bike is not available!");
		}
	}

}
