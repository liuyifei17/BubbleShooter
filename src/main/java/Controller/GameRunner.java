package Controller;

import java.awt.event.*;
import javax.swing.Timer;
import View.View;


/**
 * Created by jur on 9/5/2017.
 */
public class GameRunner {

    private final Timer timer = new Timer(30,null);
    private GridController gridController;
    private PlayerBallController ballController;
    private View view;

    private void initializeGameTimer() {
        timer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ballController.launchBall();
                    gridController.process();
                    view.redraw();
                }
            });
    }

    public void runGame() {
        initializeGameTimer();
        timer.start();
    }

    public GameRunner(GridController gridController, View view,
                          PlayerBallController ballController) {
        this.gridController = gridController;
        this.view = view;
        this.ballController = ballController;
    }
}
