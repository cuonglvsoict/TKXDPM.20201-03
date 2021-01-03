package entities.payment;

public class PaymentInfo {

	private PaymentMode paymentMode;
	private String paymentContent;
	
	public PaymentInfo(PaymentMode paymentMode, String paymentContent) {
		this.setPaymentContent(paymentContent);
		this.setPaymentMode(paymentMode);
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentContent() {
		return paymentContent;
	}

	public void setPaymentContent(String paymentContent) {
		this.paymentContent = paymentContent;
	}
}
