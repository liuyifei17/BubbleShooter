package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * The menu class.
 */
public class MainMenu extends Menu {

    private Image background;
    private ImageView playButton;
    private ImageView rankingButton;
    private ImageView exitButton;

    /**
     * the menu constructor.
     * @param view the view to set.
     * @param menuPane the menu pane to set.
     */
    public MainMenu(View view, Pane menuPane) {
        super(view, menuPane);
    }

    /**
     * draws up the main menu screen.
     */
    @Override
    public void drawMenu() {
        //draw background
        background = new Image("images/background1.png");
        getMenuPane().setBackground(new Background(
                new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT)));

        ImageView logo = new ImageView("images/logo.png");
        logo.fitWidthProperty().setValue(580);
        logo.relocate(10, 100);
        playButton = new ImageView("images/play-button.png");
        playButton.relocate(184, 240);
        playButton.fitWidthProperty().setValue(250);
        playButton.fitHeightProperty().setValue(80);
        rankingButton = new ImageView("images/ranking-button.png");
        rankingButton.relocate(184, 335);
        rankingButton.fitWidthProperty().setValue(250);
        rankingButton.fitHeightProperty().setValue(80);
        exitButton = new ImageView("images/exit-button.png");
        exitButton.relocate(184, 430);
        exitButton.fitWidthProperty().setValue(250);
        exitButton.fitHeightProperty().setValue(80);

        //add components to main menu
        getMenuPane().getChildren().add(logo);
        getMenuPane().getChildren().add(playButton);
        getMenuPane().getChildren().add(rankingButton);
        getMenuPane().getChildren().add(exitButton);
    }

    /**
     * @return play button.
     */
    public ImageView getPlayButton() {
        return playButton;
    }

    /**
     * @return exit button.
     */
    public ImageView getExitButton() {
        return exitButton;
    }

    /**
     * @return ranking button.
     */
    public ImageView getRankingButton() {
        return rankingButton;
    }

}
