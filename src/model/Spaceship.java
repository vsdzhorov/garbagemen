package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import application.Point2D;
import controller.Dimension2D;

public class Spaceship {

	private final String icon = "images/spaceship.gif";
	private int speed = 8;
	private double direction;
	private boolean crunched = false;
	private List<Laser> lasers = new ArrayList<Laser>();
	private Point2D position;
	private Dimension2D size = new Dimension2D(DEFAULT_SPACESHIP_WIDTH, DEFAULT_SPACESHIP_HEIGHT);
	private List<PowerUp> powerUps = new ArrayList<PowerUp>();
	private boolean firing;
	private double angle = 90;

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public Spaceship(Dimension2D gameBoardSize) {
		setRandomPosition(gameBoardSize);
		// lasers don't seem to work without adding this redundant laser to lasers
		Laser redundantLaser = new Laser(this);
		redundantLaser.setCrunched(true);
		this.lasers.add(redundantLaser);
		this.firing = false;
	}

	protected static final int DEFAULT_SPACESHIP_WIDTH = 30;
	protected static final int DEFAULT_SPACESHIP_HEIGHT = 30;

	protected static final int MAX_ANGLE = 360;
	protected static final int HALF_ANGLE = MAX_ANGLE / 2;

	public Point2D getPosition() {
		return this.position;
	}

	public void setPosition(double x, double y) {
		this.position = new Point2D(x, y);
	}

	public Dimension2D getSize() {
		return size;
	}

	public String getIcon() {
		return icon;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	/*
	 * Flies the spaceship in accordance with the player's input
	 */

	public void fly(Dimension2D gameBoardSize) {
		double maxX = gameBoardSize.getWidth();
		double maxY = gameBoardSize.getHeight();
		// calculate delta between old coordinates and new ones based on speed and
		// direction
		double deltaX = this.speed * Math.sin(Math.toRadians(this.direction));
		double deltaY = this.speed * Math.cos(Math.toRadians(this.direction));
		double newX = this.position.getX() + deltaX;
		double newY = this.position.getY() + deltaY;

		// calculate position in case the boarder of the game board has been reached
		if (newX < 0) {
			newX = -newX;
			this.direction = MAX_ANGLE - this.direction;
		} else if (newX + this.size.getWidth() > maxX) {
			newX = 2 * maxX - newX - 2 * this.size.getWidth();
			this.direction = MAX_ANGLE - this.direction;
		}

		if (newY < 0) {
			newY = -newY;
			this.direction = HALF_ANGLE - this.direction;
			if (this.direction < 0) {
				this.direction = MAX_ANGLE + this.direction;
			}
		} else if (newY + this.size.getHeight() > maxY) {
			newY = 2 * maxY - newY - 2 * this.size.getHeight();
			this.direction = HALF_ANGLE - this.direction;
			if (this.direction < 0) {
				this.direction = MAX_ANGLE + this.direction;
			}
		}

		// set angle
		if (this.direction == 0) {
			this.setAngle(180);
		} else if (this.direction == 180) {
			this.setAngle(0);
		} else {
			this.angle = this.direction;
		}
		// set coordinates
		this.position = new Point2D(newX, newY);
	}

	public List<Laser> getLasers() {
		return lasers;
	}

	public List<PowerUp> getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(List<PowerUp> powerUp) {
		this.powerUps = powerUp;
	}

	public boolean isFiring() {
		return firing;
	}

	public void setFiring(boolean firing) {
		this.firing = firing;
	}

	public boolean addPowerUp(PowerUp powerUp) {
		// Deterministcally calculate a random number with seed
//		// if number is 5 then get Laser boost (increase speed of laser)
//		Random r = new Random(1);
//		int answer = r.nextInt(4) + 1;
//		if (answer == 5) {
//			this.powerUp.add(powerUp);
//			return true;
//		} else if (answer == 1) {
//			this.powerUp.add(new SpeedBoost());
//			return true;
//		} else {
//			return false;
//		}
		if (powerUp.evaluate()) {
			this.powerUps.add(powerUp);
			return true;
		} else {
			return false;
		}
	}

	protected void setRandomPosition(Dimension2D gameBoardSize) {
		double carX = calculateRandomDouble(0, gameBoardSize.getWidth() - size.getWidth());
		double carY = calculateRandomDouble(0, gameBoardSize.getHeight() - size.getHeight());
		this.position = new Point2D(carX, carY);
	}

	protected static double calculateRandomDouble(double minValue, double maxValue) {
		return ThreadLocalRandom.current().nextDouble(minValue, maxValue);
	}

}
