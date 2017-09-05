package Model;

import Elements.CenterPiece;
import Elements.HexagonElement;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class GameData {

    private Grid grid;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
