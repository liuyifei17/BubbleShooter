package Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class CenterPiece extends Element {

    private ArrayList<Cell> adjacentCells;

    public CenterPiece() {
        super(null, new Image("images/center.png"));
    }

    public ArrayList<Cell> getAdjacentCells() {
        return adjacentCells;
    }
}
