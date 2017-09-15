package View;

import Model.Ball;
import Model.Cell;
import Model.GameData;
import Utility.SetTimeout;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

/**
 * The view class.
 */
public class View {

    public static final double STAGE_WIDTH = 600;
    public static final double STAGE_HEIGHT = 700;
    public static final int TOP_BAR_HEIGHT = 70;
    private static final int SCORE_BAR_HEIGHT = 40;
    private static final int SCORE_BAR_WIDTH = 240;
    private static final int POPUP_WIDTH = 300;
    private static final int POPUP_HEIGHT = 360;
    private static final int POPUP_X = 150;
    private static final int POPUP_Y = 200;
    private GameData data;
    private Pane gamePane;
    private Pane mainMenuPane;
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
    private ImageView playerBall;
    private ImageView nextBall;

    /**
     * @param mainMenuPane sets the main menu pane
     * @param gamePane sets the game pane
     * @param data sets the game data
     */
    public View(Pane mainMenuPane, Pane gamePane, GameData data) {
        this.mainMenuPane = mainMenuPane;
        this.gamePane = gamePane;
        this.data = data;
    }

    /**
     * draws up the main menu screen.
     */
    public void drawMainMenu() {
        //draw background
        mainMenuBg = new Image("images/main-menu-bg.png");
        mainMenuPane.setBackground(new Background(
                new BackgroundImage(mainMenuBg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

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
        topBar.setFitHeight(TOP_BAR_HEIGHT);

        //draw score bar
        scoreBar = new ImageView("images/scoreBar1.png");
        scoreBar.relocate(8, 15);
        scoreBar.setFitHeight(SCORE_BAR_HEIGHT);
        scoreBar.setFitWidth(SCORE_BAR_WIDTH);

        //draw score text
        scoreBarScore = new Text("Score: 0");
        scoreBarScore.setFont(Font.font("Arial", 25));
        scoreBarScore.relocate(15, 22);
        scoreBarScore.setFill(Color.YELLOW);

        //draw entities
        for (Cell c : data.getGrid().getOccupiedCells()) {
            c.getElement().getImageView().relocate(getScreenX(c), getScreenY(c));
        }

        nextBall = new ImageView(data.getPlayer().getNextBall().getSprite());
        nextBall.relocate(View.STAGE_WIDTH / 2
                        - data.getPlayer().getNextBall().getSprite().getWidth() / 4,
                View.TOP_BAR_HEIGHT - 30);
        nextBall.setFitWidth(data.getPlayer().getNextBall().getSprite().getWidth() / 2);
        nextBall.setFitHeight(data.getPlayer().getNextBall().getSprite().getHeight() / 2);

        playerBall = new ImageView(data.getPlayer().getPlayerBall().getImage());
        playerBall.relocate(data.getPlayer().getPlayerBall().getX()
                        - data.getPlayer().getPlayerBall().getImage().getWidth()
                        / 2,
                data.getPlayer().getPlayerBall().getY()
                        - data.getPlayer().getPlayerBall().getImage().getHeight() / 2);

        //create popup on game over
        createGameOverPopup();

        //add components to game pane
        gamePane.getChildren().add(topBar);
        //gamePane.getChildren().add(scoreBar);
        for (Cell c : data.getGrid().getCells()) {
            gamePane.getChildren().add(c.getElement().getImageView());
        }
        gamePane.getChildren().add(playerBall);
        gamePane.getChildren().add(nextBall);
        gamePane.getChildren().add(scoreBarScore);
        gamePane.getChildren().add(gameOverPopup);
        gameOverPopup.setVisible(false);
    }

    /**
     * redraws the screen elements, which is mainly used for animations.
     */
    public void redraw() {
        //check for changed cells and update children
        ArrayList<Cell> cells = data.getGrid().getOccupiedCells();

        //relocate elements
        for (Cell c : cells) {
            if (c.getElement().getSprite() == null) {
                continue;
            }
            c.getElement().getImageView().relocate(getScreenX(c), getScreenY(c));
            c.getElement().getImageView().rotateProperty().setValue(data.getGrid().getRotation());
        }

        playerBall.setImage(data.getPlayer().getPlayerBall().getImage());
        nextBall.setImage(data.getPlayer().getNextBall().getSprite());

        playerBall.relocate(data.getPlayer().getPlayerBall().getX()
                        - data.getPlayer().getPlayerBall().getImage().getWidth() / 2,
                data.getPlayer().getPlayerBall().getY()
                        - data.getPlayer().getPlayerBall().getImage().getHeight() / 2);

        scoreBarScore.setText("Score: " + data.getPlayer().getScore());
    }

    public void removeBall(Cell c) {
        c.getElement().setImage(new Image("images/plus1.png"));
        if (c.getElement() instanceof Ball) {
            ((Ball) c.getElement()).setColor(null);
        }

        removePlusOneIcon r = new removePlusOneIcon(c);

        SetTimeout t = new SetTimeout("Timeout Thread", 1000, r);
        t.start();
    }

    public void display(Cell c) {
        c.getElement().getImageView().relocate(getScreenX(c), getScreenY(c));
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
        gameOverPopup.setPrefSize(POPUP_WIDTH, POPUP_HEIGHT);
        gameOverPopup.relocate(POPUP_X, POPUP_Y);
        gameOverPopup.setBackground(new Background(new BackgroundImage(
                new Image("images/gameOverPopupBg.png", POPUP_WIDTH, POPUP_HEIGHT, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        //create graphical elements
        ImageView gameOverMessage = new ImageView("images/gameOverMessage.png");
        gameOverMessage.relocate(0, 20);
        popupScore = new Text("Score: 0");
        popupScore.setFont(Font.font("Arial", 35));
        popupScore.setWrappingWidth(POPUP_WIDTH);
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
     * Sets the game over popup to being visible and updates the score.
     */
    public void showGameOverPopup() {
        popupScore.setText("Score: " + data.getPlayer().getScore());
        gameOverPopup.setVisible(true);
    }

    /**
     * Sets the gam over popup to being invisible.
     */
    public void closeGameOverPopup() {
        gameOverPopup.setVisible(false);
    }

    /**
     * @param cell the cell to calculate the screen coordinates of.
     * @return the calculate screen coordinate x.
     */
    private double getScreenX(Cell cell) {
        return (cell.getCurrentX() - (cell.getElement().getSprite().getWidth() / 2));
    }

    /**
     * @param cell the cell to calculate the screen coordinates of.
     * @return the calculate screen coordinate y.
     */
    private double getScreenY(Cell cell) {
        return (cell.getCurrentY() - (cell.getElement().getSprite().getHeight() / 2));
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
     * @param gamePane sets a new Pane.
     */
    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }

    class removePlusOneIcon implements Runnable {
        private Cell cell;

        public removePlusOneIcon(Cell c) {
            this.cell = c;
        }

        public void run() {
            this.cell.getElement().setImage(null);
            data.getGrid().getOccupiedCells().remove(this.cell);
        }
    }

}