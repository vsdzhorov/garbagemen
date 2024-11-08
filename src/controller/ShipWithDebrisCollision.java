package controller;

import application.Point2D;
import model.Debris;
import model.Spaceship;

public class ShipWithDebrisCollision extends Collision {
	private Spaceship spaceship;
	private Debris debris;
	private final boolean crash;

	public ShipWithDebrisCollision(Spaceship spaceship, Debris debris) {
		this.spaceship = spaceship;
		this.debris = debris;
		crash = detectCollision();
	}

	public Spaceship getSpaceship() {
		return spaceship;
	}

	public void setSpaceship(Spaceship spaceship) {
		this.spaceship = spaceship;
	}

	public Debris getDebris() {
		return debris;
	}

	public void setDebris(Debris debris) {
		this.debris = debris;
	}

	/*
	 * Detects collisions between the player's spaceship and debris
	 */
	public boolean detectCollision() {
		Point2D spaceshipPosition = this.spaceship.getPosition();
		Dimension2D spaceshipSize = this.spaceship.getSize();

		Point2D debrisPosition = this.debris.getPosition();
		Dimension2D debrisSize = this.debris.getSize();

		boolean above = debrisPosition.getY() + debrisSize.getHeight() < spaceshipPosition.getY();
		boolean below = debrisPosition.getY() > spaceshipPosition.getY() + spaceshipSize.getHeight();
		boolean right = debrisPosition.getX() + debrisSize.getWidth() < spaceshipPosition.getX();
		boolean left = debrisPosition.getX() > spaceshipPosition.getX() + spaceshipSize.getWidth();

		return !above && !below && !right && !left;
	}

	public boolean isCrash() {
		return crash;
	}
}
