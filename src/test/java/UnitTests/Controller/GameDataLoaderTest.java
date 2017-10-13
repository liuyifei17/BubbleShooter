package UnitTests.Controller;

import Controller.GameDataLoader;
import Model.GameData;
import Model.Grid;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class provides test cases for the GameDataLoader class.
 */
class GameDataLoaderTest {
    GameDataLoader gameDataLoader;
    GameData gameData = mock(GameData.class);



   // @Test
    void initialize() {
        gameDataLoader = new GameDataLoader();
        Grid grid = mock(Grid.class);
        when(gameData.getGrid()).thenReturn(grid);
        gameDataLoader.initialize(gameData, 4, 3);
        verify(gameData).getGrid();

    }

}