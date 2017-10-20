package Model;

import Controller.GameConfiguration;
import Utility.Util;

import java.util.List;

/**
 * Ball Factory class.
 */
public class PlayerBallFactory {

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
                return new  ExplosiveBall("Explosive");
            case "Rainbow Ball":
                return new RainbowBall("Rainbow");
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
                return new  ExplosiveBall("Explosive", x, y);
            case "Rainbow Ball":
                return new RainbowBall("Rainbow", x, y);
            default:
                return null;
        }
    }
}
