import Controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main method running the application.
 */
public class Main extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        GameController game;
        game = new GameController(primaryStage);
        game.setup();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     * @param args default java argument
     */
    public static void main(String[] args) {
        launch(args);
    }
}
