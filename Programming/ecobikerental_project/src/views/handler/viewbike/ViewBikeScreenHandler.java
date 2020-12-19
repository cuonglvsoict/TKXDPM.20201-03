package views.handler.viewbike;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.BaseScreenHandler;
import views.handler.rentbike.GetBarcodeScreenHandler;

public class ViewBikeScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;
	
	@FXML
    private Button backButton;

    @FXML
    private Button rentBikeButton;
	
	public ViewBikeScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new HomeController());
	}

	public ViewBikeScreenHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(ViewBikeScreenHandler.class.getName());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    void handleBackButtonAction(ActionEvent event) {
		this.goToPreviousScreen();
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
