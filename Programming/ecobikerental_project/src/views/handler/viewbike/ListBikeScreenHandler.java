package views.handler.viewbike;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.HomeController;
import db.DBConnection;
import entities.AppData;
import entities.bike.Bike;
import entities.bike.ETandemBike;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;
import views.handler.rentbike.GetBarcodeScreenHandler;

public class ListBikeScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private Label stationName;

	@FXML
	private Label stationAddress;

	@FXML
	private ListView<?> bikeList;

	@FXML
	private Button backButton;

	@FXML
	private Button rentBikeButton;

	public ListBikeScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new HomeController());
	}

	public ListBikeScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(ListBikeScreenHandler.class.getName());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		DBConnection conn = DBConnection.getDBConnection();
		String[] stationInfo = ((String) AppData.getAttribute("selectedStation")).split("-");
		List<Bike> bikes = conn.getBikesByStation(stationInfo[0].strip());

		ObservableList list = FXCollections.observableArrayList();
		for (Bike b : bikes) {
			if (b.getBikeType() == 3) {
				list.add(b.getBikeId() + " - " + b.getBikeName() + " - " + ((ETandemBike) b).getBateryStatus()
						+ "% battery");
			} else {
				list.add(b.getBikeId() + " - " + b.getBikeName());
			}
		}
		bikeList.getItems().addAll(list);

		stationName.setText("Please choose a bike from " + stationInfo[1].strip());
		stationAddress.setText(stationInfo[2].strip());
	}

	@FXML
	void handleBackButtonAction(ActionEvent event) {
		this.goToPreviousScreen();
	}

	@FXML
	void onMouseClickedHandler(MouseEvent event) {
		String selectedBike = (String) bikeList.getSelectionModel().getSelectedItem();
		if (selectedBike != null) {
			logger.info(selectedBike + " selected.");
			AppData.setAttribute("selectedBike", selectedBike);

			ViewBikeScreenHandler viewBikeScreenHandler;
			try {
				viewBikeScreenHandler = new ViewBikeScreenHandler(this.getPrimaryStage(), Configs.VIEW_BIKE_SCREEN);
				viewBikeScreenHandler.setHomeScreenHandler(this.getHomeScreenHandler());
				viewBikeScreenHandler.setPreviousHandler(this);
				viewBikeScreenHandler.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void handleRentBikeButtonAction(ActionEvent event) {
		GetBarcodeScreenHandler barcodeHandler;
		try {
			barcodeHandler = new GetBarcodeScreenHandler(this.getPrimaryStage(), Configs.GET_BARCODE_SCREEN);
			barcodeHandler.setHomeScreenHandler(this.getHomeScreenHandler());
			barcodeHandler.setPreviousHandler(this);
			barcodeHandler.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
