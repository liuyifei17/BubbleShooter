package Controller;

import java.awt.event.*;
import javax.swing.Timer;

import Main.Main;

/**
 * Created by jur on 9/5/2017.
 */
public class GameRunner {

    private final Timer timer = new Timer(30,null);
    private GridController gridController;
    private PlayerBallController ballController;

    private void initializeGameTimer() {
        timer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ballController.launchBall();
                    gridController.process();
                    Main.getView().redraw();
                }
            });
    }

    public void runGame() {
        initializeGameTimer();
        timer.start();
    }

    public GameRunner(GridController gridController, PlayerBallController ballController) {
        this.gridController = gridController;
        this.ballController = ballController;
    }
}
