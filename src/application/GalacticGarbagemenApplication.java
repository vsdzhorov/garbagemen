package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.GameBoardUI;
import view.GameToolBar;

import java.util.Objects;

public class GalacticGarbagemenApplication extends Application {


	private static final int HEIGHT = 750;
	private static final int WIDTH = 650;

	/**
	 * Starts the Game Window by setting up a new tool bar, a new user interface and
	 * adding them to the stage.
	 *
	 * @param primaryStage the primary stage for this application, onto which the
	 *                     application scene can be set.
	 */
	@Override
	public void start(Stage primaryStage) {

		Parent root = null;
		try {
			root = FXMLLoader.load(Objects.requireNonNull(getClass()
					.getClassLoader()
					.getResource("views/WelcomeView.fxml")));

		} catch (Exception ex) {
			System.exit(0);
		}

		primaryStage.setTitle("EIST's Galactic Garbageman");
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();

		primaryStage.show();

	}

	public static void startApp(String[] args) {
		launch(args);
	}

}
