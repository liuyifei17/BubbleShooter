package Controller;

import Model.GameData;
import View.View;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by jur on 9/14/2017.
 */
public class GameController {

    private GameData data;
    private GameDataLoader loader;
    private static View view;
    private GameRunner runner;
    private GridController gridController;
    private PlayerBallController playerBallController;

    private Stage primaryStage;
    private Scene mainMenu;
    private Scene gameScreen;
    private Pane gamePane;

    public GameController(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setup(){
        //Initialize data
        data = new GameData();
        loader = new GameDataLoader();
        loader.initialize(data, View.STAGE_WIDTH / 2, (View.STAGE_HEIGHT + View.TOP_BAR_HEIGHT) / 2);

        //Initialize controllers
        gridController = new GridController(data.getGrid());
        playerBallController = new PlayerBallController(data.getPlayer(), data.getGrid());

        setupView();

        //Start running the game
        runner = new GameRunner(gridController, playerBallController);
        runner.runGame();

        setMouseControllers();
    }

    private void setMouseControllers(){
        // Create a mousebutton event that keeps track on when the mouse has been clicked,
        // so that the ball will move in that direction.
        gameScreen.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                playerBallController.setMouseX(event.getSceneX());
                playerBallController.setMouseY(event.getSceneY());
                playerBallController.calculateDelta();
            }
        });
    }

    private void setupView(){
        //Create a pane to hold the game
        gamePane = new Pane();

        //Draw elements on pane
        view = new View(gamePane, data);
        view.drawGame();

        // Create a scene containing the pane and place it in the stage
        gameScreen = new Scene(gamePane, View.STAGE_WIDTH, View.STAGE_HEIGHT);
        primaryStage.setTitle("Bubble Shooter"); // Set the stage title
        primaryStage.setScene(gameScreen); // Place the scene in the stage
        primaryStage.setResizable(false); //cannot resize game
        primaryStage.show(); // Display the stage
    }

    public static View getView() {
        return view;
    }

}
