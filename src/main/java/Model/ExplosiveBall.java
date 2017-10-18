package Model;

import Controller.GameConfiguration;
import Utility.Util;

import java.util.ArrayList;

/**
 * A special playerBall, explosive ball.
 */
public class ExplosiveBall extends PlayerBall {

    /**
     * Initiate a ball with a random image.
     * @param color the random selected color of the ball;
     */
    public ExplosiveBall(String color) {
        super(color, GameConfiguration.stageWidth / 2, GameConfiguration.topBarHeight, 0);
    }

    /**
     * Initiate a ball with a random image.
     * @param color the random selected color of the ball;
     * @param x the x coordinate of the ball.
     * @param y the y coordinate of the ball.
     */
    public ExplosiveBall(String color, double x, double y) {
        super(color, x, y, 0);
    }

    @Override
    public ArrayList<Cell> checkRemovalBalls(Cell collidedCell) {
        //initialise an arrayList which will contain all possible removedBalls
        ArrayList<Cell> removalBalls = new ArrayList<>();

        Cell current = collidedCell;
        while (removalBalls.size() < 4) {

            //loop through all neighbors
            for (Cell adjacentCell : current.getAdjacentCells()) {
                if (adjacentCell.getBall() != null && !removalBalls.contains(adjacentCell)) {
                    removalBalls.add(adjacentCell);
                }
            }

            current = removalBalls.get(removalBalls.size() - 1);
        }
        removalBalls.add(collidedCell);
        return removalBalls;
    }
}
