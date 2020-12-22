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

public class PaymentResultHandler extends BaseScreenHandler implements Initializable {

	public static Logger logger;

	@FXML
	private Text paymentResult;

	@FXML
	private Text thank;

	@FXML
	private Button exitButton;

	public PaymentResultHandler(Stage primaryStage, String fxmlPath) throws IOException {
		this(primaryStage, fxmlPath, new PaymentController());
	}

	public PaymentResultHandler(Stage primaryStage, String fxmlPath, BaseController bController) throws IOException {
		super(primaryStage, fxmlPath);
		this.setbController(bController);
		logger = utils.Utils.getLogger(PaymentResultHandler.class.getName());
	}

	@FXML
	void handleExitButtonAction(ActionEvent event) {
		this.getPrimaryStage().close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
