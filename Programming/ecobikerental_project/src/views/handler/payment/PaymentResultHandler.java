package views.handler.payment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BaseController;
import controller.PaymentController;
import entities.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.handler.BaseScreenHandler;

/**
 * The class {@code PaymentResultHandler} takes responsibilities for handle
 * payment result screen
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class PaymentResultHandler extends BaseScreenHandler implements Initializable {

	/**
	 * object to log infomation to console
	 */
	public static Logger logger;

	@FXML
	private Text paymentResult;

	@FXML
	private Text thank;

	@FXML
	private Button exitButton;

	/**
	 * Initializes a newly created payment result handler
	 * 
	 * @param primaryStage: the primary stage of the application
	 * @param fxmlPath:     the path to fxml resource of the result screen
	 * @throws IOException
	 */
	public PaymentResultHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new PaymentController());
	}

	/**
	 * Initializes a newly created payment result handler
	 * 
	 * @param primaryStage: the primary stage of the application
	 * @param fxmlPath:     the path to fxml resource of the result screen
	 * @param bController:  payment controller
	 * @throws IOException
	 */
	public PaymentResultHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(PaymentResultHandler.class.getName());
	}

	@FXML
	void handleExitButtonAction(ActionEvent event) {
		// close the primary stage and close the application
		this.getPrimaryStage().close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		disableSearch();
		// TODO Auto-generated method stub
		this.paymentResult.setText((String) AppData.getAttribute("payment_result_notif"));
		boolean status = (boolean) AppData.getAttribute("payment_status");
		if (status) {
			this.thank.setText("Thanks for choosing us!!!");
		} else {
			this.thank.setText(" ");
		}
	}

}
