package model;

import application.Point2D;
import controller.DebrisWithObstacleCollision;
import controller.Dimension2D;
import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebrisPlanetCollusion {

    private JFXPanel panel = new JFXPanel();

    /*
    Collusion test
     */
    @Test
    public void debrisCollTest1() {

        Planet planet = new Planet(new Dimension2D(10, 10),new Point2D(65,65),"");
        Debris debris = new Debris(new Dimension2D(3, 3), new Point2D(70, 70), 285, 2, null, "images/satdish.png");

        DebrisWithObstacleCollision collision = new DebrisWithObstacleCollision(debris,planet);
        boolean observed = collision.isCrash();


        assertEquals(true,observed);

    }

    /*
    Non Collusion test
     */
    @Test
    public void debrisCollTest2() {

        Planet planet = new Planet(new Dimension2D(15, 15),new Point2D(100,99),"");
        Debris debris = new Debris(new Dimension2D(3, 3), new Point2D(60, 45), 285, 2, null, "images/satdish.png");

        DebrisWithObstacleCollision collision = new DebrisWithObstacleCollision(debris,planet);
        boolean observed = collision.isCrash();


        assertEquals(false,observed);

    }

}
