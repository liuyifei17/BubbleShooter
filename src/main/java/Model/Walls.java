package Model;

import Controller.GameConfiguration;
import Utility.Util;
import javafx.scene.image.Image;

/**
 * Random walls that will be spawned.
 */
public class Walls {

    private Image walls;
    private double x;
    private double y;

    /**
     * Create a random wall.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Walls(double x, double y) {
        this.x = x;
        this.y = y;
        walls = null;
    }

    /**
     * Calculate how much the wall image has to rotate in order to be functional.
     * @param grid the hexagon
     * @return rotation of the wall
     */
    public int calculateRotation(Grid grid) {
        double centerToWallX = grid.getCenterCell().getInitialX() - x;
        double centerToWallY = grid.getCenterCell().getInitialY() - y;

        double unitDistance = Math.sqrt(centerToWallX * centerToWallX + centerToWallY
                * centerToWallY);

        double degree = Math.acos(centerToWallY / unitDistance);

        double randomDegree = Util.randomBetween(0, 10);

        if (Util.randomBetween(0, 1) == 1) {
            degree = degree + randomDegree;
        }
        else {
            degree = degree - randomDegree;
        }

        int degreeInt = (int) degree;

        if (x > GameConfiguration.stageWidth) {
            return -1 * degreeInt;
        }
        return degreeInt;
    }

    /**
     * @return image of the wall.
     */
    public Image getWalls() {
        return walls;
    }

    /**
     * @return x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * @param x Set new y coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * @param y Set new y coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

}
