package Model;

import Controller.GUIConfiguration;
import Controller.GameConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

/**
 * This class provides tests cases for the MultiplierBallTest
 */
class MultiplierBallTest {
    PlayerBall multiplierBall;


    /**
     * Setup for every test.
     */
    @BeforeEach
    void setUp() {
        GUIConfiguration.isApiDefault();
        GameConfiguration.isApiDefault();
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