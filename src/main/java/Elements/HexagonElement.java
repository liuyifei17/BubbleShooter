package Elements;

import javafx.scene.image.Image;

/**
 * Created by jur on 9/5/2017.
 */
public class HexagonElement {

    private int id;
    private double centerX;
    private double centerY;
    private Image img;

    private double[] edgeRotations = { 90, 30, 330, 270, 210, 150 };
    private HexagonElement[] adjacentElements = { null, null, null, null, null, null };

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

    public double[] getEdgeRotations() {
        return edgeRotations;
    }

    public HexagonElement[] getAdjacentElements() {
        return adjacentElements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
