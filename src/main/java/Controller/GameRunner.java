package Controller;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * The Game runner class.
 */
public class GameRunner {

    private final Timer timer = new Timer(5, null);
    private int graphicsdelay;

    private GridController gridController;
    private PlayerBallController ballController;

    private void initializeGameTimer() {
        graphicsdelay = 0;
        timer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                graphicsdelay++;
                ballController.launchBall();
                if(graphicsdelay == 6) {
                    gridController.process();
                    GameController.getView().redraw();
                    graphicsdelay = 0;
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
