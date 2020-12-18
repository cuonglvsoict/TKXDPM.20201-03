package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

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
	
	public boolean processPayOrder(PaymentInfo paymentInfo, int amount) {
		PaymentSubsystemBoundary paymentSystem = new PaymentSubsystemBoundary();
		try {
			HashMap<String, Object> paymentResult = paymentSystem.processPayOrderRequest(paymentInfo, amount);
			Transaction trans = (Transaction) paymentResult.get("transaction");
			String errorCode = (String) paymentResult.get("error_code");
			
			if (!errorCode.equals("00")) {
				this.getLOGGER().info("Payment Failed! Error code " + errorCode);
				return false;
			} else {
				this.getLOGGER().info("Successful payment");
				
				// save infomation to db
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.getLOGGER().info("Error occurred!" + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean processRefund(PaymentInfo paymentInfo, int amount) {
		return false;
	}

	public boolean getBalance() {
		return false;
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
