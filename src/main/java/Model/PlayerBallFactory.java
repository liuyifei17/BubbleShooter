package Model;

import Controller.GameConfiguration;

import java.util.List;
import java.util.Random;

/**
 * Ball Factory class.
 */
public class PlayerBallFactory {

    /**
     * This method creates a player ball.
     * @param ballType the type of the ball that will be created
     * @return an instance of one of the subclasses of PlayerBall
     */
    PlayerBall createBall(String ballType) {
        switch (ballType) {
            case "Normal Ball":
                Random random = new Random();
                List<String> colors = GameConfiguration.colors;
                int index = random.nextInt(colors.size());
                return new NormalBall(colors.get(index));
            case "Explosive Ball":
                return new  ExplosiveBall("Explosive");
            case "Rainbow Ball":
                return new RainbowBall("Rainbow");
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
                Random random = new Random();
                List<String> colors = GameConfiguration.colors;
                int index = random.nextInt(colors.size());
                return new NormalBall(colors.get(index), x, y);
            case "Explosive Ball":
                return new  ExplosiveBall("Explosive", x, y);
            case "Rainbow Ball":
                return new RainbowBall("Rainbow", x, y);
            default:
                return null;
        }
    }
}
