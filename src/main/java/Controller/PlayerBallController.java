package Controller;


import Model.*;


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
    private BallCollisionHandler ballCollisionHandler;


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
        this.ballCollisionHandler = new BallCollisionHandler(grid, player);
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

    // reset the player ball
    private void resetBall() {
        stopWatch = 0;
        collidedCell = null;
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
            ballCollisionHandler.handleCollision(collidedCell);

            //set a rotation
            calculateRotation();

            resetBall();
            return;
        }

        // if the ball has collided with the wall for a maximum of 4 times then it will reset
        // the ball
        else if (player.getPlayerBall().getCounter() >= GameConfiguration.maximumTimesBallHit) {
            resetBall();
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
     * @return the value stored in the field deltaX
     */
    public double getDeltaX() {
        return this.deltaX;
    }


    /**
     * This method is the getter for the deltaY field.
     * @return the value stored in the field deltaY
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
     * @return the value stored in the field stopWatch
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
