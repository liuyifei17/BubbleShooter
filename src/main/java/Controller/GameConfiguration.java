package Controller;

import nl.tu.delft.defpro.api.APIProvider;
import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tu.delft.defpro.exception.NotExistingVariableException;

import java.util.List;

/**
 * GameConfiguration class loads all the pre-determinded attributes.
 */
public class GameConfiguration {

    public static IDefProAPI api;
    public static double edgeToDistance;
    public static double stageWidth;
    public static double stageHeight;
    public static int topBarHeight;
    public static int scoreBarHeight;
    public static int scoreBarWidth;
    public static int popupWidth;
    public static int popupHeight;
    public static int popupX;
    public static int popupY;
    public static int ballRadius;
    public static int wallRadius;
    public static int speedup;
    public static int maximumTimesBallHit;
    public static List<Integer> leftRotation;
    public static List<Integer> rightRotation;
    public static List<String> colors;
    public static boolean isColor;
    public static boolean sounds;
    public static boolean specialBalls;
    public static boolean walls;

    /**
     * Intialize the api by reading the file.
     */
    public static void setApi() {
        try {
            api = APIProvider.getAPI("src/main/resources/configuration/attributes.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Startup all variables.
     */
    public static void isApi() {
        try {
            stageWidth = api.getRealValueOf("stageWidth");
            stageHeight = api.getRealValueOf("stageHeight");
            topBarHeight = api.getIntegerValueOf("topBarHeight");
            scoreBarHeight = api.getIntegerValueOf("scoreBarHeight");
            scoreBarWidth = api.getIntegerValueOf("scoreBarWidth");
            popupWidth = api.getIntegerValueOf("popupWidth");
            popupHeight = api.getIntegerValueOf("popupHeight");
            popupX = api.getIntegerValueOf("popupX");
            popupY = api.getIntegerValueOf("popupY");
            ballRadius = api.getIntegerValueOf("ballRadius");
            wallRadius = api.getIntegerValueOf("wallRadius");
            speedup = api.getIntegerValueOf("speedup");
            maximumTimesBallHit = api.getIntegerValueOf("maximumTimesBallHit");
            rightRotation = api.getListIntValueOf("rightRotation");
            leftRotation = api.getListIntValueOf("leftRotation");
            colors = api.getListStringValueOf("colors");
            edgeToDistance = api.getRealValueOf("edgeToDistance");
            isColor = api.getBooleanValueOf("isColor");
            sounds = api.getBooleanValueOf("sounds");
            walls = api.getBooleanValueOf("walls");
            specialBalls = api.getBooleanValueOf("specialBalls");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
        }
    }

}
