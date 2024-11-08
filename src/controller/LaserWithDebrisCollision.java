package controller;

import application.Point2D;
import model.Debris;
import model.Laser;

//code primarily taken from Collision class of Bumpers
public class LaserWithDebrisCollision extends Collision {
	protected final Laser laser;
	protected final Debris debris;
	private final boolean crash;

	public LaserWithDebrisCollision(Debris debris, Laser laser) {
		this.debris = debris;
		this.laser = laser;
		this.crash = detectCollision();
	}

	public boolean isCrash() {
		return crash;
	}

	public Laser getLaser() {
		return laser;
	}

	public Debris getDebris() {
		return debris;
	}

	/*
	 * Detects collision between the spaceship's laser and debris
	 */
	public boolean detectCollision() {
		Point2D laserPosition = laser.getPosition();
		Dimension2D laserSize = laser.getSize();

		Point2D debrisPosition = debris.getPosition();
		Dimension2D debrisSize = debris.getSize();

		boolean above = laserPosition.getY() + laserSize.getHeight() < debrisPosition.getY();
		boolean below = laserPosition.getY() > debrisPosition.getY() + debrisSize.getHeight();
		boolean right = laserPosition.getX() + laserSize.getWidth() < debrisPosition.getX();
		boolean left = laserPosition.getX() > debrisPosition.getX() + debrisSize.getWidth();

		return !above && !below && !right && !left;
	}
}
