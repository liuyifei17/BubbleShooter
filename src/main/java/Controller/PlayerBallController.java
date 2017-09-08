package Controller;

import Elements.Element;
import Elements.Player;
import Elements.PlayerBall;
import Model.Cell;
import Model.Grid;
import View.View;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlayerBallController {

    private MouseEvent mouse;
    public final int ballRadius = 15;

    public void launchBall(Player player, Grid grid) {

        if (!mouse.isPrimaryButtonDown()&&player.getPlayerBall().hasCollidedWithCell(grid)) {
            return;
        }
        double vectorX = mouse.getSceneX() - player.getPlayerBall().getX();
        double vectorY = mouse.getSceneY() - player.getPlayerBall().getY();
        double max = Math.max(vectorX, vectorY);
        double deltaX = vectorX / max;
        double deltaY = vectorY / max;

        final int maximumTimesBallHit = 4;
        while (player.getPlayerBall().getCounter() < maximumTimesBallHit) {
            if (player.getPlayerBall().hasCollidedWithWall()) {
                reflectBack(player, deltaX, deltaY);
            }
            double newXCoord = player.getPlayerBall().getX() + deltaX;
            double newYCoord = player.getPlayerBall().getY() + deltaY;

            player.getPlayerBall().setX(newXCoord);
            player.getPlayerBall().setY(newYCoord);

            if (player.getPlayerBall().hasCollidedWithCell(grid)) {
                break;
            }
        }

        player.setPlayerBall(new PlayerBall(View.STAGE_WIDTH / 2, View.TOP_BAR_HEIGHT));


    }

    private void reflectBack(Player player, double deltaX, double deltaY) {
        if ((player.getPlayerBall().getX() <= ballRadius)
                || (player.getPlayerBall().getX() >= View.STAGE_WIDTH - ballRadius)) {
            deltaX = deltaX * -1;
        }
        if ((player.getPlayerBall().getY() <= View.TOP_BAR_HEIGHT + ballRadius)
                || (player.getPlayerBall().getX() >= View.STAGE_HEIGHT - ballRadius)) {
            deltaY = deltaY * -1;
        }
    }


}
