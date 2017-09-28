package UnitTests.Model;

import Model.Ball;
import Model.Cell;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * This class provides test cases for the Cell class.
 */
class CellTest {
    private Cell cell;
    private double INITIALX = 4.0;
    private double INITIALY = 3.0;

    @BeforeEach
    void setUp() {
        cell = new Cell(INITIALX, INITIALY);
    }


    @Test
    void testElement() {
        Ball ball = mock(Ball.class);
        cell.setElement(ball);
        assertThat(cell.getElement()).isEqualTo(ball);
    }

    @Test
    void testInitialX() {
        assertThat(cell.getInitialX()).isEqualTo(INITIALX);
    }


    @Test
    void testInitialY() {
        assertThat(cell.getInitialY()).isEqualTo(INITIALY);
    }

    @Test
    void testCurrentY() {
        cell.setCurrentY(3);
        assertThat(cell.getCurrentY()).isEqualTo(3);
    }

    @Test
    void testCurrentX() {
        cell.setCurrentX(3);
        assertThat(cell.getCurrentY()).isEqualTo(3);
    }


    /**
     *
     */
    @Test
    void getY() {
        assertThat(cell.getCurrentY()).isEqualTo(INITIALY);
    }


    @org.junit.jupiter.api.Test
    void AdjecentCellsEmpty() {
        assertThat(cell.getAdjacentCells().size()).isEqualTo(0);
    }

    @org.junit.jupiter.api.Test
    void AdjecentCellsOtherCell() {
        Cell otherCell = new Cell(INITIALX, INITIALY);
        cell.getAdjacentCells().add(otherCell);
        assertThat(cell.getAdjacentCells().get(0)).isEqualTo(otherCell);
    }
    @org.junit.jupiter.api.Test
    void AdjecentCellsFilled() {
        Cell otherCell = new Cell(INITIALX, INITIALY);
        cell.getAdjacentCells().add(otherCell);
        cell.getAdjacentCells().add(otherCell);
        cell.getAdjacentCells().add(otherCell);
        cell.getAdjacentCells().add(otherCell);
        assertThat(cell.getAdjacentCells().size()).isEqualTo(4);
    }

    /**
     * Created by Henks Laptop on 28/09/2017.
     */
    static class BallTest {

        Ball ball;
        String color = "Red";
        Cell cell = mock(Cell.class);
        @Test
        void getColor() {
            ball = new Ball(color, cell, false);
            ball.setColor("green");
            AssertionsForClassTypes.assertThat(ball.getColor()).isEqualTo("green");
        }


        //@Test
        void colorExists() {
            ball = new Ball("GREEN", cell, false);
            AssertionsForClassTypes.assertThat(ball.colorExists(color)).isTrue();

        }

        @Test
        void isCenterPiece() {
            ball = new Ball(color, cell, true);
            AssertionsForClassTypes.assertThat(ball.isCenterPiece()).isTrue();
        }

        @Test
        void isNotCenterPiece() {
            ball = new Ball(color, cell, false);
            AssertionsForClassTypes.assertThat(ball.isCenterPiece()).isFalse();
        }

    }
}