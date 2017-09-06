package Utility;

/**
 * Created by jur on 9/5/2017.
 */
public class Util {

    public static int randomBetween(int min, int max) {
        if(min > max) return 1;
        if(min == max) return min;

        int randomRange = (int) Math.round(Math.random() * (max - min));
        return min + randomRange;
    }

    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
