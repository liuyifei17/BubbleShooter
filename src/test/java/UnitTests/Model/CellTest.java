package UnitTests.Model;

import Model.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * This class provides test cases for the Cell class.
 */
class CellTest {
    private Cell cell;
    private double INITIALX = 4.0;
    private double INITIALY = 3.0;



    @Test
    void getElement1() {

    }

    @Test
    void setElement1() {
    }

    @Test
    void getAdjacentCells() {
    }

    @Test
    void getInitialX() {
    }

    @Test
    void getInitialY() {
    }

    @Test
    void getCurrentX() {
    }

    @Test
    void setCurrentX() {
    }

    @Test
    void getCurrentY() {
    }

    @Test
    void setCurrentY() {
    }



    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        cell = new Cell(INITIALX, INITIALY);
    }

    @org.junit.jupiter.api.Test
    void getElement() {

    }

    @org.junit.jupiter.api.Test
    void setElement() {
    }
    
    @org.junit.jupiter.api.Test
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

}