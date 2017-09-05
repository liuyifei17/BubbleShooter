package View;

import Elements.HexagonElement;
import Model.GameData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Created by jur on 9/5/2017.
 */
public class View {

    private GameData data;
    private Pane pane;

    private Image background;
    private ImageView topBar;
    private ImageView scoreBar;

    private final int TOP_BAR_HEIGHT = 70;
    private final int SCORE_BAR_HEIGHT = 40;
    private static final int SCORE_BAR_WIDTH = 240;

    public View(Pane pane, GameData data){
        this.pane = pane;
        this.data = data;
    }

    public void draw(){
        //draw background
        background = new Image("images/background1.png");
        pane.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        //draw top bar
        topBar = new ImageView("images/topBar1.png");
        topBar.relocate(0, 0);
        topBar.fitWidthProperty().bind(pane.widthProperty());
        topBar.setFitHeight(TOP_BAR_HEIGHT);

        //draw score bar
        scoreBar = new ImageView("images/scoreBar1.png");
        scoreBar.relocate(8, 15);
        scoreBar.setFitHeight(SCORE_BAR_HEIGHT);
        scoreBar.setFitWidth(SCORE_BAR_WIDTH);

        //draw game bars & score etc
        //TODO; draw other components

        //draw entities
        ImageView centerPieceImage = new ImageView(data.getCenterPiece().getSprite());
        centerPieceImage.relocate(getScreenX(data.getCenterPiece()), getScreenY(data.getCenterPiece()));

        //draw balls
        //TODO: draw balls

        //add components to game pane
        pane.getChildren().add(topBar);
        pane.getChildren().add(scoreBar);
        pane.getChildren().add(centerPieceImage);
    }

    private double getScreenX(HexagonElement hexagonElement){
        return (hexagonElement.getX() - (hexagonElement.getSprite().getWidth() / 2));
    }

    private double getScreenY(HexagonElement hexagonElement){
        return (hexagonElement.getY() + TOP_BAR_HEIGHT - (hexagonElement.getSprite().getHeight() / 2));
    }

}
