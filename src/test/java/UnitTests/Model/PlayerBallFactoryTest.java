package UnitTests.Model;

import Controller.GameConfiguration;
import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for PlayerBallFactory class.
 */
class PlayerBallFactoryTest {
    PlayerBallFactory playerBallFactory = new PlayerBallFactory();

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
    }

    /**
     * Testing normal ball creation with the first method.
     */
    @Test
    void createNormalBall_1() {
        assertThat(playerBallFactory.createBall("Normal Ball", 1, 1)
                instanceof NormalBall).isTrue();
    }

    /**
     * Testing normal ball creation with the second method.
     */
    @Test
    void createNormalBall_2() {
        assertThat(playerBallFactory.createBall("Normal Ball")
                instanceof NormalBall).isTrue();
    }

    /**
     * Testing Explosiveball creation with the first method.
     */
    @Test
    void createExplosiveBall_1() {
        assertThat(playerBallFactory.createBall("Explosive Ball", 1, 1)
                instanceof ExplosiveBall).isTrue();
    }

    /**
     * Testing Explosive ball creation with the second method.
     */
    @Test
    void createExplosiveBall_2() {
        assertThat(playerBallFactory.createBall("Explosive Ball")
                instanceof ExplosiveBall).isTrue();
    }


    /**
     * Testing Rainbow ball creation with the second method.
     */
    @Test
    void createRainbowBallTest_1() {
        assertThat(playerBallFactory.createBall("Rainbow Ball", 1, 1)
                instanceof RainbowBall).isTrue();
    }


    /**
     * Testing Rainbow ball creation with the second method.
     */
    @Test
    void createRainbowBallTest_2() {
        assertThat(playerBallFactory.createBall("Rainbow Ball")
                instanceof RainbowBall).isTrue();
    }


    /**
     * Testing the default case for the first method.
     */
    @Test
    void createBallDefault_1() {
        assertThat(playerBallFactory.createBall("Nonsense", 1, 1)
                == null).isTrue();
    }

    /**
     * Testing the default case for the second method.
     */
    @Test
    void createBallDefault_2() {
        assertThat(playerBallFactory.createBall("Nonsense")
                == null).isTrue();
    }

}