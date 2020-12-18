package views.handler;

import java.io.IOException;
import java.util.HashMap;

import controller.BaseController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import views.handler.viewbike.HomeScreenHandler;

public abstract class BaseScreenHandler extends FXMLScreenHandler {

	private Scene scene;
	private Stage primaryStage;
	private BaseScreenHandler previousHandler;
	protected HomeScreenHandler homeScreenHandler;
	private BaseController bController;

	public BaseScreenHandler(Stage primaryStage, String fxmlPath) throws IOException {
		super(fxmlPath);
		this.primaryStage = primaryStage;
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.getRootContent());
		}
		this.primaryStage.setScene(this.scene);
		this.primaryStage.show();
	}

	public static void createAlert(AlertType type, String headText, String content) {
		Alert errorAlert = new Alert(type);
		errorAlert.setHeaderText(headText);
		errorAlert.setContentText(content);
		errorAlert.showAndWait();
	}

	public void goToHomeScreen() {
		this.getHomeScreenHandler().show();
	}

	public void goToPreviousScreen() {
		this.getPreviousHandler().show();
	}

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

}
