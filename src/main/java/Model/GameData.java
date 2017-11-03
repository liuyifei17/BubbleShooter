package Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Class containing all game data.
 */
public class GameData extends Observable {

    private Grid grid;
    private int initialBallAmount;
    private Player player;
    private ArrayList<Walls> randomWalls;
    private ArrayList<Score> scores;

    /**
     * This is the constructor.
     * @param grid the grid of the game
     * @param player the player
     * @param initialBallAmount the initial ball amount
     */
    public GameData(Grid grid, Player player, int initialBallAmount) {
        this.grid = grid;
        this.player = player;
        this.initialBallAmount = initialBallAmount;
        randomWalls = new ArrayList<>();
        scores = new ArrayList<>();
    }

    /**
     * Places the scores in the current top 10.
     */
    public void placeScore() {
        Score score = new Score(player.getScore());
        if (scores.size() == 0) {
            scores.add(score);
        }
        else {
            boolean found = false;
            for (int i = 0; i < scores.size(); i++) {
                if (score.getScore() > scores.get(i).getScore()) {
                    scores.add(i, score);
                    found = true;
                    break;
                }
            }
            if (!found) {
                scores.add(score);
            }
            if (scores.size() > 10) {
                scores.remove(scores.size() - 1);
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * @return list of randomwalls
     */
    public ArrayList<Walls> getRandomWalls() {
        return randomWalls;
    }

    /**
     * @return grid.
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * @param grid sets the grid.
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * @return the initial ball amount.
     */
    public int getInitialBallAmount() {
        return initialBallAmount;
    }

    /**
     * @param initialBallAmount sets the initial ball amount.
     */
    public void setInitialBallAmount(int initialBallAmount) {
        this.initialBallAmount = initialBallAmount;
    }

    /**
     * @return player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player sets the player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the list of scores.
     */
    public ArrayList<Score> getScores() {
        return scores;
    }

    /**
     * @param scores new score list.
     */
    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
        setChanged();
        notifyObservers();
    }
}
