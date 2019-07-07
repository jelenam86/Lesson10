package exercises;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import examples.FileHelper;

public class Hangman extends KeyAdapter {

	Stack<String> puzzles = new Stack<String>();
	ArrayList<JLabel> boxes = new ArrayList<JLabel>();
	int lives = 9;
	JLabel livesLabel = new JLabel("" + lives);

	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		hangman.addPuzzles();
		hangman.createUI();
	}

	private void addPuzzles() {
//		puzzles.push("defenestrate");
//		puzzles.push("fancypants");
//		puzzles.push("elements");
		List<String> words = FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
		Collections.shuffle(words);
		words.forEach(str -> puzzles.push(str));
	}

	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
		playDeathKnell();
		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		loadNextPuzzle();
		frame.add(panel);
		frame.setVisible(true);
//		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(200, 100);
		frame.addKeyListener(this);
	}

	private void loadNextPuzzle() {
		removeBoxes();
		lives = 9;
		livesLabel.setText("" + lives);
		puzzle = puzzles.pop();
		System.out.println("puzzle is now " + puzzle);
		createBoxes();
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		updateBoxesWithUserInput(arg0.getKeyChar());
		if (lives == 0 || checkUserInput()) {
//			playDeathKnell();
			JOptionPane.showMessageDialog(null, String.format("%s The puzzle was: %s.", endOfGame(), puzzle));
			loadNextPuzzle();
		}
	}

	private void updateBoxesWithUserInput(char keyChar) {
		boolean gotOne = false;
		for (int i = 0; i < puzzle.length(); i++) {
			if (puzzle.charAt(i) == keyChar) {
				boxes.get(i).setText("" + keyChar);
				gotOne = true;
			}
		}
		if (!gotOne)
			livesLabel.setText("" + --lives);
	}

	void createBoxes() {
		for (int i = 0; i < puzzle.length(); i++) {
			JLabel textField = new JLabel("_");
			boxes.add(textField);
			panel.add(textField);
		}
		panel.revalidate();
		panel.repaint(); // to remove residue from the previous game 
	}

	void removeBoxes() {
		for (JLabel box : boxes) {
			panel.remove(box);
		}
		boxes.clear();
	}

	public void playDeathKnell() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resource/funeral-march.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY); // to avoid overlapping
			clip.start();
//			Thread.sleep(8400);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private boolean checkUserInput() {
		StringBuilder builder = new StringBuilder();
		boxes.forEach(label -> builder.append(label.getText()));
		return builder.toString().equals(puzzle);
	}

	private String endOfGame() {
		if (lives == 0)
			return "Sorry, you lose!";
		return String.format("Well done! You won the game with %d lives left.", lives);
	}
}
