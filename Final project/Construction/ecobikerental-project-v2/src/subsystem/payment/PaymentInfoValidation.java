package subsystem.payment;

import entities.payment.PaymentCreditCard;
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

	/**
	 * Method to validate payment infomation
	 * @param info: payment infomation, including card info and transaction content
	 * @return
	 */
	public static boolean validatePaymentInfor(PaymentInfo info) {
		PaymentCreditCard card = (PaymentCreditCard) info.getPaymentMode();
		return validateCardCode(card.getCardID()) && validateCardHolderName(card.getCardHolderName())
				&& validateCvv(card.getCvvCode()) && validateDateExpired(card.getDateExpired())
				&& validateTransactionContent(info.getPaymentContent());
	}

	public static boolean validateCardCode(String cardCode) {
		if (cardCode == null || cardCode.equals(""))
			return false;
		for (char c : cardCode.toCharArray()) {
			if (c != '_' && (c < 48 || c > 122 || (57 < c && c < 65) || (90 < c && c < 97))) {
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
		
//		long time = Long.valueOf(dateExp);
//		if (time < utils.Utils.getCurrentTime()) {
//			return false;
//		}
		
		return true;
	}

	public static boolean validateTransactionContent(String content) {
		return true;
	}

	public static boolean validateAmount(int amount) {
		if (amount <= 0)
			return false;
		return true;
	}
}
