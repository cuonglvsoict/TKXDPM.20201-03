package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Station;
import entities.bike.Bike;
import entities.bike.ETandemBike;
import entities.bike.RoadBike;
import entities.bike.TandemBike;
import entities.payment.Transaction;
import utils.Configs;

public class DBConnection {

	private static DBConnection DBConnection;
	private Connection connection;

	private final String SQL_INSERT_TRANSACTION = "insert into transaction (order_id, card_id, amount, command, created_at, content) values (?, ?, ?, ?, ?, ?);";
	private final String SQL_SET_BIKE_STATUS = "update bike set available = ?, station_id = ? where bike_id = ?;";
	private final String SQL_INSERT_RENTAL_ORDER = "insert into rental_orders (card_id, bike_id, start_time) values (?, ?, ?);";
	private final String SQL_UPDATE_RENTAL_ORDER = "update rental_orders set is_return = TRUE, return_time = ?, rental_fees = ? where bike_id = ? and is_return = FALSE;";
	private final String SQL_CHECK_CARD_IN_USED = "select * from rental_orders where card_id = ? and is_return = false";
	private final String SQL_GET_ALL_STATION = "select * from station;";
	private final String SQL_GET_BIKES_BY_STATION = "select * from bike where station_id = ? and available = TRUE";
	private final String SQL_GET_BIKE_BY_ID = "select * from bike where bike_id = ?";
	private final String SQL_UPDATE_STATION_INCREASE_AVAIL = "UPDATE station SET free_dock = free_dock - 1 WHERE station_id = ?";
	private final String SQL_UPDATE_STATION_DECREASE_AVAIL = "UPDATE station SET free_dock = free_dock + 1 WHERE station_id = ?";
	private final String SQL_GET_ORDER = "select * from rental_orders where bike_id = ? and is_return = FALSE;";
	private final String SQL_CHECK_RENTAL_ORDER = "select * from rental_orders where cardCode = ? and bike_id = ?;";

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

	public void updateBike(String bikeId, String stationId, boolean newStatus) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_BIKE_STATUS);
			preparedStatement.setBoolean(1, newStatus);
			preparedStatement.setString(2, stationId);
			preparedStatement.setString(3, bikeId);

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

	public int updateOrderOnReturnBike(String bikeId, String returnTime, int rentalFees) {
		try {
			int orderId = this.getOrder(bikeId);
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_RENTAL_ORDER);
			preparedStatement.setTimestamp(1,
					new Timestamp(utils.Utils.parseDateTime(returnTime, Configs.DATETIME_FORMAT).getTime()));
			preparedStatement.setInt(2, rentalFees);
			preparedStatement.setString(3, bikeId);

			preparedStatement.executeUpdate();
			return orderId;
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
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

	public boolean checkRentalOrder(String cardId, String bikeId) {
		try {
			ResultSet result = null;
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_RENTAL_ORDER);
			preparedStatement.setString(1, cardId);
			preparedStatement.setString(2, bikeId);

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

	public int getOrder(String bikeId) {
		try {
			ResultSet result = null;
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ORDER);
			preparedStatement.setString(1, bikeId);

			result = preparedStatement.executeQuery();

			if (result.isBeforeFirst()) {
				result.next();
				return (int) result.getLong("order_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public List<Bike> getBikesByStation(String stationID) {
		List<Bike> bikes = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BIKES_BY_STATION);
			preparedStatement.setString(1, stationID);

			ResultSet result = preparedStatement.executeQuery();
			bikes = new ArrayList<Bike>();
			while (result.next()) {
				int bikeType = result.getInt("bike_type");
				Bike bike = null;
				switch (bikeType) {
				case 1: {
					bike = new RoadBike();
					break;
				}
				case 2: {
					bike = new TandemBike();
					break;
				}
				case 3: {
					bike = new ETandemBike();
					break;
				}
				}

				bike.setBikeId(result.getString("bike_id"));
				bike.setBikeName(result.getString("bike_name"));
				bike.setStationId(result.getString("station_id"));
				bike.setDescription(result.getString("bike_description"));
				bike.setImgPath(result.getString("img_path"));
				bike.setAvailable(true);
				bikes.add(bike);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bikes;
	}

	public List<Station> getAllStation() {
		List<Station> stations = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_STATION);
			ResultSet result = preparedStatement.executeQuery();
			stations = new ArrayList<Station>();

			while (result.next()) {
				Station station = new Station();
				station.setAddress(result.getString("address"));
				station.setStationId(result.getString("station_id"));
				station.setStationName(result.getString("station_name"));
				station.setDockNo(result.getInt("dock_no"));
				station.setFreeDock(result.getInt("free_dock"));
				stations.add(station);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stations;
	}

	public Bike getBikeById(String bikeId) {
		Bike bike = null;
		try {
			ResultSet result = null;
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BIKE_BY_ID);
			preparedStatement.setString(1, bikeId);

			result = preparedStatement.executeQuery();

			if (result.isBeforeFirst()) {
				result.next();
				int bikeType = result.getInt("bike_type");
				switch (bikeType) {
				case 1: {
					bike = new RoadBike();
					break;
				}
				case 2: {
					bike = new TandemBike();
					break;
				}
				case 3: {
					bike = new ETandemBike();
					break;
				}
				}
				bike.setBikeId(result.getString("bike_id"));
				bike.setBikeName(result.getString("bike_name"));
				bike.setStationId(result.getString("station_id"));
				bike.setDescription(result.getString("bike_description"));
				bike.setImgPath(result.getString("img_path"));
				bike.setAvailable(true);
				bike.setAvailable(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bike;
	}

	public void updateStationIncreaseAvail(String stationID) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATION_INCREASE_AVAIL);
			preparedStatement.setString(1, stationID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateStationDecreaseAvail(String stationID) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATION_DECREASE_AVAIL);
			preparedStatement.setString(1, stationID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		conn.updateBike(bikeId, "S002", false);
		int orderId = conn.addOrder(trans.getCardCode(), bikeId, trans.getCreatedAt());
		conn.saveTransaction(trans, orderId);

//		System.out.println(conn.checkCardIsInUsed(trans.getCardCode()));
//
//		conn.setBikeAvailability(bikeId, true);
//		conn.updateOrderOnReturnBike(bikeId, utils.Utils.formatDateTime(new Date(), Configs.DATETIME_FORMAT), 100000);

	}
}
