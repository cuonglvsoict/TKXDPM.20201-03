package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.payment.Transaction;
import utils.Configs;

public class DBInteraction {
	
	private Connection connection;
	
	private final String SQL_INSERT_TRANSACTION = "";
	private final String SQL_UPDATE_BIKE_STATUS = "";
	private final String SQL_INSERT_CURRENT_ORDER = "";
	private final String SQL_DELETE_CURRENT_ORDER = "";
	
	public DBInteraction() {
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/" + Configs.DB_NAME, Configs.DB_USERNAME, Configs.DB_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveTransaction(Transaction trans) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_TRANSACTION);
			preparedStatement.setString(1, trans.getCardCode());
            preparedStatement.setBigDecimal(2, trans.getAmount());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

            int row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateBIkeStatus(String bikeId, boolean newStatus) {
		
	}
	
	public void addOrder(String cardId, String bikeId, String createdTime) {
		
	}
	
	public void deleteOrder(String cardId, String bikeId) {
		
	}
}
