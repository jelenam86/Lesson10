package exercises;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class HangmanTest {

	private Hangman hangman = new Hangman();

	@Test
	public void testPopPuzzle1() {
		hangman.addPuzzles();
		assertThrows(FoundSpecialCharacterException.class, () -> hangman.popPuzzle());
	}

	@Test
	public void testPopPuzzle2() {
		hangman.addPuzzles();
		assertThrows(FoundSpecialCharacterException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				hangman.popPuzzle();
			}
		});
	}

	@Test
	public void testPopPuzzle3() {
		hangman.addPuzzles();
		try {
			hangman.popPuzzle();
			assertFalse(true);
		} catch (FoundSpecialCharacterException e) {
			assertTrue(true);
		}
	}

	// source: https://www.ascii-code.com/
	@Test
	public void testContainSpecialCharacter() {
		for (int i = 0; i < 65; i++) {
			assertTrue(hangman.containSpecialCharacter("" + (char) i));
		}
		for (int i = 65; i <= 90; i++) {
			assertFalse(hangman.containSpecialCharacter("" + (char) i));
		}
		for (int i = 91; i < 97; i++) {
			assertTrue(hangman.containSpecialCharacter("" + (char) i));
		}
		for (int i = 97; i <= 122; i++) {
			assertFalse(hangman.containSpecialCharacter("" + (char) i));
		}
		for (int i = 123; i < 256; i++) {
			assertTrue(hangman.containSpecialCharacter("" + (char) i));
		}
	}
}
