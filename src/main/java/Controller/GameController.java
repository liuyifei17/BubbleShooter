package Controller;

import Model.GameData;
import View.View;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

/**
 * This class is responsible for setup of the game.
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

    private boolean gamePaused;
    private long clickDelay;

    public GameController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gamePaused = false;
    }

    public static View getView() {
        return view;
    }

    public void setup() {
        //Initialize data
        data = new GameData();
        loader = new GameDataLoader();
        loader.initialize(data, View.STAGE_WIDTH / 2, (View.STAGE_HEIGHT + View.TOP_BAR_HEIGHT) / 2);

        //Initialize controllers
        gridController = new GridController(this, data.getGrid());
        playerBallController = new PlayerBallController(this, data.getPlayer(), data.getGrid());

        // set up the sound
        // if the route is not correct start the game without sound
        try {
            setupSound();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        setupView();

        //Start running the game
        runner = new GameRunner(gridController, playerBallController);
        runner.runGame();

        setMouseControllers();
        setKeyboardControllers();
    }

    private void setupSound() {
        backgroundMusicVolume = 100;
        try {
            backgroundMusic = new Media(new File("src/main/resources/sounds/bgm1.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(backgroundMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
        catch(Exception e){
            System.out.println("Error loading sound...");
        }
    }

    private void setupView() {
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
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.show(); // Display the stage
    }

    private void setMouseControllers() {
        clickDelay = System.currentTimeMillis();

        // ball firing event
        gameScreen.setOnMouseReleased(event -> {
            if (!gamePaused && (clickDelay + 800) < System.currentTimeMillis()) {
                playerBallController.setMouseX(event.getSceneX());
                playerBallController.setMouseY(event.getSceneY());
                playerBallController.calculateDelta();
                clickDelay = System.currentTimeMillis();
            }
        });

        // play button event
        view.getPlayButton().setOnMouseReleased(event -> {
            primaryStage.setScene(gameScreen);
        });

        //exit button event
        view.getExitButton().setOnMouseReleased(event -> {
            primaryStage.close();
            System.exit(0);
        });

        //sound button event
        view.getMusicButton().setOnMouseReleased(event -> {
            handleMusicButtonClick();
        });

        //sound button event
        view.getMusicIcon().setOnMouseReleased(event -> {
            handleMusicButtonClick();
        });

        //popup home button event
        view.getPopupHomeButton().setOnMouseReleased(event -> {
            resetGame();
            primaryStage.setScene(mainMenu);
        });

        //popup restart button event
        view.getPopupRestartButton().setOnMouseReleased(event -> {
            resetGame();
        });
    }

    private void handleMusicButtonClick() {
        if (mediaPlayer == null) return;

        view.changeMusicButton(backgroundMusicVolume);
        if (backgroundMusicVolume == 100) {
            backgroundMusicVolume = 0;
            mediaPlayer.pause();
        } else {
            backgroundMusicVolume = 100;
            mediaPlayer.play();
        }
    }

    private void setKeyboardControllers() {
        //pause the game
        gameScreen.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                //do nothing yet
            }
        });
    }

    private void pauseGame(){
        gamePaused = true;
        runner.pauseGame();
    }

    private void continueGame(){
        gamePaused = false;
        runner.continueGame();
    }

    private void resetGame() {
        //close the game over popup
        view.closeGameOverPopup();

        //reset data
        data = new GameData();
        loader = new GameDataLoader();
        loader.initialize(data, View.STAGE_WIDTH / 2, (View.STAGE_HEIGHT + View.TOP_BAR_HEIGHT) / 2);
        gridController = new GridController(this, data.getGrid());
        playerBallController = new PlayerBallController(this, data.getPlayer(), data.getGrid());

        //reset view
        gamePane = new Pane();
        view.setData(data);
        view.setGamePane(gamePane);
        view.drawGame();
        gameScreen = new Scene(gamePane, View.STAGE_WIDTH, View.STAGE_HEIGHT);
        primaryStage.setScene(gameScreen);

        //continue playing the game
        runner = new GameRunner(gridController, playerBallController);
        runner.runGame();
        gamePaused = false;

        //reset controllers
        setMouseControllers();
        setKeyboardControllers();
    }

    public void gameOver(){
        pauseGame();
        view.showGameOverPopup();
    }

}
