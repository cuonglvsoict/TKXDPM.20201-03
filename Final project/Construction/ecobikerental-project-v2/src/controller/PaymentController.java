package controller;

import java.io.IOException;
import java.util.HashMap;

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
	 * @return payment transaction result status and the notification to user
	 * @throws IOException
	 */
	public HashMap<String, Object> processPayOrder(PaymentInfo paymentInfo, int amount) throws IOException {
		PaymentSubsystemBoundary paymentSystem = new PaymentSubsystemBoundary();
		HashMap<String, Object> paymentResult = paymentSystem.processPayOrderRequest(paymentInfo, amount);

		HashMap<String, Object> result = new HashMap<String, Object>();
		String responseCode = (String) paymentResult.get("error_code");

		if (responseCode.equals("00")) {
			result.put("status", true);
			this.getLOGGER().info("Successful transaction");

			// save transaction
			Transaction trans = (Transaction) paymentResult.get("transaction");
//			trans.saveTransaction();
		} else {
			result.put("status", false);
			this.getLOGGER().info("transaction Failed! Error code " + responseCode);
		}

		result.put("notification", paymentResult.get("notification"));
		return result;
	}

	/**
	 * The method to process refund request
	 * 
	 * @param paymentInfo: payment information, including card infomation and
	 *                     transaction content
	 * @param amount:      the value of the transaction
	 * @return a string response message to display to use
	 */
	public HashMap<String, Object> processRefund(PaymentInfo paymentInfo, int amount) throws IOException {
		PaymentSubsystemBoundary paymentSystem = new PaymentSubsystemBoundary();
		HashMap<String, Object> paymentResult = paymentSystem.processRefundRequest(paymentInfo, amount);

		HashMap<String, Object> result = new HashMap<String, Object>();
		String responseCode = (String) paymentResult.get("error_code");

		if (responseCode.equals("00")) {
			result.put("status", true);
			this.getLOGGER().info("Successful transaction");

			// save transaction
			Transaction trans = (Transaction) paymentResult.get("transaction");
//			trans.saveTransaction();
		} else {
			result.put("status", false);
			this.getLOGGER().info("transaction Failed! Error code " + responseCode);
		}

		result.put("notification", paymentResult.get("notification"));
		return result;
	}

	public int getBalance() {
		return 0;
	}
}
