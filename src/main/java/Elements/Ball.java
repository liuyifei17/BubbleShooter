package Elements;

import Model.Cell;
import javafx.scene.image.Image;

/**
 * Created by jur on 9/5/2017.
 */
public class Ball extends HexagonElement {

    public static final String[] COLORS = {"blue","green","orange","purple","red","yellow"};
    private String color;

    public Ball(String color, Cell cell, int id) {
        super(id, cell, new Image("images/" + color + " ball.png"));
        this.color = color;
    }

    public static boolean colorExists(String color){
        for(String s: COLORS){
            if(s.equalsIgnoreCase(color)) return true;
        }
        return false;
    }

}
