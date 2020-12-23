package subsystem.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCvvCodeTest {

	@ParameterizedTest
	@CsvSource({
		"abc, false",
		"123, true",
		"123 45, false",
		"123@, false",
		", false"
	})
	void test(String cvv, boolean expected) {
		boolean isValided = PaymentInfoValidation.validateCvv(cvv);
		assertEquals(expected, isValided);
	}

}
