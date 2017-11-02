package View;

import Controller.GameConfiguration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The game over popup class.
 */
public class RankingPopup extends Popup {

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
        getPopup().setPrefSize(GameConfiguration.popupWidth, GameConfiguration.popupHeight);
        getPopup().relocate(GameConfiguration.popupX, GameConfiguration.popupY);
        getPopup().setBackground(new Background(new BackgroundImage(
                new Image("images/popupBackground.png", GameConfiguration.popupWidth,
                        GameConfiguration.popupHeight, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        //create graphical elements
        ImageView rankingMessage = new ImageView("images/rankingMessage.png");
        rankingMessage.relocate(0, 5);
        int xText = 10;
        rankOne = createScoreText("1.", xText, 70);
        rankTwo = createScoreText("2.", xText, 98);
        rankThree = createScoreText("3.", xText, 126);
        rankFour = createScoreText("4.", xText, 154);
        rankFive = createScoreText("5.", xText, 182);
        rankSix = createScoreText("6.", xText, 210);
        rankSeven = createScoreText("7.", xText, 238);
        rankEight = createScoreText("8.", xText, 266);
        rankNine = createScoreText("9.", xText, 294);
        rankTen = createScoreText("10.", xText, 322);

        closeButton = new ImageView("images/close-button.png");
        getView().createHover(closeButton, new Image("images/close-button.png"),
                new Image("images/close-button-hovered.png"));
        closeButton.relocate(270, 8);

        //add graphical elements to popup container
        getPopup().getChildren().add(rankingMessage);
        getPopup().getChildren().add(rankOne);
        getPopup().getChildren().add(rankTwo);
        getPopup().getChildren().add(rankThree);
        getPopup().getChildren().add(rankFour);
        getPopup().getChildren().add(rankFive);
        getPopup().getChildren().add(rankSix);
        getPopup().getChildren().add(rankSeven);
        getPopup().getChildren().add(rankEight);
        getPopup().getChildren().add(rankNine);
        getPopup().getChildren().add(rankTen);
        getPopup().getChildren().add(closeButton);
    }

    /**
     * @param string the string of text to display
     * @param x the x of the text element on te screen
     * @param y the y coordinate of the text on the screen
     * @return the newly created text element
     */
    private Text createScoreText(String string, int x, int y) {
        Text text = new Text(string);
        text.setFont(Font.font("Arial", 20));
        text.setFill(Color.YELLOW);
        text.setWrappingWidth(GameConfiguration.popupWidth);
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

}
