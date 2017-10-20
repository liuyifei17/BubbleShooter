package Model;

import Controller.GameConfiguration;
import Controller.GameController;
import Utility.Util;

import java.util.ArrayList;

/**
 * Grid.
 */
public class Grid {

    private ArrayList<Cell> cells;
    private ArrayList<Cell> occupiedCells;
    private Cell centerCell;
    private int rotation; //the current rotation in degrees
    private int rotationDifference; //the change in rotation for a given ball impact
    private int rotationSpeed; //turning speed in degrees per frame
    private double centerX;
    private double centerY;
    private double edgeToDistance;
    private double topBarHeight;
    private double stageWidth;
    private double stageHeight;
    private boolean doneRotating;

    /**
     * Creates a grid based on the center values of the grid and initializes all grid cells.
     * @param x the x coord of the center of the grid.
     * @param y the y coord of the center of the grid.
     */
    public Grid(double x, double y) {
        cells = new ArrayList<>();
        occupiedCells = new ArrayList<>();
        rotation = 180;
        rotationDifference = 0;
        rotationSpeed = 5;
        centerX = x;
        centerY = y;
        edgeToDistance = GameConfiguration.edgeToDistance;
        topBarHeight = GameConfiguration.topBarHeight;
        stageWidth = GameConfiguration.stageWidth;
        stageHeight = GameConfiguration.stageHeight;
        doneRotating = false;
        initializeCells(x, y);
    }

    /**
     * Calculates the cell locations on the entire screen based on the center cell.
     * It initializes all cells based on these calculations.
     * @param x the x coord of the center of the grid.
     * @param y the y coord of the center of the grid.
     */
    private void initializeCells(double x, double y) {
        //set center cell
        centerCell = new Cell(x, y);
        cells.add(centerCell);

        //find minimum x coord of grid
        double minimumX = x;
        while (minimumX >= 0) {
            minimumX -= (edgeToDistance * 3.5);
        }

        //find minimum y coord of grid
        double minimumY = y;
        while (minimumY >= topBarHeight) {
            minimumY -= (edgeToDistance * 2);
        }

        //set first lines of cells
        double cellX = minimumX;
        double cellY = minimumY;
        setCellLines(cellX, cellY, x, y, minimumY);

        //set second lines of cells
        minimumX += (edgeToDistance * 1.75);
        minimumY += (edgeToDistance);
        cellX = minimumX;
        cellY = minimumY;
        setCellLines(cellX, cellY, x, y, minimumY);
        //set adjacent cells
        setAdjacentCells();
    }

    /**
     * Sets the cells in the grid for the respective coordinates.
     * @param cellX    x coord of a cell.
     * @param cellY    y coord of a cell.
     * @param x        the x coord of the center of the grid.
     * @param y        the y coord of the center of the grid.
     * @param minimumY the minimum y coord on the screen that can contain a cell.
     */
    private void setCellLines(double cellX, double cellY, double x, double y, double minimumY) {
        while (cellX <= stageWidth) {
            while (cellY <= stageHeight) {
                Cell c = new Cell(cellX, cellY);
                cells.add(c);
                cellY += (edgeToDistance * 2);
                if (cellX == x && cellY == y) {
                    cellY += (edgeToDistance * 2);
                }
            }
            cellY = minimumY;
            cellX += (edgeToDistance * 3.5);
        }
    }

    /**
     * calculates the (maximum 6) surrounding cells of every cell in the grid.
     * These neighbouring cells are saved for every cell
     */
    private void setAdjacentCells() {
        for (Cell c1 : cells) {
            for (Cell c2 : cells) {
                if (Math.abs(c1.getCurrentX()
                        - c2.getCurrentX()) < edgeToDistance * 3
                        && Math.abs(c1.getCurrentY()
                        - c2.getCurrentY()) < edgeToDistance * 3) {
                    if (!c1.equals(c2)) {
                        c1.getAdjacentCells().add(c2);
                    }
                }
            }
        }

        for (Cell c2 : cells) {
            if (Math.abs(centerCell.getCurrentX()
                    - c2.getCurrentX()) < edgeToDistance * 3
                    && Math.abs(centerCell.getCurrentY()
                    - c2.getCurrentY()) < edgeToDistance * 3) {
                if (!centerCell.equals(c2) && !centerCell.getAdjacentCells().contains(c2)) {
                    centerCell.getAdjacentCells().add(c2);
                }
            }
        }
    }

    /**
     * finds the closest cell that does not contain an element to a certain location (x, y).
     * @param locX coord x of location.
     * @param locY coord y of location.
     * @return null if no empty cell found or an empty cell if found.
     */
    public Cell closestEmptyCellToLocation(double locX, double locY) {
        Cell closestCell = null;
        for (Cell c : cells) {
            if (c.getBall() == null) {
                if (closestCell == null) {
                    closestCell = c;
                }
                else if (Util.getDistance(c.getCurrentX(), c.getCurrentY(), locX, locY)
                        <= Util.getDistance(closestCell.getCurrentX(),
                        closestCell.getCurrentY(), locX, locY)) {
                    closestCell = c;
                }
            }

        }
        return closestCell;
    }

