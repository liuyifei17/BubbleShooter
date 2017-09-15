package View;

import Model.GameData;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.ImageView;

import static org.mockito.Mockito.*;

/**
 * Created by Henks Laptop on 10/09/2017.
 */
class ViewTest {

    View view;
    GameData gameData = mock(GameData.class);
    ImageView topBar = mock(ImageView.class);

    @BeforeEach
    public void setUp() {
    }



    @Test
    void topBar() {

    }
    @Test
    void draw() {
    }

    @Test
    void redraw() {
    }

}