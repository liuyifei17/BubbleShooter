package Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * The center piece class.
 */
public class CenterPiece extends Element {

    private ArrayList<Cell> adjacentCells;

    /**
     * the center piece constructor.
     * sets the image of the center piece.
     */
    public CenterPiece() {
        super(null, new Image("images/center.png"));
    }

    /**
     * @return adjacent cells
     */
    public ArrayList<Cell> getAdjacentCells() {
        return adjacentCells;
    }
}
