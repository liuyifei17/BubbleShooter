package Model;

import Elements.CenterPiece;
import Elements.HexagonElement;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class GameData {

    private ArrayList<HexagonElement> elementList = new ArrayList<HexagonElement>();

    private CenterPiece centerPiece;
    private int score;

    public CenterPiece getCenterPiece() {
        return centerPiece;
    }

    public void setCenterPiece(CenterPiece centerPiece) {
        this.centerPiece = centerPiece;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<HexagonElement> getElementList() {
        return elementList;
    }
}
