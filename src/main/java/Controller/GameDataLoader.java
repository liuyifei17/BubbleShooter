package Controller;

import Model.Ball;
import Model.Player;
import Model.Cell;
import Model.GameData;
import Model.Grid;
import Utility.Util;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class GameDataLoader {

    private GameData data;

    /**
     * Initializes the game data.
     * @param data    the class in which the data is saved
     * @param centerX the center coord X of the screen where the grid is initialized
     * @param centerY the center coord Y of the screen where the grid is initialized
     */
    public void initialize(GameData data, double centerX, double centerY) {
        this.data = data;
        data.setInitialBallAmount(90);
        data.setGrid(new Grid(centerX, centerY));
        data.setPlayer(new Player());
        loadElements();
    }

    /**
     * Loads the initial elements i.e. balls and centerpiece
     * into the empty cells from the center outwards.
     */
    private void loadElements() {
        //create the centerpiece
        Ball center = new Ball("center", data.getGrid().getCenterCell(), true);
        data.getGrid().getCenterCell().setElement(center);

        //add an x amount of random colored balls to start
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        emptyCells.add(data.getGrid().getCenterCell());
        int index = 0;
        while (emptyCells.size() < data.getInitialBallAmount() + 1) {
            for (Cell c : emptyCells.get(index).getAdjacentCells()) {
                if (!emptyCells.contains(c)) {
                    emptyCells.add(c);
                }
            }
            index++;
        }
        emptyCells.remove(data.getGrid().getCenterCell());
        for (int i = 0; i < data.getInitialBallAmount(); i++) {
            Cell c = emptyCells.get(i);
            String color = Ball.COLORS[Util.randomBetween(0, Ball.COLORS.length - 1)];
            Ball b = new Ball(color, c, false);
            c.setElement(b);
            b.setCell(c);
        }

        data.getGrid().getOccupiedCells().add(data.getGrid().getCenterCell());
        data.getGrid().getOccupiedCells().addAll(emptyCells);
    }

}
