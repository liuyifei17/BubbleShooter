package Controller;

import Model.Ball;
import Model.Cell;
import Model.Grid;
import Model.Player;
import Utility.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class provides the functionality in the case of a ball collision.
 */
public class BallCollisionHandler {

    private Grid grid;
    private Player player;

    /**
     * This is the constructor.
     * @param grid the grid of cells
     * @param player the player
     */
    public BallCollisionHandler(Grid grid, Player player) {
        this.grid = grid;
        this.player = player;
    }

    // this method checks after the shot ball has reached the hexagon if any balls should be removed
    private ArrayList<Cell> checkRemovalBalls(Cell collidedCell) {
        //initialise an arrayList which will contain all possible removedBalls
        ArrayList<Cell> removalBalls = new ArrayList<Cell>();
        removalBalls.add(collidedCell);

        //initialise a queue for BFS
        LinkedList<Cell> queue = new LinkedList<Cell>();
        queue.add(collidedCell);

        // initialise a list which keeps the visited cells
        ArrayList<Cell> visited = new ArrayList<Cell>();
        visited.add(collidedCell);
        Cell current;

        // loop through the queue
        while (!queue.isEmpty()) {

            current = queue.remove();

            //loop through all neighbors
            for (Cell adjacentCell : current.getAdjacentCells()) {
                if (adjacentCell.getBall() != null) {

                    Ball ball = adjacentCell.getBall();

                    boolean sameColour = player.getPlayerBall().getColor().equals(ball.getColor());


                    //if never visited and both cells contains same colour ball
                    if (!visited.contains(adjacentCell) && sameColour) {
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

    // this method removes the balls that the method checkRemovalBalls returns and adds the points
    // to the score
    private void removeBalls(ArrayList<Cell> toRemove) {
        for (Cell cell : toRemove) {
            GameController.getView().removeBall(cell);
            GameController.getView().displayPlus1(cell);
            grid.getOccupiedCells().remove(cell);
            cell.setBall(null);
        }
    }

    private ArrayList<Cell> notConnectedBalls() {
        ArrayList<Cell> visited = new ArrayList<>();
        ArrayList<Cell> notConnected = new ArrayList<>();
        Queue<Cell> queue = new LinkedList<>();

        visited.add(grid.getCenterCell());
        queue.add(grid.getCenterCell());
        Cell current;

        while (!queue.isEmpty()) {
            current = queue.remove();

            for (Cell adjacentCell : current.getAdjacentCells()) {
                if (!visited.contains(adjacentCell) && adjacentCell.getBall() != null) {
                    queue.add(adjacentCell);
                    visited.add(adjacentCell);
                }
            }
        }

        for (Cell cell : grid.getOccupiedCells()) {
            if (!visited.contains(cell) && cell.getBall() != null) {
                notConnected.add(cell);
            }
        }

        return notConnected;
    }



    /** This method takes care of the situation in which the shot ball hits the hexagon.
     *
     * @param collidedCell the cell from the hexagon that the ball collided with
     */
    public void handleCollision(Cell collidedCell) {
        // display the ball that has collided with the hexagon
        grid.getOccupiedCells().add(collidedCell);
        collidedCell.setBall(new Ball(player.getPlayerBall().getColor(), collidedCell, false));
        GameController.getView().display(collidedCell);

        // check whether the shot ball has hit at least 2 other balls of the same color

        ArrayList<Cell> ballsToBeRemoved = checkRemovalBalls(collidedCell);
        if (ballsToBeRemoved.size() >= 3) {
            player.setScore(player.getScore() + ballsToBeRemoved.size());
            removeBalls(ballsToBeRemoved);
            removeBalls(notConnectedBalls());
        } else if (player.getMissCounter() >= 5) {
            player.setMissCounter(0);
            grid.appendAdditionalBalls(Util.randomBetween(5, 15));
        } else {
            player.setMissCounter(player.getMissCounter() + 1);
        }
    }
}
