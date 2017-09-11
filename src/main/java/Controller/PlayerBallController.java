package Controller;


import Elements.Ball;
import Elements.Player;
import Elements.PlayerBall;
import Model.Cell;
import Model.Grid;
import Utility.Util;
import View.View;

/**
 * The playerballController keeps track of the movement of the ball.
 */
public class PlayerBallController {

    private final int ballRadius = 17;
    private final int  maximumTimesBallHit = 4;
    private Player player;
    private Grid grid;
    private double mouseX;
    private double mouseY;
    private double deltaX;
    private double deltaY;
    private int counter;


    /**
     * The playerballController consists of the player and the grid of the game and a counter
     *  which keeps track of the times the mouse has been clicked.
     * @param player the player object of the game.
     * @param grid the grid of the game.
     */
    public PlayerBallController(Player player, Grid grid) {
        this.player = player;
        this.grid = grid;
        counter = 0;
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

        final int speedup = 10;
        double vectorX = mouseX - player.getPlayerBall().getX();
        double vectorY = mouseY - player.getPlayerBall().getY();
        double max = Math.max(Math.abs(vectorX), Math.abs(vectorY));

        if (max > 0) {
            deltaX = speedup * vectorX / max;
            deltaY = speedup * vectorY / max;
            counter++;
        }

    }

    private void collisionBallHandler(Cell c) {

    }

    private void nextBall() {

        player.setPlayerBall(new PlayerBall(View.STAGE_WIDTH / 2 - View.SCREEN_WITH_DEVIATION,
                View.TOP_BAR_HEIGHT, player.getNextBall().getColor()));
        player.setNextBall(new Ball(Ball.COLORS[Util.randomBetween(0, Ball.COLORS.length - 1)], null));
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

        Cell collidedCell = player.getPlayerBall().hasCollidedWithCell(grid);
        if(collidedCell != null) {
            this.mouseY = 0;
            this.mouseX = 0;
            collisionBallHandler(collidedCell);
            nextBall();
        }
        // if the wall has collided with the wall for a maximum of 4 times then it will reset
        // the ball
        else if(player.getPlayerBall().getCounter() >= maximumTimesBallHit) {
            nextBall();
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
     * @param deltaX the X direction in which the ball moves.
     * @param deltaY the Y direction in whch the ball moves.
     * @return an array with the negative of either or both of the deltaX and deltaY.
     */
    private double[] reflectBack(double deltaX, double deltaY) {

        if ((player.getPlayerBall().getX() < ballRadius - View.SCREEN_WITH_DEVIATION)
                || (player.getPlayerBall().getX() >= View.STAGE_WIDTH - ballRadius)) {
            deltaX = deltaX * -1;
        }
        if ((player.getPlayerBall().getY() < View.TOP_BAR_HEIGHT)
                || (player.getPlayerBall().getY() >= View.STAGE_HEIGHT - ballRadius)) {
            deltaY = deltaY * -1;
        }
        return new double [] {deltaX, deltaY};
    }

    /**
     * @return the X coordinate of the mouse
     */
    public double getMouseX() {
        return mouseX;
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

}
