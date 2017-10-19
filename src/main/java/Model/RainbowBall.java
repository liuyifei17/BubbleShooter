package Model;

import Controller.GameConfiguration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * A rainbow playerBall, it's a special ball.
 */
public class RainbowBall extends PlayerBall {

    /**
     * Initiate a ball with a random image.
     * @param color the random selected color of the ball;
     */
    public RainbowBall(String color) {
        super(color, GameConfiguration.stageWidth / 2, GameConfiguration.topBarHeight, 0);
    }

    /**
     * Initiate a ball with a random image.
     * @param color the random selected color of the ball;
     * @param x the x coordinate of the ball.
     * @param y the y coordinate of the ball.
     */
    public RainbowBall(String color, double x, double y) {
        super(color, x, y, 0);
    }

    @Override
    public ArrayList<Cell> checkRemovalBalls(Cell collidedCell) {
        //initialise an arrayList which will contain all possible removedBalls
        ArrayList<Cell> removalBalls = new ArrayList<>();
        removalBalls.add(collidedCell);
        ArrayList<String> colors = new ArrayList<>();

        for (Cell adjacentCell : collidedCell.getAdjacentCells()) {
            if (adjacentCell.getBall() !=  null) {
                colors.add(adjacentCell.getBall().getColor());
            }
        }

        Map<Integer, ArrayList<Cell>> map = new TreeMap<>();

        // create a treeMap of arrayLists, the key is the size of returned cell.
        for (String color : colors) {
            map.put(checkColorRemoval(color, collidedCell).size(),
                    checkColorRemoval(color, collidedCell));
        }

        ArrayList<Cell> mostCells = new ArrayList<>(map.values()).get(map.values().size()-1);

        return mostCells;
    }

    /**
     * HelperMethod for checkRemovalBalls().Return for each color the cell that contains should be
     * removed balls.
     * @param color an input color, must be the color of the neighbor of rainbow Ball
     * @param collidedCell  the collided cell.
     * @return a list of cells
     */
    private ArrayList<Cell> checkColorRemoval(String color, Cell collidedCell) {
        ArrayList<Cell> removalBalls = new ArrayList<>();
        //initialise a queue for BFS
        LinkedList<Cell> queue = new LinkedList<>();
        queue.add(collidedCell);

        // initialise a list which keeps the visited cells
        ArrayList<Cell> visited = new ArrayList<>();
        visited.add(collidedCell);
        Cell current;

        // loop through the queue
        while (!queue.isEmpty()) {
            removalBalls.add(collidedCell);

            current = queue.remove();

            //loop through all neighbors
            for (Cell adjacentCell : current.getAdjacentCells()) {
                if (adjacentCell.getBall() != null && !adjacentCell.getBall().isCenterPiece()) {

                    Ball ball = adjacentCell.getBall();

                    boolean sameColour = color.equals(ball.getColor());

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
}
