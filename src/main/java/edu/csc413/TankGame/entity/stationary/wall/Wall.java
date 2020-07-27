package main.java.edu.csc413.TankGame.entity.stationary.wall;

import main.java.edu.csc413.TankGame.entity.stationary.Stationary;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Wall extends Stationary {

    public Wall(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    /**
     * Check for if a Wall is breakable.
     * @return Whether or not the wall is breakable.
     */
    public boolean isBreakable() {
        return false;
    };

    /**
     * Returns the current state of a Wall.
     * @return State of a Wall represented as an integer.
     *         3 = MAX Health
     *         0 = LOWEST Health, Broken.
     */
    public int getState() {
        return 3;
    };

    /**
     * Draws a Wall to the bufffer.
     * @param graphics buffer
     */
    @Override
    public void render(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.drawImage(getImage(), getX(), getY(), null);
    }
}
