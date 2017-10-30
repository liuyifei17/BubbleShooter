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
public class GameOverPopup extends Popup {

    private Text score;
    private ImageView restartButton;
    private ImageView homeButton;

    /**
     * @param view the view
     * @param popup the popup pane
     */
    public GameOverPopup(View view, Pane popup) {
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
        ImageView gameOverMessage = new ImageView("images/gameOverMessage.png");
        gameOverMessage.relocate(0, 5);
        score = new Text("Score: 0");
        score.setFont(Font.font("Arial", 35));
        score.setFill(Color.YELLOW);
        score.setWrappingWidth(GameConfiguration.popupWidth);
        score.setTextAlignment(TextAlignment.CENTER);
        score.setUnderline(true);
        score.relocate(0, 130);
        restartButton = new ImageView("images/restart-icon.png");
        restartButton.relocate(5, 260);
        homeButton = new ImageView("images/home-icon.png");
        homeButton.relocate(198, 260);

        //add graphical elements to popup container
        getPopup().getChildren().add(gameOverMessage);
        getPopup().getChildren().add(score);
        getPopup().getChildren().add(restartButton);
        getPopup().getChildren().add(homeButton);
    }

    /**
     * @return the score.
     */
    public Text getScore() {
        return score;
    }

    /**
     * @param score the score to set.
     */
    public void setScore(String score) {
        this.score.setText(score);
    }

    /**
     * @return the restart button.
     */
    public ImageView getRestartButton() {
        return restartButton;
    }

    /**
     * @param restartButton the restart button to set.
     */
    public void setRestartButton(ImageView restartButton) {
        this.restartButton = restartButton;
    }

    /**
     * @return the home button.
     */
    public ImageView getHomeButton() {
        return homeButton;
    }

    /**
     * @param homeButton the home button to set.
     */
    public void setHomeButton(ImageView homeButton) {
        this.homeButton = homeButton;
    }
}
