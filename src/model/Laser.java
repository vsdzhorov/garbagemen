package model;

import application.Point2D;
import controller.Dimension2D;

public class Laser {
	/*
	 * Final attribute String icon will be added later on
	 */
	private int fireRate;
	private Point2D position;
	private double direction;
	// add speed attribute
	// added spaceship attribute for direction
	private Spaceship spaceship;
	// added crunch
	private boolean crunched;
	// icon
	private static String ICON = "images/laser_projectile.png";

	public Laser(Spaceship spaceship) {
		this.spaceship = spaceship;
		this.fireRate = 5;
		this.position = new Point2D(getSpaceship().getPosition().getX(), getSpaceship().getPosition().getY());
		this.crunched = false;
		this.direction = spaceship.getDirection();
	}

	private Dimension2D size = new Dimension2D(DEFAULT_LASER_WIDTH, DEFAULT_LASER_HEIGHT);

	public int getFireRate() {
		return fireRate;
	}

	public Dimension2D getSize() {
		return size;
	}

	protected static final int DEFAULT_LASER_WIDTH = 50;
	protected static final int DEFAULT_LASER_HEIGHT = 25;

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D pos) {
		this.position = pos;
	}

	public void setIcon(String icon) {
		Laser.ICON = icon;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	/*
	 * Shoots the spaceship's laser
	 */

	public void shoot(Dimension2D gameBoardSize) {
		if (this.spaceship.isFiring()) {
			double maxX = gameBoardSize.getWidth();
			double maxY = gameBoardSize.getHeight();
			// calculate delta between old coordinates and new ones based on speed and
			// direction
			double deltaX = this.fireRate * Math.sin(Math.toRadians(this.direction));
			double deltaY = this.fireRate * Math.cos(Math.toRadians(this.direction));
			double newX = this.position.getX() + deltaX;
			double newY = this.position.getY() + deltaY;
			// calculate position in case the boarder of the game board has been reached
			if (newX < 0 || newX + size.getWidth() > maxX || newY < 0 || newY + size.getHeight() > maxY) {
				crunched = true;
				return;
			}
			// set coordinates
			this.position = new Point2D(newX, newY);
		}
		return;
	}

	public String getIcon() {
		return ICON;
	}

	public Spaceship getSpaceship() {
		return spaceship;
	}

	public void setSpaceship(Spaceship spaceship) {
		this.spaceship = spaceship;
	}

	public boolean isCrunched() {
		return crunched;
	}

	public void setCrunched(boolean crunched) {
		this.crunched = crunched;
	}

	public void setFireRate(int i) {
		this.fireRate = i;
	}

}
