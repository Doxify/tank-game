package main.java.edu.csc413.TankGame.entity.boost;

import java.awt.image.BufferedImage;

public class Speed extends Boost {

    /**
     * Increases a tank's speed.
     * When this boost is applied, it increases the
     * tank's speed by 1.5X.
     */

    private int previousTankSpeed;

    public Speed(int x, int y, int durationMilliseconds, BufferedImage image) {
        super(x, y, durationMilliseconds, image);
    }

    @Override
    public void applyBoost() {
        if(tank != null) {
            if(!tank.isRemoved()) {
                previousTankSpeed = tank.getSpeed();
                tank.setSpeed(Math.round(previousTankSpeed * 1.5f));
            }
        }
    }

    @Override
    public void removeBoost() {
        if(tank != null) {
            if(!tank.isRemoved()) {
                tank.setSpeed(previousTankSpeed);
            }
        }
    }
}
