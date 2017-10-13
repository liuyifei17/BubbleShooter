package UnitTests.Model;

import Controller.GameConfiguration;
import Model.Ball;
import Model.Player;
import Model.PlayerBall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class PlayerTest {

    Player player;

    @BeforeEach
    void setUp() {
        GameConfiguration.setApi();
        GameConfiguration.isApi();

        player = new Player();
    }

    @Test
    void testPlayerScore() {
        player.setScore(20);

        assertThat(player.getScore()).isEqualTo(20);
    }

    @Test
    void testPlayerBall() {
        PlayerBall playerBall = mock(PlayerBall.class);
        player.setPlayerBall(playerBall);
        assertThat(player.getPlayerBall()).isEqualTo(playerBall);
    }

    @Test
    void testGetNextBall() {
        assertThat(player.getNextBall()).isInstanceOf(Ball.class);
    }

    @Test
    void testNextBall() {
        PlayerBall playerBall = player.getPlayerBall();
        Ball nextBall = player.getNextBall();

        player.nextBall();

        assertThat(player.getPlayerBall().getColor()).isEqualTo(nextBall.getColor());
        assertThat(player.getPlayerBall()).isNotEqualTo(playerBall);
        assertThat(player.getNextBall()).isNotEqualTo(nextBall);
    }

    @Test
    void testMissCounter() {
        player.setMissCounter(2);
        assertThat(player.getMissCounter()).isEqualTo(2);
    }

}
