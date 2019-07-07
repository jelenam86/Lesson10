package exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidationTest {

	@Test
	void testEmail() throws Exception {
		assertTrue(Validation.validateEmail("abc.d45@yahoo.com"));
		assertTrue(Validation.validateEmail("abc.d4-5@gmail.eu"));
		assertTrue(Validation.validateEmail("dev.100@abc.com.au"));
		assertTrue(Validation.validateEmail("abc@1.com"));
		assertTrue(Validation.validateEmail("abcd_123@yahoo-test.ABC.CoM"));
		assertTrue(Validation.validateEmail("abc%d4+5@gmail.eu"));

		assertFalse(Validation.validateEmail("abc123gmailcom")); // no @
		assertFalse(Validation.validateEmail("abc#def@test.rs")); // invalid special character #
		assertFalse(Validation.validateEmail("@gmail.com")); // no local-part
		assertFalse(Validation.validateEmail("abc123@")); // no domain
		assertFalse(Validation.validateEmail("abc@123@gmail.com")); // two @ characters
		assertFalse(Validation.validateEmail("abc.123@gmail%com")); // not allowed character % in domain
		assertFalse(Validation.validateEmail("abc@123gmailcom.")); // no word characters after dot in domain
		assertFalse(Validation.validateEmail("abc@.gmailcom")); // dot after @
		assertFalse(Validation.validateEmail("abc@gmailcom")); // no dot in domain
	}

	@Test
	void testPhoneNumber() throws Exception {
		assertTrue(Validation.validatePhoneNumber("016016016"));
		assertTrue(Validation.validatePhoneNumber("+381(0)16016016"));
		assertTrue(Validation.validatePhoneNumber("+1 (201) 267-3412"));
		assertTrue(Validation.validatePhoneNumber("000 000000"));
		assertTrue(Validation.validatePhoneNumber("800-1700"));
		assertTrue(Validation.validatePhoneNumber("(541) 754-3010"));
		assertTrue(Validation.validatePhoneNumber("+1-541-754-3010"));
		assertTrue(Validation.validatePhoneNumber("123.456.7890"));
		
		assertFalse(Validation.validatePhoneNumber("onetwo3"));
		assertFalse(Validation.validatePhoneNumber("123/123456"));
	}
}
