package views.handler.viewbike;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.handler.BaseScreenHandler;

public class SearchResultScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;
	private List<Bike> bikes;
	private List<Station> stations;

	@FXML
	private ListView<?> searchResult;

	public SearchResultScreenHandler(Stage primaryStage, String fxmlPath, List<Station> stations, List<Bike> bikes)
			throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(new HomeController());
		logger = utils.Utils.getLogger(SearchResultScreenHandler.class.getName());
		this.stations = stations;
		this.bikes = bikes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		ObservableList list = FXCollections.observableArrayList();

		if (stations.size() > 0) {
			for (Station st : stations) {
				list.add(st.toString());
			}
		}
		if (bikes.size() > 0) {
			for (Bike b : bikes) {
				list.add(b.toString());
			}
		}

		searchResult.getItems().addAll(list);
	}

	@FXML
	void handleItemSelected(MouseEvent event) {

	}
}
