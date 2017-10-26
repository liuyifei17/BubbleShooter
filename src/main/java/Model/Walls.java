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
     * @param rotation ratation of the wall
     */
    public Walls(double x, double y, int rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    /**
     * Calculate how much the wall image has to rotate in order to be functional.
     * @param grid the hexagon
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

        if (x > GameConfiguration.stageWidth / 2) {
            rotation = degreeInt;
        }
        else {
            rotation = -1 * degreeInt;
        }
    }

    /**
     * @return The rotated normal vector coordinates of the top surface.
     */
    public double[] calculateUpNormal() {
        double normalVectorUp = y + 1;
        return Util.calculateRotatedCoordinates(x, normalVectorUp,
                x, y, rotation);
    }

    /**
     * @return The rotated normal vector coordinates of the bottom surface.
     */
    public double[] calculateDownNormal() {
        double normalVectorDown = y - 1;
        return Util.calculateRotatedCoordinates(x, normalVectorDown,
                x, y, rotation);
    }

    /**
     * @return The rotated normal vector coordinates of the right surface.
     */
    public double[] calculateRightNormal() {
        double normalVectorRight = x + 1;
        return Util.calculateRotatedCoordinates(normalVectorRight, y,
                x, y, rotation);
    }

    /**
     * @return The rotated normal vector coordinates of the left surface.
     */
    public double[] calculateLeftNormal() {
        double normalVectorLeft = x - 1;
        return Util.calculateRotatedCoordinates(normalVectorLeft, y,
                x, y, rotation);
    }

    /**
     * @param ball the playerballs location.
     * @return the distances between the ball and the top surface of the wall.
     */
    public double[] topDistancesToBall(PlayerBall ball) {
        double xLeft = x - GameConfiguration.wallWidth;
        double xRight = x + GameConfiguration.wallWidth;
        double yUp = y + GameConfiguration.wallHeight;
        double xHalfLeft = x - GameConfiguration.wallWidth / 2;
        double xHalfRight = x + GameConfiguration.wallWidth / 2;

        double[] topLeft = Util.calculateRotatedCoordinates(xLeft, yUp, x, y, rotation);
        double[] topLeftHalf = Util.calculateRotatedCoordinates(xHalfLeft, yUp, x, y, rotation);
        double[] topRight = Util.calculateRotatedCoordinates(xRight, yUp, x, y, rotation);
        double[] topRightHalf = Util.calculateRotatedCoordinates(xHalfRight, yUp, x, y, rotation);

        double distanceToTop = Util.getDistance(topLeftHalf[0], topLeftHalf[1],
                ball.getX(), ball.getY())
                + Util.getDistance(topRightHalf[0], topRightHalf[1], ball.getX(), ball.getY());
        double distanceToTopHalfLeft = Util.getDistance(topLeft[0], topLeft[1],
                ball.getX(), ball.getY())
                + Util.getDistance(topLeftHalf[0], topLeftHalf[1], ball.getX(), ball.getY());
        double distanceToTopHalfRight = Util.getDistance(topRight[0], topRight[1],
                ball.getX(), ball.getY())
                + Util.getDistance(topRightHalf[0], topRightHalf[1], ball.getX(), ball.getY());
        return new double[] {distanceToTop, distanceToTopHalfLeft, distanceToTopHalfRight};
    }

    /**
     * @param ball the playerballs location.
     * @return the distances between the ball and the bottom surface of the wall.
     */
    public double[] bottomDistancesToBall(PlayerBall ball) {
        double xLeft = x - GameConfiguration.wallWidth;
        double xRight = x + GameConfiguration.wallWidth;
        double yDown = y - GameConfiguration.wallHeight;
        double xHalfLeft = x - GameConfiguration.wallWidth / 2;
        double xHalfRight = x + GameConfiguration.wallWidth / 2;

        double[] bottomLeft = Util.calculateRotatedCoordinates(xLeft, yDown, x,  y, rotation);
        double[] bottomLeftHalf = Util.calculateRotatedCoordinates(xHalfLeft, yDown, x,
                y, rotation);
        double[] bottomRight = Util.calculateRotatedCoordinates(xRight, yDown, x, y, rotation);
        double[] bottomRightHalf = Util.calculateRotatedCoordinates(xHalfRight, yDown, x,
                y, rotation);

        double distanceToBottom = Util.getDistance(bottomRightHalf[0], bottomRightHalf[1],
                ball.getX(), ball.getY())
                + Util.getDistance(bottomLeftHalf[0], bottomLeftHalf[1],
                ball.getX(), ball.getY());
        double distanceToBottomHalfLeft = Util.getDistance(bottomLeftHalf[0], bottomLeftHalf[1],
                ball.getX(), ball.getY())
                + Util.getDistance(bottomLeft[0], bottomLeft[1], ball.getX(),
                ball.getY());
        double distanceToBottomHalfRight = Util.getDistance(bottomRight[0], bottomRight[1],
                ball.getX(), ball.getY())
                + Util.getDistance(bottomRightHalf[0], bottomRightHalf[1],
                ball.getX(), ball.getY());
        return new double[] {distanceToBottom, distanceToBottomHalfLeft,
                distanceToBottomHalfRight};
    }

    /**
     * @param ball the playerballs location.
     * @return the distances between the ball and the right surface of the wall.
     */
    public double rightDistanceToBall(PlayerBall ball) {
        double yUp = y + GameConfiguration.wallHeight;
        double yDown = y - GameConfiguration.wallHeight;
        double xRight = x + GameConfiguration.wallWidth;

        double[] topRight = Util.calculateRotatedCoordinates(xRight, yUp, x, y, rotation);
        double[] bottomRight = Util.calculateRotatedCoordinates(xRight, yDown, x, y, rotation);

        return  Util.getDistance(bottomRight[0], bottomRight[1],
                ball.getX(), ball.getY()) + Util.getDistance(topRight[0], topRight[1],
                ball.getX(), ball.getY());
    }

    /**
     * @param ball the playerballs location.
     * @return the distances between the ball and the left surface of the wall.
     */
    public double leftDistanceToBall(PlayerBall ball) {
        double yUp = y + GameConfiguration.wallHeight;
        double yDown = y - GameConfiguration.wallHeight;
        double xLeft = x - GameConfiguration.wallWidth;

        double[] topLeft = Util.calculateRotatedCoordinates(xLeft, yUp, x, y, rotation);
        double[] bottomLeft = Util.calculateRotatedCoordinates(xLeft, yDown, x, y, rotation);

        return  Util.getDistance(topLeft[0], topLeft[1],
                ball.getX(), ball.getY()) + Util.getDistance(bottomLeft[0], bottomLeft[1],
                ball.getX(), ball.getY());
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

    /**
     * @return the degree the wall has to be rotated.
     */
    public int getRotation() {
        return rotation;
    }
}
