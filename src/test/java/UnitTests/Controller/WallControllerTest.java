package UnitTests.Controller;

import Controller.GUIConfiguration;
import Controller.GameConfiguration;
import Controller.GameController;
import Controller.WallController;
import Model.Cell;
import Model.GameData;
import Model.Grid;
import View.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * This class provides test cases for the WallController class.
 */
class WallControllerTest {
    WallController wallController;
    GameData gameData = mock(GameData.class);
    Grid grid;
    private View view;

    @BeforeEach
    void setUp() {
        view = mock(View.class);
        GUIConfiguration.isApiDefault();
        GameConfiguration.isApiDefault();
        grid = new Grid(GUIConfiguration.stageWidth, GUIConfiguration.stageHeight);
        GameController.setView(view);
        GameConfiguration.amountOfWalls = 2;
    }

    @Test
    void placeWalls() {
        System.out.println(GameConfiguration.amountOfWalls);
        when(gameData.getGrid()).thenReturn(grid);
        ArrayList arrayList2 = new ArrayList();
        when(gameData.getRandomWalls()).thenReturn(arrayList2);
        ArrayList<Cell> arrayList = new ArrayList<>();
        arrayList.add(mock(Cell.class));
        arrayList.add(mock(Cell.class));
        arrayList.add(mock(Cell.class));
        wallController = new WallController(gameData);
        wallController.placeWalls();
        verify(gameData, times(3)).getRandomWalls();
    }

}