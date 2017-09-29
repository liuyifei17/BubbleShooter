package UnitTests.Model;

import Controller.GameConfiguration;
import Model.Ball;
import Model.Cell;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

/**
 * Ball test.
 */
class BallTest {

    private Ball ball;
    private String color = "Red";
    private Cell cell = mock(Cell.class);

    /**
     * Test color.
     */
    @Test
    void getColor() {
        ball = new Ball(color, cell, false);
        ball.setColor("green");
        AssertionsForClassTypes.assertThat(ball.getColor()).isEqualTo("green");
    }

    /**
     * Test color.
     */
    @Test
    void colorExists() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        ball = new Ball("GREEN", cell, false);
        AssertionsForClassTypes.assertThat(ball.colorExists(color)).isTrue();

    }

    /**
     * Testing centerpiece.
     */
    @Test
    void isCenterPiece() {
        ball = new Ball(color, cell, true);
        AssertionsForClassTypes.assertThat(ball.isCenterPiece()).isTrue();
    }

    /**
     * Testing not centerpiece.
     */
    @Test
    void isNotCenterPiece() {
        ball = new Ball(color, cell, false);
        AssertionsForClassTypes.assertThat(ball.isCenterPiece()).isFalse();
    }

}