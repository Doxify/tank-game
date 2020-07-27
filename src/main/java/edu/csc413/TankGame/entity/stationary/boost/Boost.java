package main.java.edu.csc413.TankGame.entity.stationary.boost;

import main.java.edu.csc413.TankGame.entity.stationary.Stationary;

import java.awt.image.BufferedImage;

public abstract class Boost extends Stationary {

    public Boost(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}
