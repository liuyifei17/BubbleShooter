package UnitTests.Model;

import Controller.GUIConfiguration;
import Controller.GameConfiguration;
import Controller.GameController;
import Controller.GridController;
import Model.Ball;
import Model.Cell;
import Model.Grid;
import View.View;
import View.GameMenu;
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
    private GameMenu gameMenu;


    @BeforeEach
    void setUp() {
        GUIConfiguration.isApiDefault();
        GameConfiguration.isApiDefault();
        grid = new Grid(GUIConfiguration.stageWidth / 2,
                (GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);

        gridController = new GridController(null, grid);
        view = Mockito.mock(View.class);
        gameMenu = Mockito.mock(GameMenu.class);
        GameController.setView(view);
    }

    @Test
    void testConstructor() {
        assertThat(grid.getCells()).isNotNull();
        assertThat(grid.getOccupiedCells()).isNotNull();
        assertThat(grid.getCenterCell().getInitialX()).isEqualTo(GUIConfiguration.stageWidth / 2);
        assertThat(grid.getCenterCell().getInitialY()).isEqualTo((GUIConfiguration.stageHeight
                + GUIConfiguration.topBarHeight) / 2);
    }

    @Test
    void testConstructor_rotation() {
        assertThat(gridController.getRotationDifference()).isEqualTo(0);
    }

    @Test
    void closestEmptyCellToLocation() {
        Cell cell = grid.getCenterCell();
        assertThat(grid.closestEmptyCellToLocation(GUIConfiguration.stageWidth / 2,
                (GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2))
                .isEqualTo(cell);
    }

    @Test
    void closestFullCellToLocation() {
        Cell cell = grid.getCenterCell();
        cell.setBall(new Ball("blue", cell, 1));
        assertThat(grid.closestFullCellToLocation(GUIConfiguration.stageWidth / 2,
                (GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2))
                .isEqualTo(cell);
    }

    @Test
    void appendAdditionalBallsTest() {
        grid.getCenterCell().setBall(new Ball("center", grid.getCenterCell(), 1));
        grid.getOccupiedCells().add(grid.getCenterCell());
        view.setGameMenu(gameMenu);
        grid.appendAdditionalBalls(5);

        assertThat(grid.getOccupiedCells().size()).isEqualTo(6);
    }
}