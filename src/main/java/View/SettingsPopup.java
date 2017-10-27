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
public class SettingsPopup extends Popup {

    private Text audioText;
    private Text wallText;
    private Text specialText;
    private ImageView audioToggle;
    private ImageView wallToggle;
    private ImageView specialToggle;
    private ImageView closeButton;

    /**
     * @param view the view
     * @param popup the popup pane
     */
    public SettingsPopup(View view, Pane popup) {
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
        closeButton = new ImageView("images/close-button.png");
        getView().createHover(closeButton, new Image("images/close-button.png"),
                new Image("images/close-button-hovered.png"));
        closeButton.relocate(270, 8);

        //add graphical elements to popup container
        getPopup().getChildren().add(settingsMessage);
        getPopup().getChildren().add(audioText);
        getPopup().getChildren().add(specialText);
        getPopup().getChildren().add(wallText);
        getPopup().getChildren().add(audioToggle);
        getPopup().getChildren().add(specialToggle);
        getPopup().getChildren().add(wallToggle);
        getPopup().getChildren().add(closeButton);

        //checks for toggle sprites
        checkAllSettingsToggles();
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
     * @return the audio toggle button.
     */
    public ImageView getAudioToggle() {
        return audioToggle;
    }

    /**
     * @return the walls toggle button.
     */
    public ImageView getWallToggle() {
        return wallToggle;
    }

    /**
     * @return the special ball toggle button.
     */
    public ImageView getSpecialToggle() {
        return specialToggle;
    }

    /**
     * @return the close button.
     */
    public ImageView getCloseButton() {
        return closeButton;
    }

}
