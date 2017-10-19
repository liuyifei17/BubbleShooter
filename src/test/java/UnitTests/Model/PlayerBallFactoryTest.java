package UnitTests.Model;

import Controller.GameConfiguration;
import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Henks Laptop on 19/10/2017.
 */
class PlayerBallFactoryTest {
    PlayerBallFactory playerBallFactory = new PlayerBallFactory();

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
    }

    @Test
    void createNormalBall() {
        assertThat(playerBallFactory.createBall("Normal Ball", 1, 1) instanceof NormalBall).isTrue();
    }

    @Test
    void createExplosiveBall() {
        assertThat(playerBallFactory.createBall("Explosive Ball", 1, 1) instanceof ExplosiveBall).isTrue();
    }

    @Test
    void createRainbowBallTest() {
        assertThat(playerBallFactory.createBall("Rainbow Ball", 1, 1) instanceof RainbowBall).isTrue();
    }


    @Test
    void createBallDefault() {
        assertThat(playerBallFactory.createBall("Nonsense", 1, 1) == null).isTrue();
    }

    @Test
    void createBall1() {
    }

}