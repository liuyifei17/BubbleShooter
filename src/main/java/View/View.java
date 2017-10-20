package View;

import Controller.GameConfiguration;
import Model.Cell;
import Model.GameData;
import Model.Player;
import Model.Walls;
import Utility.SetTimeout;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Observable;
import java.util.Observer;


/**
 * The view class.
 */
public class View implements Observer {

    private GameData data;
    private Player player;
    protected Pane gamePane;
    private Pane mainMenuPane;
    private ImageView gameSettingsIcon;
    private ImageView gamePauseIcon;
    private Image mainMenuBg;
    private ImageView playButton;
    private ImageView exitButton;
    private Image gameBg;
    private ImageView topBar;
    private ImageView scoreBar;
    private Text scoreBarScore;
    private Pane gameOverPopup;
    private Text popupScore;
    private ImageView popupRestartButton;
    private ImageView popupHomeButton;
    private ImageView playerBallImageView;
    private ImageView nextBallImageView;
    private ImageView firstWall;
    private ImageView secondWall;
    private ImageView thirdWall;
    private Pane pausePopup;
    private Button pausePopupRestartButton;
    private Button pausePopupMainMenuButton;
    private Button pausePopupExitButton;
    private Button pausePopupCloseButton;
    private Pane settingsPopup;
    private Text audioText;
    private ImageView audioToggle;
    private Text wallText;
    private ImageView wallToggle;
    private Text specialText;
    private ImageView specialToggle;
    private Button settingsPopupCloseButton;

