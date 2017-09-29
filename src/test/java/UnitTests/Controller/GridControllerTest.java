package UnitTests.Controller;

import Controller.GameController;
import Controller.GridController;
import Model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * GridControllerTest.
 */
public class GridControllerTest {

    private GameController gameController = mock(GameController.class);
    private Grid grid = new Grid(300,350);

    private GridController gridController;

    @BeforeEach
    void setUp() {
        gridController = new GridController(gameController,grid);
    }

    @Test
    void constructorTest() {
        Grid newGrid = new Grid(200,200);
        gridController.setGrid(newGrid);
        assertTrue(gridController.getGrid().getCenterX() == 200);
        assertTrue(gridController.getGrid().getCenterY() == 200);
    }

    @Test
    void setGridTest() {
        Grid newGrid = new Grid(200,200);
        gridController.setGrid(newGrid);
        assertTrue(gridController.getGrid().getCenterX() == 200);
    }

    @Test
    void progressTest() {

    }
}
