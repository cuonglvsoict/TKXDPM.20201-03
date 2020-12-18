package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import entities.payment.Transaction;

public class Utils {
	
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}
	
	public static String getMD5HashCode(Transaction transaction) {
		JSONObject obj = new JSONObject();
		obj.put("secretKey", Configs.SECRET_KEY);
        obj.put("transaction", transaction);
		return DigestUtils.md5Hex(obj.toString());
	}
	
	public static String formatCurrency(double amount) {
		return String.format("%,.0f", amount);
	}
	
	public static String formatDateTime(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(Configs.DATETIME_FORMAT);
		return formatter.format(date);
	}
	
	public static String getToday() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = new Date();
	    return dateFormat.format(date);
	}
	
	
	
}
