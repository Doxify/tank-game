package main.java.edu.csc413.TankGame.GameObject.Stationary;

import main.java.edu.csc413.TankGame.GameObject.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Stationary extends GameObject {

    public Stationary(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    public abstract void drawImage(Graphics graphics);
}
