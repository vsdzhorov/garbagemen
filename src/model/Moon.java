package model;

import application.Point2D;
import controller.Dimension2D;

public class Moon extends Obstacle {

	private Planet planet;

	public Moon(Dimension2D size, Point2D position, String icon) {
		super(size, position, icon);
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

}
