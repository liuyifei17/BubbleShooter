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
    private Pane rankingPane;
    private ImageView gameSettingsIcon;
    private ImageView gamePauseIcon;
    private ImageView gameRankingIcon;
    private Image mainMenuBg;
    private Image rankingBg;
    private ImageView playButton;
    private ImageView rankingButton;
    private ImageView settingsButton;
    private ImageView exitButton;
    private Image gameBg;
    private ImageView topBar;
    private ImageView scoreBar;
    private Text scoreBarScore;
    private ImageView playerBallImageView;
    private ImageView nextBallImageView;
    private ImageView firstWall;
    private ImageView secondWall;
    private ImageView thirdWall;
    private GameOverPopup gameOverPopup;
    private PausePopup pausePopup;
    private SettingsPopup settingsPopup;
    private RankingPopup rankingPopup;

    /**
     * @param mainMenuPane sets the main menu pane
     * @param rankingPane sets the ranking pane
     * @param gamePane sets the game pane
     * @param data sets the game data
     * @param player sets the player
     */
    public View(Pane mainMenuPane, Pane rankingPane, Pane gamePane, GameData data, Player player) {
        this.mainMenuPane = mainMenuPane;
        this.rankingPane = rankingPane;
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
            gameOverPopup.setScore("Score: " + player.getScore());
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
        playButton.relocate(184, 215);
        playButton.fitWidthProperty().setValue(250);
        playButton.fitHeightProperty().setValue(80);
        rankingButton = new ImageView("images/ranking-button.png");
        rankingButton.relocate(184, 310);
        rankingButton.fitWidthProperty().setValue(250);
        rankingButton.fitHeightProperty().setValue(80);
        exitButton = new ImageView("images/exit-button.png");
        exitButton.relocate(184, 405);
        exitButton.fitWidthProperty().setValue(250);
        exitButton.fitHeightProperty().setValue(80);

        //add components to main menu
        mainMenuPane.getChildren().add(playButton);
        mainMenuPane.getChildren().add(rankingButton);
        mainMenuPane.getChildren().add(exitButton);
    }

    /**
     * draws up the rankings screen.
     */
    public void drawRankings() {
        //draw background
        rankingBg = new Image("images/main-menu-bg.png");
        rankingPane.setBackground(new Background(
                new BackgroundImage(mainMenuBg, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT)));

        //add components to main menu
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
        gamePauseIcon.relocate(440, 10);
        gamePauseIcon.fitHeightProperty().setValue(48);
        gamePauseIcon.fitWidthProperty().setValue(48);
        gameSettingsIcon = new ImageView("images/settings-icon.png");
        createHover(gameSettingsIcon, new Image("images/settings-icon.png"),
                new Image("images/settings-icon-hovered.png"));
        gameSettingsIcon.relocate(495, 10);
        gameSettingsIcon.fitHeightProperty().setValue(46);
        gameSettingsIcon.fitWidthProperty().setValue(46);
        gameRankingIcon = new ImageView("images/score-icon.png");
        createHover(gameRankingIcon, new Image("images/score-icon.png"),
                new Image("images/score-icon-hovered.png"));
        gameRankingIcon.relocate(550, 14);
        gameRankingIcon.fitHeightProperty().setValue(40);
        gameRankingIcon.fitWidthProperty().setValue(40);

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
        gameOverPopup = new GameOverPopup(this, new Pane());
        pausePopup = new PausePopup(this, new Pane());
        settingsPopup = new SettingsPopup(this, new Pane());
        rankingPopup = new RankingPopup(this, new Pane());

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
        gamePane.getChildren().add(gameRankingIcon);
        gamePane.getChildren().add(gameOverPopup.getPopup());
        gamePane.getChildren().add(pausePopup.getPopup());
        gamePane.getChildren().add(settingsPopup.getPopup());
        gamePane.getChildren().add(rankingPopup.getPopup());
        gameOverPopup.getPopup().setVisible(false);
        pausePopup.getPopup().setVisible(false);
        settingsPopup.getPopup().setVisible(false);
        rankingPopup.getPopup().setVisible(false);
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

            removeWalls();
            placeWalls();
        });
    }

    /**
     * Place the walls based on the size on the screen.
     */
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

    /**
     * remove the walls if they have been put off and some cant be used.
     */
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
     * @param icon the icon to be hovered
     * @param normal the unhovered sprite of the icon
     * @param hovered the hovered sprite of the icon
     */
    public void createHover(ImageView icon, Image normal, Image hovered) {
        icon.setOnMouseEntered(event -> {
            icon.setImage(hovered);
        });
        icon.setOnMouseExited(event -> {
            icon.setImage(normal);
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
     * @return ranking button.
     */
    public ImageView getRankingButton() {
        return rankingButton;
    }

    /**
     * @return settings button.
     */
    public ImageView getSettingsButton() {
        return settingsButton;
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
     * @return game scores button
     */
    public ImageView getGameRankingIcon() {
        return gameRankingIcon;
    }

    /**
     * @return the game over popup.
     */
    public GameOverPopup getGameOverPopup() {
        return gameOverPopup;
    }

    /**
     * @return the pause popup.
     */
    public PausePopup getPausePopup() {
        return pausePopup;
    }

    /**
     * @return the settings popup.
     */
    public SettingsPopup getSettingsPopup() {
        return settingsPopup;
    }

    /**
     * @return the score popup.
     */
    public RankingPopup getRankingPopup() {
        return rankingPopup;
    }
}
