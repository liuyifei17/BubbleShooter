package View;

import Controller.GUIConfiguration;
import Controller.GameConfiguration;
import Model.MultiplierBall;
import Model.Cell;
import Model.GameData;
import Utility.SetTimeout;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The menu class.
 */
public class GameMenu extends Menu {

    private GameData data;
    private ImageView settingsIcon;
    private ImageView pauseIcon;
    private ImageView rankingIcon;
    private Image background;
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
     *  the menu constructor.
     * @param view the view to set.
     * @param menuPane the menu pane to set.
     * @param data the game data to set.
     */
    public GameMenu(View view, Pane menuPane, GameData data) {
        super(view, menuPane);
        this.data = data;
    }

    /**
     * draws up the main menu screen.
     */
    @Override
    public void drawMenu() {
        //draw background
        background = new Image("images/background2.png");
        getMenuPane().setBackground(new Background(
                new BackgroundImage(background,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        //draw top bar
        topBar = new ImageView("images/topBar1.png");
        topBar.relocate(0, 0);
        topBar.fitWidthProperty().bind(getMenuPane().widthProperty());
        topBar.setFitHeight(GUIConfiguration.topBarHeight);

        //draw score bar
        scoreBar = new ImageView("images/scoreBar1.png");
        scoreBar.relocate(8, 15);
        scoreBar.setFitHeight(GUIConfiguration.scoreBarHeight);
        scoreBar.setFitWidth(GUIConfiguration.scoreBarWidth);

        //draw score text
        scoreBarScore = new Text("Score: 0");
        scoreBarScore.setFont(Font.font("Arial", 25));
        scoreBarScore.relocate(15, 22);
        scoreBarScore.setFill(Color.YELLOW);

        //draw button icons
        pauseIcon = new ImageView("images/pause-icon.png");
        getView().createHover(pauseIcon, new Image("images/pause-icon.png"),
                new Image("images/pause-icon-hovered.png"));
        pauseIcon.relocate(440, 10);
        pauseIcon.fitHeightProperty().setValue(48);
        pauseIcon.fitWidthProperty().setValue(48);
        settingsIcon = new ImageView("images/settings-icon.png");
        getView().createHover(settingsIcon, new Image("images/settings-icon.png"),
                new Image("images/settings-icon-hovered.png"));
        settingsIcon.relocate(495, 10);
        settingsIcon.fitHeightProperty().setValue(46);
        settingsIcon.fitWidthProperty().setValue(46);
        rankingIcon = new ImageView("images/score-icon.png");
        getView().createHover(rankingIcon, new Image("images/score-icon.png"),
                new Image("images/score-icon-hovered.png"));
        rankingIcon.relocate(550, 14);
        rankingIcon.fitHeightProperty().setValue(40);
        rankingIcon.fitWidthProperty().setValue(40);

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
        nextBallImageView.relocate(GUIConfiguration.stageWidth / 2
                - spriteNextBall.getWidth() / 4, GUIConfiguration.topBarHeight - 30);
        nextBallImageView.setFitWidth(spriteNextBall.getWidth() / 2);
        nextBallImageView.setFitHeight(spriteNextBall.getHeight() / 2);


        Image spritePlayerBall =
                new Image("images/" + data.getPlayer().getPlayerBall().getColor() + " ball.png");
        playerBallImageView = new ImageView(spritePlayerBall);
        playerBallImageView.relocate(data.getPlayer().getPlayerBall().getX()
                        - spritePlayerBall.getWidth() / 2,
                data.getPlayer().getPlayerBall().getY() - spritePlayerBall.getHeight() / 2);

        //create popup menu's
        gameOverPopup = new GameOverPopup(getView(), new Pane());
        pausePopup = new PausePopup(getView(), new Pane());
        settingsPopup = new SettingsPopup(getView(), new Pane());
        rankingPopup = new RankingPopup(getView(), new Pane());

        //add components to game pane
        getMenuPane().getChildren().add(topBar);
        getMenuPane().getChildren().add(playerBallImageView);
        getMenuPane().getChildren().add(nextBallImageView);
        getMenuPane().getChildren().add(firstWall);
        getMenuPane().getChildren().add(secondWall);
        getMenuPane().getChildren().add(thirdWall);
        getMenuPane().getChildren().add(scoreBarScore);
        getMenuPane().getChildren().add(pauseIcon);
        getMenuPane().getChildren().add(settingsIcon);
        getMenuPane().getChildren().add(rankingIcon);
        getMenuPane().getChildren().add(gameOverPopup.getPopup());
        getMenuPane().getChildren().add(pausePopup.getPopup());
        getMenuPane().getChildren().add(settingsPopup.getPopup());
        getMenuPane().getChildren().add(rankingPopup.getPopup());
        gameOverPopup.getPopup().setVisible(false);
        pausePopup.getPopup().setVisible(false);
        settingsPopup.getPopup().setVisible(false);
        rankingPopup.getPopup().setVisible(false);
        getView().updateRanking(data.getScores(), rankingPopup.getRanks());
    }

    /**
     * redraws the screen elements, which is mainly used for animations.
     */
    public void redraw() {
        Platform.runLater(() -> {

            //relocate elements
            for (Node node : getMenuPane().getChildren()) {
                if (node instanceof BallImageView) {
                    BallImageView biv = (BallImageView) node;
                    biv.relocate(getView().getScreenX(biv.getCell(), biv.getImage()),
                            getView().getScreenY(biv.getCell(), biv.getImage()));
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
     * This method removes the image of a ball from the cell and from the GUI.
     * @param c the cell whose image needs to be removed
     */
    public void removeBall(Cell c) {
        Platform.runLater(() -> {
            for (Node e : getMenuPane().getChildren()) {
                if (e instanceof BallImageView && ((BallImageView) e).getCell().equals(c)) {
                    getMenuPane().getChildren().remove(e);
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

            getMenuPane().getChildren().add(plusIcon);
            RemovePlusOneIcon r = new RemovePlusOneIcon(plusIcon, getMenuPane());

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
            getMenuPane().getChildren().add(biv);
            biv.relocate(getView().getScreenX(c, biv.getImage()),
                    getView().getScreenY(c, biv.getImage()));
        });
    }

    /**
     * Place the walls based on the size on the screen.
     */
    public void placeWalls() {
        if (data.getRandomWalls().size() == 3 && GameConfiguration.walls) {
            firstWall.relocate(data.getRandomWalls().get(0).getX() - GUIConfiguration.wallWidth,
                    data.getRandomWalls().get(0).getY() - GUIConfiguration.wallHeight);
            secondWall.relocate(data.getRandomWalls().get(1).getX() - GUIConfiguration.wallWidth,
                    data.getRandomWalls().get(1).getY() - GUIConfiguration.wallHeight);
            thirdWall.relocate(data.getRandomWalls().get(2).getX() - GUIConfiguration.wallWidth,
                    data.getRandomWalls().get(2).getY() - GUIConfiguration.wallHeight);

            firstWall.rotateProperty().setValue(data.getRandomWalls().get(0).getRotation());
            secondWall.rotateProperty().setValue(data.getRandomWalls().get(1).getRotation());
            thirdWall.rotateProperty().setValue(data.getRandomWalls().get(2).getRotation());

            firstWall.setVisible(true);
            secondWall.setVisible(true);
            thirdWall.setVisible(true);
        }
        if (data.getRandomWalls().size() == 2 && GameConfiguration.walls) {
            firstWall.relocate(data.getRandomWalls().get(0).getX() - GUIConfiguration.wallWidth,
                    data.getRandomWalls().get(0).getY() - GUIConfiguration.wallHeight);
            secondWall.relocate(data.getRandomWalls().get(1).getX() - GUIConfiguration.wallWidth,
                    data.getRandomWalls().get(1).getY() - GUIConfiguration.wallHeight);

            firstWall.rotateProperty().setValue(data.getRandomWalls().get(0).getRotation());
            secondWall.rotateProperty().setValue(data.getRandomWalls().get(1).getRotation());

            firstWall.setVisible(true);
            secondWall.setVisible(true);
        }
        if (data.getRandomWalls().size() == 1 && GameConfiguration.walls) {
            firstWall.relocate(data.getRandomWalls().get(0).getX() - GUIConfiguration.wallWidth,
                    data.getRandomWalls().get(0).getY() - GUIConfiguration.wallHeight);

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
     * @return game settings button
     */
    public ImageView getSettingsIcon() {
        return settingsIcon;
    }

    /**
     * @return game pause button
     */
    public ImageView getPauseIcon() {
        return pauseIcon;
    }

    /**
     * @return game scores button
     */
    public ImageView getRankingIcon() {
        return rankingIcon;
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

    /**
     * @return the score bar score text.
     */
    public Text getScoreBarScore() {
        return scoreBarScore;
    }

    /**
     * @param data sets the game data.
     */
    public void setData(GameData data) {
        this.data = data;
    }

}
