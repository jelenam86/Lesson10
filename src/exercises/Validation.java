package exercises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

// https://en.wikipedia.org/wiki/Email_address#Valid_email_addresses

// email: local-part@domain

// Many websites evaluate the validity of email addresses differently than the standards specify, 
// rejecting addresses containing valid characters, such as + and /,so here we deal only with common characters 
// allowed with most websites.

	public static boolean validateEmail(String email) {
		if (!email.contains("@"))
			return false;
		String[] parts = email.split("@");
		if (parts.length != 2)
			return false;
		Pattern local = Pattern.compile("^[a-z0-9._%+-]+", Pattern.CASE_INSENSITIVE);
		Pattern domain = Pattern.compile("[a-z0-9.-]+\\.[a-z]{2,}$", Pattern.CASE_INSENSITIVE);
		Matcher lcl = local.matcher(parts[0]);
		Matcher dom = domain.matcher(parts[1]);
		return lcl.matches() && dom.matches();
	}

	public static boolean validatePhoneNumber(String number) {
		// min 5 for local numbers, max 15 digits for full number with country and area number and extra 10 for other characters
		Pattern pattern = Pattern.compile("^\\+?[0-9. ()-]{5,25}$");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
}
