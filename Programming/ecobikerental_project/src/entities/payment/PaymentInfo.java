package entities.payment;

public class PaymentInfo {

	private String cardCode;
	private String owner;
	private String cvvCode;
	private String dateExpired;
	private String transactionContent;

	public PaymentInfo() {

	}

	public PaymentInfo(String cardCode, String owner, String cvvCode, String dateExp, String appkey,
			String secretKey, String transContent) {
		this.setCardCode(cardCode);
		this.setOwner(owner);
		this.setCvvCode(cvvCode);
		this.setDateExpired(dateExp);
		this.setTransactionContent(transContent);

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

	public void setOwner(String owner) {
		this.owner = owner;
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

	public String getTransactionContent() {
		return transactionContent;
	}

	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}
}
