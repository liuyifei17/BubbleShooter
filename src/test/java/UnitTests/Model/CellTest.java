package UnitTests.Model;

import Controller.GameConfiguration;
import Model.Ball;
import Model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * This class provides test cases for the Cell class.
 */
class CellTest {
    private Cell cell;
    private double initialX = 4.0;
    private double initialY = 3.0;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        cell = new Cell(initialX, initialY);
    }


    @Test
    void testElement() {
        Ball ball = mock(Ball.class);
        cell.setBall(ball);
        assertThat(cell.getBall()).isEqualTo(ball);
    }

    @Test
    void testInitialX() {
        assertThat(cell.getInitialX()).isEqualTo(initialX);
    }


    @Test
    void testInitialY() {
        assertThat(cell.getInitialY()).isEqualTo(initialY);
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

    @Test
    void getY() {
        assertThat(cell.getCurrentY()).isEqualTo(initialY);
    }


    @Test
    void ddjecentCellsEmpty() {
        assertThat(cell.getAdjacentCells().size()).isEqualTo(0);
    }

    @Test
    void adjecentCellsOtherCell() {
        Cell otherCell = new Cell(initialX, initialY);
        cell.getAdjacentCells().add(otherCell);
        assertThat(cell.getAdjacentCells().get(0)).isEqualTo(otherCell);
    }

    @Test
    void adjacentCellsFilled() {
        Cell otherCell = new Cell(initialX, initialY);
        cell.getAdjacentCells().add(otherCell);
        cell.getAdjacentCells().add(otherCell);
        cell.getAdjacentCells().add(otherCell);
        cell.getAdjacentCells().add(otherCell);
        assertThat(cell.getAdjacentCells().size()).isEqualTo(4);
    }

    @Test
    void hasCollidedWithWallTest_true() {
        assertThat(cell.hasCollidedWithWall()).isTrue();
    }

    @Test
    void hasCollidedWithWallTest_false() {
        cell.setCurrentX(GameConfiguration.stageWidth / 2);
        cell.setCurrentY(
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        assertThat(cell.hasCollidedWithWall()).isFalse();
    }

    @Test
    void getEmptyAdjacentCellTest() {
        Cell c = new Cell(10, 10);

        cell.getAdjacentCells().add(c);

        assertThat(cell.getEmptyAdjacentCell()).isEqualTo(c);
    }

    @Test
    void getEmptyAdjacentCellTest_null() {
        Cell c = new Cell(10, 10);
        c.setBall(new Ball("blue", c, false));

        cell.getAdjacentCells().add(c);

        assertThat(cell.getEmptyAdjacentCell()).isNull();
    }
}