package UnitTests.Model;


import Controller.GameConfiguration;
import Model.Ball;
import Model.Player;
import Model.PlayerBall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

/**
 * This class provides test cases for the Player class.
 */
public class PlayerTest {

    Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void testPlayerScore() {
        player.setScore(20);
        player.getScore();
        assertThat(player.getScore()).isEqualTo(20);

        //Player pl = new Player();
    }

    @Test
    void testPlayerBall() {
        PlayerBall playerBall = mock(PlayerBall.class);
        player.setPlayerBall(playerBall);
        assertThat(player.getPlayerBall()).isEqualTo(playerBall);
    }

    @Test
    void testMissCounter() {
        player.setMissCounter(2);
        assertThat(player.getMissCounter()).isEqualTo(2);
    }

}
