package subsystem.payment;

import java.io.IOException;
import java.util.HashMap;

import entities.payment.PaymentInfo;

/**
 * The class {@code PaymentSystemInterface} provides method that allow to call
 * to payment system, including payorder, refund and check balance
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public interface PaymentSystemInterface {

	/**
	 * method process pay order request
	 * 
	 * @param info:   payment infomation that includes card code, card holder name,
	 *                date expired, cvv code
	 * @param amount: the value of transaction
	 * @return HashMap object that contains response code and the transaction
	 * @throws IOException
	 */
	public HashMap<String, Object> processPayOrderRequest(PaymentInfo info, int amount) throws IOException;

	/**
	 * method process refund request
	 * 
	 * @param info:   payment infomation that includes card code, card holder name,
	 *                date expired, cvv code
	 * @param amount: the value of transaction
	 * @return HashMap object that contains response code and the transaction
	 * @throws IOException
	 */
	public HashMap<String, Object> processRefundRequest(PaymentInfo info, int amount) throws IOException;

	/**
	 * method process check balance request
	 * 
	 * @param info:   payment infomation that includes card code, card holder name,
	 *                date expired, cvv code
	 * @return HashMap object that contains response code and the transaction
	 * @throws IOException
	 */
	public HashMap<String, Object> processCheckBalanceRequest(PaymentInfo info);
}
