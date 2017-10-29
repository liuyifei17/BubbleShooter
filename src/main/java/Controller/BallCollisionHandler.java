package Controller;

import Model.*;
import Utility.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    private void removeBalls(ArrayList<Cell> toRemove, int multiplier) {
        for (Cell cell : toRemove) {
            GameController.getView().removeBall(cell);
            GameController.getView().displayPlusIcon(cell, multiplier);
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

    /**
     * This method puts the playerball to the hexagon after collision.
     */
    private void setBallInHexagon(Cell collidedCell) {
        // display the ball that has collided with the hexagon
        grid.getOccupiedCells().add(collidedCell);

        if (player.getPlayerBall() instanceof NormalBall) {
            collidedCell.setBall(new Ball(player.getPlayerBall().getColor(), collidedCell, 1));
        } else if (player.getPlayerBall() instanceof ExplosiveBall) {
            collidedCell.setBall(new Ball(player.getPlayerBall().getColor(), collidedCell, 2));
        } else if (player.getPlayerBall() instanceof RainbowBall) {
            List<String> colors = GameConfiguration.colors;
            String color = colors.get(Util.randomBetween(0, colors.size() - 1));
            collidedCell.setBall(new Ball(color, collidedCell, 1));
        } else if (player.getPlayerBall() instanceof MultiplierBall) {
            collidedCell.setBall(new Ball(player.getPlayerBall().getColor(), collidedCell, 1));
        }

        GameController.getView().display(collidedCell);
    }

    /** This method takes care of the situation in which the shot ball hits the hexagon.
     * @param collidedCell the cell from the hexagon that the ball collided with
     */
    public void handleCollision(Cell collidedCell) {

        setBallInHexagon(collidedCell);

        // check whether the shot ball has hit at least 2 other balls of the same color

        ArrayList<Cell> ballsToBeRemoved = player.getPlayerBall().checkRemovalBalls(collidedCell);

        if (player.getPlayerBall() instanceof NormalBall
                || player.getPlayerBall() instanceof RainbowBall) {
            if (ballsToBeRemoved.size() >= 3) {
                player.setScore(player.getScore() + ballsToBeRemoved.size());
                removeBalls(ballsToBeRemoved, 1);
                removeBalls(notConnectedBalls(), 1);
            } else {
                player.setMissCounter(player.getMissCounter() + 1);
            }
        } else if (player.getPlayerBall() instanceof ExplosiveBall) {
            player.setScore(player.getScore() + ballsToBeRemoved.size());
            removeBalls(ballsToBeRemoved, 1);
            removeBalls(notConnectedBalls(), 1);
        } else if (player.getPlayerBall() instanceof MultiplierBall) {
            if (ballsToBeRemoved.size() >= 3) {
                player.setScore(player.getScore() + ballsToBeRemoved.size() * 2);
                removeBalls(ballsToBeRemoved, 2);
                removeBalls(notConnectedBalls(), 2);
            } else {
                collidedCell.setBall(new Ball(player.getPlayerBall().getColor(), collidedCell, 1));
                player.setMissCounter(player.getMissCounter() + 1);
            }
        }

        if (player.getMissCounter() >= 5) {
            player.setMissCounter(0);
            grid.appendAdditionalBalls(Util.randomBetween(5, 15));
        }
    }
}