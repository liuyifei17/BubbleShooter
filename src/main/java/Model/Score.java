package Model;

import java.util.Date;

/**
 * The score class consist of a date and the score itsel.
 */
public class Score {

    private int score;
    private Date date;

    /**
     * @param score the current score of the player.
     */
    public Score(int score) {
        this.score = score;
        date = new Date();
    }

    /**
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the current date.
     */
    public Date getDate() {
        return date;
    }

}
