package controller;

import application.Point2D;
import model.Debris;

public class DebrisWithDebrisCollision extends Collision {
	private Debris debris1;
	private Debris debris2;
	private final boolean crash;

	public DebrisWithDebrisCollision(Debris debr, Debris debri) {
		this.debris1 = debr;
		this.debris2 = debri;
		crash = detectCollision();
	}

	public Debris getDebris1() {
		return debris1;
	}

	public void setDebris1(Debris debris1) {
		this.debris1 = debris1;
	}

	public Debris getDebris2() {
		return debris2;
	}

	public void setDebris2(Debris debris2) {
		this.debris2 = debris2;
	}

	/*
	 * Detects collisions between two pieces of debris
	 */
	public boolean detectCollision() {
		Point2D debris1Position = this.getDebris1().getPosition();
		Dimension2D debris1Size = this.getDebris1().getSize();

		Point2D debris2Position = this.getDebris2().getPosition();
		Dimension2D debris2Size = this.getDebris2().getSize();

		boolean above = debris1Position.getY() + debris1Size.getHeight() < debris2Position.getY();
		boolean below = debris1Position.getY() > debris2Position.getY() + debris2Size.getHeight();
		boolean right = debris1Position.getX() + debris1Size.getWidth() < debris2Position.getX();
		boolean left = debris1Position.getX() > debris2Position.getX() + debris2Size.getWidth();

		return !above && !below && !right && !left;
	}

	public boolean isCrash() {
		return crash;
	}
}
