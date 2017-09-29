package Controller;


import Model.*;
import Utility.Util;
import View.View;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * The playerballController keeps track of the movement of the ball.
 */
public class PlayerBallController {

    private Player player;
    private Grid grid;
    private GameController gc;
    private double mouseX;
    private double mouseY;
    private double deltaX;
    private double deltaY;
    private double directionDeltaX;
    private double directionDeltaY;
    private int counter;
    private int stopWatch;
    private Cell collidedCell;


    /**
     * The playerballController consists of the player and the grid of the game and a counter
     * which keeps track of the times the mouse has been clicked.
     *
     * @param gc     the game controller object
     * @param player the player object of the game.
     * @param grid   the grid of the game.
     */
    public PlayerBallController(GameController gc, Player player, Grid grid) {
        this.gc = gc;
        this.player = player;
        this.grid = grid;
        this.collidedCell = null;
        this.counter = 0;
        this.stopWatch = 0;
        this.deltaX = 0;
        this.deltaY = 0;
        this.mouseX = 0;
        this.mouseY = 0;
    }

    /**
     * After the mouse position is given, it will calculate the deltaX and deltaY by creating
     * a vector between the mouse position and playerball position.
     * Then you will divide both of the X and Y coordinate of the vector with the highest value
     * of either the X and Y so that you will have a smaller Delta.
     */
    public void calculateDelta() {
        if (counter > 0) {
            return;
        }

        directionDeltaX = mouseX - player.getPlayerBall().getX();
        directionDeltaY = mouseY - player.getPlayerBall().getY();

        double dotProd = Math.sqrt(directionDeltaX * directionDeltaX
                + directionDeltaY * directionDeltaY);

        deltaX = GameConfiguration.speedup * directionDeltaX / dotProd;
        deltaY = GameConfiguration.speedup * directionDeltaY / dotProd;
        counter++;
    }

