package subsystem.payment;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import entities.payment.Card;
import entities.payment.PaymentInfo;
import entities.payment.Transaction;
import utils.Configs;

/**
 * The class {@code PaymentSubsystemController} includes method to process
 * payment request
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class PaymentSubsystemController {

	/**
	 * Process pay order request
	 * 
	 * @param info:   payment infomation including card code, card holder name, date
	 *                expired, cvv code
	 * @param amount: the value of transaction
	 * @return HashMap object contains response code and the transaction returned
	 *         from server
	 * @throws IOException
	 */
	protected static HashMap<String, Object> processPayOrderRequest(PaymentInfo info, int amount) throws IOException {
		Transaction trans = new Transaction(info, amount, Configs.PAY_ORDER_COMMAND_CODE);
		String url = Configs.PAYMENT_BASE_URL + Configs.PAYMENT_REQUEST_PATH;
		String requestBody = generateRequestBody(trans);

		String response = InterbankBoundary.query(url, requestBody.toString());
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("transaction", trans);
		result.put("error_code", new JSONObject(response).get("errorCode"));
		return result;
	}

	/**
	 * Process refund request
	 * 
	 * @param info:   payment infomation including card code, card holder name, date
	 *                expired, cvv code
	 * @param amount: the value of transaction
	 * @return HashMap object contains response code and the transaction returned
	 *         from server
	 * @throws IOException
	 */
	protected static HashMap<String, Object> processRefundRequest(PaymentInfo info, int amount) throws IOException {
		Transaction trans = new Transaction(info, amount, Configs.REFUND_COMMAND_CODE);
		String url = Configs.PAYMENT_BASE_URL + Configs.PAYMENT_REQUEST_PATH;
		String requestBody = generateRequestBody(trans);

		String response = InterbankBoundary.query(url, requestBody.toString());

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("transaction", trans);
		result.put("error_code", new JSONObject(response).get("errorCode"));
		return result;
	}

	/**
	 * Process refund request
	 * 
	 * @param info: payment infomation including card code, card holder name, date
	 *              expired, cvv code
	 * @return HashMap object contains response code and the transaction returned
	 *         from server
	 * @throws IOException
	 */
	protected static HashMap<String, Object> processCheckBalanceRequest(PaymentInfo info) {
		return null;
	}

	/**
	 * Process reset card request
	 * @param info: card infomation
	 * @return response message from server
	 * @throws IOException
	 * @apiNote For development only
	 */
	protected static String processResetCardRequest(PaymentInfo info) throws IOException {
		JSONObject requestBody = new JSONObject();
		Card card = info.getCard();
		requestBody.put("cardCode", card.getCardCode());
		requestBody.put("owner", card.getCardHolderName());
		requestBody.put("cvvCode", card.getCvvCode());
		requestBody.put("dateExpired", card.getDateExpired());

		String url = Configs.PAYMENT_BASE_URL + Configs.RESET_BALANCE_PATH;
		String response = InterbankBoundary.query(url, requestBody.toString());
		System.out.println(response);
		return (new JSONObject(response)).getString("errorCode");
	}

	/**
	 * generate PATCH method body from a transaction
	 * 
	 * @param trans: a transaction infomation includes card info, amount, command
	 *               and transaction creating time
	 * @return a string contains PATCH method body under json format
	 * @throws IOException
	 */
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
		info.setCard(new Card());
		info.getCard().setCardCode("118609_group3_2020");
		info.getCard().setCardHolderName("Group 3");
		info.getCard().setCvvCode("501");
		info.getCard().setDateExpired("1125");
		PaymentSubsystemController.processResetCardRequest(info);
//		CreditCardPaymentController.processPayOrderRequest(info, 200000, "test");
	}
}
