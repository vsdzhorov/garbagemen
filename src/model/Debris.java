package model;

import application.Point2D;
import controller.Dimension2D;

public class Debris {

	private Dimension2D size;
	private Point2D position;
	private double direction;
	private int speed;
	private PowerUp powerUp;
	private String icon;
	private PowerUp powerup;
	private Dimension2D gameBoardSize;

	public Debris(Dimension2D gameBoardSize) {
		// random position as an example
		this.position = new Point2D(300, 500);
		this.setGameBoardSize(gameBoardSize);
	}

	private static final int MIN_SIZE = 1;
	protected static final int MAX_ANGLE = 360;
	protected static final int HALF_ANGLE = MAX_ANGLE / 2;

	public Debris(Dimension2D size, Point2D position, double direction, int speed, PowerUp powerUp, String icon) {
		this.size = size;
		this.position = position;
		this.direction = direction;
		this.speed = speed;
		this.powerUp = powerUp;
		this.icon = icon;
	}

	public PowerUp getPowerup() {
		return powerUp;
	}

	public void setPowerup(PowerUp powerUp) {
		this.powerUp = powerUp;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public Dimension2D getSize() {
		return size;
	}

	public void setSize(Dimension2D size) {
		this.size = size;
	}

	public Point2D getPosition() {
		return this.position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public String getIcon() {
		return icon;
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/*
	 * Evaporates a piece of debris that has been pushed into an obstacle
	 */
	public void evaporate() {
		// in GameBoard itself
	}

	/*
	 * Breaks a piece of debris that has been shot
	 */
	public void breakDebris() {
		// in GameBoard itself
	}

	/*
	 * Moves the debris across the gameboard
	 */

	public void move(Dimension2D gameBoardSize) {
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
		// set coordinates
		this.position = new Point2D(newX, newY);
	}

	// drops a powerup if available
	private PowerUp dropPowerUp() {
		return null;
	}

	public Dimension2D getGameBoardSize() {
		return gameBoardSize;
	}

	public void setGameBoardSize(Dimension2D gameBoardSize) {
		this.gameBoardSize = gameBoardSize;
	}
}
