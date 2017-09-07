package View;

import Elements.Element;
import Model.Cell;
import Model.GameData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

/**
 * Created by jur on 9/5/2017.
 */
public class View {

    private GameData data;
    private Pane pane;

    private Image background;
    private ImageView topBar;
    private ImageView scoreBar;

    private ArrayList<Cell> cells;
    private ArrayList<ImageView> elementSprites;

    public static final double STAGE_WIDTH = 600;
    public static double STAGE_HEIGHT = 700;
    public static final int TOP_BAR_HEIGHT = 70;
    private static final int SCORE_BAR_HEIGHT = 40;
    private static final int SCORE_BAR_WIDTH = 240;

    public View(Pane pane, GameData data){
        this.pane = pane;
        this.data = data;
    }

    public void draw(){
        //draw background
        background = new Image("images/background1.png");
        pane.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        //draw top bar
        topBar = new ImageView("images/topBar1.png");
        topBar.relocate(0, 0);
        topBar.fitWidthProperty().bind(pane.widthProperty());
        topBar.setFitHeight(TOP_BAR_HEIGHT);

        //draw score bar
        scoreBar = new ImageView("images/scoreBar1.png");
        scoreBar.relocate(8, 15);
        scoreBar.setFitHeight(SCORE_BAR_HEIGHT);
        scoreBar.setFitWidth(SCORE_BAR_WIDTH);

        //draw entities
        cells = new ArrayList<Cell>();
        elementSprites = new ArrayList<ImageView>();
        for(Cell c: data.getGrid().getCells()){
            Element e = c.getElement();
            if(e != null){
                cells.add(c);
                elementSprites.add(new ImageView(e.getSprite()));
            }
        }
        for(int i = 0; i < elementSprites.size(); i++){
            Cell c = cells.get(i);
            elementSprites.get(i).relocate(getScreenX(c), getScreenY(c));
        }

        //add components to game pane
        pane.getChildren().add(topBar);
        pane.getChildren().add(scoreBar);
        for(ImageView iv: elementSprites){
            pane.getChildren().add(iv);
        }
    }

    private double getScreenX(Cell cell){
        return (cell.getX() - (cell.getElement().getSprite().getWidth() / 2));
    }

    private double getScreenY(Cell cell){
        return (cell.getY() - (cell.getElement().getSprite().getHeight() / 2));
    }

}
