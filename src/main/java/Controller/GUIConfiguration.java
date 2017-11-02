package Controller;

import nl.tu.delft.defpro.exception.NotExistingVariableException;

import static Controller.GameConfiguration.api;

/**
 * This class sets up the configurations for the GUI.
 */
public class GUIConfiguration {

    public static double stageWidth;
    public static double stageHeight;
    public static int topBarHeight;
    public static double edgeToDistance;
    public static int scoreBarHeight;
    public static int scoreBarWidth;
    public static int popupWidth;
    public static int popupHeight;
    public static int popupX;
    public static int popupY;
    public static int ballRadius;
    public static int wallRadius;
    public static int wallHeight;
    public static int wallWidth;

    /**
     * Create default values.
     */
    public static void isApiDefault() {
        setEdgeToDistance(-1);
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
    }

    /**
     * Startup all variables.
     * @throws Exception an exception coming from the broken api
     */
    public static void isApi() throws Exception {
        setEdgeToDistance(api.getRealValueOf("edgeToDistance"));
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
        setWallHeight(api.getIntegerValueOf("wallHeight"));
        setWallWidth(api.getIntegerValueOf("wallWidth"));
    }

    /**
     * setter stage width.
     *
     * @param stageWidth stageWidth,
     */
    public static void setStageWidth(double stageWidth) {
        double stageWidthDefault = 600;
        if (stageWidth > edgeToDistance && stageWidth > 2.0) {
            GUIConfiguration.stageWidth = stageWidth;
        } else {
            GUIConfiguration.stageWidth = stageWidthDefault;
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
            GUIConfiguration.stageHeight = stageHeight;
        } else {
            GUIConfiguration.stageHeight = stageHeightDefault;
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
            GUIConfiguration.topBarHeight = topBarHeight;
        } else {
            GUIConfiguration.topBarHeight = topBarHeightDefault;
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
            GUIConfiguration.scoreBarHeight = scoreBarHeight;
        } else {
            GUIConfiguration.scoreBarHeight = scoreBarHeightDefault;
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
            GUIConfiguration.scoreBarWidth = scoreBarWidth;
        } else {
            GUIConfiguration.scoreBarWidth = scoreBarWidthDefault;
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
            GUIConfiguration.popupWidth = popupWidth;
        } else if (stageWidth > 0) {
            GUIConfiguration.popupWidth = (int) (stageWidth / 2);
        } else {
            GUIConfiguration.popupWidth = popupWidthDefault;
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
            GUIConfiguration.popupHeight = popupHeight;
        } else {
            GUIConfiguration.popupHeight = popupHeightDefault;
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
            GUIConfiguration.popupX = popupX;
        } else {
            GUIConfiguration.popupX = popupXDefault;
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
            GUIConfiguration.popupY = popupY;
        } else {
            GUIConfiguration.popupY = popupYDefault;
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
            GUIConfiguration.edgeToDistance = edgeToDistance;
        } else {
            GUIConfiguration.edgeToDistance = edgeToDistanceDefault;
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
            GUIConfiguration.ballRadius = ballRadius;
        } else {
            GUIConfiguration.ballRadius = ballRadiusDefault;
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
            GUIConfiguration.wallRadius = wallRadius;
        } else {
            GUIConfiguration.wallRadius = wallRadiusDefault;
        }
    }

    /**
     * setter wall height.
     * @param wallHeight wall height.
     */
    public static void setWallHeight(int wallHeight) {
        if (wallHeight == 16) {
            GUIConfiguration.wallHeight = wallHeight;
        } else {
            GUIConfiguration.wallHeight = 16;
        }
    }

    /**
     * setter wall width.
     * @param wallWidth wall width.
     */
    public static void setWallWidth(int wallWidth) {
        if (wallWidth == 32) {
            GUIConfiguration.wallWidth = wallWidth;
        } else {
            GUIConfiguration.wallWidth = 32;
        }
    }
}
