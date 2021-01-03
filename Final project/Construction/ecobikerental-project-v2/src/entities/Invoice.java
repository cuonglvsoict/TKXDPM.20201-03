package entities;

import entities.payment.PaymentInfo;

public class Invoice {
	
	private RentalOrder order;
	private PaymentInfo paymentInfo;
	
	public Invoice(RentalOrder order, PaymentInfo info) {
		this.setOrder(order);
		this.setPaymentInfo(info);
	}
	
	public void saveInvoice() {
		
	}

	public RentalOrder getOrder() {
		return order;
	}

	public void setOrder(RentalOrder order) {
		this.order = order;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

}
