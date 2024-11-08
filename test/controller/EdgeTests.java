package controller;

import application.Point2D;
import controller.Dimension2D;

import model.Spaceship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.GameBoardUI;
import static org.junit.jupiter.api.Assertions.*;

import javafx.embed.swing.JFXPanel;

/*
Movement test for spaceship, we would like to make sure that assigned keyboard keys works well
We use fly() method
-Melih
 */

/*
This tests, test weather spaceship method fly complies the boundry bouncing rules.
 */
public class EdgeTests {

	private JFXPanel panel = new JFXPanel();
	private int width;
	private int height;
	private Dimension2D dimension;
	private double spaceshipWidth;
	private double spaceshipHeight;
	private int spaceshipSpeed;
	private Spaceship spaceship;

	/*
	 * Before
	 */
	@BeforeEach
	public void setUp() {
		// get according widths and heights of gameboard.
		this.width = GameBoardUI.getDefaultWidth();
		this.height = GameBoardUI.getDefaultHeight();

		/// Setup - define dimension and spaceship
		this.dimension = new Dimension2D(width, height);
		this.spaceship = new Spaceship(dimension);
		this.spaceshipWidth = spaceship.getSize().getWidth();
		this.spaceshipHeight = spaceship.getSize().getHeight();
		this.spaceshipSpeed = spaceship.getSpeed();
	}

	/*
	 * Tests the right bounce.
	 */
	@Test
	void testRightEdgeBounceControl() {
		spaceship.setPosition(width - spaceshipWidth - (spaceshipSpeed - 1), 200);
		spaceship.setDirection(90);
		spaceship.fly(dimension);

		Point2D expected = new Point2D(width - spaceshipWidth - 1, 200);
		Point2D observed = spaceship.getPosition();

		assertEquals(expected, observed);

		System.out.println(observed);

	}

	/*
	 * Tests the left bounce.
	 */
	@Test
	void testLeftEdgeBounceControl() {
		spaceship.setPosition((spaceshipSpeed - 1), 200);
		spaceship.setDirection(270);
		spaceship.fly(dimension);

		Point2D expected = new Point2D(1, 200);
		Point2D observed = spaceship.getPosition();

		assertEquals(expected, observed);

		System.out.println(observed);

	}

	/*
	 * Tests the upper bounce.
	 */
	@Test
	void testUpperEdgeBounceControl() {
		spaceship.setPosition(200, (spaceshipSpeed - 1));
		spaceship.setDirection(180);
		spaceship.fly(dimension);

		Point2D expected = new Point2D(200, 1);
		Point2D observed = spaceship.getPosition();

		assertEquals(expected, observed);

		System.out.println(observed);

	}

	/*
	 * Tests the upper bounce.
	 */
	@Test
	void testLowerEdgeBounceControl() {
		spaceship.setPosition(200, height - spaceshipHeight - (spaceshipSpeed - 1));
		spaceship.setDirection(0);
		spaceship.fly(dimension);

		Point2D expected = new Point2D(200, height - spaceshipHeight - 1);
		Point2D observed = spaceship.getPosition();

		assertEquals(expected, observed);

		System.out.println(observed);

	}
}
