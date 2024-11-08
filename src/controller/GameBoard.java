package controller;

import java.util.ArrayList;
import java.util.List;

import application.Point2D;
import audio.AudioPlayerInterface;
import model.Debris;
import model.Obstacle;
import model.Planet;
import model.Player;
import model.Spaceship;

public class GameBoard {

	/**
	 * AudioPlayer responsible for handling music and game sounds.
	 */
	private AudioPlayerInterface audioPlayer;

	/**
	 * Dimension of the GameBoard.
	 */
	private final Dimension2D size;

	/**
	 * true if game is running, false if game is stopped.
	 */
	private boolean running;

	private Player player;
	private List<Debris> debris = new ArrayList<>();
	private List<Obstacle> obstacles = new ArrayList<>();
	private Spaceship spaceship;
	private Planet earth;
	private Planet mars;
	private Planet saturn;
	private Debris debris2_1;
	private Debris debris2_2;
	private Debris debris2_3;
	private Debris debris1_1;
	private Debris debris1_2;
	private Debris debris1_3;
	private Debris debris2_4;
	private Debris debris2_5;
	private Debris debris2_6;
	private static final int SMALL_DEBRIS_DEFAULT_SIZE = 15;
	private static final int BIG_DEBRIS_DEFAULT_SIZE = 30;

	public GameBoard(Dimension2D size) {
		this.size = size;

		// add planets
		earth = new Planet(new Dimension2D(75, 75), new Point2D(300, 300), "images/earth.png");
		mars = new Planet(new Dimension2D(75, 75), new Point2D(10, 590), "images/mars.png");
		saturn = new Planet(new Dimension2D(100, 100), new Point2D(600, 515), "images/saturn.png");
		this.obstacles.add(earth);
		this.obstacles.add(mars);
		this.obstacles.add(saturn);
		// add debris
		// debris2(level 2 debris) can be broken down into smaller debris1 (level 1
		// debris)
		debris2_1 = new Debris(new Dimension2D(BIG_DEBRIS_DEFAULT_SIZE, BIG_DEBRIS_DEFAULT_SIZE), new Point2D(560, 600),
				320, 2, null, "images/ufo.png");
		debris2_2 = new Debris(new Dimension2D(BIG_DEBRIS_DEFAULT_SIZE, BIG_DEBRIS_DEFAULT_SIZE), new Point2D(750, 10),
				275, 2, null, "images/brokensatellite.png");
		// debris2_3 = new Debris(new Dimension2D(30, 30), new Point2D(250, 300), 285,
		// 2, null, "images/satdish.png");
		// debris2_4 = new Debris(new Dimension2D(30, 30), new Point2D(750, 750), 320,
		// 2, null, "images/ufo.png");
		// debris2_5 = new Debris(new Dimension2D(30, 30), new Point2D(600, 10), 275, 2,
		// null,
		// "images/brokensatellite.png");
		// debris2_6 = new Debris(new Dimension2D(30, 30), new Point2D(350, 400), 285,
		// 2, null, "images/satdish.png");
		debris1_1 = new Debris(new Dimension2D(SMALL_DEBRIS_DEFAULT_SIZE, SMALL_DEBRIS_DEFAULT_SIZE),
				new Point2D(400, 200), 260, 3, null, "images/widestone.png");
		debris1_2 = new Debris(new Dimension2D(SMALL_DEBRIS_DEFAULT_SIZE, SMALL_DEBRIS_DEFAULT_SIZE),
				new Point2D(630, 20), 200, 3, null, "images/tallstone.png");
		debris1_3 = new Debris(new Dimension2D(SMALL_DEBRIS_DEFAULT_SIZE, SMALL_DEBRIS_DEFAULT_SIZE),
				new Point2D(100, 50), 300, 3, null, "images/solar_panel.png");
		this.debris.add(debris2_1);
		this.debris.add(debris2_2);
		// this.debris.add(debris2_3);

		// this.debris.add(debris2_4);
		// this.debris.add(debris2_5);
		// this.debris.add(debris2_6);

		this.debris.add(debris1_1);
		this.debris.add(debris1_2);
		this.debris.add(debris1_3);

		spaceship = new Spaceship(size);
		this.running = false;
		player = new Player(spaceship);
		this.player.setup();
	}

	public AudioPlayerInterface getAudioPlayer() {
		return this.audioPlayer;
	}

