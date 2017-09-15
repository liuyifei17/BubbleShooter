package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Henks Laptop on 07/09/2017.
 */
class GameDataTest {


    private GameData gameData;

    @BeforeEach
    public void setUp() {
        gameData = new GameData();
    }
    @Test
    private void getScore() {
        gameData.setScore(20);
        assertThat(gameData.getScore()).isEqualTo(20);
    }

    @Test
    private void setScore() {
        gameData.setScore(20);
        assertThat(gameData.getScore()).isEqualTo(20);

    }

    @Test
    private void getGrid() {
        Grid grid = mock(Grid.class);
        gameData.setGrid(grid);
        assertThat(gameData.getGrid()).isEqualTo(grid);
    }

    @Test
    private void setGrid() {
        Grid grid = mock(Grid.class);
        gameData.setGrid(grid);
        assertThat(gameData.getGrid()).isEqualTo(grid);
    }

    @Test
    private void getInitialBallAmount() {
        gameData.setInitialBallAmount(20);
        assertThat(gameData.getInitialBallAmount()).isEqualTo(20);
    }

}