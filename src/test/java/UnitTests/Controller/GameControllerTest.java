package UnitTests.Controller;

import Controller.GameController;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * This class provides test cases for
 */
class GameControllerTest {
    private GameController gameController;
    private Stage stage;


    @BeforeEach
    void setUp() {
        stage = mock(Stage.class);
        gameController = new GameController(stage);
    }

    @Test
    void getView() {
    }

    @Test
    void setup() {
    }

    @Test
    void gameOver() {
    }

}