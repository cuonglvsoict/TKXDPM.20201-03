package subsystem.payment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ValidateCardCodeTest.class, ValidateCardHolderNameTest.class, ValidateCvvCodeTest.class,
		ValidateDateExpiredTest.class})
public class AllTests {

}
