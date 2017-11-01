package Controller;

import Model.Cell;
import Model.GameData;
import Model.Walls;
import Utility.Util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The wallcontroller puts the wall the right location and replaces them when they have to
 * be replaced.
 */
public class WallController {

    private GameData data;

    /**
     * @param data the walls are stored in the gameData.
     */
    public WallController(GameData data) {
        this.data = data;
    }

    /**
     * Place the walls at a location when the setting are put on for it. A maximum of three walls
     * are used in the game, for each shot they will be relocated.
     */
    public void placeWalls() {
        if (!GameConfiguration.walls) {
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
            Walls wall = new Walls(cell.getCurrentX(), cell.getCurrentY(), 0);
            wall.calculateRotation(data.getGrid());
            data.getRandomWalls().add(wall);

            Iterator<Cell> iter = location.iterator();
            while (iter.hasNext()) {
                Cell i = iter.next();
                if (Util.getDistance(i.getCurrentX(), i.getCurrentY(),
                        cell.getCurrentX(), cell.getCurrentY()) <= GUIConfiguration.wallRadius) {
                    iter.remove();
                }
            }
            counter++;
        }

    }


}
