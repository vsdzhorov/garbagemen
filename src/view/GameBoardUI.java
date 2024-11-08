package view;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import application.Point2D;
import audio.AudioPlayer;
import controller.Dimension2D;
import controller.GameBoard;
import controller.KeyboardControl;
import javafx.application.Platform;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.Debris;
import model.Laser;
import model.Obstacle;
import model.Spaceship;
import javafx.scene.image.ImageView;

public class GameBoardUI extends Canvas {

	private static final Color BACKGROUND_COLOR = Color.rgb(24, 24, 24);
	/**
	 * The update period of the game in ms, this gives us 60 fps.
	 */
	private static final int UPDATE_PERIOD = 1000 / 60;
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 700;
	private static final Dimension2D DEFAULT_SIZE = new Dimension2D(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private static State state; // enum that controls the menu

	private Timer gameTimer;

	private HashMap<String, Image> imageCache;
	private KeyboardControl keyboardControl;
	private final GameToolBar gameToolBar;
	private GameBoard gameBoard;

	//GETTER/SETTERS

	public static State getState() {
		return state;
	}

	public static void setState(State state) {
		GameBoardUI.state = state;
	}

	public static Dimension2D getPreferredSize() {
		return DEFAULT_SIZE;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public static int getDefaultWidth() {
		return DEFAULT_WIDTH;
	}

	public static int getDefaultHeight() {
		return DEFAULT_HEIGHT;
	}


	public GameBoardUI(GameToolBar gameToolBar) {
		this.gameToolBar = gameToolBar;
		setup();
	}

	/*
	 * Game setup
	 */

	public void setup() {
		setupGameBoard();
		setupImageCache();
		this.gameToolBar.updateToolBarStatus(false);
		paint();
	}

	/*
	 * Creates the gameboard
	 */
	private void setupGameBoard() {
		Dimension2D size = getPreferredSize();
		this.gameBoard = new GameBoard(size);
		this.gameBoard.setAudioPlayer(new AudioPlayer());
		widthProperty().set(size.getWidth());
		heightProperty().set(size.getHeight());
		this.keyboardControl = new KeyboardControl(this, this.gameBoard.getPlayer().getSpaceship());
	}

	/*
	 * Sets up an image cache
	 */
	private void setupImageCache() {
		this.imageCache = new HashMap<>();
		// spaceship
		String spaceshipImageLocation = this.gameBoard.getPlayer().getSpaceship().getIcon();
		this.imageCache.put(spaceshipImageLocation, getImage(spaceshipImageLocation));
		// lasers
		for (int i = 0; i < this.gameBoard.getPlayer().getSpaceship().getLasers().size(); i++) {
			String laserImageLocation = this.gameBoard.getPlayer().getSpaceship().getLasers().get(i).getIcon();
			this.imageCache.put(laserImageLocation, getImage(laserImageLocation));
		}
		// obstacles
		for (int i = 0; i < this.gameBoard.getObstacles().size(); i++) {
			String ImageLocation = this.gameBoard.getObstacles().get(i).getIcon();
			this.imageCache.put(ImageLocation, getImage(ImageLocation));
		}
		// debris
		for (int i = 0; i < this.gameBoard.getDebris().size(); i++) {
			String ImageLocation = this.gameBoard.getDebris().get(i).getIcon();
			this.imageCache.put(ImageLocation, getImage(ImageLocation));
		}
	}

	/**
	 * Sets images.
	 */
	private Image getImage(String carImageFilePath) {
		URL carImageUrl = getClass().getClassLoader().getResource(carImageFilePath);
		if (carImageUrl == null) {
			throw new IllegalArgumentException(
					"Please ensure that your resources folder contains the appropriate files for this exercise.");
		}
		return new Image(carImageUrl.toExternalForm());
	}

	/**
	 * Starts the GameBoardUI Thread, if it wasn't running. Starts the game board,
	 * which causes the cars to change their positions (i.e. move). Renders graphics
	 * and updates tool bar status.
	 */

	public void startGame() {
		if (!this.gameBoard.isRunning()) {
			this.gameBoard.startGame();
			this.gameToolBar.updateToolBarStatus(true);
			setState(State.GAME);
			startTimer();
			paint();
			// added requestFocus() for keyboard instructions to register
			requestFocus();
		}

		System.out.println("Game Started");
		this.gameToolBar.updateToolBarStatus(true);
	}

	/*
	 * Game timer
	 */
	private void startTimer() {
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				updateGame();
			}
		};
		if (this.gameTimer != null) {
			this.gameTimer.cancel();
		}
		this.gameTimer = new Timer();
		this.gameTimer.scheduleAtFixedRate(timerTask, UPDATE_PERIOD, UPDATE_PERIOD);
	}

