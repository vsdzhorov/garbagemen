package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.GameBoardUI;
import view.GameToolBar;

public class WelcomeViewController {

    private static final int GRID_LAYOUT_PADDING = 5;
    private static final int GRID_LAYOUT_PREF_HEIGHT = 550;
    private static final int GRID_LAYOUT_PREF_WIDTH = 505;

    @FXML
    private Button startBtn;
    @FXML
    private Button instructionsBtn;

    private Parent root;
    private Stage primaryStage;

    public void exit() {
        System.exit(0);
    }

    public void startAction() {

        primaryStage = (Stage) startBtn.getScene().getWindow();

        GameToolBar toolBar = new GameToolBar();
        GameBoardUI gameBoardUI = new GameBoardUI(toolBar);
        toolBar.initializeActions(gameBoardUI);
        Pane gridLayout = createLayout(gameBoardUI, toolBar);
        Scene scene = new Scene(gridLayout);

        // scene and stages

        primaryStage.setTitle("EIST Cream's Galactic Garbageman");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(closeEvent -> gameBoardUI.stopGame());
        primaryStage.centerOnScreen();
        primaryStage.show();
        gameBoardUI.startGame();

    }


        public void instructionBtnAction () {

            primaryStage = (Stage) instructionsBtn.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("views/Instructions.fxml"));
            } catch (Exception ex) {
                System.exit(0);
            }

            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
            primaryStage.show();
        }

    private static Pane createLayout(GameBoardUI gameBoardUI, GameToolBar toolBar) {
        // GridPanes are divided into columns and rows, like a table
        GridPane gridLayout = new GridPane();
        gridLayout.setPrefSize(GRID_LAYOUT_PREF_WIDTH, GRID_LAYOUT_PREF_HEIGHT);
        gridLayout.setVgap(GRID_LAYOUT_PADDING);
        gridLayout.setPadding(new Insets(GRID_LAYOUT_PADDING));

        // add all components to the gridLayout
        // second parameter is column index, second parameter is row index of grid
        gridLayout.add(gameBoardUI, 0, 1);
        gridLayout.add(toolBar, 0, 0);
        return gridLayout;
    }
}
