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

	@FXML
	private ListView<?> stationList;

	public HomeScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new HomeController());
	}

	public HomeScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
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
		HashMap<String, Station> stations = HomeController.getAllStation();
		
		@SuppressWarnings("rawtypes")
		ObservableList list = FXCollections.observableArrayList();

		stations.forEach((K, T) -> {
			Station st = (Station) T;
			list.add(st.getStationId() + " - " + st.getStationName() + " - " + st.getAddress());
		});

		stationList.getItems().addAll(list);
	}

	@FXML
	void handleStationSelected(MouseEvent event) {
		String selectedStation = (String) stationList.getSelectionModel().getSelectedItem();
		if (selectedStation != null) {
			logger.info(selectedStation + " selected.");
			AppData.setAttribute("selectedStation", selectedStation);

			StationDetailsScreen stationViewHandler;
			try {
				stationViewHandler = new StationDetailsScreen(this.getPrimaryStage(), Configs.STATION_DETAIL_SCREEN);
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