    /**
     * finds the closest cell that does contain an element to a certain location (x, y).
     * @param locX coord x of location.
     * @param locY coord y of location.
     * @return null if no full cell found else return the cell.
     */
    public Cell closestFullCellToLocation(double locX, double locY) {
        Cell closestCell = null;
        for (Cell c : cells) {
            if (c.getBall() != null) {
                if (closestCell == null) {
                    closestCell = c;
                }
                else if (Util.getDistance(c.getCurrentX(), c.getCurrentY(), locX, locY)
                        <= Util.getDistance(closestCell.getCurrentX(),
                        closestCell.getCurrentY(), locX, locY)) {
                    closestCell = c;
                }
            }

        }
        return closestCell;
    }


    /**
     * This method adds balls to the hexagon every time the player misses more than 6 times.
     * @param numberBalls the number of balls to add to the hexagon
     */
    public void appendAdditionalBalls(int numberBalls) {
        int randomIndex;
        ArrayList<Integer> randomIndexes = new ArrayList<Integer>();
        for (int i = 0; i < numberBalls; i++) {
            while (true) {
                randomIndex = Util.randomBetween(0, occupiedCells.size() - 1);
                Cell c = occupiedCells.get(randomIndex).getEmptyAdjacentCell();
                if (!randomIndexes.contains(randomIndex) && c != null) {
                    String color = GameConfiguration.colors.get(Util.randomBetween(0,
                            GameConfiguration.colors.size() - 1));
                    occupiedCells.add(c);
                    c.setBall(new Ball(color, c, 1));
                    GameController.getView().display(c);
                    randomIndexes.add(randomIndex);
                    break;
                }
            }
        }
    }

    /**
     * Look for all the cells within the wallRadius and if they are null they will be put in array
     * for the potential locations for the walls.
     * @return array with cell locations for the wall.
     */
    public ArrayList<Cell> emptyWallLocation() {
        ArrayList<Cell> cellLocations = new ArrayList<>();
        for (Cell i : this.getEmptyCells()) {
            boolean canBeUsed = false;
            if ((i.getCurrentX() + GameConfiguration.wallWidth < GameConfiguration.stageWidth)
                    && (i.getCurrentX() - GameConfiguration.wallWidth > 0)
                    && (i.getCurrentY() + GameConfiguration.wallWidth
                    < GameConfiguration.stageHeight)
                    && (i.getCurrentY() - GameConfiguration.wallWidth
                    > GameConfiguration.scoreBarHeight)
                    && Util.getDistance(i.getCurrentX(), i.getCurrentY(),
                    GameConfiguration.stageWidth / 2, GameConfiguration.topBarHeight) > 100) {
                canBeUsed = true;
                for (Cell k : cells) {
                    if ((Util.getDistance(i.getCurrentX(), i.getCurrentY(),
                            k.getCurrentX(), k.getCurrentY()) <= GameConfiguration.wallRadius)
                            && (k.getBall() != null)) {
                        canBeUsed = false;
                        break;
                    }
                }
            }
            if (canBeUsed) {
                cellLocations.add(i);
            }
        }
        return cellLocations;
    }

    /**
     * @return empty Cells on Grid.
     */
    public ArrayList<Cell> getEmptyCells() {
        ArrayList<Cell> emptyCell = new ArrayList<>();
        for (Cell i : cells) {
            if (i.getBall() == null) {
                emptyCell.add(i);
            }
        }
        return emptyCell;
    }

    /**
     * @return cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * @return centerCell
     */
    public Cell getCenterCell() {
        return centerCell;
    }

    /**
     * @return rotation
     */
    public int getRotation() {
        return rotation;
    }

    /**
     * @param rotation set rotation
     */
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    /**
     * @return rotation difference
     */
    public int getRotationDifference() {
        return rotationDifference;
    }

    /**
     * @param rotationDifference set the rotation difference
     */
    public void setRotationDifference(int rotationDifference) {
        this.rotationDifference = rotationDifference;

    }

    /**
     * @return rotation speed
     */
    public int getRotationSpeed() {
        return rotationSpeed;
    }

    /**
     * @return the occupied cells
     */
    public ArrayList<Cell> getOccupiedCells() {
        return this.occupiedCells;
    }

    /**
     * This is the getter for centerX.
     * @return centerX
     */
    public double getCenterX() {
        return centerX;
    }

    /**
     * This is the getter for centerY.
     * @return getter for centerY
     */
    public double getCenterY() {
        return centerY;
    }

    /**
     * @return rotating boolean
     */
    public boolean getStillRotating() {
        return doneRotating;
    }

    /**
     * @param doneRotating set true if it is rotating
     */
    public void setStillRotating(boolean doneRotating) {
        this.doneRotating = doneRotating;
    }
}
