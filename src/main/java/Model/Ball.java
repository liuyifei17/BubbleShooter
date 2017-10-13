package Model;


/**
 * This class represents the ball entity.
 */
public class Ball {

    private String color;
    private Cell cell;
    private int ballType;


    /**
     * creates a ball that is located inside a cell.
     * @param color the color of the ball
     * @param cell  the cell in which the ball is currently located
     * @param ballType  type of the ball: -1 for player ball, 0 for center, 1 for normal ball,
     *                   2 for explosive, 3 for rainbow, 4 for multiplier.
     */
    public Ball(String color, Cell cell, int ballType) {
        this.color = color;
        this.cell = cell;
        this.ballType = ballType;
    }

    /**
     * Getter for the color field.
     * @return the color of the ball
     */
    public String getColor() {
        return this.color;
    }

    /**
     * @return true if it is the centerpiece.
     */
    public boolean isCenterPiece() {
        return ballType == 0;
    }

    /**
     * @return true if it is a normal ball.
     */
    public boolean isNormalBall() {
        return ballType == 1;
    }

    /**
     * @return true if it is an explosive ball.
     */
    public boolean isExplosiveBall() {
        return ballType == 2;
    }

    /**
     * @return true if it is a rainbow ball.
     */
    public boolean isRainbowBall() {
        return ballType == 3;
    }

    /**
     * @return true if it is a multiplier ball.
     */
    public boolean isMultiplierBall() {
        return ballType == 4;
    }

    /**
     * This is the setter for cell field.
     * @param cell the cell to occupy to cell field
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     * This is the getter for the cell field.
     * @return the cell in which the ball is located.
     */
    public Cell getCell() {
        return this.cell;
    }

    /**
     * @return ball type
     */
    public int getBallType() {
        return ballType;
    }

}
