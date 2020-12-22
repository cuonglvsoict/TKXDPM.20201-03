package views.handler;

import java.io.IOException;

import controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.handler.rentbike.GetBarcodeScreenHandler;
import views.handler.returnbike.ReturnBikeScreenHandler;
import views.handler.viewbike.HomeScreenHandler;

/**
 * The abstract class {@code BaseScreenHandler} provides basic methods to work
 * on a screen handler. All the screen handlers must extend this class.
 * 
 * @author vancuongle
 * @since 1.0
 *
 */
public abstract class BaseScreenHandler extends FXMLScreenHandler {

	/**
	 * 
	 */
	private Scene scene;

	/**
	 * 
	 */
	private Stage primaryStage;

	/**
	 * A screen handler object used for turning back to the previous screen
	 */
	private BaseScreenHandler previousHandler;

	/**
	 * The Home Screen Handler used for switching to home screen
	 */
	protected HomeScreenHandler homeScreenHandler;

	/**
	 * The controller object of the current screen
	 */
	private BaseController bController;

	/**
	 * Initializes a newly created handler object
	 * 
	 * @param primaryStage: primary stage of the app
	 * @param fxmlPath:     url to fxml resource of the screen
	 * @throws IOException
	 */
	public BaseScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		super(fxmlPath);
		this.primaryStage = primaryStage;
		this.previousHandler = null;
	}

	/**
	 * the method allows to show the screen contents
	 */
	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.getRootContent());
		}
		this.primaryStage.setScene(this.scene);

		if (this.previousHandler == null) {
			this.disableBackButton();
		}

		searchInput.clear();
		this.primaryStage.show();
	}

	/**
	 * the method allows to create an waiting alert
	 * 
	 * @param type:     alert type
	 * @param headText: the alert header
	 * @param content:  the content of the alert
	 */
	public static void createAlert(AlertType type, String headText, String content) {
		Alert errorAlert = new Alert(type);
		errorAlert.setHeaderText(headText);
		errorAlert.setContentText(content);
		errorAlert.showAndWait();
	}

	/**
	 * The method allows to switch to Home screen
	 */
	public void goToHomeScreen() {
		this.getHomeScreenHandler().disableBackButton();
		this.getHomeScreenHandler().show();
	}

	/**
	 * The method allows to swithc to previous screen
	 */
	public void goToPreviousScreen() {
		this.getPreviousHandler().show();
	}

	/**
	 * The method is used to setup screen title
	 * 
	 * @param title: the screen title
	 */
	public void setSceneTitle(String title) {
		this.getPrimaryStage().setTitle(title);
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public BaseScreenHandler getPreviousHandler() {
		return previousHandler;
	}

	public void setPreviousHandler(BaseScreenHandler previousHandler) {
		this.previousHandler = previousHandler;
	}

	public HomeScreenHandler getHomeScreenHandler() {
		return homeScreenHandler;
	}

	public void setHomeScreenHandler(HomeScreenHandler homeScreenHandler) {
		this.homeScreenHandler = homeScreenHandler;
	}

	public BaseController getbController() {
		return bController;
	}

	public void setbController(BaseController bController) {
		this.bController = bController;
	}

	@FXML
	private Button homeButton;

	@FXML
	private Button backButton;

	@FXML
	private Button rentBikeButton;

	@FXML
	private Button returnBikeButton;

	@FXML
	private Button helpButton;

	@FXML
	private Button feedbackButton;

	@FXML
	private TextField searchInput;

	@FXML
	private Label contentTitle;

	@FXML
	void handleFeedbackClick(ActionEvent event) {

	}

	@FXML
	void handleHelpClick(ActionEvent event) {

	}

	@FXML
	void handleHomeClick(ActionEvent event) {
		this.goToHomeScreen();
	}

	@FXML
	void handleBackButtonClick(ActionEvent event) {
		this.goToPreviousScreen();
	}

	@FXML
	void handleRentBikeClick(ActionEvent event) {
		GetBarcodeScreenHandler viewBikeHandler;
		try {
			viewBikeHandler = new GetBarcodeScreenHandler(primaryStage, Configs.GET_BARCODE_SCREEN);
			viewBikeHandler.setHomeScreenHandler(this.getHomeScreenHandler());
			viewBikeHandler.setPreviousHandler(this);
			viewBikeHandler.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	void handleReturnBikeClick(ActionEvent event) {
		ReturnBikeScreenHandler returnBikeHandler;
		try {
			returnBikeHandler = new ReturnBikeScreenHandler(primaryStage, Configs.RETURN_BIKE_SCREEN);
			returnBikeHandler.setHomeScreenHandler(this.getHomeScreenHandler());
			returnBikeHandler.setPreviousHandler(this);
			returnBikeHandler.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void disableBackButton() {
		this.backButton.setDisable(true);
	}

	public void disableSearch(){
		this.searchInput.setDisable(true);
	}

	@FXML
	void handleSearchEnter(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			String input = searchInput.getText();
				searchByKey();
//				BaseScreenHandler.createAlert(AlertType.INFORMATION, "Searching activated", input);
				// handle search here
		}
	}
	public String getSearchKey(){
		return searchInput.getText();
	}
	public void searchByKey(){
	};

}
