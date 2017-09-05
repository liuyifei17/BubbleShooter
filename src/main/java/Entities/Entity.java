package Entities;

import javafx.scene.image.Image;

/**
 * Created by jur on 9/5/2017.
 */
public class Entity {

    private double centerX;
    private double centerY;
    private Image img;

    public double getX(){
        return centerX;
    }

    public double getY(){
        return centerY;
    }

    public Image getSprite() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setY(double centerY) {
        this.centerY = centerY;
    }

    public void setX(double centerX) {
        this.centerX = centerX;
    }
}
