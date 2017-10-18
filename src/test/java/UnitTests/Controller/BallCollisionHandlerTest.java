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
        playerBall = playerBallFactory.createBall("Normal Ball", 100, 100);
        player.setPlayerBall(playerBall);
        pbc = new PlayerBallController(gameController, gameData.getPlayer(), gameData.getGrid());
    }

    @Test
    void handleCollision_successfulHit() {
        pbc.setMouseX(GameConfiguration.stageWidth / 2);
        pbc.setMouseY((GameConfiguration.stageHeight + GameConfiguration.topBarHeight) / 2);
        pbc.calculateDelta();
        pbc.launchBall();
    }

}
