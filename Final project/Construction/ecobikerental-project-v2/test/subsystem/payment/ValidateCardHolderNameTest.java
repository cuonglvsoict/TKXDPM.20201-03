package subsystem.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCardHolderNameTest {

	@ParameterizedTest
	@CsvSource({
		"abc, true",
		"abc cde, true",
		"abc123, true",
		"abc@, false",
		", false"
	})
	void test(String cardHolderName, boolean expected) {
		boolean isValided = PaymentInfoValidation.validateCardHolderName(cardHolderName);
		assertEquals(expected, isValided);
	}

}
