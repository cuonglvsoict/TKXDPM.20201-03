package entities.payment;

import java.util.Date;

public class Transaction {
	private String cardCode;
	private String owner;
	private String cvvCode;
	private String dateExpired;
	private String command;
	private String transactionContent;
	private int amount;
	private long createdAt;

	public Transaction() {

	}

	public Transaction(PaymentInfo info, int amount, String command) {
		PaymentCreditCard card = (PaymentCreditCard) info.getPaymentMode();
		this.setCardCode(card.getCardID());
		this.setOwner(card.getCardHolderName());
		this.setCvvCode(card.getCvvCode());
		this.setDateExpired(card.getDateExpired());
		this.setTransactionContent(info.getPaymentContent());
		this.setCommand(command);
		this.setAmount(amount);
		this.setCreatedAt(new Date().getTime());
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

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getTransactionContent() {
		return transactionContent;
	}

	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

}
