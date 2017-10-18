package Model;

import Controller.GameConfiguration;

import java.util.List;
import java.util.Random;

/**
 * Ball Factory class.
 */
public class PlayerBallFactory {

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
