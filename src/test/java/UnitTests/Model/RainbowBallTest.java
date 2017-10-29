

package UnitTests.Model;

import Controller.GameConfiguration;
import Model.Ball;
import Model.Cell;
import Model.PlayerBall;
import Model.PlayerBallFactory;
import Model.RainbowBall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class provides test cases for the RainbowBall class.
 */
class RainBowBallTest {
    private PlayerBallFactory playerBallFactory;
    PlayerBall rainbowBall;


    /**
     * Setup for every test.
     */
    @BeforeEach
    void setUp() {
        playerBallFactory = PlayerBallFactory.getInstance();
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        rainbowBall = playerBallFactory.createBall("Rainbow Ball",
                GameConfiguration.ballRadius, GameConfiguration.stageWidth / 2);
    }

    /**
     * Test for remove balls method.
     */
    @Test
    void removalBallsTest() {
        Cell cell = mock(Cell.class);
        Ball ball = mock(Ball.class);
        when(ball.getColor()).thenReturn("green");
        when(cell.getBall()).thenReturn(ball);
        ArrayList<Cell> something = new ArrayList<>();
        something.add(cell);
        when(cell.getAdjacentCells()).thenReturn(something);
        assertThat(rainbowBall.checkRemovalBalls(cell).size()).isEqualTo(1);

    }

    /**
     * Test for constructor rainbowBall.
     */
    @Test
    void constructorTest() {
        rainbowBall = new RainbowBall("Black");
    }

    @Test
    void checkColors() {
        Cell cell = mock(Cell.class);
        Ball ball = mock(Ball.class);
        when(ball.getColor()).thenReturn("green");
        when(cell.getBall()).thenReturn(ball);
        ArrayList<Cell> something = new ArrayList<>();
        something.add(cell);
        when(cell.getAdjacentCells()).thenReturn(something);
        assertThat(rainbowBall.checkRemovalBalls(cell).size()).isEqualTo(1);
;
    }


}