    /**
     * @param mainMenuPane sets the main menu pane
     * @param gamePane sets the game pane
     * @param data sets the game data
     * @param player sets the player
     */
    public View(Pane mainMenuPane, Pane gamePane, GameData data, Player player) {
        this.mainMenuPane = mainMenuPane;
        this.gamePane = gamePane;
        this.data = data;
        this.player = player;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o == player) {
            popupScore.setText("Score: " + player.getScore());
            scoreBarScore.setText("Score: " + player.getScore());
        }
    }

    /**
     * draws up the main menu screen.
     */
    public void drawMainMenu() {
        //draw background
        mainMenuBg = new Image("images/main-menu-bg.png");
        mainMenuPane.setBackground(new Background(
                new BackgroundImage(mainMenuBg, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT)));

        //draw buttons
        playButton = new ImageView("images/play-button.png");
        playButton.relocate(184, 260);
        playButton.fitWidthProperty().setValue(250);
        playButton.fitHeightProperty().setValue(80);
        exitButton = new ImageView("images/exit-button.png");
        exitButton.relocate(184, 370);
        exitButton.fitWidthProperty().setValue(250);
        exitButton.fitHeightProperty().setValue(80);

        //add components to main menu
        mainMenuPane.getChildren().add(playButton);
        mainMenuPane.getChildren().add(exitButton);
    }

    /**
     * Fills the game pane with elements to initialize the game pane view.
     */
    public void drawGame() {
        //draw background
        gameBg = new Image("images/background1.png");
        gamePane.setBackground(new Background(
                new BackgroundImage(gameBg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        //draw top bar
        topBar = new ImageView("images/topBar1.png");
        topBar.relocate(0, 0);
        topBar.fitWidthProperty().bind(gamePane.widthProperty());
        topBar.setFitHeight(GameConfiguration.topBarHeight);

        //draw score bar
        scoreBar = new ImageView("images/scoreBar1.png");
        scoreBar.relocate(8, 15);
        scoreBar.setFitHeight(GameConfiguration.scoreBarHeight);
        scoreBar.setFitWidth(GameConfiguration.scoreBarWidth);

        //draw score text
        scoreBarScore = new Text("Score: 0");
        scoreBarScore.setFont(Font.font("Arial", 25));
        scoreBarScore.relocate(15, 22);
        scoreBarScore.setFill(Color.YELLOW);

        //draw button icons
        gamePauseIcon = new ImageView("images/pause-icon.png");
        gamePauseIcon.relocate(495, 10);
        gamePauseIcon.fitHeightProperty().setValue(48);
        gamePauseIcon.fitWidthProperty().setValue(48);
        gameSettingsIcon = new ImageView("images/settings-icon.png");
        gameSettingsIcon.relocate(550, 10);
        gameSettingsIcon.fitHeightProperty().setValue(44);
        gameSettingsIcon.fitWidthProperty().setValue(44);

        //draw walls
        firstWall = new ImageView("images/asteroid.png");
        secondWall = new ImageView("images/asteroid.png");
        thirdWall = new ImageView("images/asteroid.png");

        //draw entities
        for (Cell c : data.getGrid().getOccupiedCells()) {
            display(c);
        }

        Image spriteNextBall =
                new Image("images/" + data.getPlayer().getNextBall().getColor() + " ball.png");
        nextBallImageView = new ImageView(spriteNextBall);
        nextBallImageView.relocate(GameConfiguration.stageWidth / 2
                - spriteNextBall.getWidth() / 4, GameConfiguration.topBarHeight - 30);
        nextBallImageView.setFitWidth(spriteNextBall.getWidth() / 2);
        nextBallImageView.setFitHeight(spriteNextBall.getHeight() / 2);


        Image spritePlayerBall =
                new Image("images/" + data.getPlayer().getPlayerBall().getColor() + " ball.png");
        playerBallImageView = new ImageView(spritePlayerBall);
        playerBallImageView.relocate(data.getPlayer().getPlayerBall().getX()
                        - spritePlayerBall.getWidth() / 2,
                data.getPlayer().getPlayerBall().getY() - spritePlayerBall.getHeight() / 2);

        //create popup menu's
        createGameOverPopup();
        createPausePopup();
        createSettingsPopup();

        //add components to game pane
        gamePane.getChildren().add(topBar);
        //gamePane.getChildren().add(scoreBar);

        gamePane.getChildren().add(playerBallImageView);
        gamePane.getChildren().add(nextBallImageView);
        gamePane.getChildren().add(firstWall);
        gamePane.getChildren().add(secondWall);
        gamePane.getChildren().add(thirdWall);
        gamePane.getChildren().add(scoreBarScore);
        gamePane.getChildren().add(gamePauseIcon);
        gamePane.getChildren().add(gameSettingsIcon);
        gamePane.getChildren().add(gameOverPopup);
        gamePane.getChildren().add(pausePopup);
        gamePane.getChildren().add(settingsPopup);
        gameOverPopup.setVisible(false);
        pausePopup.setVisible(false);
        settingsPopup.setVisible(false);
    }

    /**
     * redraws the screen elements, which is mainly used for animations.
     */
    public void redraw() {
        Platform.runLater(() -> {

            //relocate elements
            for (Node node : gamePane.getChildren()) {
                if (node instanceof BallImageView) {
                    BallImageView biv = (BallImageView) node;
                    biv.relocate(getScreenX(biv.getCell(), biv.getImage()),
                            getScreenY(biv.getCell(), biv.getImage()));
                    if (!biv.isPlus1Icon()) {
                        biv.rotateProperty().setValue(data.getGrid().getRotation());
                    }
                }
            }

            Image spritePlayerBall = new Image("images/"
                    + data.getPlayer().getPlayerBall().getColor() + " ball.png");
            playerBallImageView.setImage(spritePlayerBall);

            Image spriteNextBall =
                    new Image("images/" + data.getPlayer().getNextBall().getColor() + " ball.png");
            nextBallImageView.setImage(spriteNextBall);

            playerBallImageView.relocate(data.getPlayer().getPlayerBall().getX()
                            - spritePlayerBall.getWidth() / 2,
                    data.getPlayer().getPlayerBall().getY() - spritePlayerBall.getHeight() / 2);

            removeWalls();
            placeWalls();
        });
    }

    public void placeWalls() {
        if (data.getRandomWalls().size() == 3 && GameConfiguration.walls) {
            firstWall.relocate(data.getRandomWalls().get(0).getX() - GameConfiguration.wallWidth,
                    data.getRandomWalls().get(0).getY() - GameConfiguration.wallHeight);
            secondWall.relocate(data.getRandomWalls().get(1).getX() - GameConfiguration.wallWidth,
                    data.getRandomWalls().get(1).getY() - GameConfiguration.wallHeight);
            thirdWall.relocate(data.getRandomWalls().get(2).getX() - GameConfiguration.wallWidth,
                    data.getRandomWalls().get(2).getY() - GameConfiguration.wallHeight);

            firstWall.rotateProperty().setValue(data.getRandomWalls().get(0).getRotation());
            secondWall.rotateProperty().setValue(data.getRandomWalls().get(1).getRotation());
            thirdWall.rotateProperty().setValue(data.getRandomWalls().get(2).getRotation());

            firstWall.setVisible(true);
            secondWall.setVisible(true);
            thirdWall.setVisible(true);

        }
        if (data.getRandomWalls().size() == 2 && GameConfiguration.walls) {
            firstWall.relocate(data.getRandomWalls().get(0).getX() - GameConfiguration.wallWidth,
                    data.getRandomWalls().get(0).getY() - GameConfiguration.wallHeight);
            secondWall.relocate(data.getRandomWalls().get(1).getX() - GameConfiguration.wallWidth,
                    data.getRandomWalls().get(1).getY() - GameConfiguration.wallHeight);

            firstWall.rotateProperty().setValue(data.getRandomWalls().get(0).getRotation());
            secondWall.rotateProperty().setValue(data.getRandomWalls().get(1).getRotation());

            firstWall.setVisible(true);
            secondWall.setVisible(true);
        }
        if (data.getRandomWalls().size() == 1 && GameConfiguration.walls) {
            firstWall.relocate(data.getRandomWalls().get(0).getX() - GameConfiguration.wallWidth,
                    data.getRandomWalls().get(0).getY() - GameConfiguration.wallHeight);

            firstWall.rotateProperty().setValue(data.getRandomWalls().get(0).getRotation());

            firstWall.setVisible(true);
        }
    }

    public void removeWalls() {
        Platform.runLater(() -> {
            if ((data.getRandomWalls().size() == 0) || !(GameConfiguration.walls)) {
                firstWall.setVisible(false);
                secondWall.setVisible(false);
                thirdWall.setVisible(false);
            }
            if (data.getRandomWalls().size() == 1) {
                secondWall.setVisible(false);
                thirdWall.setVisible(false);
            }
            if (data.getRandomWalls().size() == 2) {
                thirdWall.setVisible(false);
            }
        });
    }

    /**
     * This method removes the image of a ball from the cell and from the GUI.
     * @param c the cell whose image needs to be removed
     */
    public void removeBall(Cell c) {
        Platform.runLater(() -> {
            for (Node e : gamePane.getChildren()) {
                if (e instanceof BallImageView && ((BallImageView) e).getCell().equals(c)) {
                    gamePane.getChildren().remove(e);
                    break;
                }
            }
        });
    }

    /**
     * This method displays a plus 1 icon in the places where balls have been removed.
     * @param c the cell where a plus 1 should be displayed
     */
    public void displayPlus1(Cell c) {
        Platform.runLater(() -> {
            BallImageView plusOne = new BallImageView(new Image("images/plus1.png"), c, true);
            plusOne.relocate(c.getCurrentX(), c.getCurrentY());

            gamePane.getChildren().add(plusOne);
            RemovePlusOneIcon r = new RemovePlusOneIcon(plusOne, gamePane);

            SetTimeout t = new SetTimeout("Timeout Thread", 1000, r);
            t.start();
        });
    }

    /**
     * This method is used to display a ball on the GUI.
     * @param c the cell in which the ball is located
     */
    public void display(Cell c) {
        Platform.runLater(() -> {
            if (c.getBall() != null) {
                BallImageView biv;
                if (c.getBall().isCenterPiece()) {
                    biv = new BallImageView(new Image("images/center.png"), c, false);
                }
                else if (c.getBall().isNormalBall()) {
                    biv = new BallImageView(new Image("images/" + c.getBall().getColor()
                            + " ball.png"), c, false);
                }
                else if (c.getBall().isExplosiveBall()) {
                    biv = new BallImageView(new Image("images/explosive ball.png"),
                            c, false);
                }
                else if (c.getBall().isRainbowBall()) {
                    biv = new BallImageView(new Image("images/rainbow ball.png"),
                            c, false);
                }
                else if (c.getBall().isMultiplierBall()) {
                    biv = new BallImageView(new Image("images/multiplier ball.png"),
                            c, false);
                    biv = new BallImageView(new Image("images/explosive ball.png"), c, false);
                }
                else if (c.getBall().isRainbowBall()) {
                    biv = new BallImageView(new Image("images/rainbow ball.png"), c, false);
                }
                else if (c.getBall().isMultiplierBall()) {
                    biv = new BallImageView(new Image("images/multiplier ball.png"), c, false);
                }
                else {
                    biv = new BallImageView(new Image("images/null.png"),
                            c, false);
                }
                gamePane.getChildren().add(biv);
                biv.relocate(getScreenX(c, biv.getImage()), getScreenY(c, biv.getImage()));
            }
        });
    }

    /**
     * Creates a game over popup menu.
     */
    private void createGameOverPopup() {
        //create popup container
        gameOverPopup = new Pane();
        gameOverPopup.setPrefSize(GameConfiguration.popupWidth, GameConfiguration.popupHeight);
        gameOverPopup.relocate(GameConfiguration.popupX, GameConfiguration.popupY);
        gameOverPopup.setBackground(new Background(new BackgroundImage(
                new Image("images/popupBackground.png", GameConfiguration.popupWidth,
                        GameConfiguration.popupHeight, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        //create graphical elements
        ImageView gameOverMessage = new ImageView("images/gameOverMessage.png");
        gameOverMessage.relocate(0, 5);
        popupScore = new Text("Score: 0");
        popupScore.setFont(Font.font("Arial", 35));
        popupScore.setFill(Color.YELLOW);
        popupScore.setWrappingWidth(GameConfiguration.popupWidth);
        popupScore.setTextAlignment(TextAlignment.CENTER);
        popupScore.setUnderline(true);
        popupScore.relocate(0, 130);
        popupRestartButton = new ImageView("images/restart-icon.png");
        popupRestartButton.relocate(5, 260);
        popupHomeButton = new ImageView("images/home-icon.png");
        popupHomeButton.relocate(198, 260);

        //add graphical elements to popup container
        gameOverPopup.getChildren().add(gameOverMessage);
        gameOverPopup.getChildren().add(popupScore);
        gameOverPopup.getChildren().add(popupRestartButton);
        gameOverPopup.getChildren().add(popupHomeButton);
    }

    /**
     * Creates a pause popup menu.
     */
    private void createPausePopup() {
        //create popup container
        pausePopup = new Pane();
        pausePopup.setPrefSize(GameConfiguration.popupWidth, GameConfiguration.popupHeight);
        pausePopup.relocate(GameConfiguration.popupX, GameConfiguration.popupY);
        pausePopup.setBackground(new Background(new BackgroundImage(
                new Image("images/popupBackground.png", GameConfiguration.popupWidth,
                        GameConfiguration.popupHeight, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        //create graphical elements
        ImageView pauseMessage = new ImageView("images/pauseMessage.png");
        pauseMessage.relocate(0, 5);
        pausePopupMainMenuButton = new Button();
        pausePopupMainMenuButton.backgroundProperty().setValue(null);
        pausePopupMainMenuButton.graphicProperty().bind(
                Bindings.when(pausePopupMainMenuButton.hoverProperty())
                        .then(new ImageView("images/popup-home-button-hovered.png"))
                        .otherwise(new ImageView("images/popup-home-button.png"))
        );
        pausePopupMainMenuButton.relocate(40, 100);
        pausePopupExitButton = new Button();
        pausePopupExitButton.backgroundProperty().setValue(null);
        pausePopupExitButton.graphicProperty().bind(
                Bindings.when(pausePopupExitButton.hoverProperty())
                        .then(new ImageView("images/popup-exit-button-hovered.png"))
                        .otherwise(new ImageView("images/popup-exit-button.png"))
        );
        pausePopupExitButton.relocate(40, 170);
        pausePopupRestartButton = new Button();
        pausePopupRestartButton.backgroundProperty().setValue(null);
        pausePopupRestartButton.graphicProperty().bind(
                Bindings.when(pausePopupRestartButton.hoverProperty())
                        .then(new ImageView("images/popup-restart-button-hovered.png"))
                        .otherwise(new ImageView("images/popup-restart-button.png"))
        );
        pausePopupRestartButton.relocate(40, 240);
        pausePopupCloseButton = new Button();
        pausePopupCloseButton.backgroundProperty().setValue(null);
        pausePopupCloseButton.graphicProperty().bind(
                Bindings.when(pausePopupCloseButton.hoverProperty())
                        .then(new ImageView("images/close-button-hovered.png"))
                        .otherwise(new ImageView("images/close-button.png"))
        );
        pausePopupCloseButton.relocate(260, 5);

        //add graphical elements to popup container
        pausePopup.getChildren().add(pauseMessage);
        pausePopup.getChildren().add(pausePopupRestartButton);
        pausePopup.getChildren().add(pausePopupMainMenuButton);
        pausePopup.getChildren().add(pausePopupExitButton);
        pausePopup.getChildren().add(pausePopupCloseButton);
    }

    /**
     * Creates a settings popup menu.
     */
    private void createSettingsPopup() {
        //create popup container
        settingsPopup = new Pane();
        settingsPopup.setPrefSize(GameConfiguration.popupWidth, GameConfiguration.popupHeight);
        settingsPopup.relocate(GameConfiguration.popupX, GameConfiguration.popupY);
        settingsPopup.setBackground(new Background(new BackgroundImage(
                new Image("images/popupBackground.png", GameConfiguration.popupWidth,
                        GameConfiguration.popupHeight, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        //create graphical elements
        ImageView settingsMessage = new ImageView("images/settingsMessage.png");
        settingsMessage.relocate(0, 5);
        audioText = new Text("Sounds on/off: ");
        audioText.setFont(Font.font("Arial", 22));
        audioText.setFill(Color.YELLOW);
        audioText.setWrappingWidth(GameConfiguration.popupWidth);
        audioText.setTextAlignment(TextAlignment.LEFT);
        audioText.relocate(10, 80);
        specialText = new Text("Special balls on/off: ");
        specialText.setFont(Font.font("Arial", 22));
        specialText.setFill(Color.YELLOW);
        specialText.setWrappingWidth(GameConfiguration.popupWidth);
        specialText.setTextAlignment(TextAlignment.LEFT);
        specialText.relocate(10, 110);
        wallText = new Text("Asteroids on/off: ");
        wallText.setFont(Font.font("Arial", 22));
        wallText.setFill(Color.YELLOW);
        wallText.setWrappingWidth(GameConfiguration.popupWidth);
        wallText.setTextAlignment(TextAlignment.LEFT);
        wallText.relocate(10, 140);
        audioToggle = new ImageView("images/toggleOff.png");
        audioToggle.relocate(275, 86);
        specialToggle = new ImageView("images/toggleOff.png");
        specialToggle.relocate(275, 116);
        wallToggle = new ImageView("images/toggleOff.png");
        wallToggle.relocate(275, 146);
        settingsPopupCloseButton = new Button();
        settingsPopupCloseButton.backgroundProperty().setValue(null);
        settingsPopupCloseButton.graphicProperty().bind(
                Bindings.when(settingsPopupCloseButton.hoverProperty())
                        .then(new ImageView("images/close-button-hovered.png"))
                        .otherwise(new ImageView("images/close-button.png"))
        );
        settingsPopupCloseButton.relocate(260, 5);

        //add graphical elements to popup container
        settingsPopup.getChildren().add(settingsMessage);
        settingsPopup.getChildren().add(audioText);
        settingsPopup.getChildren().add(specialText);
        settingsPopup.getChildren().add(wallText);
        settingsPopup.getChildren().add(audioToggle);
        settingsPopup.getChildren().add(specialToggle);
        settingsPopup.getChildren().add(wallToggle);
        settingsPopup.getChildren().add(settingsPopupCloseButton);

        //checks for toggle sprites
        checkAllSettingsToggles();
    }

    /**
     * Checks all the settings option toggle and sets the sprites accordingly.
     */
    public void checkAllSettingsToggles() {
        checkSettingsAudioToggle();
        checkSettingsSpecialBallToggle();
        checkSettingsWallToggle();
    }

    /**
     * Checks whether a setting is toggled and not and updates toggle sprite accordingly.
     */
    public void checkSettingsAudioToggle() {
        if (GameConfiguration.sounds) {
            audioToggle.setImage(new Image("images/toggleOn.png"));
        }
        else {
            audioToggle.setImage(new Image("images/toggleOff.png"));
        }
    }

    /**
     * Checks whether a setting is toggled and not and updates toggle sprite accordingly.
     */
    public void checkSettingsWallToggle() {
        if (GameConfiguration.walls) {
            wallToggle.setImage(new Image("images/toggleOn.png"));
        }
        else {
            wallToggle.setImage(new Image("images/toggleOff.png"));
        }
    }

    /**
     * Checks whether a setting is toggled and not and updates toggle sprite accordingly.
     */
    public void checkSettingsSpecialBallToggle() {
        if (GameConfiguration.specialBalls) {
            specialToggle.setImage(new Image("images/toggleOn.png"));
        }
        else {
            specialToggle.setImage(new Image("images/toggleOff.png"));
        }
    }

    /**
     * Sets the game over popup to being visible and updates the score.
     */
    public void showGameOverPopup() {
        Platform.runLater(() -> {
            gameOverPopup.setVisible(true);
            gameOverPopup.toFront();
        });
    }

    /**
     * Sets the pause popup to being visible.
     */
    public void showPausePopup() {
        Platform.runLater(() -> {
            pausePopup.setVisible(true);
            pausePopup.toFront();
        });
    }

    /**
     * Sets the settings popup to being visible.
     */
    public void showSettingsPopup() {
        Platform.runLater(() -> {
            settingsPopup.setVisible(true);
            settingsPopup.toFront();
        });
    }

    /**
     * Sets the gam over popup to being invisible.
     */
    public void closeGameOverPopup() {
        Platform.runLater(() -> {
            gameOverPopup.setVisible(false);
        });
    }

    /**
     * Sets the gam over popup to being invisible.
     */
    public void closePausePopup() {
        Platform.runLater(() -> {
            pausePopup.setVisible(false);
        });
    }

    /**
     * Sets the gam over popup to being invisible.
     */
    public void closeSettingsPopup() {
        Platform.runLater(() -> {
            settingsPopup.setVisible(false);
        });
    }

    /**
     * @param cell the cell to calculate the screen coordinates of.
     * @return the calculate screen coordinate x.
     */
    private double getScreenX(Cell cell, Image im) {
        return (cell.getCurrentX() - (im.getWidth() / 2));
    }

    /**
     * @param cell the cell to calculate the screen coordinates of.
     * @return the calculate screen coordinate y.
     */
    private double getScreenY(Cell cell, Image im) {
        return (cell.getCurrentY() - (im.getHeight() / 2));
    }

    /**
     * @return play button.
     */
    public ImageView getPlayButton() {
        return playButton;
    }

    /**
     * @return exit button.
     */
    public ImageView getExitButton() {
        return exitButton;
    }

    /**
     * @return popup restart button.
     */
    public ImageView getPopupRestartButton() {
        return popupRestartButton;
    }

    /**
     * @return the popup home button.
     */
    public ImageView getPopupHomeButton() {
        return popupHomeButton;
    }

    /**
     * @param data sets a data.
     */
    public void setData(GameData data) {
        this.data = data;
    }

    /**
     * @param player sets the player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @param gamePane sets a new Pane.
     */
    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }

    /**
     * @return popup continue game button
     */
    public Button getPausePopupRestartButton() {
        return pausePopupRestartButton;
    }

    /**
     * @return popup main menu return button
     */
    public Button getPausePopupMainMenuButton() {
        return pausePopupMainMenuButton;
    }

    /**
     * @return popup exit game button
     */
    public Button getPausePopupExitButton() {
        return pausePopupExitButton;
    }

    /**
     * @return game settings button
     */
    public ImageView getGameSettingsIcon() {
        return gameSettingsIcon;
    }

    /**
     * @return game pause button
     */
    public ImageView getGamePauseIcon() {
        return gamePauseIcon;
    }

    /**
     * @return audio toggle button
     */
    public ImageView getAudioToggle() {
        return audioToggle;
    }

    /**
     * @return wall toggle button
     */
    public ImageView getWallToggle() {
        return wallToggle;
    }

    /**
     * @return special balls toggle button
     */
    public ImageView getSpecialToggle() {
        return specialToggle;
    }

    /**
     * @return settings popup menu close button
     */
    public Button getSettingsPopupCloseButton() {
        return settingsPopupCloseButton;
    }

    /**
     * @return pause popup menu close button
     */
    public Button getPausePopupCloseButton() {
        return pausePopupCloseButton;
    }
}
