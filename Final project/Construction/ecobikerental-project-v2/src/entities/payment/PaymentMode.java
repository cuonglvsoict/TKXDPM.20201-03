package entities.payment;

public abstract class PaymentMode {
	
	private String paymentMode;

	public PaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public String getPaymentMode() {
		return paymentMode;
	}
		
}
