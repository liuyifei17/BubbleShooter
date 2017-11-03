package View;

import Model.Cell;
import Model.GameData;
import Model.Player;
import Model.Score;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The view class.
 */
public class View implements Observer {

    private GameData data;
    private Player player;
    private MainMenu mainMenu;
    private RankingMenu rankingMenu;
    private GameMenu gameMenu;
    private Pane mainMenuPane;
    private Pane gamePane;
    private Pane rankingPane;

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
     * @param o the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o == player) {
            getGameOverPopup().setScore("Score: " + player.getScore());
            gameMenu.getScoreBarScore().setText("Score: " + player.getScore());
        }
        if (o == data) {
            updateRanking(data.getScores(), rankingMenu.getRanks());
            updateRanking(data.getScores(), gameMenu.getRankingPopup().getRanks());
        }
    }

    /**
     * Creates the menus.
     */
    public void createMenus() {
        mainMenu = new MainMenu(this, mainMenuPane);
        mainMenu.drawMenu();
        rankingMenu = new RankingMenu(this, rankingPane);
        rankingMenu.drawMenu();
        updateRanking(data.getScores(), rankingMenu.getRanks());
        gameMenu = new GameMenu(this, gamePane, data);
        gameMenu.drawMenu();
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
     * @param scores the scores to set.
     */
    public void updateRanking(ArrayList<Score> scores, ArrayList<Text> ranks) {
        if(scores.size() > 0) {
            for (int i = 0; i < scores.size(); i++) {
                Text t = ranks.get(i);
                t.setText(i + 1 + ".    " + scores.get(i).getDate().toString().substring(4, 16)
                        + "             " + scores.get(i).getScore());
            }
        }
        else {
            for(int i = 0; i < 10; i++) {
                ranks.get(i).setText(i + 1 + ".");
            }
        }
    }

    /**
     * @param cell the cell to calculate the screen coordinates of.
     * @param im the image to which the coordinates apply.
     * @return the calculate screen coordinate x.
     */
    public double getScreenX(Cell cell, Image im) {
        return (cell.getCurrentX() - (im.getWidth() / 2));
    }

    /**
     * @param cell the cell to calculate the screen coordinates of.
     * @param im the image to which the coordinates apply.
     * @return the calculate screen coordinate y.
     */
    public double getScreenY(Cell cell, Image im) {
        return (cell.getCurrentY() - (im.getHeight() / 2));
    }

    /**
     * @return the main menu.
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * @return the ranking menu.
     */
    public RankingMenu getRankingMenu() {
        return rankingMenu;
    }

    /**
     * @return the game menu.
     */
    public GameMenu getGameMenu() {
        return gameMenu;
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
     * @return the game over popup.
     */
    public GameOverPopup getGameOverPopup() {
        return gameMenu.getGameOverPopup();
    }

    /**
     * @return the pause popup.
     */
    public PausePopup getPausePopup() {
        return gameMenu.getPausePopup();
    }

    /**
     * @return the settings popup.
     */
    public SettingsPopup getSettingsPopup() {
        return gameMenu.getSettingsPopup();
    }

    /**
     * @return the score popup.
     */
    public RankingPopup getRankingPopup() {
        return gameMenu.getRankingPopup();
    }
}
