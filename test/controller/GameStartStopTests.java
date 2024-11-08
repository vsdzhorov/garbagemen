package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import application.Point2D;
import audio.AudioPlayer;
import controller.Dimension2D;
import controller.GameBoard;
import javafx.embed.swing.JFXPanel;

public class GameStartStopTests {
	private JFXPanel panel = new JFXPanel();
	private AudioPlayer testPlayer = new AudioPlayer();

	// when game starts, bg music should start, gameboard should have all
	// components, some of which should move
	@Test
	public void testGameStart() {
		GameBoard testBoard = new GameBoard(new Dimension2D(800, 800));
		testBoard.setAudioPlayer(testPlayer);
		assertEquals(testBoard.getAudioPlayer().isPlayingBackgroundMusic(), testBoard.isRunning());
		assertEquals(testBoard.getDebris().get(0).getPosition(), new Point2D(560, 600));
		assertEquals(testBoard.getObstacles().get(0).getPosition(), new Point2D(300, 300));
		assertEquals(testBoard.getPlayer().getSpaceship().getPosition(), new Point2D(0, 0));
		testBoard.startGame();
		testBoard.update();
		assertEquals(testBoard.getObstacles().size(), 3);
		assertEquals(testBoard.getDebris().size(), 5);
		assertNotEquals(testBoard.getDebris().get(0).getPosition(), new Point2D(750, 10));
		assertNotEquals(testBoard.getPlayer().getSpaceship().getPosition(), new Point2D(0, 0));
	}

	// bg music to stop with game and obstacles to remain intact
	@Test
	public void testGameStop() {
		GameBoard testBoard = new GameBoard(new Dimension2D(800, 800));
		testBoard.setAudioPlayer(testPlayer);
		testBoard.startGame();
		testBoard.stopGame();
		assertFalse(testBoard.getAudioPlayer().isPlayingBackgroundMusic());
		assertEquals(testBoard.getObstacles().size(), 3);
	}

}
