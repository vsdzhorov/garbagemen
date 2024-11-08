package controller;

import application.Point2D;
import controller.*;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionTests {

    @Test
    void testDebrisWithObstacleCollision() {
        Debris debris = new Debris(new Dimension2D(1.0, 1.0), new Point2D(5.0, 5.0), 0.0, 2, null, "");
        Obstacle obstacle = new Planet(new Dimension2D(2.0, 2.0), debris.getPosition(), "");
        DebrisWithObstacleCollision test = new DebrisWithObstacleCollision(debris, obstacle);
        assertTrue(test.detectCollision());
        debris.setPosition(new Point2D(1.0, 1.0));
        obstacle.setPosition(7.0, 7.0);
        assertFalse(test.detectCollision());
    }

    @Test
    void testLaserWithDebrisCollision() {
        Spaceship ship = new Spaceship(new Dimension2D(100.0, 100.0));
        Laser laser = new Laser(ship);
        Debris debris = new Debris(new Dimension2D(1.0, 1.0), laser.getPosition(), 0.0, 2, null, "");
        LaserWithDebrisCollision test = new LaserWithDebrisCollision(debris, laser);
        assertTrue(test.detectCollision());
        debris.setPosition(new Point2D(1.0, 1.0));
        laser.setPosition(new Point2D(200.0, 200.0));
        assertFalse(test.detectCollision());
    }

    @Test
    void testShipWithDebrisCollision() {
        Spaceship ship = new Spaceship(new Dimension2D(100.0, 100.0));
        Debris debris = new Debris(new Dimension2D(1.0, 1.0), ship.getPosition(), 0.0, 2, null, "");
        ShipWithDebrisCollision test = new ShipWithDebrisCollision(ship, debris);
        assertTrue(test.detectCollision());
        debris.setPosition(new Point2D(1.0, 1.0));
        ship.setPosition(200.0, 200.0);
        assertFalse(test.detectCollision());
    }

    @Test
    void testShipWithObstacleCollision() {
        Spaceship ship = new Spaceship(new Dimension2D(100.0, 100.0));
        Obstacle obstacle = new Planet(new Dimension2D(2.0, 2.0), ship.getPosition(), "");
        ShipWithObstacleCollision test = new ShipWithObstacleCollision(ship, obstacle);
        assertTrue(test.detectCollision());
        ship.setPosition(1.0, 1.0);
        obstacle.setPosition(200.0, 200.0);
        assertFalse(test.detectCollision());
    }
}
