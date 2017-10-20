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

    /**
     * @param gc sets the game controller
     * @param grid sets the grid
     */
    public GridController(GameController gc, Grid grid) {
        this.gc = gc;
        this.grid = grid;
    }

    /**
     * processes the dynamics of the grid based on a timer.
     */
    public void process() {
        if (grid.getRotationDifference() == 0  && grid.getStillRotating()) {
            grid.setStillRotating(false);
            gc.getWallController().placeWalls();
        }
        //calculate the rotated cell coordinate values based on rotation change
        if (grid.getRotationDifference() != 0) {
            if (grid.getRotationDifference() > 0) {
                grid.setRotationDifference(grid.getRotationDifference() - grid.getRotationSpeed());
                grid.setRotation(grid.getRotation() + grid.getRotationSpeed());
            }
            if (grid.getRotationDifference() < 0) {
                grid.setRotationDifference(grid.getRotationDifference() + grid.getRotationSpeed());
                grid.setRotation(grid.getRotation() - grid.getRotationSpeed());
            }

            for (Cell c : grid.getCells()) {
                double[] newCoords = Util.calculateRotatedCoordinates(
                        c.getInitialX(), c.getInitialY(), grid.getCenterCell().getInitialX(),
                        grid.getCenterCell().getInitialY(), grid.getRotation());
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
