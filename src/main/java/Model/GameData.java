package Model;

import Entities.CenterPiece;

/**
 * Created by jur on 9/5/2017.
 */
public class GameData {

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

}
