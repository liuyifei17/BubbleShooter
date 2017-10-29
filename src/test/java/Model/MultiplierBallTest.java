package Model;

import Controller.GameConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        multiplierBall = playerBallFactory.createBall("Multiplier Ball",
                GameConfiguration.ballRadius, GameConfiguration.stageWidth / 2);
    }

    @Test
    void checkRemovalBalls() {
        Cell cell = mock(Cell.class);
        Ball ball = mock(Ball.class);
        when(ball.getColor()).thenReturn("green");
        when(cell.getBall()).thenReturn(ball);
        ArrayList<Cell> something = new ArrayList<>();
        something.add(cell);
        when(cell.getAdjacentCells()).thenReturn(something);
        multiplierBall.checkRemovalBalls(cell);

    }

}