package Model;

import Controller.GUIConfiguration;
import Utility.Util;

import java.util.ArrayList;

/**
 * PlayerBall interface.
 */
public abstract class PlayerBall {

    private String color;
    private double x;
    private double y;
    private int counter;

    /**
     * This is the constructor of the class.
     * @param color the color of the ball
     * @param x the x coordinate
     * @param y the y coordiante
     * @param counter the counter of how many times it has hit the wall
     */
    public PlayerBall(String color, double x, double y, int counter) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.counter = counter;
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
     * @return count the times the ball has hit the wall.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @return the color of the ball
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Checks if there is a full cell in collision range.
     *
     * @param grid the grid associated with the ball the user is going to shoot with
     * @param deltaX the distance in the X direction, the ball makes every 5 miliseconds
     * @param deltaY  the distance in the Y direction, the ball makes every 5 miliseconds
     * @return null if not in range else return cell
     */
    public Cell getCellCollision(Grid grid, double deltaX, double deltaY) {
        Cell c = grid.closestFullCellToLocation(x, y);
        Cell c2 = grid.closestEmptyCellToLocation(c.getCurrentX(), c.getCurrentY());
        if (Util.getDistance(x, y, c2.getCurrentX(), c2.getCurrentY())
                <= GUIConfiguration.edgeToDistance / 1.3) {
            return c2;
        }
        for (int i = 1; i < 3; i++) {
            double nx = this.getX() + deltaX * i;
            double ny = this.getY() + deltaY * i;

            if (Util.getDistance(nx, ny, c.getCurrentX(), c.getCurrentY())
                    <= GUIConfiguration.edgeToDistance) {
                return grid.closestEmptyCellToLocation(this.x, this.y);
            }
        }

        return null;
    }

    /**
     * If the center of the ball is 15 away from the stage's maximum width and
     * height then it hit the wall.
     *
     * @return true if it hit the wall and false if it didn't.
     */
    public boolean hasCollidedWithWall() {
        if ((x < GUIConfiguration.ballRadius)
                || (x >= GUIConfiguration.stageWidth)
                || (y < GUIConfiguration.topBarHeight)
                || (y >= GUIConfiguration.stageHeight)) {
            counter++;
            return true;
        }

        return false;
    }

    /**
     * Checks whether ball has collided with a random wall.
     * @param wall that the ball has collided with.
     * @return whether the ball has collided or not with the random wall.
     */
    public boolean hasCollidedWithRandomWall(Walls wall) {
        double[] leftCoordinates = Util.calculateRotatedCoordinates(
                wall.getX() - GUIConfiguration.wallHeight, wall.getY(), wall.getX(),
                wall.getY(), wall.getRotation());
        double[] rightCoordinates = Util.calculateRotatedCoordinates(
                wall.getX() + GUIConfiguration.wallHeight, wall.getY(), wall.getX(),
                wall.getY(), wall.getRotation());
        if ((Util.getDistance(x, y, leftCoordinates[0], leftCoordinates[1])
                <= GUIConfiguration.wallHeight * 2.1)
                || (Util.getDistance(x, y, rightCoordinates[0],
                rightCoordinates[1]) <= GUIConfiguration.wallHeight * 2.1)) {
            return true;
        }

        return false;
    }

    /**
     * This is an abstract method that checks which balls should be removed.
     * @param collidedCell the cell the playerball has collided with
     * @return an arraylist of balls that will be removed
     */
    public abstract ArrayList<Cell> checkRemovalBalls(Cell collidedCell);
}
