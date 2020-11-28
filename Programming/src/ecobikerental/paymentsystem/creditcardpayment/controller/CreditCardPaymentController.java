package ecobikerental.paymentsystem.creditcardpayment.controller;

import ecobikerental.paymentsystem.PaymentSystemInterface;
import ecobikerental.paymentsystem.creditcardpayment.entity.CreditCard;

public class CreditCardPaymentController implements PaymentSystemInterface {

	@Override
	public boolean processRentBikePayOrder(String bikeId, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean processReturnBikePayOrder(String bikeId, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processCheckBalanceRequest() {
		// TODO Auto-generated method stub
		
	}	
	
	public void checkCardInUsed(CreditCard card) {
		
	}
}
