package UnitTests.Model;

import Model.GameData;
import Model.Grid;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * This class provides test cases for the GameData class
 */
public class GameDataTest {


    private GameData gameData;

    @BeforeEach
    public void setUp() {
        gameData = new GameData(null, null, 0);
    }

    @Test
    void getGridTest() {
        Grid grid = mock(Grid.class);
        gameData.setGrid(grid);
        assertThat(gameData.getGrid()).isEqualTo(grid);
    }

    @Test
    void setGrid() {
        Grid grid = mock(Grid.class);
        gameData.setGrid(grid);
        assertThat(gameData.getGrid()).isEqualTo(grid);
    }

    @Test
    void getInitialBallAmount() {
        gameData.setInitialBallAmount(10);
        assertThat(gameData.getInitialBallAmount()).isEqualTo(10);

    }

    @Test
    void setInitialBallAmount() {
        gameData.setInitialBallAmount(10);
        assertThat(gameData.getInitialBallAmount()).isEqualTo(10);
    }

    @Test
    void getPlayer() {
        Player player = mock(Player.class);
        gameData.setPlayer(player);
        assertThat(gameData.getPlayer()).isEqualTo(player);
    }
}