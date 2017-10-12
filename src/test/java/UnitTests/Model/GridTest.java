package UnitTests.Model;

import Model.Ball;
import Model.Cell;
import Model.Grid;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Henks Laptop on 28/09/2017.
 */
class GridTest {
    Grid grid;
    double Horizontal = 10;
    double Vertical = 20;

    @BeforeEach
    void setUp() {
        grid = new Grid(Horizontal, Vertical);
    }

    @Test
    void closestCellToLocation() {
        Cell cell = grid.getCenterCell();
        assertThat(grid.closestCellToLocation(Horizontal, Vertical)).isEqualTo(cell);
    }

    /*@Test
    void closestEmptyCellToLocation() {
        Cell someCell = mock(Cell.class);
        Ball someElement = mock(Ball.class);
        when(someCell.getBall()).thenReturn(someElement);
        //when(someElement.getSprite()).thenReturn(null);
        Cell cell = grid.closestEmptyCellToLocation(Horizontal, Horizontal);

        assertThat(cell.getElement().getSprite()).isNull();
    }

    @Test
    void closestFullCellToLocation() {
        Cell someCell = mock(Cell.class);
        Element someElement = mock(Element.class);
        when(someCell.getElement()).thenReturn(someElement);
        when(someElement.getSprite()).thenReturn(mock(Image.class));

        grid.getCells().add(someCell);
        Cell cell = grid.closestFullCellToLocation(Horizontal, Vertical);
        assertThat(cell).isNotNull();
    }*/

    @Test
    void getCells() {
    }

    @Test
    void getCenterCell() {
    }

    @Test
    void getRotation() {
    }

    @Test
    void setRotation() {
    }

    @Test
    void getRotationDifference() {
        grid.setRotationDifference(20);
        assertThat(grid.getRotationDifference()).isEqualTo(20);
    }


    @Test
    void getRotationSpeed() {
        assertThat(grid.getRotationSpeed()).isEqualTo(grid.getRotationSpeed());
    }

    @Test
    void getOccupiedCells() {

        grid.getOccupiedCells();
    }

}