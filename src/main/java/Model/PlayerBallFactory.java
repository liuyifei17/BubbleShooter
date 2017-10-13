package Model;

import Controller.GameConfiguration;

import java.util.List;
import java.util.Random;

/**
 * Ball Factory class.
 */
public abstract class PlayerBallFactory {

    public PlayerBall createBall(String ballType){
        switch (ballType){
            case "Normal Ball":
                Random random = new Random();
                List<String> colors = GameConfiguration.colors;
                int index = random.nextInt(colors.size());
                return new NormalBall(colors.get(index));
            case "Explosive Ball":
                return new  ExplosiveBall(null);
            case "Rainbow Ball":
                return new RainbowBall(null);
        }
        return null;
    }

}
