package views.handler.viewbike;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.HomeController;
import entities.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;
	HashMap<String, Station> label2station;

	@FXML
	private ListView<?> stationList;

	public HomeScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(new HomeController());
		this.setHomeScreenHandler(this);
		logger = utils.Utils.getLogger(HomeScreenHandler.class.getName());
	}

	public HomeController getBController() {
		return (HomeController) super.getbController();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		List<Station> stations = HomeController.getAllStation();
		label2station = new HashMap<String, Station>();

		@SuppressWarnings("rawtypes")
		ObservableList list = FXCollections.observableArrayList();
		for (Station st: stations) {
			list.add(st.toString());
			label2station.put(st.toString(), st);
		}
		stationList.getItems().addAll(list);
	}

	@FXML
	void handleStationSelected(MouseEvent event) {
		String selectedStation = (String) stationList.getSelectionModel().getSelectedItem();
		if (selectedStation != null) {
			logger.info(selectedStation + " selected.");

			StationDetailsScreenHandler stationViewHandler;
			try {
				stationViewHandler = new StationDetailsScreenHandler(this.getPrimaryStage(),
						Configs.STATION_DETAIL_SCREEN, label2station.get(selectedStation));
				stationViewHandler.setHomeScreenHandler(this);
				stationViewHandler.setPreviousHandler(this);
				stationViewHandler.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
