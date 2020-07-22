package main.java.edu.csc413.TankGame.Panels;

import main.java.edu.csc413.TankGame.Utilities.GameConstants;
import main.java.edu.csc413.TankGame.GameObject.Movable.Tank;
import main.java.edu.csc413.TankGame.Launcher;
import main.java.edu.csc413.TankGame.Utilities.TankControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Game extends JPanel implements Runnable {

    private BufferedImage world;
    private Tank tank1;
    private Tank tank2;
    private final Launcher launcher;
    private long tick = 0;

    public Game(Launcher launcher) {
        this.launcher = launcher;
    }

    @Override
    public void run() {
        try {
            this.resetGame();
            while(true) {
                this.tick++;
                this.repaint();
                this.tank1.update();
                this.tank2.update();
                this.repaint();

                Thread.sleep(1000 / 144);

            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    /**
     * Resets the game to its initial state.
     */
    private void resetGame() {
        this.tick = 0;
        // Reset the location of the tanks.
        this.tank1.setX(300);
        this.tank1.setY(300);
        this.tank2.setX(500);
        this.tank2.setY(500);
    }

    /**
     * Loads all the resources for the game and sets all GameObjects
     * to their default states.
     */
    public void initializeGame() {
        // Loading the world background and tank images.
        this.world = new BufferedImage(
                GameConstants.GAME_SCREEN_WIDTH,
                GameConstants.GAME_SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_RGB
        );

        BufferedImage tank1Image = null;
        BufferedImage tank2Image = null;
        try {
            tank1Image = read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("tank1.gif")));
            tank2Image = read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("tank2.gif")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        // Creating tank objects
        this.tank1 = new Tank(300, 300, 0, 0, 0, tank1Image);
        this.tank2 = new Tank(500, 500, 0, 0, 0, tank2Image);

        // Creating tank control objects
        TankControl tank1Control = new TankControl(tank1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER);
        TankControl tank2Control = new TankControl(tank2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);

        // Adding key listeners to JFrame
        this.launcher.getJFrame().addKeyListener(tank1Control);
        this.launcher.getJFrame().addKeyListener(tank2Control);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        Graphics2D buffer = this.world.createGraphics();

        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT);

        this.tank1.drawImage(buffer);
        this.tank2.drawImage(buffer);

        graphics2D.drawImage(this.world, 0, 0, null);
    }

}
