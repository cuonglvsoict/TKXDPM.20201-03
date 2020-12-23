package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entities.RentalOrder;
import entities.Station;
import entities.bike.Bike;
import entities.bike.ETandemBike;
import entities.bike.RoadBike;
import entities.bike.TandemBike;
import entities.payment.Card;
import entities.payment.Transaction;

/**
 * The class {@code DBInteraction} provides method to interaction with database
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class DBInteraction {

	/**
	 * The connection to interact with database
	 */
	private static Connection connection = DBConnection.getDBConnection();

	/**
	 * method to save payment transation to database
	 * 
	 * @param trans:   transaction content
	 * @param orderId: rental order corresponding to the payment transaction
	 */
	public static void saveTransaction(Transaction trans, int orderId) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_INSERT_TRANSACTION);
			preparedStatement.setInt(1, orderId);
			preparedStatement.setString(2, trans.getCardCode());
			preparedStatement.setInt(3, trans.getAmount());
			preparedStatement.setString(4, trans.getCommand());
			preparedStatement.setTimestamp(5, new Timestamp(trans.getCreatedAt()));
			preparedStatement.setString(6, trans.getTransactionContent());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * method to update status of a bike
	 * 
	 * @param bikeId
	 * @param stationId
	 * @param newStatus: a status of availability
	 */
	public static void updateBike(String bikeId, String stationId, boolean newStatus) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_SET_BIKE_STATUS);
			preparedStatement.setBoolean(1, newStatus);
			preparedStatement.setString(2, stationId);
			preparedStatement.setString(3, bikeId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * method to save a rental order to database
	 * 
	 * @param cardCode:   the code of a credit card used for the payment
	 * @param bikeId
	 * @param unlockTime: starting of renting time
	 * @return the created rental order
	 */
	public static RentalOrder addOrder(String cardCode, String bikeId, long unlockTime) {
		RentalOrder result = new RentalOrder();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_INSERT_RENTAL_ORDER,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, cardCode);
			preparedStatement.setString(2, bikeId);
			preparedStatement.setTimestamp(3, new Timestamp(Long.valueOf(unlockTime)));

			preparedStatement.executeUpdate();

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					result.setRentalId((int) generatedKeys.getLong(1));
					result.setBikeId(bikeId);
					result.setCardCode(cardCode);
					result.setUnlockTime(Long.valueOf(unlockTime));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * method to update a rental order when customer return bike
	 * 
	 * @param bikeId
	 * @param returnTime: the end of renting time
	 * @param rentalFees: the rental fees
	 */
	public static void updateOrderOnReturnBike(String bikeId, long returnTime, int rentalFees) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_UPDATE_RENTAL_ORDER);
			preparedStatement.setTimestamp(1, new Timestamp(returnTime));
			preparedStatement.setInt(2, rentalFees);
			preparedStatement.setString(3, bikeId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to check whether the card exists in the db
	 * 
	 * @param cardId
	 * @return true if the card is already in and false in the otherwise
	 */
	public static boolean checkCardIsInUsed(String cardId) {
		try {
			ResultSet result = null;
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_CHECK_CARD_IN_USED);
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

	/**
	 * method to check whether a rental order exists
	 * 
	 * @param cardId
	 * @param bikeId
	 * @return true if a rental order is in db and false in the otherwise
	 */
	public static boolean checkRentalOrder(String cardId, String bikeId) {
		try {
			ResultSet result = null;
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_CHECK_RENTAL_ORDER);
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

	/**
	 * method to get an order by a given bike id
	 * 
	 * @param bikeId
	 * @return
	 */
	public static RentalOrder getRentingOrder(String bikeId) {
		RentalOrder order = null;
		try {
			ResultSet result = null;
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_GET_ORDER);
			preparedStatement.setString(1, bikeId);

			result = preparedStatement.executeQuery();

			if (result.isBeforeFirst()) {
				result.next();
				order = new RentalOrder();
				order.setBikeId(result.getString("bike_id"));
				order.setCardCode(result.getString("card_id"));
				order.setRentalId(result.getInt("order_id"));
				order.setUnlockTime(result.getTimestamp("start_time").getTime());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	/**
	 * method to get all bikes of a given bike station
	 * 
	 * @param stationID
	 * @return list of bikes available at the station
	 */
	public static List<Bike> getBikesByStation(String stationID) {
		List<Bike> bikes = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_GET_BIKES_BY_STATION);
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

	/**
	 * method to get all the bike stations
	 * 
	 * @return list of bike stations
	 */
	public static List<Station> getAllStation() {
		List<Station> stations = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_GET_ALL_STATION);
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

	/**
	 * get all information of a bike by a given bike id
	 * 
	 * @param bikeId
	 * @return a bike object
	 */
	public static Bike getBikeById(String bikeId) {
		Bike bike = null;
		try {
			ResultSet result = null;
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_GET_BIKE_BY_ID);
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

	/**
	 * method to increase the number of available bikes of a station by one
	 * 
	 * @param stationID
	 */
	public static void updateStationIncreaseAvail(String stationID) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(SQLScript.SQL_UPDATE_STATION_INCREASE_AVAIL);
			preparedStatement.setString(1, stationID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * method to decrease the number of available bikes of a station by one
	 * 
	 * @param stationID
	 */
	public static void updateStationDecreaseAvail(String stationID) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(SQLScript.SQL_UPDATE_STATION_DECREASE_AVAIL);
			preparedStatement.setString(1, stationID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * method to add a card to db
	 * 
	 * @param cardId
	 * @param cardName
	 * @param dateExpired
	 * @param cvv
	 */
	public static void insertCard(String cardId, String cardName, long dateExpired, String cvv) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_INSERT_CARD);
			preparedStatement.setString(1, cardId);
			preparedStatement.setString(2, cardName);
			preparedStatement.setLong(3, dateExpired);
			preparedStatement.setString(4, cvv);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * method to get infomation of card by a given card id
	 * 
	 * @param cardId
	 * @return
	 */
	public static Card getCardById(String cardId) {
		Card card = null;
		try {
			ResultSet result = null;
			PreparedStatement preparedStatement = connection.prepareStatement(SQLScript.SQL_GET_CARD);
			preparedStatement.setString(1, cardId);
			result = preparedStatement.executeQuery();

			if (result.isBeforeFirst()) {
				result.next();
				card = new Card();
				card.setCardCode(result.getString("card_id"));
				card.setCardHolderName(result.getString("card_holder_name"));
				card.setDateExpired(Long.valueOf(result.getLong("date_expired")).toString());
				card.setCvvCode(result.getString("cvv_code"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return card;
	}

}
