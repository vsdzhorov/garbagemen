package model;

import java.util.Random;

import application.Point2D;

public class Shield implements PowerUp {

	private Point2D location;

	public Shield(Point2D location) {
		this.location = location;

	}

	@Override
	public boolean equipPowerUp(Spaceship spaceship) {
		return false;
	}

	@Override
	public Point2D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point2D location) {
		this.location = location;

	}

	@Override
	public boolean evaluate() {
		// Deterministcally calculate a random number with seed
		// if number is 5 then get Laser boost (increase speed of laser)
		Random r = new Random(1);
		int answer = r.nextInt(4) + 1;
		if (answer == 5) {
			return true;
		}
		return false;
	}
}
