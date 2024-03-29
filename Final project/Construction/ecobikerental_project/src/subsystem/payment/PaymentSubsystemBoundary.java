package subsystem.payment;

import java.io.IOException;
import java.util.HashMap;

import entities.payment.PaymentInfo;

/**
 * The class {@code PaymentSubsystemBoundary} implements the
 * {@code PaymentSystemInterface} that provides method that allow to call to
 * payment system, including payorder, refund and check balance
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class PaymentSubsystemBoundary implements PaymentSystemInterface {

	@Override
	public HashMap<String, Object> processPayOrderRequest(PaymentInfo info, int amount) throws IOException {
		// TODO Auto-generated method stub
		return PaymentSubsystemController.processPayOrderRequest(info, amount);
	}

	@Override
	public HashMap<String, Object> processRefundRequest(PaymentInfo info, int amount) throws IOException {
		// TODO Auto-generated method stub
		return PaymentSubsystemController.processRefundRequest(info, amount);
	}

	@Override
	public HashMap<String, Object> processCheckBalanceRequest(PaymentInfo info) {
		// TODO Auto-generated method stub
		return PaymentSubsystemController.processCheckBalanceRequest(info);
	}

}
