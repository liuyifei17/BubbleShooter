import Controller.GameRunner;
import Model.GameData;
import Controller.GameDataLoader;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Created by jur on 9/5/2017.
 */
public class Main extends Application {

    private final double stageWidth = 600;
    private final double stageHeight = 700;
    public GameRunner runner;
    public GameDataLoader loader;
    public GameData data;
    public View view;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        //Create a pane to hold the game
        Pane gamePane = new Pane();

        //Initialize game
        data = new GameData();
        loader = new GameDataLoader();
        loader.initialize(data, stageWidth / 2, stageHeight / 2);

        //Draw elements on pane
        view = new View(gamePane, data);
        view.draw();

        // Create a scene containing the pane and place it in the stage
        Scene scene = new Scene(gamePane, stageWidth, stageHeight);
        primaryStage.setTitle("Bubble Shooter"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false);//cannot resize game
        primaryStage.show(); // Display the stage

        //Start running the game
        runner = new GameRunner();
        runner.runGame();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
