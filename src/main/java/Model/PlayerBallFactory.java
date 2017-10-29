package Model;

import Controller.GameConfiguration;
import Utility.Util;

import java.util.List;

/**
 * Ball Factory class.
 */
public final class PlayerBallFactory {

    private static volatile PlayerBallFactory uniqueInstance = null;

    /**
     * The constructor.
     */
    private PlayerBallFactory() { }

    /**
     * @return the unique instance of the player ball factory singleton.
     */
    public static PlayerBallFactory getInstance() {
        if (uniqueInstance == null) {
            synchronized (PlayerBallFactory.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new PlayerBallFactory();
                }
            }
        }
        return uniqueInstance;
    }

    /**
     * This method creates a player ball.
     * @param ballType the type of the ball that will be created
     * @return an instance of one of the subclasses of PlayerBall
     */
    public PlayerBall createBall(String ballType) {
        List<String> colors = GameConfiguration.colors;
        switch (ballType) {
            case "Normal Ball":
                return new NormalBall(colors.get(Util.randomBetween(0, colors.size() - 1)));
            case "Explosive Ball":
                return new  ExplosiveBall("explosive");
            case "Rainbow Ball":
                return new RainbowBall("rainbow");
            case "Multiplier Ball":
                return new MultiplierBall(colors.get(Util.randomBetween(0, colors.size() - 1)));
            default:
                return null;
        }
    }

    /**
     * This method creates a player ball.
     * @param ballType the type of the ball that will be created
     * @param x the x coordinate of the ball
     * @param y the y coordinate of the ball
     * @return an instance of one of the subclasses of PlayerBall
     */
    public PlayerBall createBall(String ballType, double x, double y) {
        switch (ballType) {
            case "Normal Ball":
                List<String> colors = GameConfiguration.colors;
                return new NormalBall(colors.get(Util.randomBetween(0, colors.size() - 1)), x, y);
            case "Explosive Ball":
                return new  ExplosiveBall("explosive", x, y);
            case "Rainbow Ball":
                return new RainbowBall("rainbow", x, y);
            default:
                return null;
        }
    }
}
