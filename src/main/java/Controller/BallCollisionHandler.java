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
        collidedCell.setBall(new Ball(player.getPlayerBall().getColor(), collidedCell, 1));
        GameController.getView().display(collidedCell);

        // check whether the shot ball has hit at least 2 other balls of the same color

        ArrayList<Cell> ballsToBeRemoved = player.getPlayerBall().checkRemovalBalls(collidedCell);
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
