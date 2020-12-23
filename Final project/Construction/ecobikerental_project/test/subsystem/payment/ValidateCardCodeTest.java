package subsystem.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCardCodeTest {

	@ParameterizedTest
	@CsvSource({
		"118609_group3_2020, true",
		"abc cde, false",
		"abc123, true",
		"123 45, false",
		"abc@, false",
		", false"
	})
	void test(String cardCode, boolean expected) {
		boolean isValided = PaymentInfoValidation.validateCardCode(cardCode);
		assertEquals(expected, isValided);
	}

}
