package UnitTests.Controller;

import Controller.*;
import Model.*;
import View.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * This class provides test cases for the BallCollisionHandler class
 */
public class BallCollisionHandlerTest {

    private Grid grid;
    private Player player;
    private GameController gameController;
    private PlayerBallController pbc;
    private PlayerBall playerBall;
    private GameDataLoader dataLoader;
    private GameData gameData;
    private GridController gridController;
    private View view;
    private String blue;

    @BeforeEach
    void setUp() {
        GUIConfiguration.isApiDefault();
        GameConfiguration.isApiDefault();
        blue = "blue";
        gameController = new GameController(null);
        view = mock(View.class);
        GameController.setView(view);
        dataLoader = new GameDataLoader();
        player = new Player();
        grid = new Grid(GUIConfiguration.stageWidth / 2,
                (GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);
        gridController = new GridController(gameController, grid);
        gameData = new GameData(grid, player, 6);
        dataLoader.initialize(gameData);
        pbc = new PlayerBallController(gameController, gameData.getPlayer(), gameData.getGrid(),
                gridController);
    }

    @Test
    void handleCollision_miss() {

        pbc.setMouseX(GUIConfiguration.stageWidth / 2);
        pbc.setMouseY((GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);
        for (Cell c : grid.getCenterCell().getAdjacentCells()) {
            c.setBall(new Ball(blue, c, 1));
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
        pbc.setMouseX(GUIConfiguration.stageWidth / 2);
        pbc.setMouseY((GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);
        for (Cell c : grid.getCenterCell().getAdjacentCells()) {
            c.setBall(new Ball(blue, c, 1));
        }
        Cell c = grid.getCenterCell().getAdjacentCellInDirection(0, -1)
                .getAdjacentCellInDirection(0, -1);
        playerBall = new NormalBall(blue, c.getCurrentX(), c.getCurrentY());
        player.setPlayerBall(playerBall);
        pbc.calculateDelta();

        pbc.launchBall();

        assertThat(grid.getOccupiedCells()).hasSize(8);
        assertThat(player.getScore()).isEqualTo(7);
    }

    @Test
    void handleCollision_5misses() {
        pbc.setMouseX(GUIConfiguration.stageWidth / 2);
        pbc.setMouseY((GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);
        for (Cell c : grid.getCenterCell().getAdjacentCells()) {
            c.setBall(new Ball(blue, c, 1));
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
    @Test
    void handleCollisionRainbow() {
        pbc.setMouseX(GUIConfiguration.stageWidth / 2);
        pbc.setMouseY((GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);
        for (Cell c : grid.getCenterCell().getAdjacentCells()) {
            c.setBall(new Ball("blue", c, 1));
        }
        Cell c = grid.getCenterCell().getAdjacentCellInDirection(0, -1)
                .getAdjacentCellInDirection(0, -1);
        playerBall = new RainbowBall("Rainbow", c.getCurrentX(), c.getCurrentY());
        player.setPlayerBall(playerBall);
        pbc.calculateDelta();

        pbc.launchBall();

        assertThat(grid.getOccupiedCells()).hasSize(8);
    }
    @Test
    void handleCollisionExplosive() {
        pbc.setMouseX(GUIConfiguration.stageWidth / 2);
        pbc.setMouseY((GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);
        for (Cell c : grid.getCenterCell().getAdjacentCells()) {
            c.setBall(new Ball("blue", c, 1));
        }
        Cell c = grid.getCenterCell().getAdjacentCellInDirection(0, -1)
                .getAdjacentCellInDirection(0, -1);
        playerBall = new ExplosiveBall("Explosive", c.getCurrentX(), c.getCurrentY());
        player.setPlayerBall(playerBall);
        pbc.calculateDelta();

        pbc.launchBall();

        assertThat(grid.getOccupiedCells()).hasSize(8);
    }

    @Test
    void handleCollisionMultiplier() {
        pbc.setMouseX(GUIConfiguration.stageWidth / 2);
        pbc.setMouseY((GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);
        for (Cell c : grid.getCenterCell().getAdjacentCells()) {
            c.setBall(new Ball("blue", c, 1));
        }
        Cell c = grid.getCenterCell().getAdjacentCellInDirection(0, -1)
                .getAdjacentCellInDirection(0, -1);
        playerBall = new MultiplierBall("Multiplier", c.getCurrentX(), c.getCurrentY(), 3);
        player.setPlayerBall(playerBall);
        pbc.calculateDelta();

        pbc.launchBall();

        assertThat(grid.getOccupiedCells()).hasSize(8);
    }

}
