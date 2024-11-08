package controller;

import javafx.embed.swing.JFXPanel;
import model.Spaceship;
import org.junit.jupiter.api.Test;
import view.GameBoardUI;
import view.GameToolBar;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardIsRunningTest {
    private int width;
    private int height;
    private Spaceship spaceship;
    private int spaceshipSpeed;
    private Dimension2D dimension;
    private GameBoardUI gameBoardUI;
    private GameToolBar gameToolBar;

    @Test
    void isRunningTest() throws InterruptedException {
        JFXPanel panel = new JFXPanel();
        this.width = GameBoardUI.getDefaultWidth();
        this.height = GameBoardUI.getDefaultHeight();
        GameToolBar gameToolBar = new GameToolBar();
        GameBoardUI gameBoardUI = new GameBoardUI(gameToolBar);
        this.spaceship = gameBoardUI.getGameBoard().getSpaceship();
        spaceship.setPosition(100, 100);
        spaceship.setSpeed(1);

        gameBoardUI.startGame();

        boolean expected1 = gameBoardUI.getGameBoard().isRunning();
        assertEquals(expected1,true);

        Thread.sleep(2000);
        gameBoardUI.stopGame();
        boolean expected2 = gameBoardUI.getGameBoard().isRunning();
        assertEquals(expected2,false);


    }
}