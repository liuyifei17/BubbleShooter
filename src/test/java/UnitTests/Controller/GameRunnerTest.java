package UnitTests.Controller;

import Controller.GameRunner;
import Controller.GridController;
import Controller.PlayerBallController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.Timer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * This class provides test cases for the GameRunner class.
 */
class GameRunnerTest {
    GameRunner gameRunner;
    Timer timer;

    @BeforeEach
    void setUp() {
        timer = mock(Timer.class);
        gameRunner = new GameRunner(mock(GridController.class),
                mock(PlayerBallController.class), timer);
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
        gameRunner.continueGame();
        verify(timer).start();
    }


}