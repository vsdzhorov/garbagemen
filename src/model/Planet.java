package model;

import application.Point2D;
import controller.Dimension2D;

public class Planet extends Obstacle {

	public Planet(Dimension2D size, Point2D position, String icon) {
		super(size, position, icon);
	}

	private Moon moon;

	public Moon getMoon() {
		return moon;
	}

	public void setMoon(Moon moon) {
		this.moon = moon;
	}

}
