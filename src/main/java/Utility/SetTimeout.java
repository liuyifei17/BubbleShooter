package Utility;

/**
 * This method can be used to execute code after a certain delay.
 */
public class SetTimeout extends Thread {

    private int delay;
    private Runnable runnable;

    /**
     * This is the constructor of the class.
     * @param name the name of the thread
     * @param delay the delay after which the runnable is executed
     * @param runnable the code that will be executed
     */
    public SetTimeout(String name, int delay, Runnable runnable) {
        super(name);
        this.delay = delay;
        this.runnable = runnable;
    }

    /**
     * This method executes the runnable.
     */
    public void run() {
        try {
            Thread.sleep(this.delay);
            runnable.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
