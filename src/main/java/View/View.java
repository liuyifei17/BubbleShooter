package View;

import Model.GameData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by jur on 9/5/2017.
 */
public class View {

    private GameData data;

    public View(GameData data){
        this.data = data;
    }

    public void draw(Pane pane){
        //draw game bars & score etc
        //TODO; draw other components

        //draw centerpiece
        ImageView centerPieceImage = new ImageView(data.getCenterPiece().getSprite());
        centerPieceImage.relocate(data.getCenterPiece().getX(), data.getCenterPiece().getY());

        //draw balls
        //TODO: draw balls

        //add components to game pane
        pane.getChildren().add(centerPieceImage);
    }

}
