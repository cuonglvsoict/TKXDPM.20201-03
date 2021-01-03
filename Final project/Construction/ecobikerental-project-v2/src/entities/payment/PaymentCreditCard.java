package entities.payment;

public class PaymentCreditCard extends PaymentCard {

	private String cvvCode;
	
	public PaymentCreditCard(String cardId, String cardHolderName, String dateExpired, String cvvCode) {
		super("CREDIT_CARD_PAYMENT");
		this.setCardID(cardId);
		this.setCardHolderName(cardHolderName);
		this.setDateExpired(dateExpired);
		this.setCvvCode(cvvCode);
	}
	
	public PaymentCreditCard() {
		super("CREDIT_CARD_PAYMENT");
	}

	public String getCvvCode() {
		return cvvCode;
	}

	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
}
