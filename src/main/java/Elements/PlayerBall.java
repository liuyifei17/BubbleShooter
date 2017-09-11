package Elements;

import Controller.PlayerBallController;
import Model.Cell;
import Model.Grid;
import Utility.Util;
import View.View;
import javafx.scene.image.Image;

/**
 * PlayerBall is the ball with which the player shoots.
 */
public class PlayerBall {
    private Image image;
    private String color;
    private double x;
    private double y;
    private int counter;

    /**
     * Initiate a ball with a random image.
     * @param x the x coordinate of the ball.
     * @param y the y coordinate of the ball.
     */
    public PlayerBall(double x, double y, String color) {
        this.color =  color;
        image = new Image("images/" + this.color + " ball.png");
        counter = 0;
        this.x = x;
        this.y = y;
    }


    /**
     * @return the current x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * @param x change the x coordinate of the ball.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the current y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * @param y change the y coordinate of the ball.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the current image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return count the times the ball has hit the wall.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter set the counter to an other number.
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * @return the color of the ball
     */
    public String getColor() {
        return this.color;
    }

    /**
     * If the center of the ball is 15 away from the stage's maximum width and
     * height then it hit the wall.
     * @return true if it hit the wall and false if it didn't.
     */
    public boolean hasCollidedWithWall() {
        if ((x < PlayerBallController.BALL_RADIUS)
                || (x >= View.STAGE_WIDTH)
                || (y < View.TOP_BAR_HEIGHT)
                || (y >= View.STAGE_HEIGHT)) {
            counter++;
            return true;
        }

        return false;
    }

    /** Checks if there is a full cell in collision range
     * @param grid
     * @return null if not in range else return cell
     */
    /** Checks if there is a full cell in collision range
     * @param grid
     * @return null if not in range else return cell
     */
    public Cell getCellCollision(Grid grid) {
        Cell c = grid.closestFullCellToLocation(x,y);
        for(Cell c2: c.getAdjacentCells()){
            if(c2.getElement() == null){
                if(Util.getDistance(x,y,c2.getCurrentX(),c2.getCurrentY()) <= Cell.EDGE_CENTER_DISTANCE / 1.3){
                    return c2;
                }
            }
        }
        return null;
    }


}
