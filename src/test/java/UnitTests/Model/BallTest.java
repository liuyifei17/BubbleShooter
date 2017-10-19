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
    void isNormalBallTest() {
        assertThat(ball.isNormalBall()).isTrue();
    }

    @Test
    void isExplosiveBallTest() {
        ball = new Ball("color", null, 2);
        assertThat(ball.isExplosiveBall()).isTrue();
    }

    @Test
    void isRainbowBallTest() {
        ball = new Ball("color", null, 3);
        assertThat(ball.isRainbowBall()).isTrue();
    }

    @Test
    void isMultipleBallTest() {
        ball = new Ball("color", null, 4);
        assertThat(ball.isMultiplierBall()).isTrue();
    }

    @Test
    void getBallTypeTest() {
        assertThat(ball.getBallType()).isEqualTo(1);
    }
    @Test
    void getCellTest() {
        Cell cell = new Cell(1, 2);

        ball.setCell(cell);

        assertThat(ball.getCell()).isEqualTo(cell);
    }
}
