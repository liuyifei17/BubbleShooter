package Model;

import Controller.GameConfiguration;
import Utility.Util;

import java.util.Observable;

/**
 * The player class is keeps track of the players attributes.
 */
public class Player  extends Observable {
    private PlayerBall nextBall;
    private PlayerBall playerBall;
    private int missCounter;
    private int score;
    private PlayerBallFactory playerBallFactory;
    /**
     * The player has a score, a ball with whom it shoots and a misscounter for each missed
     * ball.
     */
    public Player() {
        playerBallFactory = new PlayerBallFactory();
        playerBall = playerBallFactory.createBall("Normal Ball");
        nextBall = playerBallFactory.createBall("Normal Ball");
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
        setChanged();
        notifyObservers();
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
    public PlayerBall getNextBall() {
        return nextBall;
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

    /**
     * This method gives the player new balls.
     */
    public void nextBall() {
        playerBall = nextBall;

        if (!GameConfiguration.specialBalls) {
            nextBall = playerBallFactory.createBall("Normal Ball");
        }
        else {
            int random = Util.randomBetween(0, 3);
            switch (random) {
                case 1://1 in 20 is an explosive ball.
                    nextBall = playerBallFactory.createBall("Explosive Ball");
                    break;
                case 2:
                case 3://2 in 20 is a rainbow ball.
                    nextBall = playerBallFactory.createBall("Rainbow Ball");
                    break;
                default://in all other cases the next ball is a normal ball.
                    nextBall = playerBallFactory.createBall("Normal Ball");
            }
        }
    }

}
