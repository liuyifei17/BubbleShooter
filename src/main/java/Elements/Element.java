package Elements;

import Model.Cell;
import javafx.scene.image.Image;

/**
 * Created by jur on 9/5/2017.
 */
public class Element {

    private Cell cell;
    private Image img;

    /**
     * @param cell
     * @param img
     */
    public Element(Cell cell, Image img){
        this.cell = cell;
        this.img = img;
    }

    public Image getSprite() {
        return img;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
