package Elements;


import Model.Cell;
import Model.Grid;
import View.View;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayerBall is the ball with which the player shoots.
 */
public class PlayerBall {
    private Image image;
    private double x;
    private double y;

    /**
     * Initiate a ball with a random image.
     * @param x the x coordinate of the ball.
     * @param y the y coordinate of the ball.
     */
    public PlayerBall(double x, double y) {
        Random randomNumber = new Random();
        int randomColor =  randomNumber.nextInt(Ball.COLORS.length);
        image = new Image("images/" + Ball.COLORS[randomColor] + " ball.png");
        this.x = x;
        this.y = y;

    }

    /**
     * @return the current x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * @param x change the x coordinate of the ball.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the current y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * @param y change the y coordinate of the ball.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the current image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * If the center of the ball is 15 away from the stage's maximum width and
     * height then it hit the wall.
     * @return true if it hit the wall and false if it didn't.
     */
    public boolean hasCollidedWithWall() {
        final int radiusBall = 15;
        if ((x <= radiusBall)
                || (x >= View.STAGE_WIDTH - radiusBall)
                || (y <= View.TOP_BAR_HEIGHT + radiusBall)
                || (y >= View.STAGE_HEIGHT - radiusBall)) {
            return true;
        }

        return false;
    }

    /**
     * Checks whether the distance from the ball is lower or equal to 30 from the cell.
     * @param grid Grid that contains all the cells.
     * @return true if it hits the cell, false if it didn't.
     */
    public boolean hasCollidedWithCell(Grid grid) {
        ArrayList<Cell> cells = grid.getCells();
        final int radiusBallDouble = 30;
        for (Cell i:cells) {
            double deltaX = i.getX() - x;
            double deltaY = i.getY() - y;
            double distance = Math.sqrt((deltaX * deltaX + deltaY * deltaY));
            if (distance <= radiusBallDouble) {
                return true;
            }
        }
        return false;
    }
}
