package Model;

import Utility.Util;
import View.View;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class Grid {

    private ArrayList<Cell> cells;
    private ArrayList<Cell> occupiedCells;
    private Cell centerCell;
    private int rotation;//the current rotation in degrees
    private int rotationDifference;//the change in rotation for a given ball impact
    private int rotationSpeed;//turning speed in degrees per frame
    private double centerX;
    private double centerY;

    /** Creates a grid based on the center values of the grid and initializes all grid cells
     * @param x the x coord of the center of the grid
     * @param y the y coord of the center of the grid
     */
    public Grid(double x, double y){
        cells = new ArrayList<Cell>();
        occupiedCells = new ArrayList<Cell>();
        rotation = 180;
        rotationDifference = 0;
        rotationSpeed = 5;
        centerX = x;
        centerY = y;
        initializeCells(x, y);
    }

    /** Calculates the cell locations on the entire screen based on the center cell
     * It initializes all cells based on these calculations
     * @param x the x coord of the center of the grid
     * @param y the y coord of the center of the grid
     */
    private void initializeCells(double x, double y){
        //set center cell
        centerCell = new Cell(x, y);
        cells.add(centerCell);

        //find minimum x coord of grid
        double minimumX = x;
        while(minimumX >= 0){
            minimumX -= (Cell.EDGE_CENTER_DISTANCE * 3.5);
        }

        //find minimum y coord of grid
        double minimumY = y;
        while(minimumY >= View.TOP_BAR_HEIGHT){
            minimumY -= (Cell.EDGE_CENTER_DISTANCE * 2);
        }

        //set first lines of cells
        double cellX = minimumX;
        double cellY = minimumY;
        setCellLines(cellX, cellY, x, y, minimumY);

        //set second lines of cells
        minimumX += (Cell.EDGE_CENTER_DISTANCE * 1.75);
        minimumY += (Cell.EDGE_CENTER_DISTANCE);
        cellX = minimumX;
        cellY = minimumY;
        setCellLines(cellX, cellY, x, y, minimumY);

        //set adjacent cells
        setAdjacentCells();
    }

    /** Sets the cells in the grid for the respective coordinates
     * @param cellX x coord of a cell
     * @param cellY y coord of a cell
     * @param x the x coord of the center of the grid
     * @param y the y coord of the center of the grid
     * @param minimumY the minimum y coord on the screen that can contain a cell
     */
    private void setCellLines(double cellX, double cellY, double x, double y, double minimumY){
        while(cellX <= View.STAGE_WIDTH) {
            while (cellY <= View.STAGE_HEIGHT) {
                Cell c = new Cell(cellX, cellY);
                cells.add(c);
                cellY += (Cell.EDGE_CENTER_DISTANCE * 2);
                if (cellX == x && cellY == y) {
                    cellY += (Cell.EDGE_CENTER_DISTANCE * 2);
                }
            }
            cellY = minimumY;
            cellX += (Cell.EDGE_CENTER_DISTANCE * 3.5);
        }
    }

    /**
     * calculates the (maximum 6) surrounding cells of every cell in the grid.
     * These neighbouring cells are saved for every cell
     */
    private void setAdjacentCells(){
        for(Cell c1: cells){
            for(Cell c2: cells){
                if(Math.abs(c1.getInitialX() - c2.getInitialX()) < Cell.EDGE_CENTER_DISTANCE * 3 &&
                        Math.abs(c1.getInitialY() - c2.getInitialY()) < Cell.EDGE_CENTER_DISTANCE * 3){
                    if(!c1.equals(c2)) c1.getAdjacentCells().add(c2);
                }
            }
        }
    }

    /** finds the closest cell to a certain location (x, y)
     * @param locX coord x of location
     * @param locY coord y of location
     * @return null if no empty cell found or an empty cell if found
     */
    public Cell closestCellToLocation(double locX, double locY){
        Cell closestCell = centerCell;
        for(Cell c: cells){
            if(Util.getDistance(c.getInitialX(), c.getInitialY(), locX, locY) <=
                    Util.getDistance(closestCell.getInitialX(), closestCell.getInitialY(), locX, locY)){
                closestCell = c;
            }
        }
        return closestCell;
    }

    /** finds the closest cell that does not contain an element to a certain location (x, y)
     * @param locX coord x of location
     * @param locY coord y of location
     * @return null if no empty cell found or an empty cell if found
     */
    public Cell closestEmptyCellToLocation(double locX, double locY){
        Cell closestCell = null;
        for(Cell c: cells){
            // if the cell has no element inside it
            if(c.getElement() == null){
                //if we haven't found an empty cell yet, we set it
                if(closestCell == null) closestCell = c;
                //if we already have found an empty cell, we compare distances
                else if(Util.getDistance(c.getInitialX(), c.getInitialY(), locX, locY) <=
                        Util.getDistance(closestCell.getInitialX(), closestCell.getInitialY(), locX, locY)){
                    closestCell = c;
                }
            }

        }
        return closestCell;
    }

    /** finds the closest cell that does contain an element to a certain location (x, y)
     * @param locX coord x of location
     * @param locY coord y of location
     * @return null if no full cell found else return the cell
     */
    public Cell closestFullCellToLocation(double locX, double locY){
        Cell closestCell = null;
        for(Cell c: cells){
            // if the cell has an element inside it
            if(c.getElement().getSprite() != null){
                //if we haven't found a full cell yet, we set it
                if(closestCell == null) closestCell = c;
                    //if we already have found a full cell, we compare distances
                else if(Util.getDistance(c.getInitialX(), c.getInitialY(), locX, locY) <=
                        Util.getDistance(closestCell.getInitialX(), closestCell.getInitialY(), locX, locY)){
                    closestCell = c;
                }
            }

        }
        return closestCell;
    }

    /**
     * @return cells
     */
    public ArrayList<Cell> getCells(){
        return cells;
    }

    /**
     * @return centerCell
     */
    public Cell getCenterCell(){
        return centerCell;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    public int getRotationDifference() {
        return rotationDifference;
    }

    public void setRotationDifference(int rotationDifference) {
        this.rotationDifference = rotationDifference;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public ArrayList<Cell> getOccupiedCells() { return this.occupiedCells; }
}
