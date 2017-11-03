package View;

import View.View;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

/**
 * The Popup abstract class.
 */
public abstract class Popup {

    private View view;
    private Pane popup;

    /**
     * @param view the view
     * @param popup the popup pane
     */
    public Popup(View view, Pane popup) {
        this.view = view;
        this.popup = popup;
        createPopup();
    }

    /**
     * Creates the popup menu.
     */
    public abstract void createPopup();

    /**
     * Sets the popup to being visible.
     */
    public void showPopup() {
        Platform.runLater(() -> {
            popup.setVisible(true);
            popup.toFront();
        });
    }

    /**
     * Sets the popup to being invisible.
     */
    public void closePopup() {
        Platform.runLater(() -> {
            popup.setVisible(false);
        });
    }

    /**
     * @return the popup Pane.
     */
    public Pane getPopup() {
        return popup;
    }

    /**
     * @return the view.
     */
    public View getView() {
        return view;
    }
}
