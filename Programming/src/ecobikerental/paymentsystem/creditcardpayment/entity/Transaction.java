package ecobikerental.paymentsystem.creditcardpayment.entity;

public class Transaction {
	
	private String command;
	private CreditCard card;
	private String transactionContent;
	private int amount;
	
	public Transaction() {
		
	}
	
	public Transaction(String command, CreditCard card, String content, int amount) {
		this.setCommand(command);
		this.setCard(card);
		this.setTransactionContent(content);
		this.setAmount(amount);	
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
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

	
}
