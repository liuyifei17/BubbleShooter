package Controller;

import Model.Cell;
import Model.Grid;
import Utility.Util;

/**
 * The grid controller class.
 */
public class GridController {

    private Grid grid;
    private GameController gc;
    private int rotation; //the current rotation in degrees
    private int rotationDifference; //the change in rotation for a given ball impact
    private int rotationSpeed; //turning speed in degrees per frame
    private boolean doneRotating;

    /**
     * @param gc sets the game controller
     * @param grid sets the grid
     */
    public GridController(GameController gc, Grid grid) {
        this.gc = gc;
        this.grid = grid;
        rotation = 180;
        rotationDifference = 0;
        rotationSpeed = 5;
        doneRotating = false;
    }

    /**
     * processes the dynamics of the grid based on a timer.
     */
    public void process() {
        if (rotationDifference == 0  && doneRotating) {
            doneRotating = false;
            gc.getWallController().placeWalls();
        }
        //calculate the rotated cell coordinate values based on rotation change
        if (rotationDifference != 0) {
            if (rotationDifference > 0) {
                rotationDifference -= rotationSpeed;
                rotation += rotationSpeed;
            }
            if (rotationDifference < 0) {
                rotationDifference += rotationSpeed;
                rotation -= rotationSpeed;
            }

            for (Cell c : grid.getCells()) {
                double[] newCoords = Util.calculateRotatedCoordinates(
                        c.getInitialX(), c.getInitialY(), grid.getCenterCell().getInitialX(),
                        grid.getCenterCell().getInitialY(), rotation);
                c.setCurrentX(newCoords[0]);
                c.setCurrentY(newCoords[1]);

                if (c.getBall() != null && !c.getBall().isCenterPiece()) {
                    if (c.hasCollidedWithWall()) {
                        gc.gameOver();
                    }
                }
            }
        }
    }

    /**
     * This is the setter for the stillRotating field.
     * @param stillRotating is the grid still rotating
     */
    public void setStillRotating(boolean stillRotating) {
        this.doneRotating = stillRotating;
    }

    /**
     * @param rotationDifference set the rotation difference
     */
    public void setRotationDifference(int rotationDifference) {
        this.rotationDifference = rotationDifference;
    }

    /**
     * This is the getter for the rotationDifference field.
     * @return the rotation difference
     */
    public int getRotationDifference() {
        return this.rotationDifference;
    }

    /**
     *
     * @return grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * @param grid sets the grid
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

}
