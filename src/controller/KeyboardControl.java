package controller;

import audio.AudioPlayer;
import audio.AudioPlayerInterface;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Laser;
import model.Spaceship;
import view.GameBoardUI;

public class KeyboardControl {

	private final Spaceship spaceship;
	private final GameBoard gameBoard;
	private AudioPlayerInterface audioPlayer;

	public KeyboardControl(GameBoardUI gameBoardUI, Spaceship userSpaceship) {
		this.spaceship = userSpaceship;
		this.gameBoard = gameBoardUI.getGameBoard();
		this.audioPlayer = new AudioPlayer();
		gameBoardUI.addEventHandler(KeyEvent.KEY_PRESSED, this::keyboardPressed);
	}

	/*
	 * Controls A - direction = 270 - go left, D - direction = 90 - go right,W -
	 * direction = 180 - go up,S - direction = 360 or 0 - go down, F increases speed
	 * by 5, C decreases speed by 5, Space bar to fire laser
	 */
	public void keyboardPressed(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.A) {
			spaceship.setAngle(270);
			spaceship.setDirection(270);
		} else if (keyEvent.getCode() == KeyCode.D) {
			spaceship.setDirection(90);
			spaceship.setAngle(90);
		} else if (keyEvent.getCode() == KeyCode.S) {
			spaceship.setDirection(0);
			spaceship.setAngle(180);
		} else if (keyEvent.getCode() == KeyCode.W) {
			spaceship.setDirection(180);
			spaceship.setAngle(360);
		} else if (keyEvent.getCode() == KeyCode.F) {
			spaceship.setSpeed(spaceship.getSpeed() + 5);
		} else if (keyEvent.getCode() == KeyCode.C) {
			if (spaceship.getSpeed() > 5) {
				spaceship.setSpeed(spaceship.getSpeed() - 5);
			}
		} else if (keyEvent.getCode() == KeyCode.SPACE) {
			spaceship.setFiring(true);
			audioPlayer.playLaserSound();
			Laser current = new Laser(spaceship);
			spaceship.getLasers().add(current);
			current.shoot(gameBoard.getSize());
		}
	}
}
