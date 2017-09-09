import Controller.GameRunner;
import Controller.GridController;
import Controller.PlayerBallController;
import Model.GameData;
import Controller.GameDataLoader;
import View.View;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

/**
 * Created by jur on 9/5/2017.
 */
public class Main extends Application {

    private GameData data;
    private GameDataLoader loader;
    private View view;
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

        //Draw elements on pane
        view = new View(gamePane, data);
        view.draw();
        playerBallController = new PlayerBallController(data.getPlayer(), data.getGrid());

        // Create a scene containing the pane and place it in the stage
        Scene scene = new Scene(gamePane, View.STAGE_WIDTH, View.STAGE_HEIGHT);
        primaryStage.setTitle("Bubble Shooter"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false); //cannot resize game
        primaryStage.show(); // Display the stage

        // Create a mousebutton event that keeps track on when the mouse has been clicked,
        // so that the ball will move in that direction.
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                    playerBallController.setMouseX(event.getX());
                    playerBallController.setMouseY(event.getY());
                    playerBallController.calculateDelta();
            }
        });

        //Start running the game
        runner = new GameRunner(gridController, view, playerBallController);
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
