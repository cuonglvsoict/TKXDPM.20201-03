package db;

public class SQLScript {

	/**
	 * sql script to insert a payment transaction to db
	 */
	public static final String SQL_INSERT_TRANSACTION = "insert into transaction (order_id, card_id, amount, command, created_at, content) values (?, ?, ?, ?, ?, ?);";
	
	/**
	 * sql script to update bike status
	 */
	public static final String SQL_SET_BIKE_STATUS = "update bike set available = ?, station_id = ? where bike_id = ?;";
	
	/**
	 * sql script to insert a rental order into database
	 */
	public static final String SQL_INSERT_RENTAL_ORDER = "insert into rental_orders (card_id, bike_id, start_time) values (?, ?, ?);";
	
	/**
	 * sql script to update a rental order in database
	 */
	public static final String SQL_UPDATE_RENTAL_ORDER = "update rental_orders set is_return = TRUE, return_time = ?, rental_fees = ? where bike_id = ? and is_return = FALSE;";
	
	/**
	 * sql script to check whether a credit card is used to rent bike
	 */
	public static final String SQL_CHECK_CARD_IN_USED = "select * from rental_orders where card_id = ? and is_return = false";
	
	/**
	 * sql script to query all station
	 */
	public static final String SQL_GET_ALL_STATION = "select * from station;";
	
	/**
	 * sql script to get all bike for a given station id
	 */
	public static final String SQL_GET_BIKES_BY_STATION = "select * from bike where station_id = ? and available = TRUE";
	
	/**
	 * sql script to get a bike record by a given bike id
	 */
	public static final String SQL_GET_BIKE_BY_ID = "select * from bike where bike_id = ?";
	
	/**
	 * sql script to increase number of bikes of a given station by one
	 */
	public static final String SQL_UPDATE_STATION_INCREASE_AVAIL = "UPDATE station SET free_dock = free_dock - 1 WHERE station_id = ?";
	
	/**
	 * sql script to decrease number of bikes of a given station by one
	 */
	public static final String SQL_UPDATE_STATION_DECREASE_AVAIL = "UPDATE station SET free_dock = free_dock + 1 WHERE station_id = ?";
	
	/**
	 * script to get a renting bike order
	 */
	public static final String SQL_GET_ORDER = "select * from rental_orders where bike_id = ? and is_return = FALSE;";
	
	/**
	 * script to check whether the rental order exists
	 */
	public static final String SQL_CHECK_RENTAL_ORDER = "select * from rental_orders where card_id = ? and bike_id = ?;";
	
	/**
	 * add a card to database if not exist
	 */
	public static final String SQL_INSERT_CARD = "INSERT ignore INTO card (card_id, card_holder_name, date_expired, cvv_code) values (?, ?, ?, ?);";
	
	/**
	 * get a card by a given card id
	 */
	public static final String SQL_GET_CARD = "select * from card where card_id = ?";
	
	public static final String SQL_SEARCH_STATION = "select * from station where station_id like ? or station_name like ? or address like ?;";
	
	public static final String SQL_SEARCH_BIKE = "select * from bike where bike_id like ? or bike_name like ?";
			

}
