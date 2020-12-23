package controller;

import java.io.IOException;
import java.util.HashMap;

import db.DBInteraction;
import entities.AppData;
import entities.RentalOrder;
import entities.bike.Bike;
import entities.payment.PaymentInfo;
import entities.payment.Transaction;
import subsystem.payment.PaymentSubsystemBoundary;

/**
 * The class {@code PaymentController} provide method to process payment request
 * such as pay order, refund.
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class PaymentController extends BaseController {

	public PaymentController() {
		super(utils.Utils.getLogger(PaymentController.class.getName()));
	}

	/**
	 * The method to process pay order request
	 * 
	 * @param paymentInfo: payment information, including card infomation and
	 *                     transaction content
	 * @param amount:      the value of the transaction
	 * @return a string response message to display to use
	 */
	public String processPayOrder(PaymentInfo paymentInfo, int amount) {
		PaymentSubsystemBoundary paymentSystem = new PaymentSubsystemBoundary();
		String responseCode = null;
		Transaction trans = null;

		try {
			// process pay order
			HashMap<String, Object> paymentResult = paymentSystem.processPayOrderRequest(paymentInfo, amount);
			responseCode = (String) paymentResult.get("error_code");

			if (responseCode.equals("00")) {
				this.getLOGGER().info("Successful transaction");
				AppData.setAttribute("payment_status", true);

				// save info to db
				Bike bike = (Bike) AppData.getAttribute("rented_bike");
				trans = (Transaction) paymentResult.get("transaction");

				DBInteraction.insertCard(paymentInfo.getCard().getCardCode(), paymentInfo.getCard().getCardHolderName(),
						Long.valueOf(paymentInfo.getCard().getDateExpired()), paymentInfo.getCard().getCvvCode());
				DBInteraction.updateBike(bike.getBikeId(), bike.getStationId(), false);
				RentalOrder order = DBInteraction.addOrder(trans.getCardCode(), bike.getBikeId(), trans.getCreatedAt());
				DBInteraction.saveTransaction(trans, order.getRentalId());
				DBInteraction.updateStationDecreaseAvail(bike.getStationId());
			} else {
				this.getLOGGER().info("transaction Failed! Error code " + responseCode);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.getLOGGER().info("Error occurred!" + e.getMessage());
		}

		return this.getPaymentResultMessage(responseCode, trans.getCommand());
	}

	/**
	 * The method to process refund request
	 * 
	 * @param paymentInfo: payment information, including card infomation and
	 *                     transaction content
	 * @param amount:      the value of the transaction
	 * @return a string response message to display to use
	 */
	public String processRefund(String cardCode, String bikeCode) {
		PaymentSubsystemBoundary paymentSystem = new PaymentSubsystemBoundary();
		Bike bike = DBInteraction.getBikeById(bikeCode);
		bike.setStationId((String) AppData.getAttribute("returnStation"));

		PaymentInfo paymentInfo = new PaymentInfo(DBInteraction.getCardById(cardCode), "ECOBIKERENTAL REFUND");
		RentalOrder order = ReturnBikeController.updateRentalOrderOnReturnBike(bike);
		String responseCode = null;
		try {
			// process refund request
			HashMap<String, Object> paymentResult = paymentSystem.processRefundRequest(paymentInfo,
					RentBikeController.getRefundAmount(order, bike));
			responseCode = (String) paymentResult.get("error_code");

			if (responseCode.equals("00")) {
				this.getLOGGER().info("Successful transaction");

				// save infomation to db
				Transaction trans = (Transaction) paymentResult.get("transaction");
				DBInteraction.updateBike(bike.getBikeId(), bike.getStationId(), true);
				DBInteraction.updateOrderOnReturnBike(bike.getBikeId(), order.getReturnTime(), order.getRentalFees());
				DBInteraction.saveTransaction(trans, order.getRentalId());
				DBInteraction.updateStationIncreaseAvail(bike.getStationId());
				return this.getPaymentResultMessage(responseCode, trans.getCommand());
			} else {
				this.getLOGGER().info("transaction Failed! Error code " + responseCode);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.getLOGGER().info("transaction Failed!");
		}
		return responseCode;
	}

	public int getBalance() {
		return 0;
	}

	/**
	 * method translates response code from inter bank to notify message to display
	 * for use
	 * 
	 * @param responseCode: response code from inter bank
	 * @param command:      payment command
	 * @return message to display
	 */
	private String getPaymentResultMessage(String responseCode, String command) {
		switch (responseCode) {
		case "00": {
			if (command.equals("pay")) {
				return "Payment accepted! Your bike has been unlocked!!!";
			} else {
				return "Successful transaction, your deposit has been refunded!!!";
			}
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
}
