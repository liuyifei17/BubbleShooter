package Model;

import Model.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by jur on 9/5/2017.
 */
public class Element {

    private Cell cell;
    private Image img;
    private ImageView imageView;

    /**
     * @param cell
     * @param img
     */
    public Element(Cell cell, Image img){
        this.cell = cell;
        this.img = img;
        this.imageView = new ImageView(img);
    }

    public Image getSprite() {
        return img;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setImage(Image im) {
        this.img = im;
        this.imageView.setImage(im);
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }


}
