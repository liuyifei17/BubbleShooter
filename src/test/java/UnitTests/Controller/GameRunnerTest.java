package UnitTests.Controller;

import Controller.GameRunner;
import Controller.GridController;
import Controller.PlayerBallController;
import Model.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.Timer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Henks Laptop on 29/09/2017.
 */
class GameRunnerTest {
    GameRunner gameRunner;
    Timer timer;

    @BeforeEach
    void setUp() {
        timer = mock(Timer.class);
        gameRunner = new GameRunner(mock(GridController.class), mock(PlayerBallController.class), timer);
    }

    @Test
    void runGame() {
        gameRunner.runGame();
        verify(timer).start();
    }

    @Test
    void pauseGame() {
        gameRunner.pauseGame();
        verify(timer).stop();
    }

    @Test
    void continueGame() {
        gameRunner.runGame();
        verify(timer).start();
    }


}