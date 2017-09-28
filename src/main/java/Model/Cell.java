package Model;

import Controller.GameConfiguration;

import java.util.ArrayList;

/**
 * This class stores the cell data.
 */
public class Cell {

    private double initialX;
    private double initialY;
    private double currentX;
    private double currentY;
    private Element element;
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
        element = new Ball(null, null, false);
        ((Ball) element).updateBall();
        element.setCell(this);
        adjacentCells = new ArrayList<Cell>();
    }

    /**
     * Getter for the element field.
     * @return the element that is associated with this cell
     */
    public Element getElement() {
        return element;
    }

    /**
     * @param element sets the element
     */
    public void setElement(Element element) {
        this.element = element;
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
}
