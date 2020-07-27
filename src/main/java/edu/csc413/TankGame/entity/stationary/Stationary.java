package main.java.edu.csc413.TankGame.entity.stationary;

import main.java.edu.csc413.TankGame.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Stationary extends Entity {

    public Stationary(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    @Override
    public abstract void render(Graphics graphics);
}
