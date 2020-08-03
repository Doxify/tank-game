package csc413.tankgame.TankGame;

import csc413.tankgame.TankGame.graphics.Screen;
import csc413.tankgame.TankGame.level.Level;

public class Game implements Runnable {

    private Thread gameThread;
    private final Launcher launcher;
    private final Level level;

    private final Screen screen;

    private boolean running;
    private static long tick;

    public Game(Launcher launcher) {
        this.launcher = launcher;
        this.level = new Level("map1");
        this.screen = new Screen(level);
        this.running = false;

        // Initializing static members
        tick = 0;
    }

    public Screen getScreen() {
        return screen;
    }

    public Level getLevel() {
        return level;
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
            this.gameThread = new Thread(this, "csc413/tankgame/TankGame");
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
                level.update();
                screen.repaint();
                Thread.sleep(1000 / 144);
            }
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
