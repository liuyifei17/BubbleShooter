package UnitTests.Model;

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
    private final double INITIALX = 4.0;
    private final double INITIALY = 3.0;

    /**
     * setup cell.
     */
    @BeforeEach
    void setUp() {
        cell = new Cell(INITIALX, INITIALY);
    }


    /**
     * Testing element.
     */
    @Test
    void testElement() {
        Ball ball = mock(Ball.class);
        cell.setElement(ball);
        assertThat(cell.getElement()).isEqualTo(ball);
    }

    /**
     * Testing initial x value.
     */
    @Test
    void testInitialX() {
        assertThat(cell.getInitialX()).isEqualTo(INITIALX);
    }

    /**
     * Testing initial Y value.
     */
    @Test
    void testInitialY() {
        assertThat(cell.getInitialY()).isEqualTo(INITIALY);
    }

    /**
     * Testing currentY.
     */
    @Test
    void testCurrentY() {
        cell.setCurrentY(3);
        assertThat(cell.getCurrentY()).isEqualTo(3);
    }

    /**
     * Testing currentX.
     */
    @Test
    void testCurrentX() {
        cell.setCurrentX(3);
        assertThat(cell.getCurrentY()).isEqualTo(3);
    }


    /**
     *  TEsting Y getter.
     */
    @Test
    void getY() {
        assertThat(cell.getCurrentY()).isEqualTo(INITIALY);
    }


    /**
     * Testing if Cell is empty.
     */
    @org.junit.jupiter.api.Test
    void adjecentCellsEmpty() {
        assertThat(cell.getAdjacentCells().size()).isEqualTo(0);
    }


    /**
     * Testing getting adjecent cells.
     */
    @org.junit.jupiter.api.Test
    void adjecentCellsOtherCell() {
        Cell otherCell = new Cell(INITIALX, INITIALY);
        cell.getAdjacentCells().add(otherCell);
        assertThat(cell.getAdjacentCells().get(0)).isEqualTo(otherCell);
    }

    /**
     * Testing adjectent cells more than one.
     */
    @org.junit.jupiter.api.Test
    void adjecentCellsFilled() {
        Cell otherCell = new Cell(INITIALX, INITIALY);
        cell.getAdjacentCells().add(otherCell);
        cell.getAdjacentCells().add(otherCell);
        cell.getAdjacentCells().add(otherCell);
        cell.getAdjacentCells().add(otherCell);
        assertThat(cell.getAdjacentCells().size()).isEqualTo(4);
    }



}