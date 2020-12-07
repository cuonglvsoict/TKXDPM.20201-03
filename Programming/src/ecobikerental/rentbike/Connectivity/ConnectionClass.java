package Connectivity;

import java.sql.*;

public class ConnectionClass {
    public Connection connection;
    public  Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/EcobikeRental","ntc","hailinh2401" );
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public ResultSet getBike(String code){
        ResultSet resultSet = null;
        String  querry = "SELECT * FROM EcobikeRental.Bike where barcode = ?;";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(querry);
            preparedStatement.setString(1, code);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
}
