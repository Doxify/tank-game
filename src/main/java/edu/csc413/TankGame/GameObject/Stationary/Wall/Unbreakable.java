package main.java.edu.csc413.TankGame.GameObject.Stationary.Wall;

import java.awt.image.BufferedImage;

public class Unbreakable extends Wall {

    public Unbreakable(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    @Override
    public boolean isBreakable() {
        return false;
    }

    @Override
    public int getState() {
        return -1;
    }

}
