package Model;

import Elements.HexagonElement;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class Cell {

    public static double EDGE_CENTER_DISTANCE = 15.0;
    private double initialX;
    private double initialY;
    private HexagonElement element;
    private ArrayList<Cell> adjacentCells;

    public Cell(double x, double y){
        initialX = x;
        initialY = y;
        element = null;
        adjacentCells = new ArrayList<Cell>();
    }

    public HexagonElement getElement() {
        return element;
    }

    public void setElement(HexagonElement element) {
        this.element = element;
    }

    public double getY() {
        return initialY;
    }

    public void setY(double initialY) {
        this.initialY = initialY;
    }

    public double getX() {
        return initialX;
    }

    public void setX(double initialX) {
        this.initialX = initialX;
    }

    public ArrayList<Cell> getAdjacentCells() {
        return adjacentCells;
    }
}
