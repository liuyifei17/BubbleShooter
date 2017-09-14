package Model;

import Model.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents an element that links the a cell to an image that can be displayed
 * in the GUI.
 */
public class Element {

    private Cell cell;
    private Image img;
    private ImageView imageView;

    /**
     * @param cell the cell object of the element
     * @param img the image/sprite the element will display on the GUI
     */
    public Element(Cell cell, Image img) {
        this.cell = cell;
        this.img = img;
        this.imageView = new ImageView(img);
    }

    /**
     * Getter for the image field.
     * @return the image that is stored in the image field
     */
    public Image getSprite() {
        return img;
    }

    /**
     * Getter for the ImageView associated with the image.
     * @return the ImageView associated with the image
     */
    public ImageView getImageView() {
        return this.imageView;
    }

    /**
     * Setter for the image field.
     * @param im the image to be put in the image field
     */
    public void setImage(Image im) {
        this.img = im;
        this.imageView.setImage(im);
    }

    /**
     * Getter for the cell field.
     * @return the cell that is stored in the cell field
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * Setter for the cell field.
     * @param cell the cell to be put in the cell field
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }


}
