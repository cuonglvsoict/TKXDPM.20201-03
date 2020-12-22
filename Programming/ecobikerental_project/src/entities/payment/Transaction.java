package entities.payment;

import java.util.Date;

import utils.Configs;

public class Transaction {
	private String cardCode;
	private String owner;
	private String cvvCode;
	private String dateExpired;
	private String command;
	private String transactionContent;
	private int amount;
	private String createdAt;
	
	public Transaction() {
		
	}
	
	public Transaction(PaymentInfo info, int amount, String command) {
		this.setCardCode(info.getCardCode());
		this.setOwner(info.getOwner());
		this.setCvvCode(info.getCvvCode());
		this.setDateExpired(info.getDateExpired());
		this.setTransactionContent(info.getTransactionContent());
		this.setCommand(command);
		this.setAmount(amount);
		this.setCreatedAt(utils.Utils.formatDateTime(new Date(), Configs.DATETIME_FORMAT));
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
}
