package Controller;

import Model.GameData;
import View.View;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

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
    private Pane mainMenuPane;

    private Media backgroundMusic;
    private MediaPlayer mediaPlayer;
    private int backgroundMusicVolume;


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

        setupSound();
        setupView();

        //Start running the game
        runner = new GameRunner(gridController, playerBallController);
        runner.runGame();

        setMouseControllers();
    }

    private void setMouseControllers(){
        // play button event
        view.getPlayButton().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameScreen);
            }
        });

        //exit button event
        view.getExitButton().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                primaryStage.close();
                System.exit(0);
            }
        });

        //sound button event
        view.getMusicButton().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                handleMusicButtonClick();
            }
        });

        //sound button event
        view.getMusicIcon().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                handleMusicButtonClick();
            }
        });

        // ball firing event
        gameScreen.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                playerBallController.setMouseX(event.getSceneX());
                playerBallController.setMouseY(event.getSceneY());
                playerBallController.calculateDelta();
            }
        });
    }

    private void setupSound(){
        backgroundMusicVolume = 100;
        backgroundMusic = new Media(new File("src/main/resources/sounds/bgm1.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(backgroundMusic);
        mediaPlayer.play();
    }

    private void setupView(){
        //Create the panes to hold game elements
        mainMenuPane = new Pane();
        gamePane = new Pane();

        //Draw elements on pane
        view = new View(mainMenuPane, gamePane, data);
        view.drawMainMenu();
        view.drawGame();

        // Create scenes containing the panes
        mainMenu = new Scene(mainMenuPane, View.STAGE_WIDTH, View.STAGE_HEIGHT);
        gameScreen = new Scene(gamePane, View.STAGE_WIDTH, View.STAGE_HEIGHT);

        // Add the scenes to the stage
        primaryStage.setTitle("Bubble Shooter"); // Set the stage title
        primaryStage.setScene(mainMenu); // Place the scene in the stage
        primaryStage.setResizable(false); //cannot resize game
        primaryStage.show(); // Display the stage
    }

    private void handleMusicButtonClick(){
        view.changeMusicButton(backgroundMusicVolume);
        if(backgroundMusicVolume == 100){
            backgroundMusicVolume = 0;
            mediaPlayer.pause();
        }
        else {
            backgroundMusicVolume = 100;
            mediaPlayer.play();
        }
    }

    public static View getView() {
        return view;
    }

}
