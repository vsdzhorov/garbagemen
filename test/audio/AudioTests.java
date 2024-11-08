package audio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import application.Point2D;
import controller.DebrisWithObstacleCollision;
import controller.Dimension2D;
import controller.LaserWithDebrisCollision;
import controller.ShipWithDebrisCollision;
import controller.ShipWithObstacleCollision;
import javafx.embed.swing.JFXPanel;
import model.Debris;
import model.Laser;
import model.Planet;
import model.Spaceship;

public class AudioTests {
	// collision sounds
	private JFXPanel panel = new JFXPanel();
	private AudioPlayer testPlayer = new AudioPlayer();

	@Test
	void testDebrisExplosionSound() {
		ShipWithDebrisCollision SWDTestCollision = new ShipWithDebrisCollision(new Spaceship(new Dimension2D(50, 50)),
				new Debris(new Dimension2D(50, 50), new Point2D(750, 10), 320, 2, null, "images/ufo.png"));
		DebrisWithObstacleCollision DWOTestCollision = new DebrisWithObstacleCollision(
				new Debris(new Dimension2D(50, 50), new Point2D(750, 10), 320, 2, null, "images/ufo.png"),
				new Planet(new Dimension2D(100, 100), new Point2D(300, 300), "images/earth.png"));
		LaserWithDebrisCollision LWDTestCollision = new LaserWithDebrisCollision(
				new Debris(new Dimension2D(50, 50), new Point2D(750, 10), 320, 2, null, "images/ufo.png"),
				new Laser(new Spaceship(new Dimension2D(50, 50))));
		assertEquals(testPlayer.getDebrisExplosionPlayer().isPlaying(), SWDTestCollision.isCrash());
		assertEquals(testPlayer.getDebrisExplosionPlayer().isPlaying(), DWOTestCollision.isCrash());
		assertEquals(testPlayer.getDebrisExplosionPlayer().isPlaying(), LWDTestCollision.isCrash());
	}

	@Test
	void testBigExplosionSound() {
		Spaceship testShip = new Spaceship(new Dimension2D(100, 100));
		Planet testPlanet = new Planet(new Dimension2D(100, 100), new Point2D(300, 300), "images/earth.png");
		ShipWithObstacleCollision SWOTestCollision = new ShipWithObstacleCollision(testShip, testPlanet);
		assertEquals(testPlayer.getSpaceShipExplosionPlayer().isPlaying(), SWOTestCollision.isCrash());
	}

}
