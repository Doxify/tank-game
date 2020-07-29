package main.java.edu.csc413.TankGame;

import main.java.edu.csc413.TankGame.entity.movable.Tank;
import main.java.edu.csc413.TankGame.graphics.Assets;
import main.java.edu.csc413.TankGame.graphics.Screen;
import main.java.edu.csc413.TankGame.graphics.ui.UIManager;
import main.java.edu.csc413.TankGame.level.Level;
import main.java.edu.csc413.TankGame.util.TankControl;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable {

    private int width, height;

    private Thread gameThread;
    private Launcher launcher;
    private Level level;

    private Screen screen;
    private static UIManager uiManager;

    private boolean running;
    public static long tick;


    public Game(Launcher launcher) {
        this.launcher = launcher;
        this.level = new Level("map1");
        this.screen = new Screen(level);
        this.running = false;

        // Initializing static members
        tick = 0;
        uiManager = new UIManager();

        initializeTanks();
    }

    /**
     * Initializes Tanks and adds them to the level.
     */
    private void initializeTanks() {
        Tank tank1 = new Tank(400, 400, 0, 0, 0, Assets.tank1Image);
        Tank tank2 = new Tank(500, 500, 0, 0, 0, Assets.tank2Image);
        TankControl tank1Control = new TankControl(tank1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER);
        TankControl tank2Control = new TankControl(tank2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_SPACE);

        // Registering control listeners
        this.launcher.getJFrame().addKeyListener(tank1Control);
        this.launcher.getJFrame().addKeyListener(tank2Control);

        // Adding to the level.
        this.level.addEntity(tank1);
        this.level.addEntity(tank2);
    }

    // TODO: Put this somwehere else.
    public JPanel getScreen() {
        return screen;
    }

    public static UIManager getUiManager() {
        return uiManager;
    }

    private void resetGameState() {

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
                this.tick++;
                this.screen.repaint();
                this.level.update();
                uiManager.render(screen);

                // If any of the tanks have been marked for removal,
                // the game is over and we show the exit screen.
                level.getTanks().forEach(tank -> {
                    if(tank.isRemoved()) {
                        this.launcher.setPanel("end");
                    }
                });

                this.screen.repaint();
                Thread.sleep(1244 / 144);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
