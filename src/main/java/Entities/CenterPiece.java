package Entities;

import javafx.scene.image.Image;

/**
 * Created by jur on 9/5/2017.
 */
public class CenterPiece extends Entity {

    private double[] corners = new double[6];

    public CenterPiece(double x, double y) {
        super.setX(x);
        super.setY(y);
        super.setImg(new Image("images/center.png"));
        //calculate hexagon corners here
    }

}
