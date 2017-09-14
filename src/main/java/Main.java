import Controller.*;
import Model.GameData;
import View.View;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by jur on 9/5/2017.
 */
public class Main extends Application {

    private GameController game;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        game = new GameController(primaryStage);
        game.setup();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
