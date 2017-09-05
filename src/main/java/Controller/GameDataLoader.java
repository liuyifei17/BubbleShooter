package Controller;

import Model.GameData;
import Entities.CenterPiece;

/**
 * Created by jur on 9/5/2017.
 */
public class GameDataLoader {

    public static void initialize(GameData data, double centerX, double centerY) {
        data.setScore(0);
        data.setCenterPiece(new CenterPiece(centerX, centerY));
    }

}
