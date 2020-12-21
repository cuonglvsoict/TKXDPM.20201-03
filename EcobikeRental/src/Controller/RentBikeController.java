package Controller;

import Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bike;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentBikeController {
    @FXML
     private TextField textcode;

    private Bike bike;
    @FXML
     private Label label_err;

    @FXML
    private Button btnrentbike;

    private ConnectionClass connect;

    @FXML
    void initialize(){

        btnrentbike.setOnAction(e->{
            connect = new ConnectionClass();
            String code = textcode.getText();
            ResultSet r = connect.getBike(code);
            int count =0;
               try {
                   while (r.next()){
                       bike = new Bike(r.getString("bks"),r.getInt("soghe"),r.getString("category"),r.getInt("price"),r.getString("descpription"));
                       count++;
                   }

               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }
               if (count == 1){
                   btnrentbike.getScene().getWindow().hide();
                   FXMLLoader loader = new FXMLLoader();
                   loader.setLocation(getClass().getResource("../View/ViewBike.fxml"));
                   try {
                       loader.load();

                   } catch (IOException ioException) {
                       ioException.printStackTrace();
                   }
                   Parent root = loader.getRoot();
                   Stage stage = new Stage();
                   stage.setScene(new Scene(root));
                   ViewBikeController controller = loader.getController();
                   controller.setBike(bike);
                   stage.showAndWait();
               }
               else label_err.setText("barcode is not correct !!! ");

        });
    }
}
