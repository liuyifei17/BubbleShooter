package UnitTests.Model;

import Controller.GameConfiguration;
import Controller.GUIConfiguration;
import Model.*;
import Model.PlayerBall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * This test provides test cases for the NormalBall test.
 */
public class NormalBallTest {

    private PlayerBallFactory playerBallFactory;
    private String normalBall;
    @BeforeEach
    void setUp() {
        GUIConfiguration.isApiDefault();
        GameConfiguration.isApiDefault();

        normalBall = "Normal Ball";
        playerBallFactory = PlayerBallFactory.getInstance();
    }

    @Test
    void testNormalBallConstructor() {
        PlayerBall pb = playerBallFactory.createBall(normalBall,
                GUIConfiguration.ballRadius, GUIConfiguration.stageWidth / 2);

        assertThat(pb.getCounter()).isEqualTo(0);
        assertTrue(GameConfiguration.colors.contains(pb.getColor()));
    }

    @Test
    void testSetCoordinates() {
        PlayerBall pb = playerBallFactory.createBall(normalBall,
                GUIConfiguration.ballRadius, GUIConfiguration.stageWidth / 2);

        pb.setX(5);
        pb.setY(8);

        assertAll("Gina", () -> assertThat(pb.getX()).isEqualTo(5),
                () -> assertThat(pb.getY()).isEqualTo(8));
    }

    @Test
    void hasCollidedWithWallTest_false() {
        PlayerBall pb = playerBallFactory.createBall(normalBall,
                GUIConfiguration.ballRadius + 1,
                GUIConfiguration.stageHeight / 2);

        assertThat(pb.hasCollidedWithWall()).isFalse();
    }

    @Test
    void hasCollidedWithWallTest_true() {
        PlayerBall pb = playerBallFactory.createBall(normalBall,
                GUIConfiguration.stageWidth, 0);

        assertThat(pb.hasCollidedWithWall()).isTrue();
    }

    @Test
    void getCollisionTest_close() {
        Grid grid = Mockito.mock(Grid.class);
        PlayerBall pb = playerBallFactory.createBall(normalBall, 200, 200);
        Cell fullCell = new Cell(210, 210);
        Cell emptyCell = new Cell(205, 205);

        Mockito.when(grid.closestFullCellToLocation(pb.getX(), pb.getY())).thenReturn(fullCell);
        Mockito.when(grid.closestEmptyCellToLocation(fullCell.getCurrentX(),
                fullCell.getCurrentY())).thenReturn(emptyCell);

        assertThat(pb.getCellCollision(grid, 1, 1)).isEqualTo(emptyCell);
    }

    @Test
    void getCollisionTest_far() {
        Grid grid = Mockito.mock(Grid.class);
        PlayerBall pb = playerBallFactory.createBall(normalBall, 200, 200);
        Cell fullCell = new Cell(250, 250);
        Cell emptyCell = new Cell(300, 300);

        Mockito.when(grid.closestFullCellToLocation(pb.getX(), pb.getY())).thenReturn(fullCell);
        Mockito.when(grid.closestEmptyCellToLocation(fullCell.getCurrentX(),
                fullCell.getCurrentY())).thenReturn(emptyCell);
        Mockito.when(grid.closestEmptyCellToLocation(pb.getX(), pb.getY())).thenReturn(emptyCell);

        assertThat(pb.getCellCollision(grid, 1, 1)).isEqualTo(null);
    }

    @Test
    void getCollisionTest_forced() {
        Grid grid = Mockito.mock(Grid.class);
        PlayerBall pb = playerBallFactory.createBall(normalBall, 200, 200);
        Cell fullCell = new Cell(200, 211);
        Cell emptyCell = new Cell(300, 300);

        Mockito.when(grid.closestFullCellToLocation(pb.getX(), pb.getY())).thenReturn(fullCell);
        Mockito.when(grid.closestEmptyCellToLocation(fullCell.getCurrentX(),
                fullCell.getCurrentY())).thenReturn(emptyCell);
        Mockito.when(grid.closestEmptyCellToLocation(pb.getX(), pb.getY())).thenReturn(emptyCell);

        assertThat(pb.getCellCollision(grid, 1, 1)).isEqualTo(emptyCell);
    }

    @Test
    void hasCollidedRandomWallTrue() {
        Walls wall = mock(Walls.class);
        PlayerBall pb = playerBallFactory.createBall("Normal Ball", 200, 200);
        pb.hasCollidedWithRandomWall(wall);
        verify(wall, times(4)).getX();
    }
}
