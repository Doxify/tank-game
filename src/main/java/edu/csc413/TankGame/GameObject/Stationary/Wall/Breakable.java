package main.java.edu.csc413.TankGame.GameObject.Stationary.Wall;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Breakable extends Wall {

    private int state = 3;

    public Breakable(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean isBreakable() {
        return true;
    }

    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public void drawImage(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.drawImage(getImage(), getX(), getY(), null);
        // TODO: different walls for different states
    }

}
