package Model;

import Controller.GameConfiguration;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * normal playerBall class, implements playerBall.
 */
public class NormalBall extends PlayerBall {

    /**
     * Initiate a ball with a random image.
     * @param color the random selected color of the ball;
     */
    public NormalBall(String color) {
        super(color, GameConfiguration.stageWidth / 2, GameConfiguration.topBarHeight, 0);
    }

    /**
     * Initiate a ball with a random image.
     * @param color the random selected color of the ball;
     * @param x the x coordinate of the ball.
     * @param y the y coordinate of the ball.
     */
    public NormalBall(String color, double x, double y) {
        super(color, x, y, 0);
    }

    /**
     * This method checks whether the player got a successful hit and a couple of balls should be
     * removed.
     * @param collidedCell The cell with which the player ball collided
     * @return  An array of cells that should be emptied
     */
    public ArrayList<Cell> checkRemovalBalls(Cell collidedCell) {
        //initialise an arrayList which will contain all possible removedBalls
        ArrayList<Cell> removalBalls = new ArrayList<>();
        removalBalls.add(collidedCell);

        //initialise a queue for BFS
        LinkedList<Cell> queue = new LinkedList<>();
        queue.add(collidedCell);

        // initialise a list which keeps the visited cells
        ArrayList<Cell> visited = new ArrayList<>();
        visited.add(collidedCell);
        Cell current;

        // loop through the queue
        while (!queue.isEmpty()) {

            current = queue.remove();

            //loop through all neighbors
            for (Cell adjacentCell : current.getAdjacentCells()) {
                if (adjacentCell.getBall() != null) {

                    Ball ball = adjacentCell.getBall();

                    boolean sameColour = getColor().equals(ball.getColor());


                    //if never visited and both cells contains same colour ball
                    if (!visited.contains(adjacentCell) && (sameColour || ball.isRainbowBall())) {
                        //add the cell into the queue and removalBallsList
                        queue.add(adjacentCell);
                        removalBalls.add(adjacentCell);
                    }

                    //this adjacentCell is visited
                    visited.add(adjacentCell);
                }
            }
        }
        return removalBalls;
    }
}
