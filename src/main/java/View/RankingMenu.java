package View;

import Controller.GUIConfiguration;
import Model.Score;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

/**
 * The menu class.
 */
public class RankingMenu extends Menu {

    private Image background;
    private ImageView homeButton;
    private ImageView resetButton;
    private ArrayList<Text> ranks;
    private Text rankOne;
    private Text rankTwo;
    private Text rankThree;
    private Text rankFour;
    private Text rankFive;
    private Text rankSix;
    private Text rankSeven;
    private Text rankEight;
    private Text rankNine;
    private Text rankTen;

    /**
     *  the menu constructor.
     * @param view the view to set.
     * @param menuPane the menu pane to set.
     */
    public RankingMenu(View view, Pane menuPane) {
        super(view, menuPane);
    }

    /**
     * Creates the menu.
     */
    @Override
    public void drawMenu() {
        //draw background
        background = new Image("images/background2.png");
        getMenuPane().setBackground(new Background(
                new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));

        ImageView rankingMessage = new ImageView("images/ranking-text.png");
        rankingMessage.relocate(0, 0);
        ImageView sidebar1 = new ImageView("images/sidebar.png");
        sidebar1.relocate(0, 0);
        ImageView sidebar2 = new ImageView("images/sidebar.png");
        sidebar1.relocate(GUIConfiguration.stageWidth - sidebar2.getImage().getWidth(), 0);
        ImageView bottombar = new ImageView("images/bottombar.png");
        bottombar.relocate(0, GUIConfiguration.stageHeight - bottombar.getImage().getHeight());
        int xText = 10;
        rankOne = createScoreTextStage("1.", xText, 105);
        rankTwo = createScoreTextStage("2.", xText, 155);
        rankThree = createScoreTextStage("3.", xText, 205);
        rankFour = createScoreTextStage("4.", xText, 255);
        rankFive = createScoreTextStage("5.", xText, 305);
        rankSix = createScoreTextStage("6.", xText, 355);
        rankSeven = createScoreTextStage("7.", xText, 405);
        rankEight = createScoreTextStage("8.", xText, 455);
        rankNine = createScoreTextStage("9.", xText, 505);
        rankTen = createScoreTextStage("10.", xText, 555);
        ranks = new ArrayList<Text>();
        ranks.add(rankOne);
        ranks.add(rankTwo);
        ranks.add(rankThree);
        ranks.add(rankFour);
        ranks.add(rankFive);
        ranks.add(rankSix);
        ranks.add(rankSeven);
        ranks.add(rankEight);
        ranks.add(rankNine);
        ranks.add(rankTen);
        homeButton = new ImageView("images/ranking-home-button.png");
        getView().createHover(homeButton, new Image("images/ranking-home-button.png"),
                new Image("images/ranking-home-button-hovered.png"));
        homeButton.relocate(50, 625);
        resetButton = new ImageView("images/ranking-reset-button.png");
        getView().createHover(resetButton, new Image("images/ranking-reset-button.png"),
                new Image("images/ranking-reset-button-hovered.png"));
        resetButton.relocate(350, 625);

        //add components to main menu
        getMenuPane().getChildren().add(rankingMessage);
        getMenuPane().getChildren().add(sidebar1);
        getMenuPane().getChildren().add(sidebar2);
        getMenuPane().getChildren().add(bottombar);
        for (Text t: ranks) {
            getMenuPane().getChildren().add(t);
        }
        getMenuPane().getChildren().add(homeButton);
        getMenuPane().getChildren().add(resetButton);
    }

    /**
     * @param string the string of text to display
     * @param x the x of the text element on te screen
     * @param y the y coordinate of the text on the screen
     * @return the newly created text element
     */
    private Text createScoreTextStage(String string, int x, int y) {
        Text text = new Text(string);
        text.setFont(Font.font("Arial", 40));
        text.setFill(Color.YELLOW);
        text.setWrappingWidth(GUIConfiguration.stageWidth);
        text.setTextAlignment(TextAlignment.LEFT);
        text.relocate(x, y);
        return text;
    }

    /**
     * @return the ranking screen home button.
     */
    public ImageView getHomeButton() {
        return homeButton;
    }

    /**
     * @return the ranking screen reset button
     */
    public ImageView getResetButton() {
        return resetButton;
    }

    /**
     * @return the rank elements
     */
    public ArrayList<Text> getRanks() {
        return ranks;
    }

}
