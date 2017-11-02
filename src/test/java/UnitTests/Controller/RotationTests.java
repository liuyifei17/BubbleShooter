package UnitTests.Controller;

import Controller.*;
import Model.Cell;
import Model.Grid;
import Model.NormalBall;
import Model.Player;
import View.View;
import org.assertj.core.util.CheckReturnValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class provides test cases for the different rotations.
 */
public class RotationTests {

    private Grid grid;
    private Player player;
    private GameController gameController;
    private GridController gridController;
    private View view;
    private PlayerBallController pbc;
    private NormalBall playerBall;

    @BeforeEach
    void setUp() {
        GUIConfiguration.isApiDefault();
        GameConfiguration.isApiDefault();

        grid = new Grid(GUIConfiguration.stageWidth / 2,
                (GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);
        gridController = new GridController(gameController, grid);
        player = Mockito.mock(Player.class);
        playerBall = Mockito.mock(NormalBall.class);
        view = Mockito.mock(View.class);
        GameController.setView(view);
        Mockito.when(player.getPlayerBall()).thenReturn(playerBall);
        pbc = new PlayerBallController(gameController, player, grid, gridController);
    }

    @Test
    @CheckReturnValue
    void launchBallTest_normalCollision_noRotation() {
        gameController = Mockito.mock(GameController.class);
        grid = new Grid(GUIConfiguration.stageWidth / 2,
                (GUIConfiguration.stageHeight + GUIConfiguration.topBarHeight) / 2);
        player = Mockito.mock(Player.class);
        playerBall = Mockito.mock(NormalBall.class);
        Mockito.when(player.getPlayerBall()).thenReturn(playerBall);
        Mockito.when(playerBall.getCellCollision(grid, 0, 0)).thenReturn(new Cell(102, 102));
        pbc = new PlayerBallController(gameController, player, grid, gridController);
        pbc.setMouseY(10);

        pbc.launchBall();
        assertThat(player.getMissCounter() >= 0).isTrue();
        Mockito.verify(player,
                Mockito.times(1)).setMissCounter(Mockito.anyInt());
        Mockito.verify(player, Mockito.times(1)).nextBall();
        assertThat(pbc.getMouseY()).isEqualTo(0);
        assertThat(gridController.getRotationDifference()).isEqualTo(0);
    }

    @Test
    void launchBallTest_normalCollision_rightRotation1() {
        pbc.setMouseY(10);
        pbc.setMouseX(10);
        pbc.calculateDelta();
        double x = pbc.getDeltaX();
        double y = pbc.getDeltaY();
        Mockito.when(playerBall.getCellCollision(grid, x, y))
                .thenReturn(new Cell(102, 102));

        pbc.launchBall();

        assertThat(gridController.getRotationDifference())
                .isEqualTo(GameConfiguration.rightRotation.get(0));
    }

    @Test
    void launchBallTest_normalCollision_rightRotation2() {
        pbc.setMouseY(10);
        pbc.setMouseX(10);
        pbc.calculateDelta();
        pbc.setStopWatch(81);
        double x = pbc.getDeltaX();
        double y = pbc.getDeltaY();
        Mockito.when(playerBall.getCellCollision(grid, x, y))
                .thenReturn(new Cell(102, 102));

        pbc.launchBall();

        assertThat(gridController.getRotationDifference())
                .isEqualTo(GameConfiguration.rightRotation.get(1));
    }

    @Test
    void launchBallTest_normalCollision_rightRotation3() {
        pbc.setMouseY(10);
        pbc.setMouseX(10);
        pbc.calculateDelta();
        pbc.setStopWatch(171);
        double x = pbc.getDeltaX();
        double y = pbc.getDeltaY();
        Mockito.when(playerBall.getCellCollision(grid, x, y))
                .thenReturn(new Cell(102, 102));

        pbc.launchBall();

        assertThat(gridController.getRotationDifference())
                .isEqualTo(GameConfiguration.rightRotation.get(2));
    }

    @Test
    void launchBallTest_normalCollision_leftRotation1() {
        pbc.setMouseY(10);
        pbc.setMouseX(-10);
        pbc.calculateDelta();
        double x = pbc.getDeltaX();
        double y = pbc.getDeltaY();
        Mockito.when(playerBall.getCellCollision(grid, x, y))
                .thenReturn(new Cell(102, 102));

        pbc.launchBall();

        assertThat(gridController.getRotationDifference())
                .isEqualTo(GameConfiguration.leftRotation.get(0));
    }

    @Test
    void launchBallTest_normalCollision_leftRotation2() {
        pbc.setMouseY(10);
        pbc.setMouseX(-10);
        pbc.calculateDelta();
        pbc.setStopWatch(81);
        double x = pbc.getDeltaX();
        double y = pbc.getDeltaY();
        Mockito.when(playerBall.getCellCollision(grid, x, y))
                .thenReturn(new Cell(102, 102));

        pbc.launchBall();

        assertThat(gridController.getRotationDifference())
                .isEqualTo(GameConfiguration.leftRotation.get(1));
    }

    @Test
    void launchBallTest_normalCollision_leftRotation3() {
        pbc.setMouseY(10);
        pbc.setMouseX(-10);
        pbc.calculateDelta();
        pbc.setStopWatch(171);
        double x = pbc.getDeltaX();
        double y = pbc.getDeltaY();
        Mockito.when(playerBall.getCellCollision(grid, x, y))
                .thenReturn(new Cell(102, 102));

        pbc.launchBall();

        assertThat(gridController.getRotationDifference())
                .isEqualTo(GameConfiguration.leftRotation.get(2));
    }
}
