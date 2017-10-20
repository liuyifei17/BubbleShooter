package Controller;

import Model.Cell;
import Model.GameData;
import Model.Walls;
import Utility.Util;

import java.util.ArrayList;
import java.util.Iterator;

public class WallController {

    private GameData data;

    public WallController(GameData data) {
        this.data = data;
    }

    public void placeWalls() {
        if (!(GameConfiguration.walls)) {
            data.getRandomWalls().clear();
            return;
        }

        ArrayList<Cell> location = data.getGrid().emptyWallLocation();
        int counter = 0;
        data.getRandomWalls().clear();
        while (counter < GameConfiguration.amountOfWalls) {
            int index = Util.randomBetween(0, location.size() - 1);

            if (location.size() == 0) {
                break;
            }

            Cell cell = location.get(index);
            Walls wall = new Walls(cell.getCurrentX(), cell.getCurrentY(),0);
            wall.calculateRotation(data.getGrid());
            data.getRandomWalls().add(wall);

            Iterator<Cell> iter = location.iterator();
            while (iter.hasNext()) {
                Cell i = iter.next();
                if (Util.getDistance(i.getCurrentX(), i.getCurrentY(),
                        cell.getCurrentX(), cell.getCurrentY()) <= GameConfiguration.wallRadius) {
                    iter.remove();
                }
            }
            counter++;
        }

    }


}
