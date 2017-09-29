package UnitTests.Controller;

import Controller.GameConfiguration;
import Controller.GameController;
import Controller.PlayerBallController;
import Model.Grid;
import Model.Player;
import Model.PlayerBall;
import javafx.scene.image.Image;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class provides test cases for the PlayerBallController class.
 */
public class PlayerBallControllerTest {

    Grid grid;
    Player player;
    GameController gameController;
    PlayerBallController pbc;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void calculateDeltaTest_normal() {
        grid = Mockito.mock(Grid.class);
        player = Mockito.mock(Player.class);
        gameController = Mockito.mock(GameController.class);
        pbc = new PlayerBallController(gameController, player, grid);
        PlayerBall playerBall = new PlayerBall(50.0, 50.0);
        Mockito.when(player.getPlayerBall()).thenReturn(playerBall);
        Mockito.when(player.getPlayerBall()).thenReturn(playerBall);
        pbc.setMouseX(200);
        pbc.setMouseY(200);

        pbc.calculateDelta();

        assertThat(pbc.getDeltaX()).isBetween(3.534, 3.536);
        assertThat(pbc.getDeltaY()).isBetween(3.534, 3.536);
    }


    @Test
    void calculateDeltaTest_secondTime() {
        grid = Mockito.mock(Grid.class);
        player = Mockito.mock(Player.class);
        gameController = Mockito.mock(GameController.class);
        pbc = new PlayerBallController(gameController, player, grid);
        PlayerBall playerBall = new PlayerBall(50.0, 50.0);
        Mockito.when(player.getPlayerBall()).thenReturn(playerBall);
        Mockito.when(player.getPlayerBall()).thenReturn(playerBall);
        pbc.setMouseX(200);
        pbc.setMouseY(200);

        pbc.calculateDelta();
        pbc.setMouseY(250);
        pbc.setMouseY(60);
        pbc.calculateDelta();

        Mockito.verify(player, Mockito.times(2)).getPlayerBall();

        assertThat(pbc.getDeltaX()).isBetween(3.534, 3.536);
        assertThat(pbc.getDeltaY()).isBetween(3.534, 3.536);
    }

    @Test
    void launchBallTest1() {
        gameController = new GameController(null);
        grid = new Grid(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        player = new Player();
        pbc = new PlayerBallController(gameController, player, grid);

        pbc.launchBall();

    }


}
