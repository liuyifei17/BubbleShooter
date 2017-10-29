package UnitTests.Controller;

import Controller.GameConfiguration;
import Controller.GameController;
import Controller.GameDataLoader;
import Controller.PlayerBallController;
import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    private GameDataLoader dataLoader;
    private GameData gameData;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        playerBallFactory = PlayerBallFactory.getInstance();
        gameController = new GameController(null);
        dataLoader = new GameDataLoader();
        player = new Player();
        grid = new Grid(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        gameData = new GameData(grid, player, 90);
        dataLoader.initialize(gameData);
        playerBall = playerBallFactory.createBall("Normal Ball", 100, 100);
        player.setPlayerBall(playerBall);
        pbc = new PlayerBallController(gameController, gameData.getPlayer(), gameData.getGrid(),
                null);
    }

    @Test
    void calculateDeltaTest_normal() {
        pbc.setMouseX(200);
        pbc.setMouseY(200);

        pbc.calculateDelta();

        assertThat(pbc.getDeltaX()).isBetween(3.534, 3.536);
        assertThat(pbc.getDeltaY()).isBetween(3.534, 3.536);
    }

    @Test
    void calculateDeltaTest_secondTime() {
        pbc.setMouseX(200);
        pbc.setMouseY(200);

        pbc.calculateDelta();
        pbc.setMouseX(250);
        pbc.setMouseY(60);
        pbc.calculateDelta();

        assertThat(pbc.getDeltaX()).isBetween(3.534, 3.536);
        assertThat(pbc.getDeltaY()).isBetween(3.534, 3.536);
    }

    @Test
    void launchBallTest_invalidClick() {
        pbc.launchBall();

        assertThat(pbc.getStopWatch()).isEqualTo(0);
    }

    @Test
    void launchBallTest_noCollision() {
        pbc.setMouseX(GameConfiguration.stageWidth);
        pbc.setMouseY((GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        pbc.setDeltaX(1);
        pbc.setDeltaY(1);

        pbc.launchBall();

        assertThat(playerBall.getX()).isEqualTo(101);
        assertThat(playerBall.getY()).isEqualTo(101);
    }

    @Test
    void launchBallTest_wallCollision1() {
        playerBall.setX(GameConfiguration.stageWidth);
        playerBall.setY((GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        pbc.setDeltaX(1);
        pbc.setDeltaY(1);
        pbc.setMouseY(1);

        pbc.launchBall();

        assertThat(pbc.getDeltaX()).isEqualTo(-1);
        assertThat(pbc.getDeltaY()).isEqualTo(1);
    }

    @Test
    void launchBallTest_wallCollision2() {
        playerBall.setX(GameConfiguration.stageWidth / 2);
        playerBall.setY(GameConfiguration.stageHeight + GameConfiguration.topBarHeight);
        pbc.setDeltaX(1);
        pbc.setDeltaY(1);
        pbc.setMouseY(1);

        pbc.launchBall();

        assertThat(pbc.getDeltaX()).isEqualTo(1);
        assertThat(pbc.getDeltaY()).isEqualTo(-1);
    }

    @Test
    void launchBallTest_wallCollsion_4times() {
        pbc.setMouseY(1);

        for (int i = 0; i < 4; i++) {
            playerBall.setX(GameConfiguration.stageWidth / 2);
            playerBall.setY(GameConfiguration.stageHeight + GameConfiguration.topBarHeight);
            pbc.setDeltaX(1);
            pbc.setDeltaY(1);
            pbc.launchBall();
        }
        pbc.launchBall();

        assertThat(pbc.getDeltaX()).isEqualTo(0);
        assertThat(pbc.getDeltaY()).isEqualTo(0);
        assertThat(player.getPlayerBall()).isNotEqualTo(playerBall);
        assertThat(pbc.getStopWatch()).isEqualTo(0);
    }


}
