package subsystem.payment;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import entities.payment.PaymentInfo;
import entities.payment.Transaction;
import utils.Configs;

public class PaymentSubsystemController {

	protected static HashMap<String, Object> processPayOrderRequest(PaymentInfo info, int amount)
			throws IOException {
		Transaction trans = new Transaction(info, amount, Configs.PAY_ORDER_COMMAND_CODE);
		String url = Configs.PAYMENT_BASE_URL + Configs.PAYMENT_REQUEST_PATH;
		String requestBody = generateRequestBody(trans);

		String response = InterbankBoundary.query(url, requestBody.toString());
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("transaction", trans);
		result.put("error_code", new JSONObject(response).get("errorCode"));
		return result;
	}

	protected static HashMap<String, Object> processRefundRequest(PaymentInfo info, int amount)
			throws IOException {
		Transaction trans = new Transaction(info, amount, Configs.REFUND_COMMAND_CODE);
		String url = Configs.PAYMENT_BASE_URL + Configs.PAYMENT_REQUEST_PATH;
		String requestBody = generateRequestBody(trans);

		String response = InterbankBoundary.query(url, requestBody.toString());
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("transaction", trans);
		result.put("error_code", new JSONObject(response).get("errorCode"));
		return result;
	}

	protected static HashMap<String, Object> processCheckBalanceRequest(PaymentInfo info) {
		return null;
	}

	protected static String processResetCardRequest(PaymentInfo info) throws IOException {
		JSONObject requestBody = new JSONObject();
		requestBody.put("cardCode", info.getCardCode());
		requestBody.put("owner", info.getOwner());
		requestBody.put("cvvCode", info.getCvvCode());
		requestBody.put("dateExpired", info.getDateExpired());

		String url = Configs.PAYMENT_BASE_URL + Configs.RESET_BALANCE_PATH;
		String response = InterbankBoundary.query(url, requestBody.toString());
		System.out.println(response);
		return (new JSONObject(response)).getString("errorCode");
	}

	private static String generateRequestBody(Transaction trans) throws IOException {
		// generate body request
		JSONObject requestBody = new JSONObject();
		requestBody.put("version", Configs.PAYMENT_API_VERSION);
		requestBody.put("transaction", new JSONObject(trans));
		requestBody.put("appCode", Configs.APP_CODE);
		requestBody.put("hashCode", utils.Utils.getMD5HashCode(trans));
		return requestBody.toString();
	}
	
	public static void main(String[] args) throws IOException {
		PaymentInfo info = new PaymentInfo();
		info.setCardCode("118609_group3_2020");
		info.setOwner("Group 3");
		info.setCvvCode("501");
		info.setDateExpired("1125");
		PaymentSubsystemController.processResetCardRequest(info);
//		CreditCardPaymentController.processPayOrderRequest(info, 200000, "test");
	}
}
