package View;

import Controller.GameConfiguration;
import Model.Cell;
import Model.GameData;
import Model.MultiplierBall;
import Model.Player;
import Utility.SetTimeout;
import javafx.application.Platform;
import javafx.scene.Node;
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
    private Pane gamePane;
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
    private Pane pausePopup;
    private ImageView pausePopupRestartButton;
    private ImageView pausePopupMainMenuButton;
    private ImageView pausePopupExitButton;
    private ImageView pausePopupCloseButton;
    private Pane settingsPopup;
    private Text audioText;
    private ImageView audioToggle;
    private Text wallText;
    private ImageView wallToggle;
    private Text specialText;
    private ImageView specialToggle;
    private ImageView settingsPopupCloseButton;

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
        createHover(gamePauseIcon, new Image("images/pause-icon.png"),
                new Image("images/pause-icon-hovered.png"));
        gamePauseIcon.relocate(495, 10);
        gamePauseIcon.fitHeightProperty().setValue(48);
        gamePauseIcon.fitWidthProperty().setValue(48);
        gameSettingsIcon = new ImageView("images/settings-icon.png");
        createHover(gameSettingsIcon, new Image("images/settings-icon.png"),
                new Image("images/settings-icon-hovered.png"));
        gameSettingsIcon.relocate(550, 10);
        gameSettingsIcon.fitHeightProperty().setValue(46);
        gameSettingsIcon.fitWidthProperty().setValue(46);

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

            Image spritePlayerBall;
            Image spriteNextBall;
            if (data.getPlayer().getPlayerBall() instanceof MultiplierBall) {
                spritePlayerBall = new Image("images/multiplier "
                        + data.getPlayer().getPlayerBall().getColor() + " ball.png");
                playerBallImageView.setImage(spritePlayerBall);
            } else {
                spritePlayerBall = new Image("images/"
                        + data.getPlayer().getPlayerBall().getColor() + " ball.png");
                playerBallImageView.setImage(spritePlayerBall);
            }
            if (data.getPlayer().getNextBall() instanceof MultiplierBall) {
                spriteNextBall = new Image("images/multiplier "
                        + data.getPlayer().getNextBall().getColor() + " ball.png");
                nextBallImageView.setImage(spriteNextBall);
            } else {
                spriteNextBall = new Image("images/"
                        + data.getPlayer().getNextBall().getColor() + " ball.png");
                nextBallImageView.setImage(spriteNextBall);
            }

            playerBallImageView.relocate(data.getPlayer().getPlayerBall().getX()
                            - spritePlayerBall.getWidth() / 2,
                    data.getPlayer().getPlayerBall().getY() - spritePlayerBall.getHeight() / 2);
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
     * This method displays a plus amount icon in the places where balls have been removed.
     * @param c the cell where a plus amount should be displayed
     * @param amount the amount to be displayed
     */
    public void displayPlusIcon(Cell c, int amount) {
        Platform.runLater(() -> {
            BallImageView plusIcon = new BallImageView(
                    new Image("images/plus" + amount + ".png"), c, true);
            plusIcon.relocate(c.getCurrentX(), c.getCurrentY());

            gamePane.getChildren().add(plusIcon);
            RemovePlusOneIcon r = new RemovePlusOneIcon(plusIcon, gamePane);

            SetTimeout t = new SetTimeout("Timeout Thread", 1000, r);
            t.start();
        });
    }

    /**
     * This method is used to display a ball on the GUI.
     * @param c the cell in which the ball is located
     */
    public void display(Cell c) {
        if (c != null && c.getBall() != null) {
            if (c.getBall().isCenterPiece()) {
                changePlayerBallImageView(new Image("images/center.png"), c);
            }
            else if (c.getBall().isNormalBall()) {
                changePlayerBallImageView(new Image("images/" + c.getBall().getColor()
                        + " ball.png"), c);
            }
            else if (c.getBall().isExplosiveBall()) {
                changePlayerBallImageView(new Image("images/explosive ball.png"), c);
            }
            else if (c.getBall().isRainbowBall()) {
                changePlayerBallImageView(new Image("images/rainbow ball.png"), c);
            }
            else if (c.getBall().isMultiplierBall()) {
                changePlayerBallImageView(new Image("images/multiplier " + c.getBall().getColor()
                        + " ball.png"), c);
            }
        }
    }

    /**
     * Sets a new player ball image view.
     * @param image The image to set in the ball image view
     * @param c the cell c in which the ball is cntained
     */
    private void changePlayerBallImageView(Image image, Cell c) {
        Platform.runLater(() -> {
            BallImageView biv = new BallImageView(image, c, false);
            gamePane.getChildren().add(biv);
            biv.relocate(getScreenX(c, biv.getImage()), getScreenY(c, biv.getImage()));
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
        pausePopupMainMenuButton = new ImageView("images/popup-home-button.png");
        createHover(pausePopupMainMenuButton, new Image("images/popup-home-button.png"),
                new Image("images/popup-home-button-hovered.png"));
        pausePopupMainMenuButton.relocate(50, 105);
        pausePopupExitButton = new ImageView("images/popup-exit-button.png");
        createHover(pausePopupExitButton, new Image("images/popup-exit-button.png"),
                new Image("images/popup-exit-button-hovered.png"));
        pausePopupExitButton.relocate(50, 180);
        pausePopupRestartButton = new ImageView("images/popup-restart-button.png");
        createHover(pausePopupRestartButton, new Image("images/popup-restart-button.png"),
                new Image("images/popup-restart-button-hovered.png"));
        pausePopupRestartButton.relocate(50, 255);
        pausePopupCloseButton = new ImageView("images/close-button.png");
        createHover(pausePopupCloseButton, new Image("images/close-button.png"),
                new Image("images/close-button-hovered.png"));
        pausePopupCloseButton.relocate(270, 8);

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
        settingsPopupCloseButton = new ImageView("images/close-button.png");
        createHover(settingsPopupCloseButton, new Image("images/close-button.png"),
                new Image("images/close-button-hovered.png"));
        settingsPopupCloseButton.relocate(270, 8);

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
     * @param icon the icon to be hovered
     * @param normal the unhovered sprite of the icon
     * @param hovered the hovered sprite of the icon
     */
    private void createHover(ImageView icon, Image normal, Image hovered) {
        icon.setOnMouseEntered(event -> {
            icon.setImage(hovered);
        });
        icon.setOnMouseExited(event -> {
            icon.setImage(normal);
        });
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
    public ImageView getPausePopupRestartButton() {
        return pausePopupRestartButton;
    }

    /**
     * @return popup main menu return button
     */
    public ImageView getPausePopupMainMenuButton() {
        return pausePopupMainMenuButton;
    }

    /**
     * @return popup exit game button
     */
    public ImageView getPausePopupExitButton() {
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
    public ImageView getSettingsPopupCloseButton() {
        return settingsPopupCloseButton;
    }

    /**
     * @return pause popup menu close button
     */
    public ImageView getPausePopupCloseButton() {
        return pausePopupCloseButton;
    }
}
