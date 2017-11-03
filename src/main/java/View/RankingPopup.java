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
 * The game over popup class.
 */
public class RankingPopup extends Popup {

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

    private ImageView closeButton;

    /**
     * @param view the view
     * @param popup the popup pane
     */
    public RankingPopup(View view, Pane popup) {
        super(view, popup);
    }

    /**
     * Creates the popup menu.
     */
    @Override
    public void createPopup() {
        //create popup container
        getPopup().setPrefSize(GUIConfiguration.popupWidth, GUIConfiguration.popupHeight);
        getPopup().relocate(GUIConfiguration.popupX, GUIConfiguration.popupY);
        getPopup().setBackground(new Background(new BackgroundImage(
                new Image("images/popupBackground.png", GUIConfiguration.popupWidth,
                        GUIConfiguration.popupHeight, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        //create graphical elements
        ImageView rankingMessage = new ImageView("images/rankingMessage.png");
        rankingMessage.relocate(0, 5);
        int xText = 10;
        rankOne = createScoreTextPopup("1.", xText, 70);
        rankTwo = createScoreTextPopup("2.", xText, 98);
        rankThree = createScoreTextPopup("3.", xText, 126);
        rankFour = createScoreTextPopup("4.", xText, 154);
        rankFive = createScoreTextPopup("5.", xText, 182);
        rankSix = createScoreTextPopup("6.", xText, 210);
        rankSeven = createScoreTextPopup("7.", xText, 238);
        rankEight = createScoreTextPopup("8.", xText, 266);
        rankNine = createScoreTextPopup("9.", xText, 294);
        rankTen = createScoreTextPopup("10.", xText, 322);
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

        closeButton = new ImageView("images/close-button.png");
        getView().createHover(closeButton, new Image("images/close-button.png"),
                new Image("images/close-button-hovered.png"));
        closeButton.relocate(270, 8);

        //add graphical elements to popup container
        getPopup().getChildren().add(rankingMessage);
        for (Text t: ranks) {
            getPopup().getChildren().add(t);
        }
        getPopup().getChildren().add(closeButton);
    }

    /**
     * @param string the string of text to display
     * @param x the x of the text element on te screen
     * @param y the y coordinate of the text on the screen
     * @return the newly created text element
     */
    private Text createScoreTextPopup(String string, int x, int y) {
        Text text = new Text(string);
        text.setFont(Font.font("Arial", 20));
        text.setFill(Color.YELLOW);
        text.setWrappingWidth(GUIConfiguration.popupWidth);
        text.setTextAlignment(TextAlignment.LEFT);
        text.relocate(x, y);
        return text;
    }

    /**
     * @return the close button.
     */
    public ImageView getCloseButton() {
        return closeButton;
    }

    /**
     * @return the rank elements
     */
    public ArrayList<Text> getRanks() {
        return ranks;
    }

}
