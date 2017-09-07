package Model;

import Elements.Element;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class Cell {

    public static double EDGE_CENTER_DISTANCE = 15.0;
    private double initialX;
    private double initialY;
    private Element element;
    private ArrayList<Cell> adjacentCells;

    public Cell(double x, double y){
        initialX = x;
        initialY = y;
        element = null;
        adjacentCells = new ArrayList<Cell>();
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
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