package controller;

import application.Point2D;
import javafx.embed.swing.JFXPanel;
import model.Spaceship;
import org.junit.jupiter.api.Test;
import view.GameBoardUI;
import view.GameToolBar;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardSpaceshipMoveTest {

    private int width;
    private int height;
    private Spaceship spaceship;
    private Dimension2D dimension;
    private GameBoardUI gameBoardUI;
    private GameToolBar gameToolBar;

    @Test
    void moveSpaceship() {
        JFXPanel panel = new JFXPanel();
        this.width = GameBoardUI.getDefaultWidth();
        this.height = GameBoardUI.getDefaultHeight();
        GameToolBar gameToolBar = new GameToolBar();
        GameBoardUI gameBoardUI = new GameBoardUI(gameToolBar);
        this.spaceship = gameBoardUI.getGameBoard().getSpaceship();
        spaceship.setPosition(100, 100);

        gameBoardUI.getGameBoard().moveSpaceship();
        System.out.println(spaceship.getPosition());

        assertNotEquals(spaceship.getPosition(), new Point2D(100,100));

    }
}