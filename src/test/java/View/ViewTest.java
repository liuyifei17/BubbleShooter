package View;

import Model.GameData;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.text.html.ImageView;

import java.awt.*;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by Henks Laptop on 10/09/2017.
 */
class ViewTest {

    View view;
    Pane pane = mock(Pane.class);
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