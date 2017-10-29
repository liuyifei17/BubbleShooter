package UnitTests.Model;

import Controller.GameConfiguration;
import Controller.GameController;
import Controller.GridController;
import Model.*;
import View.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * This class provides test cases for the Grid class.
 */
class GridTest {
    private Grid grid;
    private View view;
    private GridController gridController;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        grid = new Grid(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);

        gridController = new GridController(null, grid);
        view = Mockito.mock(View.class);
        GameController.setView(view);
    }

    @Test
    void testConstructor() {
        assertThat(grid.getCells()).isNotNull();
        assertThat(grid.getOccupiedCells()).isNotNull();
        assertThat(grid.getCenterCell().getInitialX()).isEqualTo(GameConfiguration.stageWidth / 2);
        assertThat(grid.getCenterCell().getInitialY()).isEqualTo((GameConfiguration.stageHeight
                + GameConfiguration.topBarHeight) / 2);
    }

    @Test
    void testConstructor_rotation() {
        assertThat(gridController.getRotationDifference()).isEqualTo(0);
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
        cell.setBall(new Ball("blue", cell, 1));
        assertThat(grid.closestFullCellToLocation(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2))
                .isEqualTo(cell);
    }

    @Test
    void appendAdditionalBallsTest() {
        grid.getCenterCell().setBall(new Ball("center", grid.getCenterCell(), 1));
        grid.getOccupiedCells().add(grid.getCenterCell());

        grid.appendAdditionalBalls(5);

        assertThat(grid.getOccupiedCells().size()).isEqualTo(6);
    }

    @Test
    void isStillRotating() {
        assertThat(grid.getStillRotating()).isFalse();
    }
}