	public void setAudioPlayer(AudioPlayerInterface audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	/**
	 * Starts the game. Debris and Player start to move and background music starts
	 * to play.
	 */
	public void startGame() {
		playMusic();
		this.running = true;
	}

	/**
	 * Stops the game. Debris and Player stop moving and background music stops
	 * playing.
	 */
	public void stopGame() {
		stopMusic();
		this.running = false;
	}

	/**
	 * Returns if game is currently running.
	 *
	 * @return true if the game is currently running, false otherwise
	 */
	public boolean isRunning() {
		return this.running;
	}

	/**
	 * Sets whether the game should be currently running.
	 * <p>
	 * Also used for testing on Artemis.
	 *
	 * @param running true if the game should be running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * Starts the background music.
	 */
	public void playMusic() {
		this.audioPlayer.playBackgroundMusic();
	}

	/**
	 * Stops the background music.
	 */
	public void stopMusic() {
		this.audioPlayer.stopBackgroundMusic();
	}

	////// Getter and Setters
	// ****

	public void update() {
		moveSpaceship();
		moveDebris();
		if (!player.getSpaceship().getLasers().isEmpty()) {
			moveLasers();
		}
	}

	public Dimension2D getSize() {
		return size;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Debris> getDebris() {
		return debris;
	}

	public void setDebris(List<Debris> debris) {
		this.debris = debris;
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(List<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public void setSpaceship(Spaceship spaceship) {
		this.spaceship = spaceship;
	}
	//// Getters and Setters

	/*
	 * Moves the debris across the gameboard
	 */
	public void moveDebris() {
		for (int i = 0; i < debris.size(); i++) {
			debris.get(i).move(size);
		}
		for (int j = 0; j < this.getObstacles().size(); j++) {
			for (int i = 0; i < debris.size(); i++) {
				// ignore collisions between biggest debris chunks and obstacles as they can
				// get destroyed only by first getting split by lasers before smaller chunks get
				// evaporated by obstacles
				if (this.getDebris().get(i).getSize().getHeight() == BIG_DEBRIS_DEFAULT_SIZE)
					continue;
				DebrisWithObstacleCollision collision = new DebrisWithObstacleCollision(this.getDebris().get(i),
						this.getObstacles().get(j));
				if (collision.isCrash() && this.getDebris().get(i).getSize().getHeight() != BIG_DEBRIS_DEFAULT_SIZE) {
					audioPlayer.playDebrisExplosionSound();
					this.getDebris().remove(i);
					continue;
				}
			}
		}
		// this is for the pushing debris action
		for (int i = 0; i < debris.size(); i++) {
			ShipWithDebrisCollision collision = new ShipWithDebrisCollision(player.getSpaceship(),
					this.getDebris().get(i));
			if (collision.isCrash()) {
				audioPlayer.playDebrisExplosionSound();
				// added push off
				// pushoff cannot be done constantly
				// spaceship gets launched back in the opposite direction after pushoff to avoid
				// push spam
				this.getDebris().get(i).setDirection(player.getSpaceship().getDirection());
				this.getDebris().get(i).setSpeed(this.getDebris().get(i).getSpeed() + 2);
				this.getDebris().get(i).move(size);
				this.getDebris().get(i).setSpeed(this.getDebris().get(i).getSpeed() - 2);
				spaceship.setDirection(spaceship.getDirection() + 180);
				spaceship.setSpeed(spaceship.getSpeed() + 2);
				spaceship.fly(size);
				spaceship.setSpeed(spaceship.getSpeed() - 2);

				continue;
			}
		}

		// Debris with debris collision
		for (int i = 0; i < debris.size(); i++) {
			if (i == debris.size() - 1) {
				continue;
			}
			for (int ii = i + 1; ii < debris.size(); ii++) {
				DebrisWithDebrisCollision collision1 = new DebrisWithDebrisCollision(this.getDebris().get(i),
						this.getDebris().get(ii));
				if (collision1.isCrash()) {
					audioPlayer.playDebrisCrashSound();
					this.getDebris().get(i).setDirection(getDebris().get(ii).getDirection());
					this.getDebris().get(i).setSpeed(this.getDebris().get(i).getSpeed() + 2);
					this.getDebris().get(i).move(size);
					this.getDebris().get(i).setSpeed(this.getDebris().get(i).getSpeed() - 2);

					this.getDebris().get(ii).setDirection(getDebris().get(ii).getDirection() + 180);
					this.getDebris().get(ii).setSpeed(this.getDebris().get(ii).getSpeed() + 2);
					this.getDebris().get(ii).move(size);
					this.getDebris().get(ii).setSpeed(this.getDebris().get(ii).getSpeed() - 2);

					continue;
				}
			}

		}

		if (debris.isEmpty())
			stopGame();
		for (int j = 0; j < this.getPlayer().getSpaceship().getLasers().size(); j++) {
			for (int i = 0; i < debris.size(); i++) {
				// ignore collisions between smallest debris chunks and lasers as they can
				// get destroyed only by colliding with obstacles
				if (this.getDebris().get(i).getSize().getHeight() == SMALL_DEBRIS_DEFAULT_SIZE)
					continue;
				LaserWithDebrisCollision collision = new LaserWithDebrisCollision(this.getDebris().get(i),
						this.getPlayer().getSpaceship().getLasers().get(j));
				if (collision.isCrash()) {
					audioPlayer.playDebrisExplosionSound();
					this.getPlayer().getSpaceship().getLasers().remove(j);
					Debris brokenDebris = this.getDebris().remove(i);
					// declaring second piece of new debris after contact with laser
					Debris newDebris1_2;
					// if debris was not at its minimum size
					if (brokenDebris.getSize().getHeight() != SMALL_DEBRIS_DEFAULT_SIZE) {
						double X15;
						if (brokenDebris.getPosition().getX() > 400) {
							X15 = brokenDebris.getPosition().getX() - 15.0;
						} else {
							X15 = brokenDebris.getPosition().getX() + 15.0;
						}

						double Y15;
						if (brokenDebris.getPosition().getY() > 350) {
							Y15 = brokenDebris.getPosition().getY() - 15.0;
						} else {
							Y15 = brokenDebris.getPosition().getY() + 15.0;
						}

						Debris newDebris1_1 = new Debris(
								new Dimension2D(SMALL_DEBRIS_DEFAULT_SIZE, SMALL_DEBRIS_DEFAULT_SIZE),
								new Point2D(brokenDebris.getPosition().getX(), brokenDebris.getPosition().getY()),
								brokenDebris.getDirection() + 45, 3, null, "images/tallstone.png");
						// if broken debris was satellite, other new debris piece should be solar panel,
						// else white chunk (tallstone.png)
						if (brokenDebris.getIcon().equals("images/brokensatellite.png")) {
							newDebris1_2 = new Debris(
									new Dimension2D(SMALL_DEBRIS_DEFAULT_SIZE, SMALL_DEBRIS_DEFAULT_SIZE),
									new Point2D(X15, Y15), brokenDebris.getDirection() - 45, 3, null,
									"images/solar_panel.png");
						} else {
							newDebris1_2 = new Debris(
									new Dimension2D(SMALL_DEBRIS_DEFAULT_SIZE, SMALL_DEBRIS_DEFAULT_SIZE),
									new Point2D(X15, Y15), brokenDebris.getDirection() - 45, 3, null,
									"images/widestone.png");
						}
						this.getDebris().add(newDebris1_1);
						this.getDebris().add(newDebris1_2);
					}
					break;
				}
			}
		}
	}

	/*
	 * Moves the player's spaceship across the gameboard
	 */
	public void moveSpaceship() {
		this.getPlayer().getSpaceship().fly(size);
		for (int i = 0; i < this.getObstacles().size(); i++) {
			ShipWithObstacleCollision collision = new ShipWithObstacleCollision(this.getPlayer().getSpaceship(),
					this.getObstacles().get(i));
			if (collision.isCrash()) {
				audioPlayer.playShipExplosionSound();
				// to let audio finish playing before game ends
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				this.stopGame();
			}
		}

	}

	/*
	 * Moves moons
	 */
	public void moveMoons() {
		// moons to be added later on
	}

	public void moveLasers() {
		for (int i = 0; i < this.getPlayer().getSpaceship().getLasers().size(); i++)
			if (!this.getPlayer().getSpaceship().getLasers().get(i).isCrunched())
				this.getPlayer().getSpaceship().getLasers().get(i).shoot(size);
			else
				this.getPlayer().getSpaceship().getLasers().remove(i);
	}

	public Spaceship getSpaceship() {
		return this.spaceship;
	}
}
