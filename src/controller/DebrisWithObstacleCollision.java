package controller;

import application.Point2D;
import model.Debris;
import model.Obstacle;

//code primarily taken from Collision class of Bumpers
public class DebrisWithObstacleCollision extends Collision {
	protected final Debris debris;
	protected final Obstacle obstacle;
	private final boolean crash;

	public DebrisWithObstacleCollision(Debris debris, Obstacle obstacle) {
		this.debris = debris;
		this.obstacle = obstacle;
		this.crash = detectCollision();
	}

	public boolean isCrash() {
		return crash;
	}

	public Debris getDebris() {
		return debris;
	}

	public Obstacle getObstalce() {
		return obstacle;
	}

	/*
	 * Detects collisions between debris and obstacles
	 */
	public boolean detectCollision() {
		Point2D debrisPosition = debris.getPosition();
		Dimension2D debrisSize = debris.getSize();

		Point2D obstaclePosition = obstacle.getPosition();
		Dimension2D obstacleSize = obstacle.getSize();

		boolean above = debrisPosition.getY() + debrisSize.getHeight() < obstaclePosition.getY();
		boolean below = debrisPosition.getY() > obstaclePosition.getY() + obstacleSize.getHeight();
		boolean right = debrisPosition.getX() + debrisSize.getWidth() < obstaclePosition.getX();
		boolean left = debrisPosition.getX() > obstaclePosition.getX() + obstacleSize.getWidth();

		return !above && !below && !right && !left;
	}
}
