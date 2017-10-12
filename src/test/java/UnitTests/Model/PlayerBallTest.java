package UnitTests.Model;

import Controller.GameConfiguration;
import Controller.PlayerBallController;
import Model.Cell;
import Model.Grid;
import Model.Player;
import Model.PlayerBall;
import Utility.Util;
import View.View;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * This test provides test cases for the PlayerBall test.
 */
public class PlayerBallTest {


    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
    }

    @Test
    void testPlayerBallConstructor() {
        PlayerBall pb = new PlayerBall("blue",
                GameConfiguration.ballRadius, GameConfiguration.stageWidth / 2);

        assertThat(pb.getCounter()).isEqualTo(0);
        assertThat(pb.getColor()).isEqualTo("blue");
    }

    /*@Test
    void testPlayerBallSetColorAndImage() {
        PlayerBall pb = new PlayerBall(GameConfiguration.ballRadius, GameConfiguration.stageWidth/2);
        Image im = Mockito.mock(Image.class);

        pb.setColor("blue");
        pb.setImage(im);

        assertThat(pb.getColor()).isEqualTo("blue");
        assertThat(pb.getImage()).isInstanceOf(Image.class);
    }*/

    @Test
    void testSetCoordinates() {
        PlayerBall pb = new PlayerBall("yellow", GameConfiguration.ballRadius, GameConfiguration.stageWidth/2);

        pb.setX(5);
        pb.setY(8);

        assertAll("Gina", () -> assertThat(pb.getX()).isEqualTo(5),
                () -> assertThat(pb.getY()).isEqualTo(8));
    }

    @Test
    void hasCollidedWithWallTest_false() {
        PlayerBall pb = new PlayerBall("red", GameConfiguration.ballRadius+1, GameConfiguration.stageHeight/2);

        assertThat(pb.hasCollidedWithWall()).isFalse();
    }

    @Test
    void hasCollidedWithWallTest_true() {
       PlayerBall pb = new PlayerBall("blue", GameConfiguration.stageWidth,0);

        assertThat(pb.hasCollidedWithWall()).isTrue();
    }


    @Test
    void getCollisionTest_close() {
        Grid grid = Mockito.mock(Grid.class);
        PlayerBall pb = new PlayerBall("blue", 200, 200);
        Cell fullCell = new Cell(210,210);
        Cell emptyCell = new Cell(205, 205);

        Mockito.when(grid.closestFullCellToLocation(pb.getX(), pb.getY())).thenReturn(fullCell);
        Mockito.when(grid.closestEmptyCellToLocation(fullCell.getCurrentX(),
                fullCell.getCurrentY())).thenReturn(emptyCell);

        assertThat(pb.getCellCollision(grid, 1, 1)).isEqualTo(emptyCell);
    }

    @Test
    void getCollisionTest_far() {
        Grid grid = Mockito.mock(Grid.class);
        PlayerBall pb = new PlayerBall("green", 200, 200);
        Cell fullCell = new Cell(250, 250);
        Cell emptyCell = new Cell(300, 300);

        Mockito.when(grid.closestFullCellToLocation(pb.getX(), pb.getY())).thenReturn(fullCell);
        Mockito.when(grid.closestEmptyCellToLocation(fullCell.getCurrentX(),
                fullCell.getCurrentY())).thenReturn(emptyCell);
        Mockito.when(grid.closestEmptyCellToLocation(pb.getX(), pb.getY())).thenReturn(emptyCell);

        assertThat(pb.getCellCollision(grid, 1, 1)).isEqualTo(null);
    }

    /**
     *
     */
    @Test
    void getCollisionTest_forced() {
        Grid grid = Mockito.mock(Grid.class);
        PlayerBall pb = new PlayerBall("blue", 200, 200);
        Cell fullCell = new Cell(200,211);
        Cell emptyCell = new Cell(300, 300);

        Mockito.when(grid.closestFullCellToLocation(pb.getX(), pb.getY())).thenReturn(fullCell);
        Mockito.when(grid.closestEmptyCellToLocation(fullCell.getCurrentX(),
                fullCell.getCurrentY())).thenReturn(emptyCell);
        Mockito.when(grid.closestEmptyCellToLocation(pb.getX(), pb.getY())).thenReturn(emptyCell);

        assertThat(pb.getCellCollision(grid, 1, 1)).isEqualTo(emptyCell);
    }
}
