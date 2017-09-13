package View;

import Elements.Ball;
import Model.Cell;
import Model.GameData;
import Utility.setTimeout;
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



    private ImageView playerBall;
    private ImageView nextBall;
    private ImageView fake;

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

        for(Cell c: data.getGrid().getOccupiedCells()){
            c.getElement().getImageView().relocate(getScreenX(c), getScreenY(c));
        }

        nextBall = new ImageView(data.getPlayer().getNextBall().getSprite());
        nextBall.relocate(View.STAGE_WIDTH / 2 - data.getPlayer().getNextBall().getSprite().getWidth()/4,
                View.TOP_BAR_HEIGHT - 30);
        nextBall.setFitWidth(data.getPlayer().getNextBall().getSprite().getWidth()/2);
        nextBall.setFitHeight(data.getPlayer().getNextBall().getSprite().getHeight()/2);


        playerBall = new ImageView(data.getPlayer().getPlayerBall().getImage());
        playerBall.relocate(data.getPlayer().getPlayerBall().getX() - data.getPlayer().getPlayerBall().getImage().getWidth() / 2,
                data.getPlayer().getPlayerBall().getY() - data.getPlayer().getPlayerBall().getImage().getHeight() / 2);


        //add components to game pane
        pane.getChildren().add(topBar);
        pane.getChildren().add(scoreBar);
        for(Cell c: data.getGrid().getCells()){
            pane.getChildren().add(c.getElement().getImageView());
        }
        pane.getChildren().add(playerBall);
        pane.getChildren().add(nextBall);

    }

    public void redraw(){
        //check for changed cells and update children
        ArrayList<Cell> cells = data.getGrid().getOccupiedCells();


        //relocate elements
        for(Cell c:cells){
            c.getElement().getImageView().relocate(getScreenX(c), getScreenY(c));
            c.getElement().getImageView().rotateProperty().setValue(data.getGrid().getRotation());
        }

        playerBall.setImage(data.getPlayer().getPlayerBall().getImage());
        nextBall.setImage(data.getPlayer().getNextBall().getSprite());

        playerBall.relocate(data.getPlayer().getPlayerBall().getX() - data.getPlayer().getPlayerBall().getImage().getWidth() / 2,
                data.getPlayer().getPlayerBall().getY() - data.getPlayer().getPlayerBall().getImage().getHeight() / 2);


    }

    class removePlusOneIcon implements Runnable{

        private Cell cell;

        public removePlusOneIcon(Cell c) {
            this.cell = c;
        }

        public void run() {
            this.cell.getElement().setImage(null);
            data.getGrid().getOccupiedCells().remove(this.cell);
        }
    }

    // this method removes a ball and displays a '+1' icon for 1 second
    public void removeBall(Cell c) {
        c.getElement().setImage(new Image("images/plus1.png"));
        if(c.getElement() instanceof Ball) {
            ((Ball) c.getElement()).setColor(null);
        }

        removePlusOneIcon r = new removePlusOneIcon(c);

        setTimeout t = new setTimeout("Timeout Thread", 1000, r);
        t.start();

    }

    // This method displays a ball when it has hit the hexagon
    // Without this function you get a 1sec in which the ball is at (0,0)
    public void display(Cell c) {
        c.getElement().getImageView().relocate(getScreenX(c), getScreenY(c));
    }

    private double getScreenX(Cell cell){
        return (cell.getCurrentX() - (cell.getElement().getSprite().getWidth() / 2));
    }

    private double getScreenY(Cell cell){
        return (cell.getCurrentY() - (cell.getElement().getSprite().getHeight() / 2));
    }

}