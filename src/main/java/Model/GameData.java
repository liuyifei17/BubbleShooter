package Model;

import Elements.Player;

/**
 * Created by jur on 9/5/2017.
 */
public class GameData {

    private Grid grid;
    private int score;
    private int initialBallAmount;
    private Player player;

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
