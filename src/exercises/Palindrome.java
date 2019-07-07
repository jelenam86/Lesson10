package exercises;

import java.util.List;

import examples.FileHelper;

public class Palindrome {

	public List<String> loadWords() {
		return FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	}

	public boolean wordExist(String word) {
		return loadWords().contains(word);
	}

	public boolean isPalindrome(String word) {
		String str = new StringBuilder(word).reverse().toString();
		return wordExist(word) && wordExist(str);
	}

	public boolean isPhrasePalindrome(String phrase) {
		String str = phrase.replaceAll("[^a-zA-Z0-9]+", "");
		String reversed = new StringBuilder(str).reverse().toString();
		return reversed.equalsIgnoreCase(str);
	}
}
