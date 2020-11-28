package ecobikerental.paymentsystem.creditcardpayment.entity;

public class CreditCard {
	
	private String cardCode;
	private String owner;
	private String cvvCode;
	private String dateExpired;
	
	public CreditCard() {
		
	}
	
	public CreditCard(String cardCode, String cardOwner, String cvvCode, String dateExp) {
		this.setCardCode(cardCode);
		this.setOwner(cardOwner);
		this.setCvvCode(cvvCode);
		this.setDateExpired(dateExp);
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String cardOwner) {
		this.owner = cardOwner;
	}

	public String getCvvCode() {
		return cvvCode;
	}

	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}

	public String getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(String dateExpired) {
		this.dateExpired = dateExpired;
	}

}
