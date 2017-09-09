package Elements;

import View.View;

/**
 * The player class is keeps track of the players attributes.
 */
public class Player {
    private PlayerBall playerBall;
    private int missCounter;

    /**
     * The player has a score, a ball with whom it shoots and a misscounter for each missed
     * ball.
     */
    public Player() {
        playerBall = new PlayerBall(View.STAGE_WIDTH / 2 - View.SCREEN_WITH_DEVIATION,
                View.TOP_BAR_HEIGHT);
        missCounter = 0;
    }

    /**
     * @return get the current ball.
     */
    public PlayerBall getPlayerBall() {
        return playerBall;
    }

    /**
     * @param playerBall set a new ball.
     */
    public void setPlayerBall(PlayerBall playerBall) {
        this.playerBall = playerBall;
    }

    /**
     * @return get the amount of times u missed a ball.
     */
    public int getMissCounter() {
        return missCounter;
    }

    /**
     * @param missCounter increase or decrease the counter.
     */
    public void setMissCounter(int missCounter) {
        this.missCounter = missCounter;
    }

}
