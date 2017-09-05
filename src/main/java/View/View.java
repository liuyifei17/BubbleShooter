package View;

import Entities.Entity;
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

    public static final int TOP_BAR_HEIGHT = 70;

    public View(Pane pane, GameData data){
        this.pane = pane;
        this.data = data;
    }

    public void draw(){
        System.out.println(data.getCenterPiece().getX());
        System.out.println(data.getCenterPiece().getY());

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
        scoreBar.setFitHeight(40);
        scoreBar.setFitWidth(240);

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

    private double getScreenX(Entity entity){
        return (entity.getX() - (entity.getSprite().getWidth() / 2));
    }

    private double getScreenY(Entity entity){
        return (entity.getY() + TOP_BAR_HEIGHT - (entity.getSprite().getHeight() / 2));
    }

}
