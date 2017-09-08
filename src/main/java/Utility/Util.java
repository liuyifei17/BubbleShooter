package Utility;

/**
 * Created by jur on 9/5/2017.
 */
public class Util {

    /** Calculate the rotated coordinates of rotating point (X,Y) around a center point (X,Y).
     * @param rotatingX
     * @param rotatingY
     * @param centerX
     * @param centerY
     * @param rotation
     * @return the new rotated coordinates (X,Y) of the rotated point
     */
    public static double[] calculateRotatedCoordinates(double rotatingX, double rotatingY, double centerX, double centerY, double rotation){
        double angleInRadians = Math.toRadians(rotation);
        double vecX = centerX - rotatingX;
        double vecY = centerY - rotatingY;
        double rotateX = vecX * Math.cos(angleInRadians) - vecY * Math.sin(angleInRadians);
        double rotateY = vecX * Math.sin(angleInRadians) + vecY * Math.cos(angleInRadians);
        double[] coords = {(rotateX + centerX),(rotateY + centerY)};
        return coords;
    }

    /**
     * @param min the minimum random value to output
     * @param max the maximum random value to output
     * @return outputs a random integer value between min and max
     */
    public static int randomBetween(int min, int max) {
        if(min > max) return 1;
        if(min == max) return min;

        int randomRange = (int) Math.round(Math.random() * (max - min));
        return min + randomRange;
    }

    /** Calculates the distance between the points (x1, y1) and (x2, y2)
     * @param x1, x coord of point 1
     * @param y1, y coord of point 1
     * @param x2, x coord of point 2
     * @param y2, y coord of point 2
     * @return the distance as a double value
     */
    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
