package Elements;

import Model.Cell;
import javafx.scene.image.Image;

/**
 * Created by jur on 9/5/2017.
 */
public class HexagonElement {

    private int id;
    private Cell cell;
    private Image img;

    public HexagonElement(int id, Cell cell, Image img){
        this.id = id;
        this.cell = cell;
        this.img = img;
    }

    public Image getSprite() {
        return img;
    }

    public int getId() {
        return id;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
