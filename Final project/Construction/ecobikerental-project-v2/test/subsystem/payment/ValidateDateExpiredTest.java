package subsystem.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateDateExpiredTest {

	@ParameterizedTest
	@CsvSource({
		"abc, false",
		"a123, false",
		"123 45, false",
		"123@, false",
		"123, false",
		"2608679022442, true"
	})
	void test(String dateExpired, boolean expected) {
		boolean isValided = PaymentInfoValidation.validateDateExpired(dateExpired);
		assertEquals(expected, isValided);
	}

}
