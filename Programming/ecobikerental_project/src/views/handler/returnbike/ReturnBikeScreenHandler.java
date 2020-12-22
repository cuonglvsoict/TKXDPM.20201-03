package views.handler.returnbike;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.HomeController;
import controller.ReturnBikeController;
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

public class ReturnBikeScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private ListView<?> stationList;

	public ReturnBikeScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new ReturnBikeController());
	}

	public ReturnBikeScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(ReturnBikeScreenHandler.class.getName());
	}

	public HomeController getBController() {
		return (HomeController) super.getbController();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		HashMap<String, Station> stations = Station.getAllStation();

		@SuppressWarnings("rawtypes")
		ObservableList list = FXCollections.observableArrayList();

		stations.forEach((K, T) -> {
			Station st = (Station) T;
			if (st.getFreeDock() > 0) {
				list.add(st.getStationId() + " - " + st.getStationName() + " - Free " + st.getFreeDock() + "docks - "
						+ st.getAddress());
			}
		});

		stationList.getItems().addAll(list);
	}

	@FXML
	void handleStationSelected(MouseEvent event) {
		String selectedStation = (String) stationList.getSelectionModel().getSelectedItem();
		if (selectedStation != null) {
			logger.info(selectedStation + " selected.");
			AppData.setAttribute("returnStation", selectedStation);

			GetBarcodeReturnBikeScreenHandler getBarcodeHandler;
			try {
				getBarcodeHandler = new GetBarcodeReturnBikeScreenHandler(this.getPrimaryStage(),
						Configs.GET_BARCODE_TO_RETURN_BIKE);
				getBarcodeHandler.setHomeScreenHandler(this.getHomeScreenHandler());
				getBarcodeHandler.setPreviousHandler(this);
				getBarcodeHandler.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
