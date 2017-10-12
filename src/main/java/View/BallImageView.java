package View;

import Model.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * This class keeps track of an ImageView and the class it belongs to.
 */
public class BallImageView extends ImageView {

    private Cell cell;

    /**
     * This creates a new instance of this class.
     * @param im the image
     * @param c the cell that is associated with the image
     */
    public BallImageView(Image im, Cell c) {
        super(im);
        this.cell = c;
    }

    /**
     * This is the getter for the Cell field.
     * @return the cell
     */
    public Cell getCell() {
        return this.cell;
    }
}
