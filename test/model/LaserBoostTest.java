package model;

import application.Point2D;
import controller.Dimension2D;
import javafx.embed.swing.JFXPanel;
import view.GameBoardUI;
import view.GameToolBar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
Test whether laser boost increase the fire rate of the lasers.
 */
public class LaserBoostTest {

    private int width;
    private int height;
    private Spaceship spaceship;
    private Dimension2D dimension;
    private GameBoardUI gameBoardUI;
    private GameToolBar gameToolBar;

    @Test
    public void isLaserBoostUpdatesLaserSpeed() {
        JFXPanel panel = new JFXPanel();
        this.width = GameBoardUI.getDefaultWidth();
        this.height = GameBoardUI.getDefaultHeight();
        GameToolBar gameToolBar = new GameToolBar();
        GameBoardUI gameBoardUI = new GameBoardUI(gameToolBar);
        this.spaceship = gameBoardUI.getGameBoard().getSpaceship();
        spaceship.setPosition(100, 100);

        int before = 5;
        LaserBoost laserBoost = new LaserBoost(new Point2D(0,0));
        laserBoost.equipPowerUp(spaceship);

        int fireRateafter = 5;
        for (int i = 0; i < spaceship.getLasers().size(); i++) {
            int rate = spaceship.getLasers().get(i).getFireRate();
            if (rate > fireRateafter) {
                fireRateafter = rate;
            }
        }

        assertNotEquals(fireRateafter, before);

    }

}