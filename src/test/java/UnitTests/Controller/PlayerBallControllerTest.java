package UnitTests.Controller;

import Controller.GameConfiguration;
import Controller.GameController;
import Controller.GameDataLoader;
import Controller.PlayerBallController;
import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class provides test cases for the PlayerBallController class.
 */
public class PlayerBallControllerTest {

    private Grid grid;
    private Player player;
    private GameController gameController;
    private PlayerBallController pbc;
    private PlayerBall playerBall;
    private PlayerBallFactory playerBallFactory;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        playerBallFactory = new PlayerBallFactory();
    }


    @Test
    void calculateDeltaTest_normal() {
        grid = Mockito.mock(Grid.class);
        player = Mockito.mock(Player.class);
        gameController = Mockito.mock(GameController.class);
        pbc = new PlayerBallController(gameController, player, grid);
        PlayerBall playerBall = playerBallFactory.createBall("Normal Ball", 50.0, 50.0);
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
        PlayerBall playerBall = playerBallFactory.createBall("Normal Ball", 50.0, 50.0);
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
    void launchBallTest_invalidClick() {
        gameController = new GameController(null);
        grid = new Grid(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        player = new Player();
        playerBall = playerBallFactory.createBall("Normal Ball", 100, 100);
        player.setPlayerBall(playerBall);
        pbc = new PlayerBallController(gameController, player, grid);

        pbc.launchBall();

        assertThat(pbc.getStopWatch()).isEqualTo(0);
    }

    @Test
    void launchBallTest_noCollision() {
        gameController = new GameController(null);
        GameDataLoader dataLoader = new GameDataLoader();
        player = new Player();
        Grid grid = new Grid(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        GameData gameData = new GameData(grid, player, 90);
        dataLoader.initialize(gameData);
        playerBall = playerBallFactory.createBall("Normal Ball", 100, 100);
        player.setPlayerBall(playerBall);
        double initialX = playerBall.getX();
        double initialY = playerBall.getY();
        pbc = new PlayerBallController(gameController, gameData.getPlayer(), gameData.getGrid());
        pbc.setMouseX(GameConfiguration.stageWidth);
        pbc.setMouseY((GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);

        pbc.launchBall();

        assertThat(playerBall.getX()).isEqualTo(initialX + pbc.getDeltaX());
        assertThat(playerBall.getY()).isEqualTo(initialY + pbc.getDeltaY());
    }

}
