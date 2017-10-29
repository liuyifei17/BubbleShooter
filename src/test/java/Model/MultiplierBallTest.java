package Model;

import Controller.GameConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Henks Laptop on 29/10/2017.
 */
class MultiplierBallTest {
    private PlayerBallFactory playerBallFactory;
    PlayerBall multiplierBall;


    /**
     * Setup for every test.
     */
    @BeforeEach
    void setUp() {
        playerBallFactory = PlayerBallFactory.getInstance();
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        multiplierBall = new MultiplierBall("Multiplier Ball");
    }
    @Test
    void constructorTest() {
        multiplierBall = new MultiplierBall("Multiplier Ball", 2, 3, 2);
        assertThat(multiplierBall.getX()).isEqualTo(2);
    }

    @Test
    void checkRemovalBalls() {
        Cell cell = mock(Cell.class);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cell);
        assertThat(multiplierBall.checkRemovalBalls(cell)).isEqualTo(arrayList);

    }

}