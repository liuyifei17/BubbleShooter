package Utility;

/**
 * The utility class containing various useful methods.
 */
public class Util {

    /**
     * Calculate the rotated coordinates of rotating point (X,Y) around a center point (X,Y).
     * @param rotatingX x coordinate of rotating variable.
     * @param rotatingY y coordinate of rotating variable.
     * @param centerX x coordinate of center around which to rotate.
     * @param centerY y coordinate of center around which to rotate.
     * @param rotation in degrees.
     * @return the new rotated coordinates (X,Y) of the rotated point.
     */
    public static double[] calculateRotatedCoordinates(
            double rotatingX, double rotatingY, double centerX, double centerY, double rotation) {
        double angleInRadians = Math.toRadians(rotation);
        double vecX = centerX - rotatingX;
        double vecY = centerY - rotatingY;
        double rotateX = vecX * Math.cos(angleInRadians) - vecY * Math.sin(angleInRadians);
        double rotateY = vecX * Math.sin(angleInRadians) + vecY * Math.cos(angleInRadians);
        return new double[]{(rotateX + centerX), (rotateY + centerY)};
    }

    /**
     * @param min the minimum random value to output
     * @param max the maximum random value to output
     * @return outputs a random integer value between min and max
     */
    public static int randomBetween(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }

        int randomRange = (int) Math.round(Math.random() * (max - min));
        return min + randomRange;
    }

    /**
     * Calculates the distance between the points (x1, y1) and (x2, y2).
     * @param x1 x coordinate of point 1.
     * @param y1 y coordinate of point 1.
     * @param x2 x coordinate of point 2.
     * @param y2 y coordinate of point 2.
     * @return the distance as a double value.
     */
    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
