package csc413.tankgame.TankGame.entity.wall;

import csc413.tankgame.TankGame.util.Assets;

import java.awt.image.BufferedImage;

public class Breakable extends Wall {

    private int state = 2;

    public Breakable(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    public void decreaseState() {
        if(state > 0) {
            state -= 1;
        }

        // Change the image of the wall when it's state
        // is at 1. (MAX = 2, MIN = 1)
        if (state == 1) {
            image = Assets.breakableWallImage2;
        }

        // Mark for removal when the state is 0.
        if(state == 0 && !this.isRemoved()) {
            this.setRemoved();
        }
    }

    @Override
    public boolean isBreakable() {
        return true;
    }

}
