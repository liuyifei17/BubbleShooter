package Model;

/**
 * Class containing all game data.
 */
public class GameData {

    private Grid grid;
    private int initialBallAmount;
    private Player player;

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
