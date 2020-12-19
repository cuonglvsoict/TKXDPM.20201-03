package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import entities.bike.Bike;
import entities.payment.Transaction;
import utils.Configs;

public class DBConnection {

	private static DBConnection DBConnection;
	private Connection connection;

	private final String SQL_INSERT_TRANSACTION = "insert into transaction (order_id, card_id, amount, command, created_at, content) values (?, ?, ?, ?, ?, ?);";
	private final String SQL_SET_BIKE_AVAIL = "update bike set available = ? where bike_id = ?;";
	private final String SQL_INSERT_RENTAL_ORDER = "insert into rental_orders (card_id, bike_id, start_time) values (?, ?, ?);";
	private final String SQL_UPDATE_RENTAL_ORDER = "update rental_orders set is_return = TRUE, return_time = ?, rental_fees = ? where bike_id = ? and is_return = FALSE;";
	private final String SQL_CHECK_CARD_IN_USED = "select * from rental_orders where card_id = ? and is_return = false";
	private final String SQL_GET_ALL_STATION = "select * from station;";
	private final String SQL_GET_BIKES_BY_STATION = "select * from bike when station_id = ?";

	private DBConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Configs.DB_NAME, Configs.DB_USERNAME,
					Configs.DB_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DBConnection getDBConnection() {
		if (DBConnection == null) {
			DBConnection = new DBConnection();
		}
		return DBConnection;
	}

	public void saveTransaction(Transaction trans, int orderId) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_TRANSACTION);
			preparedStatement.setInt(1, orderId);
			preparedStatement.setString(2, trans.getCardCode());
			preparedStatement.setInt(3, trans.getAmount());
			preparedStatement.setString(4, trans.getCommand());
			preparedStatement.setTimestamp(5,
					new Timestamp(utils.Utils.parseDateTime(trans.getCreatedAt(), Configs.DATETIME_FORMAT).getTime()));
			preparedStatement.setString(6, trans.getTransactionContent());

			preparedStatement.executeUpdate();
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setBikeAvailability(String bikeId, boolean newStatus) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_BIKE_AVAIL);
			preparedStatement.setBoolean(1, newStatus);
			preparedStatement.setString(2, bikeId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int addOrder(String cardId, String bikeId, String startTime) {
		int orderId = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_RENTAL_ORDER,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, cardId);
			preparedStatement.setString(2, bikeId);
			preparedStatement.setTimestamp(3,
					new Timestamp(utils.Utils.parseDateTime(startTime, Configs.DATETIME_FORMAT).getTime()));

			preparedStatement.executeUpdate();

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					orderId = (int) generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderId;
	}

	public void updateOrderOnReturnBike(String bikeId, String returnTime, int rentalFees) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_RENTAL_ORDER);
			preparedStatement.setTimestamp(1,
					new Timestamp(utils.Utils.parseDateTime(returnTime, Configs.DATETIME_FORMAT).getTime()));
			preparedStatement.setInt(2, rentalFees);
			preparedStatement.setString(3, bikeId);

			preparedStatement.executeUpdate();
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkCardIsInUsed(String cardId) {
		try {
			ResultSet result = null;
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_CARD_IN_USED);
			preparedStatement.setString(1, cardId);

			result = preparedStatement.executeQuery();

			if (!result.isBeforeFirst()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public List<Bike> getBikesByStation(String stationID) {
		List<Bike> bikes = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BIKES_BY_STATION);
			preparedStatement.setString(1, stationID);

			ResultSet result = preparedStatement.executeQuery();
			Bike bike = null;
			while (result.next()) {
				int bikeType = result.getInt("bike_type");
				// bike = new Bike() ...
				// bikes.add(bike);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bikes;
	}
	
	public void getAllStation() {
		
	}

	public static void main(String[] args) {
		Transaction trans = new Transaction();
		trans.setAmount(400000);
		trans.setCardCode("118609_group3_2020");
		trans.setCommand("pay");
		trans.setCreatedAt(utils.Utils.formatDateTime(new Date(), Configs.DATETIME_FORMAT));
		trans.setTransactionContent("Test DBConnection API");

		String bikeId = "RB001";

		DBConnection conn = DBConnection.getDBConnection();
		conn.setBikeAvailability(bikeId, false);
		int orderId = conn.addOrder(trans.getCardCode(), bikeId, trans.getCreatedAt());
		conn.saveTransaction(trans, orderId);
		
//		System.out.println(conn.checkCardIsInUsed(trans.getCardCode()));
//
//		conn.setBikeAvailability(bikeId, true);
//		conn.updateOrderOnReturnBike(bikeId, utils.Utils.formatDateTime(new Date(), Configs.DATETIME_FORMAT), 100000);

	}
}
