package model;

public class Player {
	private static final double START_X_COORDINATE = 0.0;
	private static final double START_Y_COORDINATE = 0.0;
	private static final int START_DIRECTION = 90;

	private Spaceship spaceship;

	public Player(Spaceship ship) {
		spaceship = ship;
	}

	public void setup() {
		// The player starts in the top left corner
		spaceship.setPosition(START_X_COORDINATE, START_Y_COORDINATE);
		spaceship.setDirection(START_DIRECTION);
	}

	public Spaceship getSpaceship() {
		return spaceship;
	}

	public void setSpaceship(Spaceship spaceship) {
		this.spaceship = spaceship;
	}

}
