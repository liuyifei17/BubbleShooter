package UnitTests.Model;

import Controller.GUIConfiguration;
import Controller.GameConfiguration;
import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class ExplosiveBallTest {
    PlayerBall explosiveBall;
    PlayerBallFactory playerBallFactory;

    @BeforeEach
    void setUp() {
        GUIConfiguration.isApiDefault();
        GameConfiguration.isApiDefault();
        playerBallFactory = PlayerBallFactory.getInstance();
        explosiveBall = playerBallFactory.createBall("Explosive Ball",
                GUIConfiguration.ballRadius, GUIConfiguration.stageWidth / 2);

    }

    @Test
    void checkRemovalBallsTest() {
        Cell cell = mock(Cell.class);
        Ball ball = mock(Ball.class);
        ArrayList<Cell> cellList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cellList.add(mock(Cell.class));
            when(cellList.get(i).getBall()).thenReturn(ball);
        }
        cellList.add(cell);
        when(cell.getAdjacentCells()).thenReturn(cellList);
        when(cell.getBall()).thenReturn(ball);
        assertThat(explosiveBall.checkRemovalBalls(cell).size()).isEqualTo(7);
    }

    @Test
    void constructorTest() {
        explosiveBall = new ExplosiveBall("Explosive");
    }

}
