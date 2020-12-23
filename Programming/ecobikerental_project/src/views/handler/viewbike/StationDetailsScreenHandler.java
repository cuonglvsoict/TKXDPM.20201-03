package views.handler.viewbike;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.HomeController;
import entities.AppData;
import entities.Station;
import entities.bike.Bike;
import entities.bike.ETandemBike;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;

public class StationDetailsScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private ListView<?> bikeList;

	@FXML
	private Label stationAddress;

	@FXML
	private Label availNumber;

	@FXML
	private Label freeDockNumber;

	@FXML
	private Label stationName;

	public StationDetailsScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new HomeController());
	}

	public StationDetailsScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(StationDetailsScreenHandler.class.getName());
	}


	@FXML
	void handleBikeSelected(MouseEvent event) {
		String selectedBike = (String) bikeList.getSelectionModel().getSelectedItem();
		if (selectedBike != null) {
			logger.info(selectedBike + " selected.");
			AppData.setAttribute("selectedBike", selectedBike);

			BikeDetailsScreenHandler viewBikeScreenHandler;
			try {
				viewBikeScreenHandler = new BikeDetailsScreenHandler(this.getPrimaryStage(),
						Configs.BIKE_DETAILS_SCREEN);
				viewBikeScreenHandler.setHomeScreenHandler(this.getHomeScreenHandler());
				viewBikeScreenHandler.setPreviousHandler(this);
				viewBikeScreenHandler.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String[] stationInfo = ((String) AppData.getAttribute("selectedStation")).split(" - ");
		Station station = HomeController.getAllStation().get(stationInfo[0].strip());
		HashMap<String, Bike> bikes = HomeController.getAllBikeByStationId(stationInfo[0]);

		@SuppressWarnings("rawtypes")
		ObservableList list = FXCollections.observableArrayList();

		bikes.forEach((K, T) -> {
			Bike b = (Bike) T;
			if (b.getBikeType() == 3) {
				list.add(b.getBikeId() + " - " + b.getBikeName() + " - " + ((ETandemBike) b).getBateryStatus()
						+ "% battery");
			} else {
				list.add(b.getBikeId() + " - " + b.getBikeName());
			}
		});
		bikeList.getItems().addAll(list);

		stationName.setText(stationInfo[1].strip());
		stationAddress.setText(stationInfo[2].strip());
		this.availNumber.
		setText(Integer.valueOf(station.getDockNo() - station.getFreeDock())
				.toString());
		this.freeDockNumber.setText(Integer.valueOf(station.getFreeDock()).toString());
	}

}
