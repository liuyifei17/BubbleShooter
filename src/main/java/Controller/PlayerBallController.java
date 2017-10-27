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
        //gc.getWallController().placeWalls();
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

        if (gc.getData() != null
                && gc.getData().getRandomWalls().size() > 0) {
            WallIterator iterator = new WallIterator(gc.getData().getRandomWalls());
            while (iterator.hasNext()) {
                Walls i = iterator.next();
                if (player.getPlayerBall().hasCollidedWithRandomWall(i)) {
                    double[] wallDelta = reflectBack(deltaX, deltaY, i);
                    deltaX = wallDelta[0];
                    deltaY = wallDelta[1];
                }
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

    /**
     * Return the reflected deltas for which the ball will travels after
     * colliding with the wall at a certain surface.
     * @param deltaX the x direction in which the ball travels.
     * @param deltaY the y direction in which the ball travels.
     * @param wall the location of the wall.
     * @return the reflected deltaX and deltaY coordinates.
     */
    private double[] reflectBack(double deltaX, double deltaY, Walls wall) {
        double[] upVector = wall.calculateUpNormal();
        double[] downVector = wall.calculateDownNormal();
        double[] rightVector = wall.calculateRightNormal();
        double[] leftVector = wall.calculateLeftNormal();

        double[] topDistances = wall.topDistancesToBall(player.getPlayerBall());
        double[] bottomDistances = wall.bottomDistancesToBall(player.getPlayerBall());
        double distanceToTop = topDistances[0];
        double distanceToTopHalfLeft = topDistances[1];
        double distanceToTopHalfRight = topDistances[2];
        double distanceToRight = wall.rightDistanceToBall(player.getPlayerBall());
        double distanceToBottom = bottomDistances[0];
        double distanceToBottomHalfLeft = bottomDistances[1];
        double distanceToBottomHalfRight = bottomDistances[2];
        double distanceToLeft = wall.leftDistanceToBall(player.getPlayerBall());

        double[] reflectDeltas = new double[2];
        if (wall.hasCollidedTop(distanceToTop, distanceToBottom, distanceToTopHalfLeft,
                distanceToRight, distanceToTopHalfRight, distanceToLeft)) {
            reflectDeltas = reflectionDeltas(deltaX, deltaY, (upVector[0] - wall.getX()),
                    (upVector[1] - wall.getY()));
        }
        else if (wall.hasCollidedBottom(distanceToTop, distanceToBottom, distanceToBottomHalfLeft,
                distanceToRight, distanceToBottomHalfRight, distanceToLeft)) {
            reflectDeltas = reflectionDeltas(deltaX, deltaY, (downVector[0] - wall.getX()),
                    (downVector[1] - wall.getY()));
        }
        else if (wall.hasCollidedRight(distanceToRight, distanceToBottomHalfRight,
                distanceToLeft, distanceToTopHalfRight)) {
            reflectDeltas = reflectionDeltas(deltaX, deltaY, (rightVector[0] - wall.getX()),
                    (rightVector[1] - wall.getY()));
        }
        else if (wall.hasColllidedLeft(distanceToLeft, distanceToTopHalfLeft,
                distanceToRight, distanceToBottomHalfLeft)) {
            reflectDeltas = reflectionDeltas(deltaX, deltaY, (leftVector[0] - wall.getX()),
                    (leftVector[1] - wall.getY()));
        }

        return reflectDeltas;
    }

    /**
     * Calculate reflected deltas on the random wall.
     * @param deltaX the x where the ball is traveling toward.
     * @param deltaY the y where the ball is traveling toward.
     * @param normX the normal x on the surface.
     * @param normY the normal y on the surface.
     * @return the reflected deltas.
     */
    private double[] reflectionDeltas(double deltaX, double deltaY, double normX, double normY) {

        double distanceDelta = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        deltaX = deltaX / distanceDelta;
        deltaY = deltaY / distanceDelta;
        double dotX = 2 * (normX * deltaX + normY * deltaY);
        double reflectDeltaX = deltaX - dotX * normX;
        double reflectDeltaY = deltaY - dotX * normY;
        reflectDeltaX = reflectDeltaX * distanceDelta;
        reflectDeltaY = reflectDeltaY * distanceDelta;

        return new double[]{reflectDeltaX, reflectDeltaY};
    }

    /**
     * Rotate a certain degree based on the time it takes to hit the grid.
     */
    private void calculateRotation() {
        grid.setStillRotating(true);
        int rotationIndex = stopWatch / 80;
        if (rotationIndex >= 3) {
            rotationIndex = 2;
        }
        if (directionDeltaX > 0 && directionDeltaY > 0) {
            grid.setRotationDifference(GameConfiguration.rightRotation.get(rotationIndex));
        }

        if (directionDeltaX < 0 && directionDeltaY > 0) {
            grid.setRotationDifference(GameConfiguration.leftRotation.get(rotationIndex));
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
     *
     * @return the value stored in the field deltaX
     */
    public double getDeltaX() {
        return this.deltaX;
    }


    /**
     * This method is the getter for the deltaY field.
     *
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
     *
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
