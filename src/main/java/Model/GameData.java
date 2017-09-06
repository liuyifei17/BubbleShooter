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
    private int initialBallAmount;

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

    public int getInitialBallAmount() {
        return initialBallAmount;
    }

    public void setInitialBallAmount(int initialBallAmount) {
        this.initialBallAmount = initialBallAmount;
    }
}
