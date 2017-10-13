package UnitTests.Model;

import Controller.GameConfiguration;
import Model.Ball;
import Model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class provides test cases for the Ball class.
 */
public class BallTest {
    private Ball ball;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();

        ball = new Ball("blue", null, 1);
    }

    @Test
    void getColorTest() {
        assertThat(ball.getColor()).isEqualTo("blue");
    }

    @Test
    void isCenterPieceTest_false() {
        assertThat(ball.isCenterPiece()).isFalse();
    }

    @Test
    void isCenterPieceTest_true() {
        Ball newBall = new Ball("yellow", null, 0);

        assertThat(newBall.isCenterPiece()).isTrue();
    }

    @Test
    void getCellTest() {
        Cell cell = new Cell(1, 2);

        ball.setCell(cell);

        assertThat(ball.getCell()).isEqualTo(cell);
    }
}
