package UnitTests.Model;

import Controller.GameConfiguration;
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
 * This class provides test cases for the Grid class.
 */
class GridTest {
    private Grid grid;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        grid = new Grid(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
    }

    @Test
    void testConstructor() {
        assertThat(grid.getCells()).isNotNull();
        assertThat(grid.getOccupiedCells()).isNotNull();
        assertThat(grid.getCenterX()).isEqualTo(GameConfiguration.stageWidth / 2);
        assertThat(grid.getCenterY()).isEqualTo((GameConfiguration.stageHeight
                + GameConfiguration.topBarHeight) / 2);
    }

    @Test
    void testConstructor_rotation() {
        assertThat(grid.getRotationDifference()).isEqualTo(0);
        assertThat(grid.getRotationSpeed()).isEqualTo(5);
        assertThat(grid.getRotation()).isEqualTo(180);
    }

    @Test
    void setRotationDifferenceTest() {
        grid.setRotationDifference(100);

        assertThat(grid.getRotationDifference()).isEqualTo(100);
    }

    @Test
    void setRotation() {
        grid.setRotation(100);

        assertThat(grid.getRotation()).isEqualTo(100);
    }

    @Test
    void closestEmptyCellToLocation() {
        Cell cell = grid.getCenterCell();
        assertThat(grid.closestEmptyCellToLocation(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2))
                .isEqualTo(cell);
    }

    @Test
    void closestFullCellToLocation() {
        Cell cell = grid.getCenterCell();
        cell.setBall(new Ball("blue", cell, true));
        assertThat(grid.closestFullCellToLocation(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2))
                .isEqualTo(cell);
    }
}