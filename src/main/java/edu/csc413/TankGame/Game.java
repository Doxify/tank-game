package main.java.edu.csc413.TankGame;

import main.java.edu.csc413.TankGame.graphics.Screen;
import main.java.edu.csc413.TankGame.graphics.ui.UIManager;
import main.java.edu.csc413.TankGame.level.Level;

public class Game implements Runnable {

    private Thread gameThread;
    private final Launcher launcher;
    private final Level level;

    private final Screen screen;

    private static UIManager uiManager;

    private boolean running;
    private static long tick;

    public Game(Launcher launcher) {
        this.launcher = launcher;
        this.level = new Level("map1");
        this.screen = new Screen(level);
        this.running = false;

        // Initializing static members
        tick = 0;
        uiManager = new UIManager();
    }

    public static UIManager getUiManager() {
        return uiManager;
    }

    public Screen getScreen() {
        return screen;
    }

    private void resetGameState() {
        //TODO: This
    }

    public static long getTick() {
        return tick;
    }

    /**
     * Starts the game thread.
     */
    public synchronized void start() {
        if(!running) {
            this.gameThread = new Thread(this, "TankGame");
            this.gameThread.start();
            this.running = true;
        }
    }

    /**
     * Stops the game thead.
     */
    public synchronized void stop() {
        if(running) {
            this.running = false;
            try {
                this.gameThread.join();
                this.launcher.setPanel("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Implementing the run function from Runnable.
     * This is the game loop.
     */
    public void run() {
        try {
            while(running) {
                tick++;
                screen.repaint();
                Thread.sleep(10);
            }
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
