package Elements;


import java.awt.*;
import javafx.scene.image.Image;
import java.util.Random;

/**
 * Created by kabi on 6-9-2017.
 */
public class PlayerBall {
    public static final String[] COLORS = {"blue","green","orange","purple","red","yellow"};
    private Image image;
    private double X;
    private double Y;

    public PlayerBall(double X, double Y){
        Random randomNumber= new Random();
        int randomColor =  randomNumber.nextInt(COLORS.length);
        this.image = new Image("images/" + COLORS[randomColor] + " ball.png");
        this.X=X;
        this.Y=Y;

    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }
}
