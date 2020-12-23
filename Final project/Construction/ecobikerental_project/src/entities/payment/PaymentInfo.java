package entities.payment;

public class PaymentInfo {

	private Card card;
	private String transactionContent;

	public PaymentInfo() {

	}
	
	public PaymentInfo(Card card, String transactionContent) {
		this.card = card;
		this.transactionContent = transactionContent;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getTransactionContent() {
		return transactionContent;
	}

	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}

	
}
