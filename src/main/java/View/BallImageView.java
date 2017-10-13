package View;

import Model.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * This class keeps track of an ImageView and the class it belongs to.
 */
public class BallImageView extends ImageView {

    private Cell cell;
    private boolean isPlus1Icon;

    /**
     * This creates a new instance of this class.
     * @param im the image
     * @param c the cell that is associated with the image
     * @param isPlus1Icon a boolean that specifies if the ImageView is a +1 icon
     */
    public BallImageView(Image im, Cell c, boolean isPlus1Icon) {
        super(im);
        this.cell = c;
        this.isPlus1Icon = isPlus1Icon;
    }

    /**
     * This is the getter for the Cell field.
     * @return the cell
     */
    public Cell getCell() {
        return this.cell;
    }

    /**
     * @return true or false whether the BallImageView is a plus 1 icon
     */
    public boolean isPlus1Icon() {
        return this.isPlus1Icon;
    }
}
