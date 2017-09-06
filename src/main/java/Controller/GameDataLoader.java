package Controller;

import Elements.Ball;
import Elements.CenterPiece;
import Model.Cell;
import Model.GameData;
import Model.Grid;
import Utility.Util;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class GameDataLoader {

    GameData data;

    public void initialize(GameData data, double centerX, double centerY) {
        this.data = data;
        data.setScore(0);
        data.setInitialBallAmount(90);
        data.setGrid(new Grid(centerX, centerY));
        loadElements();
    }

    private void loadElements(){
        //create the centerpiece
        CenterPiece center = new CenterPiece();
        center.setCell(data.getGrid().getCenterCell());
        data.getGrid().getCenterCell().setElement(center);

        //add 90 random colored balls to start
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        emptyCells.add(data.getGrid().getCenterCell());
        int index = 0;
        while(emptyCells.size() < data.getInitialBallAmount() + 1){
            for(Cell c : emptyCells.get(index).getAdjacentCells()){
                if(c.getElement() == null && !emptyCells.contains(c)){
                    emptyCells.add(c);
                }
            }
            index++;
        }
        emptyCells.remove(data.getGrid().getCenterCell());
        for(int i = 0; i < data.getInitialBallAmount(); i++){
            Cell c = emptyCells.get(i);
            String color = Ball.COLORS[Util.randomBetween(0,Ball.COLORS.length - 1)];
            Ball b = new Ball(color, c, 1);
            c.setElement(b);
            b.setCell(c);
        }
    }

}
