package UnitTests.Controller;

import Controller.GameConfiguration;
import Controller.GameController;
import Controller.GridController;
import Model.Ball;
import Model.Cell;
import Model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * GridControllerTest.
 */
class GridControllerTest {

    private GameController gameController = mock(GameController.class);
    private Grid grid = Mockito.mock(Grid.class);

    private GridController gridController;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        gridController = new GridController(gameController, grid);
    }

    @Test
    void constructorTest() {
        Grid newGrid = new Grid(200, 200);
        gridController.setGrid(newGrid);
        assertTrue(gridController.getGrid().getCenterX() == 200);
        assertTrue(gridController.getGrid().getCenterY() == 200);
    }

    @Test
    void setGridTest() {
        Grid newGrid = new Grid(200, 200);
        gridController.setGrid(newGrid);
        assertTrue(gridController.getGrid().getCenterX() == 200);
    }

    @Test
    void progressTest1() {
        Grid newGrid = mock(Grid.class);
        gridController.setGrid(newGrid);
        gridController.process();
        verify(newGrid,atLeast(1)).getRotationDifference();
    }

    @Test
    void progressTest2() {
        Grid newGrid = mock(Grid.class);
        when(newGrid.getRotationDifference()).thenReturn(1);
        when(newGrid.getRotationSpeed()).thenReturn(5);
        gridController.setGrid(newGrid);
        gridController.process();
        verify(newGrid).setRotationDifference(-4);
    }

    @Test
    void progressTest3() {
        Grid newGrid = mock(Grid.class);
        when(newGrid.getRotationDifference()).thenReturn(-1);
        when(newGrid.getRotationSpeed()).thenReturn(5);
        gridController.setGrid(newGrid);
        gridController.process();
        verify(newGrid).setRotationDifference(4);
    }

    /**
     * Testing ball rotation.
     */
    @Test
    void progressTest4() {
        Grid newGrid = mock(Grid.class);
        Cell cell = new Cell(2.0, 5.0);
        Cell centerCell = new Cell(2.0, 2.0);
        ArrayList<Cell> cellArrayList = new ArrayList<>();
        cellArrayList.add(cell);
        when(newGrid.getRotationDifference()).thenReturn(-1);
        when(newGrid.getRotationSpeed()).thenReturn(5);
        when(newGrid.getCells()).thenReturn(cellArrayList);
        when(newGrid.getCenterCell()).thenReturn(centerCell);
        gridController.setGrid(newGrid);
        gridController.process();
        assertThat(cell.getInitialY()).isNotEqualTo(cell.getCurrentY());
    }

    /**
     * Testing if the collision with wall ends the game.
     */
    @Test
    void progressGameOverTest() {
        Grid newGrid = mock(Grid.class);
        Cell cell = mock(Cell.class);
        Ball ball = mock(Ball.class);
        when(cell.getBall()).thenReturn(ball);
        Cell centerCell = new Cell(2.0, 2.0);
        when(cell.hasCollidedWithWall()).thenReturn(true);
        ArrayList<Cell> cellArrayList = new ArrayList<>();
        cellArrayList.add(cell);
        when(newGrid.getRotationDifference()).thenReturn(-1);
        when(newGrid.getCells()).thenReturn(cellArrayList);
        when(newGrid.getCenterCell()).thenReturn(centerCell);
        gridController.setGrid(newGrid);
        gridController.process();
        verify(gameController).gameOver();
    }
}
