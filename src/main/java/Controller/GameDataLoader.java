package Controller;

import Model.GameData;
import Elements.CenterPiece;

/**
 * Created by jur on 9/5/2017.
 */
public class GameDataLoader {

    GameData data;

    public void initialize(GameData gameData, double centerX, double centerY) {
        data = gameData;
        data.setScore(0);
        loadElements(centerX, centerY);
    }

    private void loadElements(double centerX, double centerY){
        CenterPiece center = new CenterPiece(centerX, centerY);
        data.setCenterPiece(center);
        data.getElementList().add(center);
    }

}
