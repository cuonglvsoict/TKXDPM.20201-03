package views.handler.viewbike;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.HomeController;
import entities.Station;
import entities.bike.Bike;
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

public class StationDetailsScreenHandler extends BaseScreenHandler {

	public static Logger logger;
	private Station station;
	private HashMap<String, Bike> label2bike;

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

	public StationDetailsScreenHandler(Stage primaryStage, String fxmlPath, Station station) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(new HomeController());
		logger = utils.Utils.getLogger(StationDetailsScreenHandler.class.getName());
		this.station = station;
	}

	@FXML
	void handleBikeSelected(MouseEvent event) {
		String selectedBike = (String) bikeList.getSelectionModel().getSelectedItem();
		if (selectedBike != null) {
			logger.info(selectedBike + " selected.");

			BikeDetailsScreenHandler viewBikeScreenHandler;
			try {
				viewBikeScreenHandler = new BikeDetailsScreenHandler(this.getPrimaryStage(),
						Configs.BIKE_DETAILS_SCREEN, label2bike.get(selectedBike));
				viewBikeScreenHandler.setHomeScreenHandler(this.getHomeScreenHandler());
				viewBikeScreenHandler.setPreviousHandler(this);
				viewBikeScreenHandler.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void show() {
		List<Bike> bikes = HomeController.getAllBikeByStationId(station.getStationId());
		label2bike = new HashMap<String, Bike>();
		
		@SuppressWarnings("rawtypes")
		ObservableList list = FXCollections.observableArrayList();
		for (Bike b : bikes) {
			list.add(b.toString());
			label2bike.put(b.toString(), b);
		}
		bikeList.getItems().addAll(list);
		
		stationName.setText(station.getStationName());
		stationAddress.setText(station.getAddress());
		this.availNumber.setText(Integer.valueOf(station.getAvailableBikes()).toString());
		this.freeDockNumber.setText(Integer.valueOf(station.getFreeDock()).toString());
		super.show();
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

}
