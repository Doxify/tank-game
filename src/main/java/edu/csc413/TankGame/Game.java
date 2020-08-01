package main.java.edu.csc413.TankGame;

import main.java.edu.csc413.TankGame.graphics.Screen;
import main.java.edu.csc413.TankGame.graphics.ui.UIManager;
import main.java.edu.csc413.TankGame.level.Level;
import main.java.edu.csc413.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable {

    private int width = GameConstants.GAME_WIDTH;
    private int height = GameConstants.GAME_HEIGHT;

    private Thread gameThread;
    private final Launcher launcher;
    private final Level level;

    private final BufferedImage image;
    private final Screen screen;
    private static UIManager uiManager;

    private boolean running;
    public static long tick;

    public Game(Launcher launcher) {
        this.launcher = launcher;
        this.level = new Level("map1");
        this.screen = new Screen(level);
        this.running = false;

        // Initializing Game image
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Initializing static members
        tick = 0;
        uiManager = new UIManager();
    }

    public static UIManager getUiManager() {
        return uiManager;
    }

    private void resetGameState() {
        //TODO: This
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
                this.tick++;
                update();
                Thread.sleep(10);
            }
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        repaint();
        level.update();
        repaint();
    }

//    public void render() {
//        Graphics2D graphics = this.image.createGraphics();
//        graphics.setColor(Color.BLACK);
//        graphics.fillRect(0, 0, width, height);
//
//        // First we render the screen
//        screen.render(graphics);
//        graphics.drawImage(image, 0, 0, null);
//
//        System.out.println("called");
//        // Then we render the UI on top of the level
//    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        Graphics buffer = this.image.createGraphics();

        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, width, height);

//        buffer.drawImage(Assets.worldImage, 0, 0, width, height, null);

        screen.render(buffer);
        g2.drawImage(image, 0, 0, width, height, null);
    }
}
