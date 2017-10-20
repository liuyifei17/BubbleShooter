package Controller;


import Model.*;
import Utility.Util;


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
        gc.getWallController().placeWalls();
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
            return;
        }

        // if the ball has collided with the wall the deltaX or deltaY will become negative.
        if (player.getPlayerBall().hasCollidedWithWall()) {
            double[] newDelta = reflectBack(deltaX, deltaY);
            deltaX = newDelta[0];
            deltaY = newDelta[1];
        }

        for (Walls i: gc.getData().getRandomWalls()) {
            if (player.getPlayerBall().hasCollidedWithRandomWall(i)) {
                double[] wallDelta = reflectBack(deltaX, deltaY, i);
                deltaX = wallDelta[0];
                deltaY = wallDelta[1];
            }
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

    private double[] reflectBack(double deltaX, double deltaY, Walls wall) {
        double xLeft = wall.getX() - GameConfiguration.wallWidth;
        double xRight = wall.getX() + GameConfiguration.wallWidth;
        double yUp = wall.getY() + GameConfiguration.wallHeight;
        double yDown = wall.getY() - GameConfiguration.wallHeight;
        double xHalfLeft = wall.getX() - GameConfiguration.wallWidth / 2;
        double xHalfRight = wall.getX() + GameConfiguration.wallWidth / 2;


        double[] topLeft = Util.calculateRotatedCoordinates(xLeft, yUp, wall.getX(), wall.getY(),
                wall.getRotation());
        double[] topLeftHalf = Util.calculateRotatedCoordinates(xHalfLeft, yUp, wall.getX(),
                wall.getY(), wall.getRotation());
        double[] topRight = Util.calculateRotatedCoordinates(xRight, yUp, wall.getX(), wall.getY(),
                wall.getRotation());
        double[] topRightHalf = Util.calculateRotatedCoordinates(xHalfRight, yUp, wall.getX(),
                wall.getY(), wall.getRotation());
        double[] bottomLeft = Util.calculateRotatedCoordinates(xLeft, yDown, wall.getX(),
                wall.getY(), wall.getRotation());
        double[] bottomLeftHalf = Util.calculateRotatedCoordinates(xHalfLeft, yDown, wall.getX(),
                wall.getY(), wall.getRotation());
        double[] bottomRight = Util.calculateRotatedCoordinates(xRight, yDown, wall.getX(),
                wall.getY(), wall.getRotation());
        double[] bottomRightHalf = Util.calculateRotatedCoordinates(xHalfRight, yDown, wall.getX(),
                wall.getY(), wall.getRotation());

        double normalVectorUp = wall.getY() + 1;
        double normalVectorDown = wall.getY() - 1;
        double normalVectorRight = wall.getX() + 1;
        double normalVectorLeft = wall.getX() - 1;

        double[] upVector = Util.calculateRotatedCoordinates(wall.getX(), normalVectorUp,
                wall.getX(), wall.getY(), wall.getRotation());
        double[] downVector = Util.calculateRotatedCoordinates(wall.getX(), normalVectorDown,
                wall.getX(), wall.getY(), wall.getRotation());
        double[] rightVector = Util.calculateRotatedCoordinates(normalVectorRight, wall.getY(),
                wall.getX(), wall.getY(), wall.getRotation());
        double[] leftVector = Util.calculateRotatedCoordinates(normalVectorLeft, wall.getY(),
                wall.getX(), wall.getY(), wall.getRotation());

        double distanceToTop = Util.getDistance(topLeftHalf[0], topLeftHalf[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY())
                + Util.getDistance(topRightHalf[0], topRightHalf[1], player.getPlayerBall().getX(),
                        player.getPlayerBall().getY());
        double distanceToTopHalfLeft = Util.getDistance(topLeft[0], topLeft[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY())
                + Util.getDistance(topLeftHalf[0], topLeftHalf[1], player.getPlayerBall().getX(),
                player.getPlayerBall().getY());
        double distanceToTopHalfRight = Util.getDistance(topRight[0], topRight[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY())
                + Util.getDistance(topRight[0], topRight[1], player.getPlayerBall().getX(),
                player.getPlayerBall().getY());

        double distanceToRight = Util.getDistance(bottomRight[0], bottomRight[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY())
                + Util.getDistance(topRight[0], topRight[1], player.getPlayerBall().getX(),
                player.getPlayerBall().getY());

        double distanceToBottom = Util.getDistance(bottomRightHalf[0], bottomRightHalf[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY())
                + Util.getDistance(bottomLeftHalf[0], bottomLeftHalf[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY());
        double distanceToBottomHalfLeft = Util.getDistance(bottomLeftHalf[0], bottomLeftHalf[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY())
                + Util.getDistance(bottomLeft[0], bottomLeft[1], player.getPlayerBall().getX(),
                player.getPlayerBall().getY());
        double distanceToBottomHalfRight = Util.getDistance(bottomRight[0], bottomRight[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY())
                + Util.getDistance(bottomRightHalf[0], bottomRightHalf[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY());

        double distanceToLeft = Util.getDistance(topLeft[0], topLeft[1],
                player.getPlayerBall().getX(), player.getPlayerBall().getY())
                + Util.getDistance(bottomLeft[0], bottomLeft[1], player.getPlayerBall().getX(),
                player.getPlayerBall().getY());

        double[] reflectDeltas = new double[2];
        if (((distanceToTop > distanceToBottom) || (distanceToTopHalfLeft > distanceToBottom)
                || (distanceToTopHalfRight > distanceToBottom))
                && ((distanceToTop > distanceToRight) || (distanceToTopHalfLeft > distanceToRight)
                || (distanceToTopHalfRight > distanceToRight))
                && ((distanceToTop > distanceToLeft) || (distanceToTopHalfLeft > distanceToLeft)
                || (distanceToTopHalfRight > distanceToLeft))) {
            System.out.println("A");
            reflectDeltas = reflectionDeltas(deltaX, deltaY, (upVector[0] - wall.getX()),
                    (upVector[1] - wall.getY()));
        }
        else if (((distanceToBottom > distanceToTop)
                || (distanceToBottomHalfLeft > distanceToTop)
                || (distanceToBottomHalfRight > distanceToTop))
                && ((distanceToBottom > distanceToLeft)
                || (distanceToBottomHalfLeft > distanceToLeft)
                || (distanceToBottomHalfRight > distanceToLeft))
                && ((distanceToBottom > distanceToRight)
                || (distanceToBottomHalfLeft > distanceToRight)
                || (distanceToBottomHalfRight > distanceToRight))) {
            //System.out.println("C");
            reflectDeltas = reflectionDeltas(deltaX, deltaY, (downVector[0] - wall.getX()),
                    (downVector[1] - wall.getY()));
        }
        else if ((distanceToRight > distanceToBottomHalfRight) && (distanceToRight > distanceToLeft)
                && (distanceToRight > distanceToTopHalfRight)) {
            //System.out.println("B");
            reflectDeltas = reflectionDeltas(deltaX, deltaY, (rightVector[0] - wall.getX()),
                    (rightVector[1] - wall.getY()));
        }
        else if ((distanceToLeft > distanceToTopHalfLeft) && (distanceToLeft > distanceToBottom)
                && (distanceToLeft > distanceToBottomHalfLeft)) {
            //System.out.println("D");
            reflectDeltas = reflectionDeltas(deltaX, deltaY, (leftVector[0] - wall.getX()),
                    (leftVector[1] - wall.getY()));
        }

        return reflectDeltas;
    }

    private double[] reflectionDeltas(double deltaX, double deltaY, double normX, double normY) {

        double dotX = 2 * (normX * deltaX +  normY * deltaY);
        double reflectDeltaX = deltaX - dotX * normX;
        double reflectDeltaY = deltaY - dotX * normY;

        return new double[]{reflectDeltaX, reflectDeltaY};
    }

    /**
     * Rotate a certain degree based on the time it takes to hit the grid.
     */
    private void calculateRotation() {
        grid.setStillRotating(true);
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
