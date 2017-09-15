package Model;

import Controller.PlayerBallController;
import View.View;

import java.util.ArrayList;

/**
 * This class stores
 */
public class Cell {

    public static double EDGE_CENTER_DISTANCE = 15.0;
    private double initialX;
    private double initialY;
    private double currentX;
    private double currentY;
    private Element element;
    private ArrayList<Cell> adjacentCells;

    public Cell(double x, double y) {
        initialX = x;
        initialY = y;
        currentX = x;
        currentY = y;
        element = new Ball(null, null);
        element.setCell(this);
        adjacentCells = new ArrayList<Cell>();
    }

    /**
     * Getter for the element field.
     *
     * @return the element that is associated with this cell
     */
    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

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

    public double getCurrentX() {
        return currentX;
    }

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }


    public boolean hasCollidedWithWall() {
        if ((currentX < PlayerBallController.BALL_RADIUS)
                || (currentX >= View.STAGE_WIDTH)
                || (currentY < View.TOP_BAR_HEIGHT)
                || (currentY >= View.STAGE_HEIGHT)) {
            return true;
        }
        return false;
    }
}
