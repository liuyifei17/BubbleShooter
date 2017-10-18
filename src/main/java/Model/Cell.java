package Model;

import Controller.GameConfiguration;
import Utility.Util;

import java.util.ArrayList;

/**
 * This class stores the cell data.
 */
public class Cell {

    private double initialX;
    private double initialY;
    private double currentX;
    private double currentY;
    private Ball ball;
    private ArrayList<Cell> adjacentCells;

    /**
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     */
    public Cell(double x, double y) {
        initialX = x;
        initialY = y;
        currentX = x;
        currentY = y;
        ball = null;
        adjacentCells = new ArrayList<Cell>();
    }

    /**
     * Getter for the ball field.
     * @return the ball that is associated with this cell
     */
    public Ball getBall() {
        return this.ball;
    }

    /**
     * @param ball sets the element
     */
    public void setBall(Ball ball) {
        this.ball = ball;
    }

    /**
     * @return the adjacent cells
     */
    public ArrayList<Cell> getAdjacentCells() {
        return adjacentCells;
    }

    /**
     * Getter for the x field.
     *
     * @return the initial x coordinate of the Cell
     */
    public double getInitialX() {
        return initialX;
    }

    /**
     * Getter for the y field.
     *
     * @return the initial y coordinate of the Cell
     */
    public double getInitialY() {
        return initialY;
    }

    /**
     * @return current x
     */
    public double getCurrentX() {
        return currentX;
    }

    /**
     * @param currentX set the current x
     */
    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    /**
     * @return the current y
     */
    public double getCurrentY() {
        return currentY;
    }

    /**
     * @param currentY set the current y
     */
    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }


    /**
     * @return checks if a ball has collided with a wall
     */
    public boolean hasCollidedWithWall() {
        if ((currentX < GameConfiguration.ballRadius)
                || (currentX >= GameConfiguration.stageWidth)
                || (currentY < GameConfiguration.topBarHeight)
                || (currentY >= GameConfiguration.stageHeight)) {
            return true;
        }
        return false;
    }

    /**
     * This method finds a adjacent cell that is empty.
     * @return An empty adjacent cell
     */
    public Cell getEmptyAdjacentCell() {
        for (Cell c : adjacentCells) {
            if (c.getBall() == null) {
                return c;
            }
        }
        return null;
    }

    /**
     * This method gets the adjacent cell in a certain direction.
     * @param dx the direction in the x axis
     * @param dy the direction in the y axis
     * @return the adjacent cell in that direction
     */
    public Cell getAdjacentCellInDirection(int dx, int dy) {
        Cell adjacentCell = adjacentCells.get(0);
        for (Cell c : adjacentCells) {
            if (Util.getDistance(c.getCurrentX(), c.getCurrentY(), currentX + dx, currentY + dy)
                    <= Util.getDistance(adjacentCell.getCurrentX(), adjacentCell.getCurrentY(),
                    currentX + dx, currentY + dy)) {
                adjacentCell = c;
            }
        }
        return adjacentCell;
    }
}
