package Model;

import Controller.GameConfiguration;
import Utility.Util;
import javafx.scene.image.ImageView;

/**
 * Random walls that will be spawned.
 */
public class Walls {

    private ImageView walls;
    private double x;
    private double y;
    private int rotation;

    /**
     * Create a random wall.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Walls(double x, double y, int rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    /**
     * Calculate how much the wall image has to rotate in order to be functional.
     * @param grid the hexagon
     * @return rotation of the wall
     */
    public void calculateRotation(Grid grid) {
        double centerToWallX = grid.getCenterCell().getInitialX() - x;
        double centerToWallY = grid.getCenterCell().getInitialY() - y;
        double unitDistance = Math.sqrt(centerToWallX * centerToWallX + centerToWallY
                * centerToWallY);

        double degree = Math.acos(centerToWallY / unitDistance);
        double randomDegree = Util.randomBetween(0, 10);
        degree = degree * 180 / Math.PI;

        if (Util.randomBetween(0, 1) == 1) {
            degree = degree + randomDegree;
        }
        else {
            degree = degree - randomDegree;
        }

        int degreeInt = (int) degree;
        rotation = degreeInt;

       if (x > GameConfiguration.stageWidth/2) {
            rotation = degreeInt;
        }
        else {
            rotation = -1 * degreeInt;
        }
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

    public int getRotation() {
        return rotation;
    }
}
