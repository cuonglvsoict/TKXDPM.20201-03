import java.io.IOException;

import entities.bike.Bike;
import entities.bike.feescalculator.FeesCalculator01;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Configs;
import views.handler.SplashScreenHandler;
import views.handler.viewbike.HomeScreenHandler;

/**
 * The {@code Main} class is the application entry
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// initialize the scene
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Configs.SPLASH_SCREEN));
		loader.setController(new SplashScreenHandler());
		Pane root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Welcome");
		primaryStage.show();

		// Load splash screen with fade in effect
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setCycleCount(1);

		// Finish splash with fade out effect
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.setCycleCount(1);

		// After fade in, start fade out
		fadeIn.play();
		fadeIn.setOnFinished((e) -> {
			fadeOut.play();
		});

		// After fade out, load actual content
		fadeOut.setOnFinished((e) -> {
			HomeScreenHandler viewBikeHandler;
			try {
				viewBikeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_SCREEN);
				viewBikeHandler.setSceneTitle("EcoBikeRental");
				viewBikeHandler.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

	public static void main(String[] args) {
		String params[] = { "ecobikerental", "root", "" };
		Bike.setFeesCal(new FeesCalculator01());
		Configs.databaseName = params[0];
		Configs.databaseUserName = params[1];
		Configs.databasePassword = params[2];
		
		launch(args);
	}

}
