package Model;

import Controller.PlayerBallController;
import View.View;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class Cell {

    public static double EDGE_CENTER_DISTANCE = 15.0;
    private double initialX;
    private double initialY;
    private double currentX;
    private double currentY;
    private Element element;
    private ArrayList<Cell> adjacentCells;

    public Cell(double x, double y){
        initialX = x;
        initialY = y;
        currentX = x;
        currentY = y;
        element = new Ball(null, null);
        element.setCell(this);
        adjacentCells = new ArrayList<Cell>();
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public ArrayList<Cell> getAdjacentCells() {
        return adjacentCells;
    }

    public double getInitialX() {
        return initialX;
    }

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
