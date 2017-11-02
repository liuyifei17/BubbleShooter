package Model;

import Controller.GUIConfiguration;
import Utility.Util;

/**
 * Random walls that will be spawned.
 */
public class Walls {

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

        if (x > GUIConfiguration.stageWidth / 2) {
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
        double xLeft = x - GUIConfiguration.wallWidth;
        double xRight = x + GUIConfiguration.wallWidth;
        double yUp = y + GUIConfiguration.wallHeight;
        double xHalfLeft = x - GUIConfiguration.wallWidth / 2;
        double xHalfRight = x + GUIConfiguration.wallWidth / 2;

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
        double xLeft = x - GUIConfiguration.wallWidth;
        double xRight = x + GUIConfiguration.wallWidth;
        double yDown = y - GUIConfiguration.wallHeight;
        double xHalfLeft = x - GUIConfiguration.wallWidth / 2;
        double xHalfRight = x + GUIConfiguration.wallWidth / 2;

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
        double yUp = y + GUIConfiguration.wallHeight;
        double yDown = y - GUIConfiguration.wallHeight;
        double xRight = x + GUIConfiguration.wallWidth;

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
        double yUp = y + GUIConfiguration.wallHeight;
        double yDown = y - GUIConfiguration.wallHeight;
        double xLeft = x - GUIConfiguration.wallWidth;

        double[] topLeft = Util.calculateRotatedCoordinates(xLeft, yUp, x, y, rotation);
        double[] bottomLeft = Util.calculateRotatedCoordinates(xLeft, yDown, x, y, rotation);

        return  Util.getDistance(topLeft[0], topLeft[1],
                ball.getX(), ball.getY()) + Util.getDistance(bottomLeft[0], bottomLeft[1],
                ball.getX(), ball.getY());
    }

    /**
     * @param distanceToTop the top distance to the surface.
     * @param distanceToBottom the bottom distance to the surface.
     * @param distanceToTopHalfLeft the top left distance to surface.
     * @param distanceToRight the right distance to surface.
     * @param distanceToTopHalfRight the top right distance to surface.
     * @param distanceToLeft the left distance to surface.
     * @return whether the top surface is the closest surface to the ball.
     */
    public boolean hasCollidedTop(double distanceToTop, double distanceToBottom,
                                  double distanceToTopHalfLeft, double distanceToRight,
                                  double distanceToTopHalfRight, double distanceToLeft) {
        return ((distanceToTop > distanceToBottom)
                || (distanceToTopHalfLeft > distanceToBottom)
                || (distanceToTopHalfRight > distanceToBottom))
                && ((distanceToTop > distanceToRight)
                || (distanceToTopHalfLeft > distanceToRight)
                || (distanceToTopHalfRight > distanceToRight))
                && ((distanceToTop > distanceToLeft)
                || (distanceToTopHalfLeft > distanceToLeft)
                || (distanceToTopHalfRight > distanceToLeft));
    }

    /**
     * @param distanceToTop the top distance to the surface
     * @param distanceToBottom the bottom distance to the surface.
     * @param distanceToBottomHalfLeft the bottom left distance to the surface.
     * @param distanceToRight the right distance to the surface.
     * @param distanceToBottomHalfRight the bottom right distance to the surface.
     * @param distanceToLeft the left distance to the surface.
     * @return whether the bottom surface is the closest surface to the ball.
     */
    public boolean hasCollidedBottom(double distanceToTop, double distanceToBottom,
                                     double distanceToBottomHalfLeft, double distanceToRight,
                                     double distanceToBottomHalfRight, double distanceToLeft) {
        return ((distanceToBottom > distanceToTop)
                || (distanceToBottomHalfLeft > distanceToTop)
                || (distanceToBottomHalfRight > distanceToTop))
                && ((distanceToBottom > distanceToLeft)
                || (distanceToBottomHalfLeft > distanceToLeft)
                || (distanceToBottomHalfRight > distanceToLeft))
                && ((distanceToBottom > distanceToRight)
                || (distanceToBottomHalfLeft > distanceToRight)
                || (distanceToBottomHalfRight > distanceToRight));
    }

    /**
     * @param distanceToRight the right distance to surface.
     * @param distanceToBottomHalfRight the bottom right distance to surface.
     * @param distanceToLeft the left distance to surface.
     * @param distanceToTopHalfRight the top right distance to surface.
     * @return whether the right surface is the closest surface to the ball.
     */
    public boolean hasCollidedRight(double distanceToRight, double distanceToBottomHalfRight,
                                    double distanceToLeft, double distanceToTopHalfRight) {
        return ((distanceToRight > distanceToBottomHalfRight)
                && (distanceToRight > distanceToLeft)
                && (distanceToRight > distanceToTopHalfRight));
    }

    /**
     * @param distanceToLeft the left distance to surface.
     * @param distanceToTopHalfLeft the top left distance to surface.
     * @param distanceToRight the right distance to surface.
     * @param distanceToBottomHalfLeft the bottom left distance to surface.
     * @return whether the left surface is the closest surface to the ball.
     */
    public boolean hasColllidedLeft(double distanceToLeft, double distanceToTopHalfLeft,
                                    double distanceToRight, double distanceToBottomHalfLeft) {
        return ((distanceToLeft > distanceToTopHalfLeft)
                && (distanceToLeft > distanceToRight)
                && (distanceToLeft > distanceToBottomHalfLeft));
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
