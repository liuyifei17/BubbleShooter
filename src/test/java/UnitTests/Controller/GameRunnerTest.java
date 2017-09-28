package UnitTests.Controller;

import Controller.GridController;
import Controller.PlayerBallController;
import org.junit.jupiter.api.Test;
import View.View;


import static org.mockito.Mockito.mock;

/**
 * This class provides test cases for the GameRunner class.
 */
class GameRunnerTest {
    GridController gridController = mock(GridController.class);
    View view = mock(View.class);
    PlayerBallController playerController = mock(PlayerBallController.class);


    @Test
    void runGame() {

    }

}