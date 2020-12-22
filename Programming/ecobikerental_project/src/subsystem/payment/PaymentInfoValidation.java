package subsystem.payment;

import entities.payment.Card;
import entities.payment.PaymentInfo;

/**
 * The {@code PaymentInfoValidation} provides methods for validating payment
 * input
 * 
 * @author vancuonglee
 * @since 1.0
 *
 */
public class PaymentInfoValidation {

	public static boolean validatePaymentInfor(PaymentInfo info) {
		Card card = info.getCard();
		return validateCardCode(card.getCardCode()) && validateCardHolderName(card.getCardHolderName())
				&& validateCvv(card.getCvvCode()) && validateDateExpired(card.getDateExpired())
				&& validateTransactionContent(info.getTransactionContent());
	}

	public static boolean validateCardCode(String cardCode) {
		if (cardCode == null || cardCode.equals(""))
			return false;
		for (char c : cardCode.toCharArray()) {
			if ((c < 48 || c > 122 || (57 < c && c < 65) || (90 < c && c < 97))) {
				return false;
			}
		}
		return true;
	}

	public static boolean validateCardHolderName(String name) {
		if (name == null || name.equals(""))
			return false;
		for (char c : name.toCharArray()) {
			if (c != ' ' && (c < 48 || c > 122 || (57 < c && c < 65) || (90 < c && c < 97))) {
				return false;
			}
		}
		return true;
	}

	public static boolean validateCvv(String cvv) {
		if (cvv == null || cvv.equals(""))
			return false;
		for (char c : cvv.toCharArray()) {
			if (c < 48 || 57 < c) {
				return false;
			}
		}
		return true;
	}

	public static boolean validateDateExpired(String dateExp) {
		if (dateExp == null || dateExp.equals(""))
			return false;
		for (char c : dateExp.toCharArray()) {
			if (c < 48 || 57 < c) {
				return false;
			}
		}
		
		long time = Long.valueOf(dateExp);
		if (time < utils.Utils.getCurrentTime()) {
			return false;
		}
		
		return true;
	}

	public static boolean validateTransactionContent(String content) {
		if (content == null || content.equals(""))
			return false;
		for (char c : content.toCharArray()) {
			if (c != ' ' && (c < 48 || c > 122 || (57 < c && c < 65) || (90 < c && c < 97))) {
				return false;
			}
		}
		return true;
	}

	public static boolean validateAmount(int amount) {
		if (amount <= 0)
			return false;
		return true;
	}
}
