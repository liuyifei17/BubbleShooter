package Model;

import javafx.scene.image.Image;

/**
 * Created by jur on 9/5/2017.
 */
public class Ball extends Element {

    public static final String[] COLORS = {"blue", "green", "orange", "purple", "red", "yellow"};
    private String color;

    /** creates a ball that is located inside a cell
     * @param color the color of the ball
     * @param cell the cell in which the ball is currently located
     */
    public Ball(String color, Cell cell) {
        super(cell, null);
        this.color = color;
        if (color != null) super.setImage(new Image("images/" + color + " ball.png"));
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) { this.color = color; }

    /** Checks if a ball with a certain color exists
     * @param color the color to check for
     * @return true if color exists and false if it does not
     */
    public static boolean colorExists(String color) {
        for(String s: COLORS) {
            if(s.equalsIgnoreCase(color)) return true;
        }
        return false;
    }

}
