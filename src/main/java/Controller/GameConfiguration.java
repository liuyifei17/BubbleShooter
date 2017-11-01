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
    public static int amountOfWalls;
    public static int wallHeight;
    public static int wallWidth;
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
        setStageWidth(-1);
        setStageHeight(-1);
        setTopBarHeight(-1);
        setScoreBarHeight(-1);
        setScoreBarWidth(-1);
        setPopupWidth(-1);
        setPopupHeight(-1);
        setPopupX(-1);
        setPopupY(-1);
        setBallRadius(-1);
        setWallRadius(-1);
        setSpeedup(-1);
        setMaximumTimesBallHit(-1);
        setAmountOfWalls(-1);
        setWallHeight(-1);
        setWallWidth(-1);
        setRightRotation(new ArrayList<>());
        setLeftRotation(new ArrayList<>());
        setColors(new ArrayList<>());
        setEdgeToDistance(-1);
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
            setStageWidth(api.getRealValueOf("stageWidth"));
            setStageHeight(api.getRealValueOf("stageHeight"));
            setTopBarHeight(api.getIntegerValueOf("topBarHeight"));
            setScoreBarHeight(api.getIntegerValueOf("scoreBarHeight"));
            setScoreBarWidth(api.getIntegerValueOf("scoreBarWidth"));
            setPopupWidth(api.getIntegerValueOf("popupWidth"));
            setPopupHeight(api.getIntegerValueOf("popupHeight"));
            setPopupX(api.getIntegerValueOf("popupX"));
            setPopupY(api.getIntegerValueOf("popupY"));
            setBallRadius(api.getIntegerValueOf("ballRadius"));
            setWallRadius(api.getIntegerValueOf("wallRadius"));
            setSpeedup(api.getIntegerValueOf("speedup"));
            setMaximumTimesBallHit(api.getIntegerValueOf("maximumTimesBallHit"));
            setAmountOfWalls(api.getIntegerValueOf("amountOfWalls"));
            setWallHeight(api.getIntegerValueOf("wallHeight"));
            setWallWidth(api.getIntegerValueOf("wallWidth"));
            setRightRotation(api.getListIntValueOf("rightRotation"));
            setLeftRotation(api.getListIntValueOf("leftRotation"));
            setColors(api.getListStringValueOf("colors"));
            setEdgeToDistance(api.getRealValueOf("edgeToDistance"));
            setIsColor(api.getBooleanValueOf("isColor"));
            setSounds(api.getBooleanValueOf("sounds"));
            setWalls(api.getBooleanValueOf("walls"));
            setSpecialBalls(api.getBooleanValueOf("specialBalls"));
        } catch (Exception e) {
            //e.printStackTrace();
            GameConfiguration.isApiDefault();
        } finally {
            GameConfiguration.isApiDefault();
        }
    }

    /**
     * setter edge to distance, return default fault if invalid input.
     *
     * @param edgeToDistance value;
     */
    public static void setEdgeToDistance(double edgeToDistance) {
        double edgeToDistanceDefault = 15;
        if (edgeToDistance >= 1.0) {
            GameConfiguration.edgeToDistance = edgeToDistance;
        } else {
            GameConfiguration.edgeToDistance = edgeToDistanceDefault;
        }
    }

    /**
     * setter stage width.
     *
     * @param stageWidth stageWidth,
     */
    public static void setStageWidth(double stageWidth) {
        double stageWidthDefault = 600;
        if (stageWidth > edgeToDistance && stageWidth > 2.0) {
            GameConfiguration.stageWidth = stageWidth;
        } else {
            GameConfiguration.stageWidth = stageWidthDefault;
        }
    }

    /**
     * setter stage Height.
     *
     * @param stageHeight stage Height,
     */
    public static void setStageHeight(double stageHeight) {
        double stageHeightDefault = 700;
        if (stageHeight > edgeToDistance && stageWidth > 2.0) {
            GameConfiguration.stageHeight = stageHeight;
        } else {
            GameConfiguration.stageHeight = stageHeightDefault;
        }
    }

    /**
     * settter top bar height.
     *
     * @param topBarHeight top bar height
     */
    public static void setTopBarHeight(int topBarHeight) {
        int topBarHeightDefault = 70;
        if (topBarHeight > 0) {
            GameConfiguration.topBarHeight = topBarHeight;
        } else {
            GameConfiguration.topBarHeight = topBarHeightDefault;
        }
    }

    /**
     * setter scoreBar height.
     *
     * @param scoreBarHeight scoreBar Height,
     */
    public static void setScoreBarHeight(int scoreBarHeight) {
        int scoreBarHeightDefault = 40;
        if (scoreBarHeight < topBarHeight && scoreBarHeight > 0) {
            GameConfiguration.scoreBarHeight = scoreBarHeight;
        } else {
            GameConfiguration.scoreBarHeight = scoreBarHeightDefault;
        }
    }

    /**
     * setter scoreBar width.
     *
     * @param scoreBarWidth scoreBar Width,
     */
    public static void setScoreBarWidth(int scoreBarWidth) {
        int scoreBarWidthDefault = 240;
        if (scoreBarWidth > 5) {
            GameConfiguration.scoreBarWidth = scoreBarWidth;
        } else {
            GameConfiguration.scoreBarWidth = scoreBarWidthDefault;
        }
    }

    /**
     * setter PopUp Width.
     *
     * @param popupWidth popUpWidth.
     */
    public static void setPopupWidth(int popupWidth) {
        int popupWidthDefault = 300;
        if (popupWidth > 0 && popupWidth < stageWidth) {
            GameConfiguration.popupWidth = popupWidth;
        } else if (stageWidth > 0) {
            GameConfiguration.popupWidth = (int) (stageWidth / 2);
        } else {
            GameConfiguration.popupWidth = popupWidthDefault;
        }
    }

    /**
     * setter PopUpHeight.
     *
     * @param popupHeight popUpHeight.
     */
    public static void setPopupHeight(int popupHeight) {
        int popupHeightDefault = 360;
        if (popupHeight > 0 && popupHeight < stageHeight) {
            GameConfiguration.popupHeight = popupHeight;
        } else {
            GameConfiguration.popupHeight = popupHeightDefault;
        }
    }

    /**
     * setter popUpX.
     *
     * @param popupX popUp X cord.
     */
    public static void setPopupX(int popupX) {
        int popupXDefault = 150;
        if (popupX > 10) {
            GameConfiguration.popupX = popupX;
        } else {
            GameConfiguration.popupX = popupXDefault;
        }
    }

    /**
     * setter popY.
     *
     * @param popupY popUpY cord.
     */
    public static void setPopupY(int popupY) {
        int popupYDefault = 150;
        if (popupY > 10) {
            GameConfiguration.popupY = popupY;
        } else {
            GameConfiguration.popupY = popupYDefault;
        }
    }

    /**
     * setter ball radius.
     *
     * @param ballRadius ball radius.
     */
    public static void setBallRadius(int ballRadius) {
        int ballRadiusDefault = 15;
        if (ballRadius > 0 && ballRadius < stageWidth && ballRadius < stageHeight) {
            GameConfiguration.ballRadius = ballRadius;
        } else {
            GameConfiguration.ballRadius = ballRadiusDefault;
        }
    }

    /**
     * setter wall radius.
     *
     * @param wallRadius wall radius.
     */
    public static void setWallRadius(int wallRadius) {
        int wallRadiusDefault = 100;
        if (wallRadius > 30) {
            GameConfiguration.wallRadius = wallRadius;
        } else {
            GameConfiguration.wallRadius = wallRadiusDefault;
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
     * setter wall height.
     * @param wallHeight wall height.
     */
    public static void setWallHeight(int wallHeight) {
        if (wallHeight == 16) {
            GameConfiguration.wallHeight = wallHeight;
        } else {
            GameConfiguration.wallHeight = 16;
        }
    }

    /**
     * setter wall width.
     * @param wallWidth wall width.
     */
    public static void setWallWidth(int wallWidth) {
        if (wallWidth == 32) {
            GameConfiguration.wallWidth = wallWidth;
        } else {
            GameConfiguration.wallWidth = 32;
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
