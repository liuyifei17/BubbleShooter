package Elements;


import Model.Cell;
import Model.Grid;
import Utility.Util;
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
    private int counter;

    /**
     * Initiate a ball with a random image.
     * @param x the x coordinate of the ball.
     * @param y the y coordinate of the ball.
     */
    public PlayerBall(double x, double y) {
        int randomColor =  Util.randomBetween(0,Ball.COLORS.length-1);
        System.out.println(randomColor);
        image = new Image("images/" + Ball.COLORS[randomColor] + " ball.png");
        counter = 0;
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
     * @return count the times the ball has hit the wall.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter set the counter to an other number.
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * If the center of the ball is 15 away from the stage's maximum width and
     * height then it hit the wall.
     * @return true if it hit the wall and false if it didn't.
     */
    public boolean hasCollidedWithWall() {
        final int radiusBall = 17;
        if ((x < radiusBall - View.SCREEN_WITH_DEVIATION)
                || (x >= View.STAGE_WIDTH - radiusBall)
                || (y < View.TOP_BAR_HEIGHT)
                || (y >= View.STAGE_HEIGHT - radiusBall)) {
            counter++;
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
        Cell closestCell = grid.closestCellToLocation(x, y);
        final double radiusBallDouble = 30;

        double distance = Util.getDistance(x, y, closestCell.getCurrentX(),
                closestCell.getCurrentY());
        if (distance <= radiusBallDouble) {
            return true;
        }

        return false;
    }
}
