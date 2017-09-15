package Controller;

import Model.Ball;
import Model.Cell;
import Model.Grid;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by Henks Laptop on 10/09/2017.
 */
class GridControllerTest {
    Grid grid;
    GridController gridController;

    @BeforeEach
    public void setUp() {
        grid = mock(Grid.class);
        gridController = new GridController(grid);
    }
    @Test
    void processPositiveRotation() {
        when(grid.getRotationDifference()).thenReturn(10);
        gridController.process();
        verify(grid).setRotationDifference(anyInt());
        verify(grid).setRotation(anyInt());
    }


    @Test
    void processNegativeRotation() {
        when(grid.getRotationDifference()).thenReturn(-19);
        gridController.process();
        verify(grid).setRotationDifference(anyInt());
        verify(grid).setRotation(anyInt());
    }

    @Test
    void processNoRotation() {
        when(grid.getRotationDifference()).thenReturn(0);
        gridController.process();
        verify(grid, times(0)).setRotationDifference(anyInt());
        verify(grid, times(0)).setRotation(anyInt());
    }

    @Test
    void cellTest() {
        when(grid.getRotationDifference()).thenReturn(-19);
        Cell cell = mock(Cell.class);
        Ball ball = mock(Ball.class);

        Cell centerCell = mock(Cell.class);
        when(centerCell.getCurrentX()).thenReturn(20.0);
        when(centerCell.getCurrentY()).thenReturn(20.0);
        when(cell.getElement()).thenReturn(ball);
        when(ball.getSprite()).thenReturn(mock(Image.class));
        when(cell.hasCollidedWithWall()).thenReturn(true);
        when(grid.getCenterCell()).thenReturn(centerCell);


        ArrayList<Cell> cellList = new ArrayList<Cell>();
        cellList.add(cell);
        when(grid.getCells()).thenReturn(cellList);

        gridController.process();
        verify(cell).getInitialX();
        verify(cell).getInitialY();

        verify(cell).setCurrentY(anyDouble());
        verify(cell).setCurrentX(anyDouble());

    }

}