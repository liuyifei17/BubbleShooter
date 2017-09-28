package Model;

import Controller.GameConfiguration;
import javafx.scene.image.Image;

/**
 * This class represents the ball entity.
 */
public class Ball extends Element {

    private String color;
    private boolean isCenterPiece;

    /**
     * creates a ball that is located inside a cell.
     * @param color the color of the ball
     * @param cell  the cell in which the ball is currently located
     * @param isCP  is the current ball the center piece
     */
    public Ball(String color, Cell cell, boolean isCP) {
        super(cell, null);
        this.color = color;
        this.isCenterPiece = isCP;

    }

    /**
     * updates ball values.
     */
    public void updateBall() {
        if (this.color != null && this.color.equals("center")) {
            super.setImage(new Image("images/center.png"));
        }
        else if (color != null) {
            super.setImage(new Image("images/" + color + " ball.png"));
        }
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
            if (s.equalsIgnoreCase(color)) {
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

}
