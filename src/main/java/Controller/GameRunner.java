package Controller;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * Created by jur on 9/5/2017.
 */
public class GameRunner {

        private final Timer timer = new Timer(40,null);

        private void initializeGameTimer(){
            timer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                   System.out.println("Running game now...");
                }
            });
        }

        public void runGame(){
            initializeGameTimer();
            timer.start();
        }
}
