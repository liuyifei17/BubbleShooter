package Entities;

import javafx.scene.image.Image;

/**
 * Created by jur on 9/5/2017.
 */
public class CenterPiece {

    private double centerX;
    private double centerY;
    private double[] corners = new double[6];
    private Image img;

    public CenterPiece(double x, double y){
        centerX = x;
        centerY = y;
        img = new Image("images/center.png");
        //calculate hexagon corners here
    }

    public double getX(){
        return (centerX - (img.getWidth() / 2));
    }

    public double getY(){
        return (centerY - (img.getHeight() / 2));
    }

    public Image getSprite() {
        return img;
    }

}
