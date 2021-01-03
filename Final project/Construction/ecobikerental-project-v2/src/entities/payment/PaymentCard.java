package entities.payment;

public abstract class PaymentCard extends PaymentMode {
	
	private String cardID;
	private String cardHolderName;
	private String dateExpired;
	
	public PaymentCard(String paymentMode) {
		super(paymentMode);
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(String dateExpired) {
		this.dateExpired = dateExpired;
	}


}