	/*
	 * Updates the game state
	 */
	private void updateGame() {
		if (gameBoard.isRunning()) {
			gameBoard.update();
			paint();
		}
	}

	/**
	 * Stops the game board and set the tool bar to default values.
	 */

	public void stopGame() {
		if (this.gameBoard.isRunning()) {
			this.gameBoard.stopGame();
			this.gameToolBar.updateToolBarStatus(false);
			this.gameTimer.cancel();
			setState(State.MENU);
		}
		System.out.println("Game Stopped");
		this.gameToolBar.updateToolBarStatus(false);
		paint();
	}

	/**
	 * Render the graphics of the whole game
	 */
	private void paint() {
		getGraphicsContext2D().setFill(BACKGROUND_COLOR);
		getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());

		paintSpaceship(this.gameBoard.getPlayer().getSpaceship());
		paintObstacles(this.gameBoard.getObstacles());
		paintDebris(this.gameBoard.getDebris());
		if (this.gameBoard.getPlayer().getSpaceship().isFiring() == true
				&& this.gameBoard.getPlayer().getSpaceship().getLasers().isEmpty() == false) {
			for (int i = 0; i < this.gameBoard.getPlayer().getSpaceship().getLasers().size(); i++)
				if (!this.gameBoard.getPlayer().getSpaceship().getLasers().get(i).isCrunched())
					paintLaser(this.gameBoard.getPlayer().getSpaceship().getLasers().get(i));
		}


	}

	/*
	 * Paints player's spaceship on the gameboard
	 */
	private void paintSpaceship(Spaceship spaceship) {
		Point2D spaceshipPosition = spaceship.getPosition();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				ImageView rotate = new ImageView(imageCache.get(spaceship.getIcon()));
				rotate.setRotate(getGameBoard().getPlayer().getSpaceship().getAngle());
				SnapshotParameters params = new SnapshotParameters();
				params.setFill(Color.TRANSPARENT);
				Image rotatedImage = rotate.snapshot(params, null);

				getGraphicsContext2D().drawImage(rotatedImage, spaceshipPosition.getX(), spaceshipPosition.getY(),
						spaceship.getSize().getWidth(), spaceship.getSize().getHeight());

			}

		});
	}

	private void paintDebris(List<Debris> debrisGroup) {
		for (int i = 0; i < debrisGroup.size(); i++) {
			Point2D position = debrisGroup.get(i).getPosition();
			getGraphicsContext2D().drawImage(this.imageCache.get(debrisGroup.get(i).getIcon()), position.getX(),
					position.getY(), debrisGroup.get(i).getSize().getWidth(), debrisGroup.get(i).getSize().getHeight());
		}
	}

	public void paintLaser(Laser laser) {
		Point2D laserPosition = laser.getPosition();
		getGraphicsContext2D().drawImage(this.imageCache.get(laser.getIcon()), laserPosition.getX(),
				laserPosition.getY(), laser.getSize().getWidth(), laser.getSize().getHeight());
	}

	private void paintObstacles(List<Obstacle> obstacles) {
		for (int i = 0; i < obstacles.size(); i++) {
			Point2D position = obstacles.get(i).getPosition();
			getGraphicsContext2D().drawImage(this.imageCache.get(obstacles.get(i).getIcon()), position.getX(),
					position.getY(), obstacles.get(i).getSize().getWidth(), obstacles.get(i).getSize().getHeight());
		}
	}

}
