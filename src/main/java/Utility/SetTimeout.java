package Utility;

/**
 * Created by le on 13-9-17.
 */
public class SetTimeout extends Thread {

    private int delay;
    private Runnable runnable;

    public SetTimeout(String name, int delay, Runnable runnable) {
        super(name);
        this.delay = delay;
        this.runnable = runnable;
    }

    public void run() {
        try {
            Thread.sleep(this.delay);
            runnable.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
