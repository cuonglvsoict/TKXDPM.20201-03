package entities.payment;

public class PaymentInfo {

<<<<<<< HEAD:Programming/ecobikerental_project/src/entities/payment/PaymentInfo.java
=======
public class CreditCard {

>>>>>>> 0a701e69e37edad3be2f7cbfbac6de43a367cbe3:Programming/src/ecobikerental/paymentsystem/creditcardpayment/entity/CreditCard.java
	private String cardCode;
	private String owner;
	private String cvvCode;
	private String dateExpired;
<<<<<<< HEAD:Programming/ecobikerental_project/src/entities/payment/PaymentInfo.java
	private String transactionContent;

	public PaymentInfo() {

	}

	public PaymentInfo(String cardCode, String owner, String cvvCode, String dateExp, String appkey,
			String secretKey, String transContent) {
=======

	public CreditCard() {

	}

	public CreditCard(String cardCode, String cardOwner, String cvvCode, String dateExp) {
>>>>>>> 0a701e69e37edad3be2f7cbfbac6de43a367cbe3:Programming/src/ecobikerental/paymentsystem/creditcardpayment/entity/CreditCard.java
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
