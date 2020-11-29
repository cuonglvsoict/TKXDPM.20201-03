package javafxsample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller {
	@FXML
	private Text actiontarget;

	public Controller() {
		this.actiontarget = new Text();
	}

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		actiontarget.setText("Done");
	}

}