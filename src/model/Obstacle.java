package model;

import application.Point2D;
import controller.Dimension2D;

public abstract class Obstacle {
	/*
	 * Final attribute String icon will be added later on
	 */
	protected Dimension2D size;
	protected Point2D position;
	protected String icon;

	public Obstacle(Dimension2D size, Point2D position, String icon) {
		this.size = size;
		this.position = position;
		this.icon = icon;
	}

	public Dimension2D getSize() {
		return size;
	}

	public void setSize(Dimension2D size) {
		this.size = size;
	}

	public String getIcon() {
		return this.icon;
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(double x, double y) {
		this.position = new Point2D(x, y);
	}

}
