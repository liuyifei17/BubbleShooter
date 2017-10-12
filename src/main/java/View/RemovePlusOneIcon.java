package View;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * This class creates an object that when executed removes the +1 icons in the game.
 */
public class RemovePlusOneIcon implements Runnable {
    private ImageView imageView;
    private Pane gamePane;

    /**
     * This is the constructor of the class.
     * @param im the imageView associated with the +1 icon
     * @param gamePane the pane of the game
     */
    RemovePlusOneIcon(ImageView im, Pane gamePane) {
        this.imageView = im;
        this.gamePane = gamePane;
    }

    /**
     * The code that removes the +1 image from the cell stored in the cell field.
     */
    public void run() {
        Platform.runLater(() -> {
            gamePane.getChildren().remove(this.imageView);
        });
    }
}