package UnitTests.Model;

import Controller.GameConfiguration;
import Model.NormalBall;
import Model.Player;
import Model.PlayerBall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class PlayerTest {

    private Player player;

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
        NormalBall playerBall = mock(NormalBall.class);
        player.setPlayerBall(playerBall);
        assertThat(player.getPlayerBall()).isEqualTo(playerBall);
    }

    @Test
    void testGetNextBall() {
        assertThat(player.getNextBall()).isInstanceOf(PlayerBall.class);
    }

    @Test
    void testNextBall() {
        NormalBall playerBall = (NormalBall) player.getPlayerBall();
        PlayerBall nextBall = player.getNextBall();
        player.nextBall();

        assertThat(player.getPlayerBall()).isNotEqualTo(playerBall);
        assertThat(player.getPlayerBall().getColor()).isEqualTo(nextBall.getColor());
        assertThat(player.getNextBall()).isNotEqualTo(nextBall);
    }

    @Test
    void testMissCounter() {
        player.setMissCounter(2);
        assertThat(player.getMissCounter()).isEqualTo(2);
    }



}
