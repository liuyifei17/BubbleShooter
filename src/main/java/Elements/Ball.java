package Elements;

import Model.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by jur on 9/5/2017.
 */
public class Ball extends Element {

    public static final String[] COLORS = {"blue","green","orange","purple","red","yellow"};
    private String color;
    private ImageView imageView;

    /** creates a ball that is located inside a cell
     * @param color the color of the ball
     * @param cell the cell in which the ball is currently located
     */
    public Ball(String color, Cell cell) {
        super(cell, new Image("images/" + color + " ball.png"));
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    /** Checks if a ball with a certain color exists
     * @param color the color to check for
     * @return true if color exists and false if it does not
     */
    public static boolean colorExists(String color){
        for(String s: COLORS){
            if(s.equalsIgnoreCase(color)) return true;
        }
        return false;
    }

}
