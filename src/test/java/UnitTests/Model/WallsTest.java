package UnitTests.Model;

import Controller.GameConfiguration;
import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Henks Laptop on 28/10/2017.
 */
class WallsTest {
    Walls wall;
    Grid grid;
    PlayerBall ball;
    Cell cell;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();
        cell = mock(Cell.class);
        ball = mock(PlayerBall.class);
        grid = new Grid(GameConfiguration.stageWidth, GameConfiguration.stageHeight);
        wall = new Walls(2,3, 3);
    }


    @Test
    void calculateRotation() {
        wall.calculateRotation(grid);

    }

    @Test
    void calculateUpNormal() {
        assertThat(wall.calculateUpNormal()).isEqualTo(new double[]{2.0523359562429437, 2.0013704652454263});
    }

    @Test
    void calculateDownNormal() {
        assertThat(wall.calculateDownNormal()).isEqualTo(new double[]{1.9476640437570563, 3.9986295347545737});
    }

    @Test
    void calculateRightNormal() {
        assertThat(wall.calculateRightNormal()).isEqualTo(new double[]{1.0013704652454263, 2.9476640437570563});

    }

    @Test
    void calculateLeftNormal() {
        assertThat(wall.calculateLeftNormal()).isEqualTo(new double[]{2.9986295347545737, 3.0523359562429437});
    }

    @Test
    void topDistancesToBall() {

        assertThat(wall.topDistancesToBall(ball)).isEqualTo(new double[]{41.45919958770641, 58.975878943834445, 51.66451192243433});

    }

    @Test
    void bottomDistancesToBall() {
        assertThat(wall.bottomDistancesToBall(ball)).isEqualTo(new double[]{49.62213114215846, 65.23095019701454, 58.743866459282444});

    }

    @Test
    void rightDistanceToBall() {
        assertThat(wall.rightDistanceToBall(ball)).isEqualTo(67.91969901140939);
    }

    @Test
    void leftDistanceToBall() {
        assertThat(wall.leftDistanceToBall(ball)).isEqualTo(75.61417778129152);

    }

    @Test
    void getX() {
        assertThat(wall.getX()).isEqualTo(2);
    }

    @Test
    void setX() {
        wall.setX(3);
        assertThat(wall.getX()).isEqualTo(3);
    }

    @Test
    void getY() {
        assertThat(wall.getY()).isEqualTo(3);
    }

    @Test
    void setY() {
        wall.setY(8);
        assertThat(wall.getY()).isEqualTo(8);
    }

    @Test
    void getRotation() {
        assertThat(wall.getRotation()).isEqualTo(3);
    }

}