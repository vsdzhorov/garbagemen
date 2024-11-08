package model;

import application.Point2D;

public interface PowerUp {
	public static final Point2D location = null;

	public Point2D getLocation();

	public void setLocation(Point2D location);

	/*
	 * Equips a dropped power up to the player's ship
	 */
	public boolean equipPowerUp(Spaceship spaceship);

	public boolean evaluate();
}
