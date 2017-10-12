package Model;

import Controller.GameConfiguration;

/**
 * This class represents the ball entity.
 */
public class Ball {

    private String color;
    private Cell cell;
    private boolean isCenterPiece;

    /**
     * creates a ball that is located inside a cell.
     * @param color the color of the ball
     * @param cell  the cell in which the ball is currently located
     * @param isCP  is the current ball the center piece
     */
    public Ball(String color, Cell cell, boolean isCP) {
        this.color = color;
        this.cell = cell;
        this.isCenterPiece = isCP;
    }

    /**
     * Getter for the color field.
     * @return the color of the ball
     */
    public String getColor() {
        return this.color;
    }


    /**
     * @param color sets the color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Checks if a ball with a certain color exists.
     * @param color the color to check for
     * @return true if color exists and false if it does not
     */
    public static boolean colorExists(String color) {
        for (String s : GameConfiguration.colors) {
            if (s.equalsIgnoreCase(color) && GameConfiguration.isColor) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true if it is the centerpiece
     */
    public boolean isCenterPiece() {
        return isCenterPiece;
    }

    /**
     * This is the setter for cell field.
     * @param cell the cell to occupy to cell field
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     * This is the getter for the cell field.
     * @return the cell in which the ball is located.
     */
    public Cell getCell() {
        return this.cell;
    }

}
