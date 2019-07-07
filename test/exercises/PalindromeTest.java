package exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromeTest {

	Palindrome testPalindrome = new Palindrome();

	@Test
	void testLoadWords() {
		assertTrue(testPalindrome.loadWords().contains("apple"));
	}

	// 2. Test that a word exists in the dictionary

	@Test
	void testWordExist() throws Exception {
		assertTrue(testPalindrome.wordExist("about"));
		assertTrue(testPalindrome.wordExist("kayak"));
		
		assertFalse(testPalindrome.wordExist("oko"));
	}

	// 3. Test that a word is a palindrome

	@Test
	void testIsPalindrome() throws Exception {
		assertTrue(testPalindrome.isPalindrome("madam"));
		assertTrue(testPalindrome.isPalindrome("civic"));
		assertTrue(testPalindrome.isPalindrome("reviver"));
		assertTrue(testPalindrome.isPalindrome("level"));
	}

	@Test
	void testIsPhrasePalindrome() throws Exception {
		assertTrue(testPalindrome.isPhrasePalindrome("I did, did I?"));
		assertTrue(testPalindrome.isPhrasePalindrome("123454321"));
		assertTrue(testPalindrome.isPhrasePalindrome("A car, a man, a maraca."));
		assertTrue(testPalindrome.isPhrasePalindrome("Never odd or even"));

		assertFalse(testPalindrome.isPhrasePalindrome("Java"));
		assertFalse(testPalindrome.isPhrasePalindrome("1234 5 1234"));
	}

}
