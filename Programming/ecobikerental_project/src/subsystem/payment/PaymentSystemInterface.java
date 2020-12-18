package subsystem.payment;

import java.io.IOException;
import java.util.HashMap;

import entities.payment.PaymentInfo;

/**
 * The {@code PaymentSystemInterface} ...
 * @author vancuonglee
 * @since 1.0
 *
 */
public interface PaymentSystemInterface {
	
	/**
	 * @throws IOException 
	 * 
	 * 
	 * 
	 * */
	public HashMap<String, Object> processPayOrderRequest(PaymentInfo info, int amount) throws IOException;
	
	/**
	 * @throws IOException
	 * 
	 * 
	 * 
	 */
	public HashMap<String, Object> processRefundRequest(PaymentInfo info, int amount) throws IOException;
	
	/**
	 * 
	 * 
	 * 
	 * */
	public HashMap<String, Object> processCheckBalanceRequest(PaymentInfo info);
}
