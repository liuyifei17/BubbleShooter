package Model;

import Utility.Util;
import View.View;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class Grid {

    private ArrayList<Cell> cells;
    private Cell centerCell;
    private int rotation;
    private double centerX;
    private double centerY;

    public Grid(double x, double y){
        cells = new ArrayList<Cell>();
        rotation = 0;
        centerX = x;
        centerY = y;
        initializeCells(x, y);
    }

    private void initializeCells(double x, double y){
        //set center cell
        centerCell = new Cell(x, y);
        cells.add(centerCell);

        //find minimum x coord of grid
        double minimumX = x;
        while(minimumX >= 0){
            minimumX -= (Cell.EDGE_CENTER_DISTANCE * 4);
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
        minimumX += (Cell.EDGE_CENTER_DISTANCE * 2);
        minimumY += (Cell.EDGE_CENTER_DISTANCE);
        cellX = minimumX;
        cellY = minimumY;
        setCellLines(cellX, cellY, x, y, minimumY);

        //set adjacent cells
        setAdjacentCells();
    }

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
            cellX += (Cell.EDGE_CENTER_DISTANCE * 4);
        }
    }

    private void setAdjacentCells(){
        for(Cell c1: cells){
            for(Cell c2: cells){
                if(Math.abs(c1.getX() - c2.getX()) < Cell.EDGE_CENTER_DISTANCE * 3 &&
                        Math.abs(c1.getY() - c2.getY()) < Cell.EDGE_CENTER_DISTANCE * 3){
                    c1.getAdjacentCells().add(c2);
                }
            }
        }
    }

    public Cell closestCellToLocation(double locX, double locY){
        Cell closestCell = centerCell;
        for(Cell c: cells){
            if(Util.getDistance(c.getX(), c.getY(), locX, locY) <=
                    Util.getDistance(closestCell.getX(), closestCell.getY(), locX, locY)){
                closestCell = c;
            }
        }
        return closestCell;
    }

    /**
     * @param locX
     * @param locY
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
                else if(Util.getDistance(c.getX(), c.getY(), locX, locY) <=
                        Util.getDistance(closestCell.getX(), closestCell.getY(), locX, locY)){
                    closestCell = c;
                }
            }

        }
        return closestCell;
    }



    public ArrayList<Cell> getCells(){
        return cells;
    }

    public Cell getCenterCell(){
        return centerCell;
    }

}
