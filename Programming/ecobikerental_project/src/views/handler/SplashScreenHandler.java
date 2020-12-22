package views.handler;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Configs;

/**
 * Class to initialize the splash screen
 * @author vancuonglee
 * @since 1.0
 *
 */

public class SplashScreenHandler implements Initializable {

	/**
	 * logo image view
	 */
    @FXML
    ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File(Configs.LOGO_PATH);
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }
}