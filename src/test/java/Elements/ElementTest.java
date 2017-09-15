package Elements;

import Model.Cell;
import Model.Element;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Henks Laptop on 07/09/2017.
 */
class ElementTest {
    private Element element;
    private Cell cell;
    private Image img;
    private Application application = mock(Application.class);

    @BeforeEach
    public void setUp() {
        cell = mock(Cell.class);
        img = mock(Image.class);
        element = new Element(cell, img);
//        primaryStage.show();
    }


    @Test
    void getSprite() {
        assertThat(element.getSprite()).isEqualTo(img);
    }

    @Test
    void getCell() {
        assertThat(element.getCell()).isEqualTo(cell);
    }

    @Test
    void setCell() {
        Cell otherCell = mock(Cell.class);
        element.setCell(otherCell);
        assertThat(element.getCell()).isEqualTo(otherCell);
    }

}