package main.java.edu.csc413.TankGame.entity.wall;

import main.java.edu.csc413.TankGame.entity.Entity;

import java.awt.image.BufferedImage;

public abstract class Wall extends Entity {

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

}
