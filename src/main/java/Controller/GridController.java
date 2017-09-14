package Controller;

import Model.Cell;
import Model.Grid;
import Utility.Util;

/**
 * Created by jur on 9/7/2017.
 */
public class GridController {

    private Grid grid;

    public void process() {
        //calculate the rotated cell coordinate values based on rotation change
        if(grid.getRotationDifference() != 0) {
            if(grid.getRotationDifference() > 0) {
                grid.setRotationDifference(grid.getRotationDifference() - grid.getRotationSpeed());
                grid.setRotation(grid.getRotation() + grid.getRotationSpeed());
            }
            if(grid.getRotationDifference() < 0) {
                grid.setRotationDifference(grid.getRotationDifference() + grid.getRotationSpeed());
                grid.setRotation(grid.getRotation() - grid.getRotationSpeed());
            }
            for(Cell c: grid.getCells()) {
                double[] newCoords = Util.calculateRotatedCoordinates(c.getInitialX(), c.getInitialY(),
                        grid.getCenterCell().getInitialX(), grid.getCenterCell().getInitialY(), grid.getRotation());
                c.setCurrentX(newCoords[0]);
                c.setCurrentY(newCoords[1]);
            }
        }
    }

    public GridController(Grid grid){
        this.grid = grid;
    }

}
