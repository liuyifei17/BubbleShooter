package Controller;

import nl.tu.delft.defpro.api.APIProvider;
import nl.tu.delft.defpro.api.IDefProAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * GameConfiguration class loads all the pre-determinded attributes.
 */
public class GameConfiguration {

    public static IDefProAPI api;
    public static int speedup;
    public static int maximumTimesBallHit;
    public static int amountOfWalls;
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
     * Create default values.
     */
    public static void isApiDefault() {
        setSpeedup(-1);
        setMaximumTimesBallHit(-1);
        setAmountOfWalls(-1);
        setRightRotation(new ArrayList<>());
        setLeftRotation(new ArrayList<>());
        setColors(new ArrayList<>());
        setIsColor(true);
        setSounds(false);
        setWalls(true);
        setSpecialBalls(true);
    }

    /**
     * Startup all variables.
     */
    public static void isApi() {
        try {
            GUIConfiguration.isApi();
            setSpeedup(api.getIntegerValueOf("speedup"));
            setMaximumTimesBallHit(api.getIntegerValueOf("maximumTimesBallHit"));
            setAmountOfWalls(api.getIntegerValueOf("amountOfWalls"));
            setRightRotation(api.getListIntValueOf("rightRotation"));
            setLeftRotation(api.getListIntValueOf("leftRotation"));
            setColors(api.getListStringValueOf("colors"));
            setIsColor(api.getBooleanValueOf("isColor"));
            setSounds(api.getBooleanValueOf("sounds"));
            setWalls(api.getBooleanValueOf("walls"));
            setSpecialBalls(api.getBooleanValueOf("specialBalls"));
        } catch (Exception e) {
            //e.printStackTrace();
            GUIConfiguration.isApiDefault();
            GameConfiguration.isApiDefault();
        } finally {
            GameConfiguration.isApiDefault();
        }
    }

    /**
     * setter speed up rotation.
     *
     * @param speedup speed up of rotation.
     */
    public static void setSpeedup(int speedup) {
        int speedupDefault = 5;
        if (speedup > 0) {
            GameConfiguration.speedup = speedup;
        } else {
            GameConfiguration.speedup = speedupDefault;
        }
    }

    /**
     * setter max count without hit anything.
     *
     * @param maximumTimesBallHit setter max counter.
     */
    public static void setMaximumTimesBallHit(int maximumTimesBallHit) {
        int maximumTimesBallHitDefault = 4;
        if (maximumTimesBallHit > 1) {
            GameConfiguration.maximumTimesBallHit = maximumTimesBallHit;
        } else {
            GameConfiguration.maximumTimesBallHit = maximumTimesBallHitDefault;
        }
    }

    /**
     * setter amount of walls.
     *
     * @param amountOfWalls amount of walls.
     */
    public static void setAmountOfWalls(int amountOfWalls) {
        int amountOfWallsDefault = 3;
        if (amountOfWalls > 0 && amountOfWalls < 5) {
            GameConfiguration.amountOfWalls = amountOfWalls;
        } else {
            GameConfiguration.amountOfWalls = amountOfWallsDefault;
        }
    }

    /**
     * setter left rotation.
     * @param leftRotation left rotation
     */
    public static void setLeftRotation(List<Integer> leftRotation) {
        List<Integer> leftRotationDefault = new ArrayList<>();
        leftRotationDefault.add(-15);
        leftRotationDefault.add(30);
        leftRotationDefault.add(-45);

        if (leftRotation.size() == 3) {
            GameConfiguration.leftRotation = leftRotation;
        } else {
            GameConfiguration.leftRotation = leftRotationDefault;
        }
    }

    /**
     * setter right rotation.
     * @param rightRotation right rotation values.
     */
    public static void setRightRotation(List<Integer> rightRotation) {
        List<Integer> rightRotationDefault = new ArrayList<>();
        rightRotationDefault.add(15);
        rightRotationDefault.add(30);
        rightRotationDefault.add(45);
        if (rightRotation.size() == 3) {
            GameConfiguration.rightRotation = rightRotation;
        } else {
            GameConfiguration.rightRotation = rightRotationDefault;
        }
    }

    /**
     * setter colors.
     * @param colors return colors.
     */
    public static void setColors(List<String> colors) {
        List<String> colorsDefault = new ArrayList<>();
        colorsDefault.add("blue");
        colorsDefault.add("green");
        colorsDefault.add("orange");
        colorsDefault.add("purple");
        colorsDefault.add("red");
        colorsDefault.add("yellow");
        GameConfiguration.colors = colorsDefault;
    }

    /**
     * setter color.
     *
     * @param isColor true if same color.
     */
    public static void setIsColor(boolean isColor) {

        GameConfiguration.isColor = isColor;
    }

    /**
     * setter sound.
     *
     * @param sounds sounds.
     */
    public static void setSounds(boolean sounds) {
        GameConfiguration.sounds = sounds;
    }

    /**
     * setter special balls.
     *
     * @param specialBalls true if need specialBall.
     */
    public static void setSpecialBalls(boolean specialBalls) {
        GameConfiguration.specialBalls = specialBalls;
    }

    /**
     * setter walls or not.
     *
     * @param walls boolean, true if want walls.
     */
    public static void setWalls(boolean walls) {
        GameConfiguration.walls = walls;
    }


}
