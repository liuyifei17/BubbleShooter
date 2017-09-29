package UnitTests.Model;

import Model.Cell;
import Model.Element;
import Model.Grid;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Grid Test.
 */
class GridTest {
    private Grid grid;
    private double Horizontal = 10;
    private double Vertical = 20;


    /**
     * setup.
     */
    @BeforeEach
    void setUp() {
        grid = new Grid(Horizontal, Vertical);
    }


    /**
     * Testing closest to location.
     */
    @Test
    void closestCellToLocation() {
        Cell cell = grid.getCenterCell();
        assertThat(grid.closestCellToLocation(Horizontal, Vertical)).isEqualTo(cell);
    }

    /**
     * Testing closest empty cell.
     */
    @Test
    void closestEmptyCellToLocation() {
        Cell someCell = mock(Cell.class);
        Element someElement = mock(Element.class);
        when(someCell.getElement()).thenReturn(someElement);
        when(someElement.getSprite()).thenReturn(null);
        Cell cell = grid.closestEmptyCellToLocation(Horizontal, Horizontal);

        assertThat(cell.getElement().getSprite()).isNull();
    }

    /**
     * Testing full cell to location.
     */
    @Test
    void closestFullCellToLocation() {
        Cell someCell = mock(Cell.class);
        Element someElement = mock(Element.class);
        when(someCell.getElement()).thenReturn(someElement);
        when(someElement.getSprite()).thenReturn(mock(Image.class));

        grid.getCells().add(someCell);
        Cell cell = grid.closestFullCellToLocation(Horizontal, Vertical);
        assertThat(cell).isNotNull();
    }

    /**
     * Testing rotation difference.
     */
    @Test
    void getRotationDifference() {
        grid.setRotationDifference(20);
        assertThat(grid.getRotationDifference()).isEqualTo(20);
    }


    /**
     * Testing rotation Speed.
     */
    @Test
    void getRotationSpeed() {
        assertThat(grid.getRotationSpeed()).isEqualTo(grid.getRotationSpeed());
    }


}