package csc413.tankgame.TankGame.entity.wall;

import csc413.tankgame.TankGame.entity.Entity;

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
