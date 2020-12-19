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
import entities.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;
import views.handler.rentbike.GetBarcodeScreenHandler;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private ListView<?> stationList;

//	@FXML
//	private Button rentBikeButton;

	@FXML
	void onMouseClickedHandler(MouseEvent event) {
		String selectedStation = (String) stationList.getSelectionModel().getSelectedItem();
		if (selectedStation != null) {
			logger.info(selectedStation + " selected.");
			AppData.setAttribute("selectedStation", selectedStation);

			ListBikeScreenHandler listBikeHandler;
			try {
				listBikeHandler = new ListBikeScreenHandler(this.getPrimaryStage(), Configs.BIKE_LIST_SCREEN);
				listBikeHandler.setHomeScreenHandler(this);
				listBikeHandler.setPreviousHandler(this);
				listBikeHandler.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//	@FXML
//	void handleRentBikeButtonAction(ActionEvent event) {
//		GetBarcodeScreenHandler barcodeHandler;
//		try {
//			barcodeHandler = new GetBarcodeScreenHandler(this.getPrimaryStage(), Configs.GET_BARCODE_SCREEN);
//			barcodeHandler.setHomeScreenHandler(this);
//			barcodeHandler.setPreviousHandler(this);
//			barcodeHandler.show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		DBConnection conn = DBConnection.getDBConnection();
		List<Station> stations = conn.getAllStation();
		ObservableList list = FXCollections.observableArrayList();

		for (Station st : stations) {
			list.add(st.getStationId() + " - " + st.getStationName() + " - " + st.getAddress());
		}
		stationList.getItems().addAll(list);
	}
}
