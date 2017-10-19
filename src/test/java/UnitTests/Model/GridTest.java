package UnitTests.Model;

import Controller.GameConfiguration;
import Controller.GameController;
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

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        grid = new Grid(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);


        view = Mockito.mock(View.class);
        GameController.setView(view);
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
}