package Model;

import Controller.GUIConfiguration;

import java.util.ArrayList;

/**
 * MultiplierBall.
 */
public class MultiplierBall extends PlayerBall {

    /**
     * This is the constructor of the class.
     *
     * @param color   the color of the ball
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param counter the counter of how many times it has hit the wall
     */
    public MultiplierBall(String color, double x, double y, int counter) {
        super(color, x, y, counter);
    }

    /**
     *  Constructor.
     * @param color the color of the ball
     */
    public MultiplierBall(String color) {
        super(color, GUIConfiguration.stageWidth / 2, GUIConfiguration.topBarHeight, 0);
    }

    @Override
    public ArrayList<Cell> checkRemovalBalls(Cell collidedCell) {
        NormalBall normalBall = new NormalBall(getColor());
        ArrayList<Cell> removed = normalBall.checkRemovalBalls(collidedCell);
        return removed;
    }
}
