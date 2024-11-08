package model;

import java.util.Random;

import application.Point2D;

public class LaserBoost implements PowerUp {
	private Point2D location;

	public LaserBoost(Point2D location) {
		this.location = location;

	}

	public boolean equipPowerUp(Spaceship s) {
		for (int i = 0; i < s.getLasers().size(); i++) {
			s.getLasers().get(i).setFireRate(s.getLasers().get(i).getFireRate() + 2);
		}
		return true;
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
