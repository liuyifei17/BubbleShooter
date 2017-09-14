package Controller;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * Created by jur on 9/5/2017.
 */
public class GameRunner {

    private final Timer slowTimer = new Timer(30,null);
    private final Timer fastTimer = new Timer(5,null);
    private GridController gridController;
    private PlayerBallController ballController;

    private void initializeGameTimer() {
        fastTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ballController.launchBall();
            }
        });
        slowTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gridController.process();
                GameController.getView().redraw();
            }
        });
    }

    public void runGame() {
        initializeGameTimer();
        fastTimer.start();
        slowTimer.start();
    }

    public GameRunner(GridController gridController, PlayerBallController ballController) {
        this.gridController = gridController;
        this.ballController = ballController;
    }
}
