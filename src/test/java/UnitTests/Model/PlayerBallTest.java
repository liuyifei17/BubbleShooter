package UnitTests.Model;

import Controller.PlayerBallController;
import Model.PlayerBall;
import View.View;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * This test provides test cases for the PlayerBall test.
 */
public class PlayerBallTest {

    @Test
    void hasCollidedWithWallTest_false() {
        PlayerBall pb = new PlayerBall(PlayerBallController.BALL_RADIUS,View.STAGE_HEIGHT/2);

        assertThat(pb.hasCollidedWithWall()).isFalse();
    }

    @Test
    void hasCollidedWithWallTest_true() {
       PlayerBall pb = new PlayerBall(View.STAGE_WIDTH,0);

        assertThat(pb.hasCollidedWithWall()).isTrue();
    }
}
