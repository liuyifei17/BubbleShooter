package Model;

import java.util.ArrayList;

/**
 * Class containing all game data.
 */
public class GameData {

    private Grid grid;
    private int initialBallAmount;
    private Player player;
    private ArrayList<Walls> randomWalls;

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
}
