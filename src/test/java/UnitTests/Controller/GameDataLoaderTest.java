package UnitTests.Controller;

import Controller.GameDataLoader;
import Model.GameData;
import Model.Grid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Henks Laptop on 28/09/2017.
 */
class GameDataLoaderTest {
    GameDataLoader gameDataLoader = new GameDataLoader();
    GameData gameData = mock(GameData.class);

    @Test
    void initialize() {
    }

}