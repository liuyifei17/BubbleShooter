package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Game runner class.
 */
public class GameRunner {

    private Timer timer;
    private int graphicsDelay;

    private GridController gridController;
    private PlayerBallController ballController;

    private void initializeGameTimer() {
        graphicsDelay = 0;
        timer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                graphicsDelay++;
                ballController.launchBall();
                if (graphicsDelay == 6) {
                    gridController.process();
                    GameController.getView().redraw();
                    graphicsDelay = 0;
                }
            }
        });
    }

    /**
     * Initializes the timers and starts running the game.
     */
    public void runGame() {
        initializeGameTimer();
        timer.start();
    }

    /**
     * Pauses the game timers.
     */
    public void pauseGame() {
        timer.stop();
    }

    /**
     * Restarts the game timers.
     */
    public void continueGame() {
        timer.start();
    }

    /**
     * @param gridController sets the grid controller.
     * @param ballController sets the ball conrollers.
     * @param timer a timer
     */
    public GameRunner(GridController gridController, PlayerBallController ballController,
                      Timer timer) {
        this.gridController = gridController;
        this.ballController = ballController;
        this.timer = timer;
    }
}
