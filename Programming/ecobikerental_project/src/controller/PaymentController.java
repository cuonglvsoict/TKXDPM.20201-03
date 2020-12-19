package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import db.DBConnection;
import entities.AppData;
import entities.bike.Bike;
import entities.payment.PaymentInfo;
import entities.payment.Transaction;
import subsystem.payment.PaymentSubsystemBoundary;

public class PaymentController extends BaseController {

	public PaymentController() {
		super(utils.Utils.getLogger(PaymentController.class.getName()));
	}

	public PaymentController(Logger logger) {
		super(logger);
	}

	public String processPayOrder(PaymentInfo paymentInfo, int amount) {
		PaymentSubsystemBoundary paymentSystem = new PaymentSubsystemBoundary();
		String responseCode = null;

		try {
			HashMap<String, Object> paymentResult = paymentSystem.processPayOrderRequest(paymentInfo, amount);
			responseCode = (String) paymentResult.get("error_code");

			if (responseCode.equals("00")) {
				this.getLOGGER().info("Successful transaction");

				// save info to db
				DBConnection conn = DBConnection.getDBConnection();
				Bike bike = (Bike) AppData.getAttribute("bike");
				Transaction trans = (Transaction) paymentResult.get("transaction");

				conn.updateBike(bike.getBikeId(), bike.getStationId(), false);
				int orderId = conn.addOrder(trans.getCardCode(), bike.getBikeId(), trans.getCreatedAt());
				conn.saveTransaction(trans, orderId);
			} else {
				this.getLOGGER().info("transaction Failed! Error code " + responseCode);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.getLOGGER().info("Error occurred!" + e.getMessage());
		}

		return this.getPaymentResultMessage(responseCode);
	}

	public String processRefund(PaymentInfo paymentInfo, int amount) {
		PaymentSubsystemBoundary paymentSystem = new PaymentSubsystemBoundary();
		String responseCode = null;

		try {
			HashMap<String, Object> paymentResult = paymentSystem.processRefundRequest(paymentInfo, amount);
			responseCode = (String) paymentResult.get("error_code");

			if (responseCode.equals("00")) {
				this.getLOGGER().info("Successful transaction");

				// save info to db
				DBConnection conn = DBConnection.getDBConnection();
				Bike bike = (Bike) AppData.getAttribute("bike");
				Transaction trans = (Transaction) paymentResult.get("transaction");

				conn.updateBike(bike.getBikeId(), bike.getStationId(), true);
				int orderId = conn.addOrder(trans.getCardCode(), bike.getBikeId(), trans.getCreatedAt());
				conn.saveTransaction(trans, orderId);
			} else {
				this.getLOGGER().info("Successful transaction");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.getLOGGER().info("transaction Failed! Error code " + responseCode);
		}

		return this.getPaymentResultMessage(responseCode);
	}

	public int getBalance() {
		return 0;
	}

	private String getPaymentResultMessage(String responseCode) {
		switch (responseCode) {
		case "00": {
			return "Payment accepted! Your bike has been unlocked!!!";
		}
		case "01": {
			return responseCode + " Invalid card!!! Please verify your infomation and try again";
		}
		case "02": {
			return responseCode + " Payment failed due to not enough balance error!!!";
		}
		case "03": {
			return responseCode + " Payment failed due to internal Interbank Server Error!!!";
		}
		case "04": {
			return responseCode + " Payment failded due to interbank server cannot verify infomation!!!";
		}
		case "05": {
			return responseCode + " Payment failded due to missing transaction infomation!!!";
		}
		case "06": {
			return responseCode + " Payment failded due to missing transaction infomation!!!";
		}
		case "07": {
			return responseCode + " Payment failed due to invalid amount number!!!";
		}
		default: {
			return responseCode + " Payment failded";
		}
		}
	}

	public boolean validatePaymentInfor(PaymentInfo info) {
		return true;
	}

	public boolean validateCardCode(String cardCode) {
		return true;
	}

	public boolean validateCardHolderName(String name) {
		return true;
	}

	public boolean validateCvv(String cvv) {
		return true;
	}

	public boolean validateDateExpired(String dateExp) {
		return true;
	}

	public boolean validateTransactionContent(String content) {
		return true;
	}

	public boolean validateAmount(int amount) {
		return true;
	}
}
