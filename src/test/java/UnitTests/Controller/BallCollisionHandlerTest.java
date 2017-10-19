package UnitTests.Controller;

import Controller.GameConfiguration;
import Controller.GameController;
import Controller.GameDataLoader;
import Controller.PlayerBallController;
import Model.*;
import View.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class provides test cases for the BallCollisionHandler class
 */
public class BallCollisionHandlerTest {

    private Grid grid;
    private Player player;
    private GameController gameController;
    private PlayerBallController pbc;
    private PlayerBall playerBall;
    private PlayerBallFactory playerBallFactory;
    private GameDataLoader dataLoader;
    private GameData gameData;
    private View view;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        playerBallFactory = new PlayerBallFactory();
        gameController = new GameController(null);
        view = Mockito.mock(View.class);
        GameController.setView(view);
        dataLoader = new GameDataLoader();
        player = new Player();
        grid = new Grid(GameConfiguration.stageWidth / 2,
                (GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        gameData = new GameData(grid, player, 6);
        dataLoader.initialize(gameData);
        pbc = new PlayerBallController(gameController, gameData.getPlayer(), gameData.getGrid());
    }

    @Test
    void handleCollision_miss() {
        pbc.setMouseX(GameConfiguration.stageWidth / 2);
        pbc.setMouseY((GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        for (Cell c : grid.getCenterCell().getAdjacentCells()) {
            c.setBall(new Ball("blue", c, 1));
        }
        Cell c = grid.getCenterCell().getAdjacentCellInDirection(0, -1)
                .getAdjacentCellInDirection(0, -1);
        playerBall = new NormalBall("yellow", c.getCurrentX(), c.getCurrentY());
        player.setPlayerBall(playerBall);
        pbc.calculateDelta();

        pbc.launchBall();

        assertThat(c.getBall().getColor()).isEqualTo(playerBall.getColor());
        assertThat(grid.getOccupiedCells()).hasSize(8);
    }

    @Test
    void handleCollision_successfulShot() {
        pbc.setMouseX(GameConfiguration.stageWidth / 2);
        pbc.setMouseY((GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        for (Cell c : grid.getCenterCell().getAdjacentCells()) {
            c.setBall(new Ball("blue", c, 1));
        }
        Cell c = grid.getCenterCell().getAdjacentCellInDirection(0, -1)
                .getAdjacentCellInDirection(0, -1);
        playerBall = new NormalBall("blue", c.getCurrentX(), c.getCurrentY());
        player.setPlayerBall(playerBall);
        pbc.calculateDelta();

        pbc.launchBall();

        assertThat(grid.getOccupiedCells()).hasSize(1);
        assertThat(player.getScore()).isEqualTo(7);
    }

    @Test
    void handleCollision_5misses() {
        pbc.setMouseX(GameConfiguration.stageWidth / 2);
        pbc.setMouseY((GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        for (Cell c : grid.getCenterCell().getAdjacentCells()) {
            c.setBall(new Ball("blue", c, 1));
        }
        Cell c = grid.getCenterCell().getAdjacentCellInDirection(0, -1)
                .getAdjacentCellInDirection(0, -1);
        playerBall = new NormalBall("red", c.getCurrentX(), c.getCurrentY());
        player.setPlayerBall(playerBall);
        player.setMissCounter(5);
        pbc.calculateDelta();

        pbc.launchBall();

        assertThat(grid.getOccupiedCells().size()).isBetween(11, 23);
        assertThat(player.getScore()).isEqualTo(0);
    }
}
