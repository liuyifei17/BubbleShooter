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

}
