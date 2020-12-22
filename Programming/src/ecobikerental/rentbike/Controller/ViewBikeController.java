package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Bike;


public class ViewBikeController {
    @FXML
    private Label bksLabel;


    @FXML
    private Label categoryLabel;

    @FXML
    private Label sogheLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label despositeLabel;

    @FXML
    private  Label descriptionLabel;

    public void setBike( Bike bike){
        bksLabel.setText(bike.getBks());
        categoryLabel.setText(bike.getCategory());
        sogheLabel.setText(String.valueOf(bike.getSoghe()));
        priceLabel.setText(String.valueOf(bike.getPrice()));
        despositeLabel.setText(String.valueOf(bike.getDesposit()));
        descriptionLabel.setText(bike.getDescription());
    }
}
