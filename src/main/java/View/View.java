package View;

import Controller.GameConfiguration;
import Model.Cell;
import Model.GameData;
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
    protected Pane gamePane;
    private Pane mainMenuPane;
    private ImageView gameSettingsIcon;
    private ImageView gamePauseIcon;
    private Image mainMenuBg;
    private ImageView playButton;
    private ImageView exitButton;
    private ImageView musicButton;
    private ImageView settingsButton;
    private Image musicIconMax;
    private Image musicIconMin;
    private ImageView musicIcon;
    private ImageView settingsIcon;
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
    private ImageView popupContinueButton;
    private ImageView popupMainMenuButton;
    private ImageView popupExitButton;

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
        settingsButton = new ImageView("images/backButton.png");
        settingsButton.relocate(20, 600);
        settingsButton.fitWidthProperty().setValue(100);
        settingsButton.fitHeightProperty().setValue(100);
        musicButton = new ImageView("images/backButton.png");
        musicButton.relocate(490, 600);
        musicButton.fitWidthProperty().setValue(100);
        musicButton.fitHeightProperty().setValue(100);
        settingsIcon = new ImageView("images/settings-icon.png");
        settingsIcon.relocate(40, 620);
        settingsIcon.fitWidthProperty().setValue(60);
        settingsIcon.fitHeightProperty().setValue(60);
        musicIconMax = new Image("images/sound-icon-max.png");
        musicIconMin = new Image("images/sound-icon-min.png");
        musicIcon = new ImageView(musicIconMax);
        musicIcon.relocate(510, 620);
        musicIcon.fitWidthProperty().setValue(60);
        musicIcon.fitHeightProperty().setValue(60);

        //add components to main menu
        mainMenuPane.getChildren().add(playButton);
        mainMenuPane.getChildren().add(exitButton);
        mainMenuPane.getChildren().add(settingsButton);
        mainMenuPane.getChildren().add(musicButton);
        mainMenuPane.getChildren().add(settingsIcon);
        mainMenuPane.getChildren().add(musicIcon);
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
        gameOverPopup.setVisible(false);
        pausePopup.setVisible(false);
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
     * @param volume the music volume based on which the music icon is changed
     */
    public void changeMusicButton(int volume) {
        if (volume == 100) {
            musicIcon.setImage(musicIconMin);
        }
        else if (volume == 0) {
            musicIcon.setImage(musicIconMax);
        }
        musicIcon.relocate(510, 620);
        musicIcon.fitWidthProperty().setValue(60);
        musicIcon.fitHeightProperty().setValue(60);
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
                new Image("images/gameOverPopupBg.png", GameConfiguration.popupWidth,
                        GameConfiguration.popupHeight, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        //create graphical elements
        ImageView gameOverMessage = new ImageView("images/gameOverMessage.png");
        gameOverMessage.relocate(0, 20);
        popupScore = new Text("Score: 0");
        popupScore.setFont(Font.font("Arial", 35));
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
     * Creates a game over popup menu.
     */
    private void createPausePopup() {
        //create popup container
        pausePopup = new Pane();
        pausePopup.setPrefSize(GameConfiguration.popupWidth, GameConfiguration.popupHeight);
        pausePopup.relocate(GameConfiguration.popupX, GameConfiguration.popupY);
        pausePopup.setBackground(new Background(new BackgroundImage(
                new Image("images/gameOverPopupBg.png", GameConfiguration.popupWidth,
                        GameConfiguration.popupHeight, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        //create graphical elements
        ImageView pauseMessage = new ImageView("images/pauseMessage.png");
        pauseMessage.relocate(0, 20);
        popupContinueButton = new ImageView("images/restart-icon.png");
        popupContinueButton.relocate(5, 260);
        popupExitButton = new ImageView("images/exit-icon.png");
        popupExitButton.relocate(100, 260);
        popupMainMenuButton = new ImageView("images/home-icon.png");
        popupMainMenuButton.relocate(198, 260);

        //add graphical elements to popup container
        pausePopup.getChildren().add(pauseMessage);
        pausePopup.getChildren().add(popupContinueButton);
        pausePopup.getChildren().add(popupMainMenuButton);
        pausePopup.getChildren().add(popupExitButton);
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
     * Sets the gam over popup to being invisible.
     */
    public void closeGameOverPopup() {
        gameOverPopup.setVisible(false);
    }

    /**
     * Sets the gam over popup to being invisible.
     */
    public void closePausePopup() {
        pausePopup.setVisible(false);
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
     * @return music button.
     */
    public ImageView getMusicButton() {
        return musicButton;
    }

    /**
     * @return music icon.
     */
    public ImageView getMusicIcon() {
        return musicIcon;
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
    public ImageView getPopupContinueButton() {
        return popupContinueButton;
    }

    /**
     * @return popup main menu return button
     */
    public ImageView getPopupMainMenuButton() {
        return popupMainMenuButton;
    }

    /**
     * @return popup exit game button
     */
    public ImageView getPopupExitButton() {
        return popupExitButton;
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
}