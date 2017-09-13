package Main;

import Controller.GameDataLoader;
import Controller.GameRunner;
import Controller.GridController;
import Controller.PlayerBallController;
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

    private GameData data;
    private GameDataLoader loader;
    private static View view;
    private GameRunner runner;
    private GridController gridController;
    private PlayerBallController playerBallController;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        //Create a pane to hold the game
        Pane gamePane = new Pane();

        //Initialize data
        data = new GameData();
        loader = new GameDataLoader();
        loader.initialize(data, View.STAGE_WIDTH / 2, (View.STAGE_HEIGHT + View.TOP_BAR_HEIGHT) / 2);

        //Initialize controllers
        gridController = new GridController(data.getGrid());
        playerBallController = new PlayerBallController(data.getPlayer(), data.getGrid());

        //Draw elements on pane
        view = new View(gamePane, data);
        view.draw();

        // Create a scene containing the pane and place it in the stage
        Scene scene = new Scene(gamePane, View.STAGE_WIDTH, View.STAGE_HEIGHT);
        primaryStage.setTitle("Bubble Shooter"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false); //cannot resize game
        primaryStage.show(); // Display the stage

        //Start running the game
        runner = new GameRunner(gridController, playerBallController);
        runner.runGame();

        // Create a mousebutton event that keeps track on when the mouse has been clicked,
        // so that the ball will move in that direction.
        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                playerBallController.setMouseX(event.getSceneX());
                playerBallController.setMouseY(event.getSceneY());
                playerBallController.calculateDelta();
            }
        });

    }

    public static View getView() {
        return view;
    }
    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
