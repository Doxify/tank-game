package csc413.tankgame.TankGame.entity.boost;

import java.awt.image.BufferedImage;

public class BulletSpeed extends Boost {

    /**
     * Increase the speed of a tank's bullet.
     * When this boost is applied, the speed at which
     * a tank's bullet moves is increased by 1.5x.
     */

    private int previousFiringSpeed;

    public BulletSpeed(int x, int y, int durationMilliseconds, BufferedImage image) {
        super(x, y, durationMilliseconds, image);
    }

    @Override
    public void applyBoost() {
        if(tank != null) {
            if(!tank.isRemoved()) {
                previousFiringSpeed = tank.getFiringSpeed();
                tank.setFiringSpeed(Math.round(previousFiringSpeed * 1.5f));
            }
        }
    }

    @Override
    public void removeBoost() {
        if(tank != null) {
            if(!tank.isRemoved()) {
                tank.setFiringSpeed(previousFiringSpeed);
            }
        }
    }
}
