package views.handler.viewbike;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.handler.BaseScreenHandler;

public class SearchResultScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private ListView<?> searchResult;

	public SearchResultScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new HomeController());
	}

	public SearchResultScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController)
			throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(SearchResultScreenHandler.class.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		List<Station> stationList = (List<Station>) AppData.getAttribute("search_station_result");
		List<Bike> bikeList = (List<Bike>) AppData.getAttribute("search_bike_result");
		
		@SuppressWarnings("rawtypes")
		ObservableList list = FXCollections.observableArrayList();
		if (stationList.size() > 0) {
			for (Station st : stationList) {
				list.add(st.getStationId() + " - " + st.getStationName() + " - " + st.getAddress());
			}
		} else if (bikeList.size() > 0) {
			for (Bike b : bikeList) {
				if (b.getBikeType() == 3) {
					list.add(b.getBikeId() + " - " + b.getBikeName() + " - " + ((ETandemBike) b).getBateryStatus()
							+ "% battery");
				} else {
					list.add(b.getBikeId() + " - " + b.getBikeName());
				}
			}
		}
		searchResult.getItems().addAll(list);
	}

	@FXML
	void handleItemSelected(MouseEvent event) {

	}

}
