package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import entities.payment.Transaction;

/**
 * The class provides utilities for the application
 * 
 * @author vancuonglee
 *
 */
public class Utils {

	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

	/**
	 * Method to generate MD5 hash code from a payment transaction
	 * @param transaction: a transaction needs to hash
	 * @return hash code
	 */
	public static String getMD5HashCode(Transaction transaction) {
		JSONObject obj = new JSONObject();
		obj.put("secretKey", Configs.SECRET_KEY);
		obj.put("transaction", transaction);
		return DigestUtils.md5Hex(obj.toString());
	}

	/**
	 * The method to format currency
	 * @param amount: number needs to format
	 * @return amount formated as a string
	 */
	public static String formatCurrency(double amount) {
		return String.format("%,.0f", amount);
	}

	public static String getToday() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static long getCurrentTime() {
		return new Date().getTime();
	}

}
