package controller;

import application.Point2D;
import model.Obstacle;
import model.Spaceship;

//code primarily taken from Collision class of Bumpers
public class ShipWithObstacleCollision extends Collision {
	protected final Spaceship spaceship;
	protected final Obstacle obstacle;
	private final boolean crash;

	public ShipWithObstacleCollision(Spaceship spaceship, Obstacle obstacle) {
		this.spaceship = spaceship;
		this.obstacle = obstacle;
		this.crash = detectCollision();
	}

	public boolean isCrash() {
		return crash;
	}

	public Spaceship getSpaceship() {
		return spaceship;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	/*
	 * Detects collisions between player's spaceship and obstacles
	 */
	public boolean detectCollision() {
		Point2D spaceshipPosition = spaceship.getPosition();
		Dimension2D spaceshipSize = spaceship.getSize();

		Point2D obstaclePosition = obstacle.getPosition();
		Dimension2D obstacleSize = obstacle.getSize();

		boolean above = spaceshipPosition.getY() + spaceshipSize.getHeight() < obstaclePosition.getY();
		boolean below = spaceshipPosition.getY() > obstaclePosition.getY() + obstacleSize.getHeight();
		boolean right = spaceshipPosition.getX() + spaceshipSize.getWidth() < obstaclePosition.getX();
		boolean left = spaceshipPosition.getX() > obstaclePosition.getX() + obstacleSize.getWidth();

		return !above && !below && !right && !left;
	}

}
