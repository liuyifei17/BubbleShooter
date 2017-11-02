package Controller;

import Model.GameData;
import Model.Grid;
import Model.Player;
import View.View;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;

/**
 * This class is responsible for setup of the game.
 */
public class GameController {

    private static View view;
    private GameData data;
    private GameDataLoader loader;
    private GameRunner runner;
    private GridController gridController;
    private PlayerBallController playerBallController;
    private WallController wallController;
    private IOController ioController;

    private Stage primaryStage;
    private Scene mainMenu;
    private Scene gameScreen;
    private Pane gamePane;
    private Pane mainMenuPane;

    private Media backgroundMusic;
    private MediaPlayer mediaPlayer;

    private boolean gamePaused;
    private long clickDelay;

    /**
     * the constructor of the game controller class.
     * @param primaryStage sets the primary stage
     */
    public GameController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gamePaused = false;
    }

    /**
     * @return the view.
     */
    public static View getView() {
        return view;
    }

    /**
     * This is the setter for the View field.
     * @param v the view
     */
    public static void setView(View v) {
        view = v;
    }

    /**
     * initializes the game.
     */
    public void setup() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();

        //Initialize data
        data = new GameData(new Grid(GUIConfiguration.stageWidth / 2,
                (GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2),
                new Player(), 90);
        loader = new GameDataLoader();
        loader.initialize(data);

        //Initialize controllers
        wallController = new WallController(data);
        wallController.placeWalls();
        gridController = new GridController(this, data.getGrid());
        playerBallController = new PlayerBallController(this, data.getPlayer(), data.getGrid(),
                gridController);
        ioController = new IOController(data.getScores(),
                "src/main/resources/configuration/scores.json");
        ioController.readFromFile(data);


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
        runner = new GameRunner(gridController, playerBallController, new Timer(5, null));
        runner.runGame();

        setMouseControllers();
        setKeyboardControllers();
    }

    private void setupSound() {
        try {
            backgroundMusic = new Media(
                    new File("src/main/resources/sounds/track1.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(backgroundMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            if (GameConfiguration.sounds) {
                mediaPlayer.play();
            }
        }
        catch (Exception e) {
            System.out.println("Error loading sound...");
        }
    }

    private void setupView() {
        //Create the panes to hold game elements
        mainMenuPane = new Pane();
        gamePane = new Pane();

        //Draw elements on pane
        view = new View(mainMenuPane, gamePane, data, data.getPlayer());
        data.getPlayer().addObserver(view);
        view.drawMainMenu();
        view.drawGame();

        // Create scenes containing the panes
        mainMenu = new Scene(mainMenuPane, GUIConfiguration.stageWidth,
                GUIConfiguration.stageHeight);
        gameScreen = new Scene(gamePane, GUIConfiguration.stageWidth,
                GUIConfiguration.stageHeight);

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
            if (!gamePaused && !(event.getSceneY() < GUIConfiguration.topBarHeight + 20)
                    && (clickDelay + 800) < System.currentTimeMillis()) {
                playerBallController.setMouseX(event.getSceneX());
                playerBallController.setMouseY(event.getSceneY());
                playerBallController.calculateDelta();
                clickDelay = System.currentTimeMillis();
            }
        });

        // play button event
        view.getPlayButton().setOnMouseReleased(event -> {
            if (gamePaused) {
                resumeGame();
            }
            primaryStage.setScene(gameScreen);
        });

        //exit button event
        view.getExitButton().setOnMouseReleased(event -> {
            primaryStage.close();
            System.exit(0);
        });

        //popup home button event
        view.getGameOverPopup().getHomeButton().setOnMouseReleased(event -> {
            resetGame();
            primaryStage.setScene(mainMenu);
        });

        //popup restart button event
        view.getGameOverPopup().getRestartButton().setOnMouseReleased(event -> {
            resetGame();
        });


        setMouseControllers_PausePopup();
        setMouseControllers_SettingsPopup();

    }

    private void setMouseControllers_PausePopup() {
        view.getGamePauseIcon().setOnMouseReleased(event -> {
            if (!gamePaused) {
                view.getPausePopup().showPopup();
                pauseGame();
            }
        });

        //continue playing the game
        view.getPausePopup().getRestartButton().setOnMouseReleased(event -> {
            clickDelay = System.currentTimeMillis();
            resetGame();
        });

        //continue playing the game
        view.getPausePopup().getExitButton().setOnMouseReleased(event -> {
            System.exit(0);
        });

        //return to main menu
        view.getPausePopup().getHomeButton().setOnMouseReleased(event -> {
            clickDelay = System.currentTimeMillis();
            primaryStage.setScene(mainMenu);
            view.getPausePopup().closePopup();
        });

        view.getPausePopup().getCloseButton().setOnMouseReleased(event -> {
            clickDelay = System.currentTimeMillis();
            view.getPausePopup().closePopup();
            resumeGame();
        });
    }

    private void setMouseControllers_SettingsPopup() {
        view.getGameSettingsIcon().setOnMouseReleased(event -> {
            if (!gamePaused) {
                view.getSettingsPopup().showPopup();
                pauseGame();
            }
        });

        view.getSettingsPopup().getAudioToggle().setOnMouseReleased(event -> {
            handleMusicButtonClick();
            view.getSettingsPopup().checkSettingsAudioToggle();
        });

        view.getSettingsPopup().getWallToggle().setOnMouseReleased(event -> {
            GameConfiguration.walls = !GameConfiguration.walls;
            view.getSettingsPopup().checkSettingsWallToggle();
        });

        view.getSettingsPopup().getSpecialToggle().setOnMouseReleased(event -> {
            GameConfiguration.specialBalls = !GameConfiguration.specialBalls;
            view.getSettingsPopup().checkSettingsSpecialBallToggle();
        });

        view.getSettingsPopup().getCloseButton().setOnMouseReleased(event -> {
            clickDelay = System.currentTimeMillis();
            view.getSettingsPopup().closePopup();
            resumeGame();
        });
    }

    private void handleMusicButtonClick() {
        if (mediaPlayer == null) {
            return;
        }
        if (GameConfiguration.sounds) {
            GameConfiguration.sounds = false;
            mediaPlayer.pause();
        } else {
            GameConfiguration.sounds = true;
            mediaPlayer.play();
        }
    }

    private void setKeyboardControllers() {
        //pause the game
        gameScreen.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (!gamePaused) {
                    view.getPausePopup().showPopup();
                    pauseGame();
                } else {
                    view.getSettingsPopup().closePopup();
                    view.getPausePopup().closePopup();
                    resumeGame();
                }
            }
        });
    }

    private void pauseGame() {
        gamePaused = true;
        runner.pauseGame();
    }

    private void resumeGame() {
        gamePaused = false;
        runner.continueGame();
    }

    private void resetGame() {
        //close the game over popup
        view.getGameOverPopup().createPopup();

        //reset data
        data = new GameData(new Grid(GUIConfiguration.stageWidth / 2,
                (GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2),
                new Player(), 90);
        loader = new GameDataLoader();
        loader.initialize(data);
        wallController = new WallController(data);
        wallController.placeWalls();
        gridController = new GridController(this, data.getGrid());
        playerBallController = new PlayerBallController(this, data.getPlayer(), data.getGrid(),
                gridController);
        ioController = new IOController(data.getScores(),
                "src/main/resources/configuration/scores.json");
        ioController.readFromFile(data);

        //reset view
        gamePane = new Pane();
        view.setData(data);
        view.setPlayer(data.getPlayer());
        data.getPlayer().addObserver(view);
        view.setGamePane(gamePane);
        view.drawGame();
        gameScreen = new Scene(gamePane, GUIConfiguration.stageWidth,
                GUIConfiguration.stageHeight);
        primaryStage.setScene(gameScreen);

        //continue playing the game

        runner = new GameRunner(gridController, playerBallController, new Timer(5, null));
        runner.runGame();
        gamePaused = false;

        //reset controllers
        setMouseControllers();
        setKeyboardControllers();
    }

    /**
     * Pauses the game and shows the game over popup.
     */
    public void gameOver() {
        pauseGame();
        data.placeScore();
        ioController.writeToFile();
        view.getGameOverPopup().showPopup();
    }

    /**
     * @return the wallcontroller.
     */
    public WallController getWallController() {
        return wallController;
    }

    /**
     * @return the data of the gamecontroller.
     */
    public GameData getData() {
        return data;
    }
}
