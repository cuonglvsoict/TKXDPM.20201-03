package ecobikerental.paymentsystem.ewalletpayment.controller.copy;

import ecobikerental.paymentsystem.PaymentSystemInterface;

public class EWalletPaymentController implements PaymentSystemInterface {

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

}
