package Model;

/**
 * PlayerBall interface.
 */
public interface PlayerBall {

    Cell getCellCollision(Grid grid, double deltaX, double deltaY);

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    boolean hasCollidedWithWall();

    String getColor();

    int getCounter();

}
