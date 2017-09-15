package Controller;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * The Game runner class.
 */
public class GameRunner {

    private final Timer slowTimer = new Timer(30, null);
    private final Timer fastTimer = new Timer(5, null);

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

    /**
     * Initializes the timers and starts running the game.
     */
    public void runGame() {
        initializeGameTimer();
        fastTimer.start();
        slowTimer.start();
    }

    /**
     * Pauses the game timers.
     */
    public void pauseGame() {
        fastTimer.stop();
        slowTimer.stop();
    }

    /**
     * Restarts the game timers.
     */
    public void continueGame() {
        fastTimer.start();
        slowTimer.start();
    }

    /**
     * @param gridController sets the grid controller.
     * @param ballController sets the ball conrollers.
     */
    public GameRunner(GridController gridController, PlayerBallController ballController) {
        this.gridController = gridController;
        this.ballController = ballController;
    }

    /**
     * @param gridController sets the grid controller.
     */
    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }

    /**
     * @param ballController sets the ball controller.
     */
    public void setBallController(PlayerBallController ballController) {
        this.ballController = ballController;
    }
}
