package Elements;

import javafx.scene.image.Image;

/**
 * Created by jur on 9/5/2017.
 */
public class CenterPiece extends HexagonElement {

    public CenterPiece(double x, double y) {
        super.setId(0);
        super.setX(x);
        super.setY(y);
        super.setImg(new Image("images/center.png"));
    }

}
