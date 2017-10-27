package View;

import Controller.GameConfiguration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * The pause popup class.
 */
public class PausePopup extends Popup {

    private ImageView restartButton;
    private ImageView homeButton;
    private ImageView exitButton;
    private ImageView closeButton;

    /**
     * @param view the view
     * @param popup the popup pane
     */
    public PausePopup(View view, Pane popup) {
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
        ImageView pauseMessage = new ImageView("images/pauseMessage.png");
        pauseMessage.relocate(0, 5);
        homeButton = new ImageView("images/popup-home-button.png");
        getView().createHover(homeButton, new Image("images/popup-home-button.png"),
                new Image("images/popup-home-button-hovered.png"));
        homeButton.relocate(50, 105);
        exitButton = new ImageView("images/popup-exit-button.png");
        getView().createHover(exitButton, new Image("images/popup-exit-button.png"),
                new Image("images/popup-exit-button-hovered.png"));
        exitButton.relocate(50, 180);
        restartButton = new ImageView("images/popup-restart-button.png");
        getView().createHover(restartButton, new Image("images/popup-restart-button.png"),
                new Image("images/popup-restart-button-hovered.png"));
        restartButton.relocate(50, 255);
        closeButton = new ImageView("images/close-button.png");
        getView().createHover(closeButton, new Image("images/close-button.png"),
                new Image("images/close-button-hovered.png"));
        closeButton.relocate(270, 8);

        //add graphical elements to popup container
        getPopup().getChildren().add(pauseMessage);
        getPopup().getChildren().add(restartButton);
        getPopup().getChildren().add(homeButton);
        getPopup().getChildren().add(exitButton);
        getPopup().getChildren().add(closeButton);
    }

    /**
     * @return popup continue game button
     */
    public ImageView getRestartButton() {
        return restartButton;
    }

    /**
     * @return popup main menu return button
     */
    public ImageView getHomeButton() {
        return homeButton;
    }

    /**
     * @return popup exit game button
     */
    public ImageView getExitButton() {
        return exitButton;
    }

    /**
     * @return pause popup menu close button
     */
    public ImageView getCloseButton() {
        return closeButton;
    }

}
