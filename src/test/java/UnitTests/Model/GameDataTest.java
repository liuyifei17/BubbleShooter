package UnitTests.Model;

import Model.GameData;
import Model.Grid;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * This class provides test cases for the GameData class.
 */
class GameDataTest {


    private GameData gameData;

    /**
     * Setup.
     */
    @BeforeEach
    void setUp() {
        gameData = new GameData();
    }

    /**
     * getGridTEst.
     */
    @Test
    void getGridTest() {
        Grid grid = mock(Grid.class);
        gameData.setGrid(grid);
        assertThat(gameData.getGrid()).isEqualTo(grid);
    }

    /**
     * Testing setter.
     */
    @Test
    void setGrid() {
        Grid grid = mock(Grid.class);
        gameData.setGrid(grid);
        assertThat(gameData.getGrid()).isEqualTo(grid);
    }

    /**
     * Testing Ball amount.
     */
    @Test
    void getInitialBallAmount() {
        gameData.setInitialBallAmount(10);
        assertThat(gameData.getInitialBallAmount()).isEqualTo(10);

    }

    /**
     * Testing setter ball amount.
     */
    @Test
    void setInitialBallAmount() {
        gameData.setInitialBallAmount(10);
        assertThat(gameData.getInitialBallAmount()).isEqualTo(10);
    }

    /**
     * testgin player Getter.
     */
    @Test
    void getPlayer() {
        Player player = mock(Player.class);
        gameData.setPlayer(player);
        assertThat(gameData.getPlayer()).isEqualTo(player);
    }




}