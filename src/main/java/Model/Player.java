package Model;

import Controller.GameConfiguration;
import Utility.Util;
import View.View;

/**
 * The player class is keeps track of the players attributes.
 */
public class Player {
    private Ball nextBall;
    private PlayerBall playerBall;
    private int missCounter;
    private int score;

    /**
     * The player has a score, a ball with whom it shoots and a misscounter for each missed
     * ball.
     */
    public Player() {
        nextBall = new Ball(GameConfiguration.colors.get(Util.randomBetween(0,
                GameConfiguration.colors.size() - 1)), null, false);
        playerBall = new PlayerBall(GameConfiguration.stageWidth / 2,
                GameConfiguration.topBarHeight, GameConfiguration.colors.get(Util.randomBetween(0,
                GameConfiguration.colors.size() - 1)));
        missCounter = 0;
        score = 0;
    }

    /**
     * This is the getter for the score fiels.
     * @return score the score the player has achieved
     */
    public int getScore() {
        return score;
    }

    /**
     * This is the method used to update the score.
     * @param score the new score
     */
    public void setScore(int score) {
        this.score = score;
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
     * @return get the next ball.
     */
    public Ball getNextBall() {
        return nextBall;
    }

    /**
     * @param n set next ball.
     */
    public void setNextBall(Ball n) {
        this.nextBall = n;
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