    // this method checks after the shot ball has reached the hexagon if any balls should be removed
    private ArrayList<Cell> checkRemovalBalls() {
        //initialise an arrayList which will contain all possible removedBalls
        ArrayList<Cell> removalBalls = new ArrayList<Cell>();
        removalBalls.add(collidedCell);

        //initialise a queue for BFS
        Queue queue = new LinkedList<Cell>();
        queue.add(collidedCell);

        // initialise a list which keeps the visited cells
        ArrayList<Cell> visited = new ArrayList<Cell>();
        visited.add(collidedCell);
        Cell current;

        // loop through the queue
        while (!queue.isEmpty()) {

            current = (Cell) queue.remove();

            //loop through all neighbors
            for (Cell adjacentCell : current.getAdjacentCells()) {
                if (adjacentCell.getElement() instanceof Ball) {

                    Ball ball = (Ball) adjacentCell.getElement();

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

    private boolean fillEmptyCell(int index) {
        for (Cell c : grid.getOccupiedCells().get(index).getAdjacentCells()) {
            if (c.getElement().getSprite() == null) {
                String color = GameConfiguration.colors.get(Util.randomBetween(0,
                        GameConfiguration.colors.size() - 1));
                grid.getOccupiedCells().add(c);
                c.getElement().setImage(new Image("images/" + color + " ball.png"));
                if (c.getElement() instanceof Ball) {
                    ((Ball) c.getElement()).setColor(color);
                }
                GameController.getView().display(c);
                return true;
            }
        }
        return false;
    }

    // this method removes the balls that the method checkRemovalBalls returns and adds the points
    // to the score
    private void removeBalls(ArrayList<Cell> toRemove) {
        if (toRemove.size() > 2) {
            for (Cell cell : toRemove) {
                if (cell.getElement() instanceof Ball) {
                    GameController.getView().removeBall(cell);
                    ((Ball) cell.getElement()).setColor(null);
                }
            }
        }

        toRemove.clear();
        toRemove = notConnectedBalls();
        for (Cell cell : toRemove) {
            if (cell.getElement() instanceof Ball) {
                GameController.getView().removeBall(cell);
                ((Ball) cell.getElement()).setColor(null);
            }
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
                if (!visited.contains(adjacentCell) && adjacentCell.getElement() instanceof Ball
                        && ((Ball) adjacentCell.getElement()).getColor() != null) {
                    queue.add(adjacentCell);
                    visited.add(adjacentCell);
                }
            }
        }

        for (Cell cell : grid.getOccupiedCells()) {
            if (!visited.contains(cell) && cell.getElement() instanceof Ball
                    && ((Ball) cell.getElement()).getColor() != null) {
                notConnected.add(cell);
            }
        }

        return notConnected;
    }

    // this method adds balls to the hexagon every time the player misses more than 6 times
    private void appendAdditionalBalls() {
        int numberBalls = Util.randomBetween(5, 15);
        int randomIndex;
        ArrayList<Integer> randomIndexes = new ArrayList<Integer>();
        for (int i = 0; i < numberBalls; i++) {
            while (true) {
                randomIndex = Util.randomBetween(0, grid.getOccupiedCells().size() - 1);
                if (!randomIndexes.contains(randomIndex) && fillEmptyCell(randomIndex)) {
                    randomIndexes.add(randomIndex);
                    break;
                }
            }
        }
    }

    // this method takes care of the situation in which the shot ball hits the hexagon
    private void ballCollisionHandler() {
        //put ball in cell
        //collidedCell.setElement(new Ball(player.getPlayerBall().getColor(), collidedCell));

        // display the ball that has collided with the hexagon
        grid.getOccupiedCells().add(collidedCell);
        collidedCell.getElement().setImage(player.getPlayerBall().getImage());
        if (collidedCell.getElement() instanceof Ball) {
            ((Ball) collidedCell.getElement()).setColor(player.getPlayerBall().getColor());
        }

        if(GameController.getView()!= null) GameController.getView().display(collidedCell);

        // check whether the shot ball has hit at least 2 other balls of the same color

        ArrayList<Cell> ballsToBeRemoved = checkRemovalBalls();
        if (ballsToBeRemoved.size() >= 3) {
            player.setScore(player.getScore() + ballsToBeRemoved.size());
            removeBalls(ballsToBeRemoved);
        } else if (player.getMissCounter() >= 5) {
            player.setMissCounter(0);
            appendAdditionalBalls();
        } else {
            player.setMissCounter(player.getMissCounter() + 1);
        }

        //reset variables
        mouseY = 0;
        mouseX = 0;
        collidedCell = null;

        //set a rotation
        calculateRotation();

        //nextBall
        player.nextBall();
        this.setMouseY(0);
        this.setMouseX(0);
        this.setDeltaX(0);
        this.setDeltaY(0);
        counter = 0;
    }



    /**
     * Launches the ball in the direction of the mouse.
     */
    public void launchBall() {
        if (getMouseY() == 0) {
            return;
        }

        stopWatch++;

        //checks for collisions with cells
        if (collidedCell == null) {
            collidedCell = player.getPlayerBall().getCellCollision(grid, deltaX, deltaY);
        }
        if (collidedCell != null) {
            ballCollisionHandler();
            return;
        }

        // if the ball has collided with the wall for a maximum of 4 times then it will reset
        // the ball
        else if (player.getPlayerBall().getCounter() >= GameConfiguration.maximumTimesBallHit) {
            stopWatch = 0;
            player.nextBall();
            this.setMouseY(0);
            this.setMouseX(0);
            this.setDeltaX(0);
            this.setDeltaY(0);
            counter = 0;
        }

        // if the ball has collided with the wall the deltaX or deltaY will become negative.
        if (player.getPlayerBall().hasCollidedWithWall()) {
            double[] newDelta = reflectBack(deltaX, deltaY);
            deltaX = newDelta[0];
            deltaY = newDelta[1];
        }

        // the new coordinate of the ball is the the previous one added with the delta.
        double newXCoord = player.getPlayerBall().getX() + deltaX;
        double newYCoord = player.getPlayerBall().getY() + deltaY;

        // set the new coordinate for the playerball.
        player.getPlayerBall().setX(newXCoord);
        player.getPlayerBall().setY(newYCoord);
    }

    /**
     * When the ball collides with the wall, then it will make deltaX negative if it collided
     * with the walls in the width and deltaY negative if it collided with the s in the height.
     *
     * @param deltaX the X direction in which the ball moves.
     * @param deltaY the Y direction in whch the ball moves.
     * @return an array with the negative of either or both of the deltaX and deltaY.
     */
    private double[] reflectBack(double deltaX, double deltaY) {

        if ((player.getPlayerBall().getX() < GameConfiguration.ballRadius)
                || (player.getPlayerBall().getX() >= GameConfiguration.stageWidth)) {
            deltaX = deltaX * -1;
        }
        if ((player.getPlayerBall().getY() < GameConfiguration.topBarHeight)
                || (player.getPlayerBall().getY() >= GameConfiguration.stageHeight)) {
            deltaY = deltaY * -1;
        }
        return new double[]{deltaX, deltaY};
    }

    /**
     * Rotate a certain degree based on the time it takes to hit the grid.
     */
    private void calculateRotation() {
        if (directionDeltaX > 0 && directionDeltaY > 0) {
            if (stopWatch < 70) {
                grid.setRotationDifference(GameConfiguration.rightRotation.get(0));
            }
            if (stopWatch >= 70 && stopWatch < 170) {
                grid.setRotationDifference(GameConfiguration.rightRotation.get(1));
            }
            if (stopWatch > 170) {
                grid.setRotationDifference(GameConfiguration.rightRotation.get(2));
            }
        }

        if (directionDeltaX < 0 && directionDeltaY > 0) {
            if (stopWatch < 70) {
                grid.setRotationDifference(GameConfiguration.leftRotation.get(0));
            }
            if (stopWatch >= 70 && stopWatch < 170) {
                grid.setRotationDifference(GameConfiguration.leftRotation.get(1));
            }
            if (stopWatch > 170) {
                grid.setRotationDifference(GameConfiguration.leftRotation.get(2));
            }
        }
        stopWatch = 0;
    }

    /**
     * @param mouseX set the X coordinate of the mouse
     */
    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    /**
     * @return the Y coordinate of the mouse
     */
    public double getMouseY() {
        return mouseY;
    }

    /**
     * @param mouseY set the Y coordinate of the mouse
     */
    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    /**
     * This method is the getter for the deltaX field.
     */
    public double getDeltaX() {
        return this.deltaX;
    }


    /**
     * This method is the getter for the deltaY field.
     */
    public double getDeltaY() {
        return this.deltaY;
    }


    /**
     * @param deltaX the X translation of the ball
     */
    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }


    /**
     * @param deltaY the Y translation of the ball
     */
    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    /**
     * This is the setter for the grid field.
     *
     * @param grid the grid object that is associated with this controller
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }


    /**
     * This method is the getter for the stopWatch field.
     */
    public double getStopWatch() {
        return this.stopWatch;
    }

    /**
     * This is the setter for the grid field.
     *
     * @param stopWatch the integer to replace the value in the stopWatch field
     */
    public void setStopWatch(int stopWatch) {
        this.stopWatch = stopWatch;
    }
}
